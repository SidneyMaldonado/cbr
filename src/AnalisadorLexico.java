import java.util.List;

public class AnalisadorLexico {

	public AnalisadorLexico() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean analisar(String linha, List<String> alfabeto) {
		String caracteres = alfabeto.get(0); 

		for(int i =0; i < linha.length();i++) {
			char letra = linha.charAt(i);
			Boolean erro;

			erro = !caracteres.contains(String.valueOf(letra));

			if (erro) {
				System.out.println("caracter invalido:"+letra+":");
				return false;
			}
			
		}
		return true;
	}

}
