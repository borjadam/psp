package serpis.psp;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final int port = 8080;
		ServerSocket serverSocket = new ServerSocket(port);
		
		while (true){
			Socket socket = serverSocket.accept();
			
			Runnable runnable = new ThreadServer(socket);
			Thread thread = new Thread(runnable);
			thread.start();

//			SimpleServer.Process(socket);
			
		}
		
	}
	

}
