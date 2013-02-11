package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ThreadServer implements Runnable {
	
	private static final String newLine = "\r\n";
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private String fileName;
	private boolean fileExist;
	
	private String response200 = "HTTP/1.0 200 OK";
	private String response404 = "HTTP/1.0 404 Not Found";
	private String defaultFileName = "index.html";
	private String fileNmeError404 = "fileError404.html";
	
	
	public ThreadServer(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("" +Thread.currentThread().getName() + "paso {0}");
		
		fileName = "";
		
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			getFileName();
			getFileExists();
			writeHeader();
			writeFile();
			
			
//			filename = getFileName(socket.getInputStream());
//			writeHeader(socket.getOutputStream(), filename);
//			writeFile(socket.getOutputStream(), filename);
			socket.close();	
		} catch (IOException e) {
		} catch (InterruptedException e) {
		} catch (NoSuchElementException ex) {
		}
		
	}
	
	
	private String getFileName(){
	Scanner scanner = new Scanner(inputStream);
		
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

	private void getFileExists() {
		File file = new File(fileName);
		fileExist = file.exists();
	}
	
	private void writeHeader() throws IOException{
		File file = new File(fileName);
		
		String response = file.exists() ? response200 : response404;		
		
		String header = response + newLine + newLine;	
		byte[] headerBuffer = header.getBytes();
		
		outputStream.write(headerBuffer);
		
	}
	
	private void writeFile() throws IOException, InterruptedException{	
		File file = new File(fileName);
		String responseFileName = file.exists() ? fileName : fileNmeError404;
		
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
