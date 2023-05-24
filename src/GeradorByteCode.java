import java.util.Scanner;

public class GeradorByteCode {

	public GeradorByteCode() {
		// TODO Auto-generated constructor stub
	}
	
	public String gerar(String linha) {
		String[] token = linha.split(" ");
		
		if (token[0].equals("inteiro")){
			return "int " + token[1];
		}
		if (token[0].equals("int")) {
			return "int " + token[1];
		}
		
		if (token[0].equals("ler")) {
			return token[1].replace(";","") + "=teclado.nextInt();";
		}
		if (token[1].equals("=")) {
			return linha;
		}
		
	
		return "";
		
	}

}
