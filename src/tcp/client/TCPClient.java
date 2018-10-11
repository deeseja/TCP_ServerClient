package tcp.client;

import java.io.*;
import java.net.*;

public class TCPClient {
	OutputStream os;
	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	String host = "andysmac";
	int port = 8080;
	
	public void sendFile() throws IOException {
		File myFile = new File("src/file.txt");
		byte[] mybytearray = new byte[(int) myFile.length()]; 
		FileInputStream fis;
		try {
			fis = new FileInputStream(myFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
		      
			dis = new DataInputStream(bis);     
			dis.readFully(mybytearray, 0, mybytearray.length); 
		      
			sock = new Socket(host, port);
		
			os = sock.getOutputStream();
		      
			//Sending file name and file size to the server
			dos = new DataOutputStream(os); 
			dos.writeUTF(myFile.getName());     
			dos.writeLong(mybytearray.length);     
			dos.write(mybytearray, 0, mybytearray.length);
			dos.flush();  
		      
			os.write(mybytearray, 0, mybytearray.length);  
			os.flush();
		  } catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (os != null)
				os.close();
			if (dos != null)
				dos.close();  
			if (sock != null)
				sock.close();  
			if (dis != null)
				dis.close();
		}
	}
}
