import java.util.List;

public class AnalisadorSintatico {

	public AnalisadorSintatico() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean analisar(String linha, List<String> expressoes) {
		for(int i =0;i<expressoes.size();i++) {
			if( linha.trim().matches(expressoes.get(i))) {
				return true;
			}
			//System.out.println(expressoes.get(i));
		}
		
		System.out.println("Erro na linha:"+linha);
		
		return false;
	}

}
