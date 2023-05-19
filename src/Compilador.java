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

		boolean valido = true;

		for( int i =0; i < exemplo.size();i++) {
			linha = exemplo.get(i);
			if(!lexico.analisar(linha, alfabeto)){
				valido=false;
				break;
			}
		}

		if(valido){
			System.out.println("Análise léxica bem sucedida!");
		}else{
			return;
		}

		AnalisadorSintatico sintatico = new AnalisadorSintatico();
		for( int i =0; i < exemplo.size();i++) 
		{
			linha = exemplo.get(i);
			if(!sintatico.analisar(linha, expressoes)){
				valido=false;
				break;
			}
		}

		if(valido){
			System.out.println("Análise sintática bem sucedida!");
		}else{
			return;
		}
		
		AnalisadorSemantico semantico = new AnalisadorSemantico();
		for( int i =0; i < exemplo.size();i++) 
		{
			linha = exemplo.get(i);
			if(!semantico.Analisar(linha, dicionario)){
				valido=false;
				break;
			}
		}

		if(valido){
			System.out.println("Análise semântica bem sucedida!");
		}else{
			return;
		}
		System.out.println("Fim da analise semantica");

		String byteCode = "";
		GeradorByteCode gerador = new GeradorByteCode();
		byteCode += "Scanner teclado = new Scanner(System.in);\n";

		for( int i =0; i < exemplo.size();i++) 
		{
			linha = exemplo.get(i);
			byteCode += gerador.gerar(linha)+"\n";
			System.out.print(byteCode);
		}
	}
}
