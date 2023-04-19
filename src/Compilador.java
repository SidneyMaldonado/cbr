import java.io.IOException;
import java.util.List;

public class Compilador {

	public List<String> alfabeto;
	public List<String> exemplo;
	
	public Compilador() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void Iniciar() throws IOException {
		String linha;
		System.out.println("Iniciando o compilador...");
		
		LerArquivo leitor = new LerArquivo();
		alfabeto = leitor.ler("c:\\cbr\\alfabeto.txt");
		exemplo = leitor.ler("c:\\cbr\\exemplo.txt");
		for( int i =0; i < alfabeto.size();i++) {
			System.out.println(alfabeto.get(i));
		}
		System.out.println("--------------");
		AnalisadorLexico lexico = new AnalisadorLexico();
		for( int i =0; i < exemplo.size();i++) {
			linha = exemplo.get(i);
			lexico.analisar(linha, alfabeto);
		}

	}



}
