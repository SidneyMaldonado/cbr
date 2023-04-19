import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivo {

	public LerArquivo() {
		
	}
	
	public List<String> ler(String nomeArquivo) throws IOException{
		List<String> dados = new ArrayList();
		String linha;
		try {
			FileReader leitor= new FileReader(nomeArquivo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			while ((linha = buffer.readLine()) != null) {
				dados.add(linha);
			}
			
			buffer.close();
			leitor.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao abrir o arquivo"+nomeArquivo);
		}

		return dados;
	}
	

}
