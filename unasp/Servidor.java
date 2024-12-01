package unasp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class Servidor {
	
	public static final int PORT = 4000;
	private ServerSocket socket;
	private BufferedWriter out;
	private Socket clienteSocket;
	int[] nums = null;
	
	
	
	public void start() throws IOException{
		socket = new ServerSocket(PORT);
		System.out.println("Servidor iniciado na porta: " + PORT);
		serverLoop();
		;
		
	}
	
	private void serverLoop() throws IOException {
		while(nums == null) {
			Socket cliente = socket.accept();
			System.out.println("O cliente: " + cliente.getRemoteSocketAddress() + " se conectou.");
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			nums = bubbleSort(stringParaArray(in.readLine()));
			System.out.println("O cliente: " + cliente.getRemoteSocketAddress() + " esta fazendo operacoes com o servidor..");
			this.out = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
			out.write(arrayParaString(nums));
			out.newLine();
			out.flush();
		}
		
	}
	private int[] stringParaArray(String s) {
		String[] strArray = s.split(" ");
		int[] nums = new int[strArray.length];
		for(int i = 0;i < strArray.length ; i++) {
			nums[i] = Integer.parseInt(strArray[i]);
		}	
		return nums;
	}
	
	private String arrayParaString(int[] a) {
		String res = "";
		for(int n : a) {
			res = res + n + " " ;
		}
		return res;
	}
	
	private int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    }
            }
            }
        return array;
        }
                
	public static void main(String[] args) {
		try {
			Servidor server = new Servidor();
			server.start();
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Servidor Finalizado");
	}

}
