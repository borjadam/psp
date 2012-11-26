package serpis.psp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class TCPServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//holaMundo();
		//pingPong();
		connectAndWrite();

	}
	
	private static void pingPong() throws IOException {
		
		//recibe el mensaje
		System.out.println("Recibiendo mensaje del cliente");
		int port = 10001;
		
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());	
		String line = scanner.nextLine();
		System.out.println("Mensaje del cliente recibido");
		System.out.println("line = " + line);
		
		//envia el mensaje
		System.out.println("Enviando mensaje al cliente");
		OutputStream outputStream = socket.getOutputStream();
		
		String lineUp = line.toUpperCase();
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.println("line = " + lineUp);
		System.out.println("Mensaje enviado");
		
		socket.close();
		serverSocket.close();
		
	}
	
	private static void connectAndRead(String[] args) {
		
		
		System.out.println("Argumentos: ");
		for (int i=0; i< args.length; i++) {
			System.out.println(args[i]);
		}		
	}
	
	private static void connectAndWrite() throws IOException {
		int port = 10001;
		ServerSocket serverSocket = new ServerSocket(port);		
		Socket socket = serverSocket.accept();
		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		printWriter.println("La hora es (vez 2) " + new Date());
		printWriter.println("La hora es (vez 2) " + new Date());
		printWriter.println("La hora es (vez 3) " + new Date());
		
		socket.close();
		serverSocket.close();
	}
	
	private static void holaMundo() throws IOException {
		
		int port = 10001;
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());	
		String line = scanner.nextLine();
		System.out.println("line = " + line);
		
		socket.close();
		serverSocket.close();
	}

}
