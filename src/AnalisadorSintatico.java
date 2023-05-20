import java.util.List;

public class AnalisadorSintatico {

	public AnalisadorSintatico() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean analisar(String linha, List<String> expressoes) {
		
		for (String expressao : expressoes) {
			if( linha.trim().matches(expressao)) {
				return true;
			}
			//System.out.println(expressao);
		}
		
		System.out.println("Erro na linha:"+linha);
		
		return false;
	}

}
