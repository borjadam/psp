package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
	
	private static String newLine = "\r\n";
	
	public static void Process(Socket socket) throws IOException, InterruptedException{
		

		
//		String threadName = Thread.currentThread().getName();
//		System.out.println(threadName);
		
		String filename = getFileName(socket.getInputStream());
		writeHeader(socket.getOutputStream(), filename);
		writeFile(socket.getOutputStream(), filename);
		socket.close();	
		
	}
	
private static String getFileName(InputStream inputStream){
		
		Scanner scanner = new Scanner(inputStream);
		String fileName = "";
		
		while(true) {
			String line = scanner.nextLine();
			System.out.println(line);
			if (line.contains("GET ")){
				String aux = line.substring(5);	
				String[] aux2 = aux.split(" ");
				fileName = aux2[0];
				System.out.println("filename = " + fileName);
			}
			else if (line.equals("")){
				break;
			}
		}
		return fileName;		
	}
	
	private static void writeHeader(OutputStream outputStream, String filename) throws IOException{
		final String response200 = "HTTP/1.0 200 OK";
		final String response404 = "HTTP/1.0 404 Not Found";
		File file = new File(filename);
		
		String response = file.exists() ? response200 : response404;		
		
		String header = response + newLine + newLine;	
		byte[] headerBuffer = header.getBytes();
		
		outputStream.write(headerBuffer);
		
	}
	
	private static void writeFile(OutputStream outputStream, String filename) throws IOException, InterruptedException{
		final String fileNmeError404 = "fileError404.html";
		File file = new File(filename);
		String responseFileName = file.exists() ? filename : fileNmeError404;
		
		final int bufferSize = 2048;
		byte[] buffer = new byte[bufferSize];
		
		
		FileInputStream fileInputStream = new FileInputStream(responseFileName);
		
		int count;
		
		while((count = fileInputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, count);
		}		
		
		fileInputStream.close();		
	}

}
