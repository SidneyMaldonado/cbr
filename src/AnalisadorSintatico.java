import java.util.List;

public class AnalisadorSintatico {

	public AnalisadorSintatico() {
		// TODO Auto-generated constructor stub
	}
	
	public void analisar(String linha, List<String> expressoes) {
		Boolean resultado=false;
		for(int i =0;i<expressoes.size();i++) {
			if( linha.trim().matches(expressoes.get(i))) {
				System.out.println(linha+": ok");
				return;
			}
			//System.out.println(expressoes.get(i));
		}
		if (resultado) {
			System.out.println(linha+": ok");
		} else {
		    System.out.println("Erro na linha:"+linha);
		}
		
		return;
	}

}
