import java.util.List;

public class AnalisadorLexico {

	public AnalisadorLexico() {
		// TODO Auto-generated constructor stub
	}
	
	public void analisar(String linha, List<String> alfabeto) {
		String caracteres = alfabeto.get(0); 

		for(int i =0; i < linha.length();i++) {
			char letra = linha.charAt(i);
			Boolean erro = true;
			for(int j =0; j< caracteres.length();j++) {
				//System.out.println("Comparando:"+letra+" com "+ caracteres.charAt(i));
				if (letra == caracteres.charAt(j)) {
					erro = false;
				}
			}
			if (erro) {
				System.out.println("caracter invalido:"+letra+":");
				return;
			}
			
		}
		System.out.println("Analise lexica bem sucedida!");
	}

}
