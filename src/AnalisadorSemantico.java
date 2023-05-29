import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AnalisadorSemantico {

	public List<String> tabelaSimbolos;
	public AnalisadorSemantico() {
		tabelaSimbolos = new ArrayList();
	}
	
	public boolean Analisar(String linha, List<String> dicionario) {
		linha = linha.replace(";","");
		linha = Pattern.compile("\"(?:\\\\\\\\|\\\\\"|\\\\\'|[^\'\"\\\\])*\"").matcher(linha).replaceAll("\"\"");
		String[] token = linha.split(" ");

		if(token.length==0)return true;

		if (token[0].equals("inteiro") ||
			token[0].equals("int")	) {
			// adicionar na tabela de simbolos
			if (estaNaTabelaSimbolos(token[1].toString())) {
				System.out.println("Variavel j� declarada:"+token[1]);
				return false;
			}
			tabelaSimbolos.add(token[1]);
			return true;
		}
		
		// percorre todas as palavras da linha
		for(int i=0;i<token.length;i++) {
			// verificar se � uma vari�vel
			if (ehVariavel(token[i].toString(), dicionario)) {
				if (estaNaTabelaSimbolos(token[i].toString())) {
					return true;
				} else {
					System.out.println("Variavel nao declarada:"+token[i]);
					return false;
				}
			}
		}

		return true;
	}
	
	public Boolean ehVariavel(String palavra, List<String> dicionario) {
		
		if(palavra.matches("[a-z][a-z0-9]{0,9}")) {
			for(int i=0; i<dicionario.size();i++) {
				if (dicionario.get(i).toString().equals(palavra)) {
					return false;
				}
			}
			return true;			
		}
		return false;
	}
	public Boolean estaNaTabelaSimbolos(String palavra) {
		
		return tabelaSimbolos.contains(palavra);
		
	}
}
