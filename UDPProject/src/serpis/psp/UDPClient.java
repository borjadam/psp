package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UDPClient {


	public static void main(String[] args) throws IOException, InterruptedException {

			String mensaje = "Hola. Este es un mensaje del cliente UDP enviado al servidor";

			int puerto = 10001;
			int puerto2 = 10002;
			
			InetAddress dir = InetAddress.getByName("localhost");
			
			byte[]buf = mensaje.getBytes();
			
			
			DatagramSocket socket = new DatagramSocket();
			DatagramSocket socket2 = new DatagramSocket(puerto2);
			
			DatagramPacket paquete = new DatagramPacket(buf, buf.length, dir, puerto);
			
			int i=1;
			
			while(i<=10){		
				System.out.println("Enviando mensaje...");
		
				//envia el mensaje
				socket.send(paquete);			
				
				byte[] buf2 = new byte[2048];
				DatagramPacket paquete2 = new DatagramPacket(buf2, buf2.length);
				
				//recibe el mensaje
				socket2.receive(paquete2);
				
				byte[] data = paquete2.getData();
				String texto = new String(data);
				
				System.out.println("Mensaje del servidor: " + texto);			
				
				Thread.sleep(5000);
			}
			i++;
			
	}

}
