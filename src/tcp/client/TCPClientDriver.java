package tcp.client;
import java.io.IOException;

public class TCPClientDriver {
	public static void main(String[] args) {
		TCPClient client = new TCPClient();
		int max = 100;
		try {
			for (int i = 0; i <= max; i++) {
				client.sendFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
