import java.io.IOException;
import java.util.List;

public class Compilador {

	public List<String> alfabeto;
	public List<String> exemplo;
	public List<String> expressoes;
	public List<String> dicionario;
	
	public Compilador() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void Iniciar() throws IOException {
		String linha;
		System.out.println("Iniciando o compilador...");
		
		LerArquivo leitor = new LerArquivo();
		alfabeto = leitor.ler("alfabeto.txt");
		exemplo = leitor.ler("exemplo.txt");
		expressoes = leitor.ler("expressoes.txt");
		dicionario = leitor.ler("dicionario.txt");
		for( int i =0; i < alfabeto.size();i++) {
			System.out.println(alfabeto.get(i));
		}
		System.out.println("--------------");
		AnalisadorLexico lexico = new AnalisadorLexico();
		for( int i =0; i < exemplo.size();i++) {
			linha = exemplo.get(i);
			lexico.analisar(linha, alfabeto);
		}
		AnalisadorSintatico sintatico = new AnalisadorSintatico();
		for( int i =0; i < exemplo.size();i++) 
		{
			linha = exemplo.get(i);
			sintatico.analisar(linha, expressoes);
		}
		
		System.out.println("Analisando semanticamente");
		AnalisadorSemantico semantico = new AnalisadorSemantico();
		for( int i =0; i < exemplo.size();i++) 
		{
			linha = exemplo.get(i);
			semantico.Analisar(linha, dicionario);
		}
		System.out.println("Fim da analise semantica");

	}



}
