package unasp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	
	public static final String ENDERECO = "127.0.0.1";
	private Socket socket;
	private BufferedWriter out;
	String nums = null;
	String recebida = null;
	
	public void start() throws IOException {
		socket = new Socket(ENDERECO, Servidor.PORT);
		System.out.println("Cliente conectado na porta: " + Servidor.PORT);
		clientLoop();
		this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		out.write(nums);
		out.newLine();
		out.flush();
		segundoLoop();
	}
	
	private void clientLoop() throws IOException {
		
		Scanner leitor = new Scanner(System.in);
		while(nums == null) {
			System.out.println("Digite uma lista de numeros separados por um espaço para ordenar.. [input ex: 5 8 2 4 6]");
			System.out.print("Input: ");
			nums = leitor.nextLine();
			
		}
	}
	private void segundoLoop() throws IOException {
		while(recebida == null) {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			recebida = in.readLine();
			System.out.println("Ouput: " + recebida);
			System.out.println("Fim da execucao");
		}
	}
	
	public static void main(String[] args) {
		Cliente client = new Cliente();
		try {
			client.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
