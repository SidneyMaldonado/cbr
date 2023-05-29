import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeradorByteCode {

	private HashMap<String, String> tiposVariaveis = new HashMap<>();

	public GeradorByteCode() {
		this.tiposVariaveis = new HashMap<>();
	}

	public String gerar(String linha) {

		final String VAR = "[a-z][a-z0-9]*"; // Variaveis
		final String NUM = "[0-9]+(?:\\.[0-9]+)?"; // Números
		final String STRING = "\"(?:\\\\\\\\|\\\\\"|\\\\\'|[^\'\"\\\\])*\""; // Textos
		final String VAR_OR_NUM = String.format("(?:%s|%s)", VAR, NUM); // Variaveis ou números
		final String VAR_OR_STRING = String.format("(?:%s|%s)", VAR, STRING); // Variaveis ou textos

		final String EXP = String.format("(?:%s(?:\\s*[*/+-]\\s*%s)*)", VAR_OR_NUM, VAR_OR_NUM); // Expressões simples
																									// com variaveis ou
																									// números, ex: 2 *
																									// x

		final String SIMPLE_CONDITION = String.format("(?:%s\\s*(<|>|<=|>=|=|!)\\s*%s)", EXP, EXP);

		final String COMP_CONDITION1 = String.format("(?:(?:nao )?%s)", SIMPLE_CONDITION);

		final String COMP_CONDITION2 = String.format("(?:%s(?:\\s*(e|ou)\\s*%s)*)", COMP_CONDITION1, COMP_CONDITION1);

		Matcher m;

		m = Pattern.compile("^\\s*(inteiro|int)\\s+(" + VAR + ")\\s*;$").matcher(linha);
		if (m.matches()) {
			String tipo = "int";
			String variavel = m.group(2);

			tiposVariaveis.put(variavel, tipo);

			return String.format("int %s;", variavel);
		}

		m = Pattern.compile("^\\s*(real)\\s+(" + VAR + ")\\s*;$").matcher(linha);
		if (m.matches()) {
			String tipo = "double";
			String variavel = m.group(2);

			tiposVariaveis.put(variavel, tipo);

			return String.format("double %s;", variavel);
		}

		m = Pattern.compile("^\\s*(texto)\\s+(" + VAR + ")\\s*;$").matcher(linha);
		if (m.matches()) {
			String tipo = "String";
			String variavel = m.group(2);

			tiposVariaveis.put(variavel, tipo);

			return String.format("String %s;", variavel);
		}
		m = Pattern.compile("^\\s*(logico)\\s+(" + VAR + ")\\s*;$").matcher(linha);
		if (m.matches()) {
			String tipo = "boolean";
			String variavel = m.group(2);

			tiposVariaveis.put(variavel, tipo);

			return String.format("boolean %s;", variavel);
		}

		m = Pattern.compile("^\\s*ler\\s+(" + VAR + ")\\s*;$").matcher(linha);
		if (m.matches()) {

			String variavel = m.group(1);
			String tipo = this.tiposVariaveis.get(variavel);

			switch (tipo) {
				case "int":
					return String.format("%s = teclado.nextInt();", variavel);

				case "double":
					return String.format("%s = teclado.nextDouble();", variavel);

				case "boolean":
					return String.format("%s = teclado.nextBoolean();", variavel);

				case "String":
					return String.format("%s = teclado.next();", variavel);
			}

		}

		m = Pattern.compile("^\\s*(" + VAR + ")\\s*=\\s*(" + EXP + ")\\s*;$").matcher(linha);
		if (m.matches()) {

			String variavel = m.group(1);
			String exprecao = m.group(2);

			return String.format("%s = %s;", variavel, exprecao);

		}

		m = Pattern.compile("^\\s*imprimir\\s+(" + VAR_OR_STRING + ")\\s*;$").matcher(linha);
		if (m.matches()) {

			String texto = m.group(1);

			return String.format("System.out.println(%s);", texto);

		}

		m = Pattern.compile("^\\s*se\\s+(" + COMP_CONDITION2 + ")\\s+entao\\s*$").matcher(linha);
		if (m.matches()) {

			String cond = m.group(1)
					.replaceAll("=", "==")
					.replaceAll("!", "!=")
					.replaceAll("\\b(e)\\b", "&&")
					.replaceAll("\\b(ou)\\b", "||")
					.replaceAll("\\b(nao)\\b", "!");

			return String.format("if(%s){", cond);
		}

		m = Pattern.compile(
				"^\\s*para\\s+(" + VAR + ")\\s+de\\s+(" + VAR_OR_NUM + ")\\s+ate\\s+(" + VAR_OR_NUM + ")\\s+faca\\s*$")
				.matcher(linha);
		if (m.matches()) {

			String var = m.group(1);
			String de = m.group(2);
			String ate = m.group(3);

			return String.format("for(%s = %s; %s <= %s; %s++){", var, de, var, ate, var);
		}

		m = Pattern.compile("^\\s*(fimse|fimpara)\\s*$").matcher(linha);
		if (m.matches()) {

			return "}";

		}

		m = Pattern.compile("^\\s*(senao)\\s*$").matcher(linha);
		if (m.matches()) {

			return "} else {";

		}

		m = Pattern.compile("^\\s*$").matcher(linha);
		if (m.matches()) {
			return "";
		}

		return String.format("// %s", linha);

	}

}
