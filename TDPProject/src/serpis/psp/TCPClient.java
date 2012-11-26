package serpis.psp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;


public class TCPClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//holaMundo();
		//pingPong();
		//connectAndRead(args);
		connectUpvWeb();

		
	}
	
	private static void pingPong() throws UnknownHostException, IOException  {
		
		//envia el mensaje
		System.out.println("Enviando mensaje al servidor");
		int port = 10001;
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		Socket socket = new Socket(inetAddress, port);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.println("Hola desde TCPClient a las " + new Date());
		System.out.println("Mensaje enviado");
		
		//recibe el mensaje
		System.out.println("Recibiendo mensaje del servidor");
		ServerSocket serverSocket = new ServerSocket(port);
		serverSocket.accept();
		
		InputStream inputStream = socket.getInputStream();
		Scanner scanner = new Scanner(inputStream);
		String line = scanner.nextLine();
		System.out.println("Mensaje del servidor recibido");
		
		System.out.println("line = " + line);
		
		printWriter.close();
		socket.close();
		
	}
	
	private static void connectAndRead(String[] args) throws IOException {
		
		
		System.out.println("Argumentos: ");
		for (int i=0; i< args.length; i++) {
			System.out.println(args[i]);
		}		
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		InetAddress inetAddress = InetAddress.getByName(host);
		Socket socket = new Socket(inetAddress, port);
		
		Scanner scanner = new Scanner(socket.getInputStream());
		
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		socket.close();
	}
	
	private static void connectUpvWeb() throws IOException {
		String host = "www.upv.es";
		String line = "GET /index.html HTTP/1.1";
		String line2 = "";
		
		int port = 80;
		InetAddress inetAddress = InetAddress.getByName(host);
		Socket socket = new Socket(inetAddress, port);
				
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		printWriter.println(line);
		printWriter.println(line2);
		
		Scanner scanner = new Scanner(socket.getInputStream());
		
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		socket.close();
		
		
		
	}
	
	private static void holaMundo() throws UnknownHostException, IOException {
		String host = null;
		int port = 10001;
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		Socket socket = new Socket(inetAddress, port);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.println("Hola desde TCPClient a las " + new Date());
		
		printWriter.close();
		socket.close();
		
	}

}
