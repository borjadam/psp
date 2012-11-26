package serpis.psp;

public class StringUtil {
	
	public static void FillByteArray(byte[] buf, String data){
		
		byte[] bufData = data.getBytes();
				
		for (int i =0; i<bufData.length; i++){
			buf[i] = bufData[i];
		}
	}

}
