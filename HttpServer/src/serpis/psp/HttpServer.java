package serpis.psp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		int port = 8080;
		String newLine= "\r\n";
		
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());
		
		while(true) {
			String line = scanner.nextLine();
			if (line.equals("")){
				break;
			}
			
			System.out.println(line);
		}
		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		printWriter.println("HTTP/1.0 404 Not found" + newLine);
		printWriter.println(newLine);
		
		printWriter.flush();
		
		socket.close();
		serverSocket.close();
	}

}
