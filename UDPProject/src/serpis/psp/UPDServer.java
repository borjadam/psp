package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UPDServer {

	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		byte[] buf = new byte[2400];
		
		int puerto = 10001;
		int puerto2 = 10002;
		int puerto3 = 10003;	
		
		DatagramSocket socket = new DatagramSocket(puerto);
		DatagramSocket socket3 = new DatagramSocket(puerto3);
		
		int i=1;
		while (i<=10){
			System.out.println("Esperando mensaje...");
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			
			//recibe el paquete
			socket.receive(paquete);
			
			System.out.println("Mensaje recibido");
			
			InetAddress direCli = paquete.getAddress();
			byte[] datosCli = paquete.getData();
			
			String data = new String(datosCli);
			String dataMay = data.toUpperCase();
			
			byte[] buf2 = dataMay.getBytes();
			
			DatagramPacket paquete2 = new DatagramPacket(buf2, buf2.length, direCli, puerto2);
			
			System.out.println("Reenviando mensaje...");
			//envia el paquete
			socket3.send(paquete2);
			
			Thread.sleep(5000);
		}
		i++;
		
//		System.out.println("Esperando...");
//		
//		socket.receive(paquete);
//	
//		int puertoCli = paquete.getPort();
//		int longCli = paquete.getLength();
//		InetAddress direCli = paquete.getAddress();
//		byte[] datosCli = paquete.getData();
//		
//		String mensajeCli = new String(datosCli);
		
		//byte[] datosEnv = mensajeCli.toUpperCase().getBytes();
		
//		System.out.println(mensajeCli + " Longitud del mensaje: " + mensajeCli.length());
		

		// System.out.println("El puerto del ciente es: " + puertoCli);
		// System.out.println("La longitud del paquete del ciente es: " + longCli);
		// System.out.println("La direccion del cliente es: " + direCli);
		// System.out.println("El mensaje del ciente es: " + mensajeCli.toUpperCase());
	
		
		//DatagramPacket paqueteEnv = new DatagramPacket(datosEnv, datosEnv.length, direCli, puertoEnv);
		//socket2.send(paqueteEnv);
		

	}

}
