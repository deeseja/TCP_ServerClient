package tcp.server;
import java.io.*;
import java.net.*;

public class TCPServer {
	public void readFile() {
		int bytesRead;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(8080);
			System.out.println("listening");
			while (true) {
				Socket clientSocket = null;
				clientSocket = serverSocket.accept();
	
				InputStream in = clientSocket.getInputStream();
				DataInputStream clientData = new DataInputStream(in);
	
				String fileName = clientData.readUTF();
				OutputStream output = new FileOutputStream(fileName);
				System.out.println("Recieved file " + fileName);
				long size = clientData.readLong();
				byte[] buffer = new byte[1024];
				while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
					output.write(buffer, 0, bytesRead);
					size -= bytesRead;
				}
	
				// Closing the FileOutputStream handle
				in.close();
				clientData.close();
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
