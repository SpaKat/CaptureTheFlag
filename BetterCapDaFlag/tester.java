import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class tester {


	public static void main(String[] args)  {
		try {
		Socket s = new Socket("127.0.0.1", 8008);
		DatagramSocket ds = new DatagramSocket();
		ds.connect(s.getInetAddress(), s.getPort());
		Thread.sleep(100);
		String sss = "HEART";
		DatagramPacket dp = new DatagramPacket(sss.getBytes(), sss.getBytes().length);
		ds.send(dp);
		//test1();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void test1() {
		new server();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new client();
	}

}


class server extends Thread{
	public server() {
		start();
	}

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(8008);
			try {
				Socket s = ss.accept();
				s.close();
				DatagramSocket ds = new DatagramSocket(8008);
				Thread.sleep(1000);
				byte[] b = new byte[5000];
				DatagramPacket dp = new DatagramPacket(b , b.length);

				ds.receive(dp);
				
				String v = new String(b);
				
				System.out.println(v);
			} catch (Exception e) {
				//	System.err.println("GameRunnable woke up");
				e.printStackTrace();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

class client extends Thread{
	public client() {
		start();
	}
	@Override
	public void run() {
		try {
			Socket s = new Socket("127.0.0.1", 8008);
			DatagramSocket ds = new DatagramSocket();
			ds.connect(s.getInetAddress(), 8008);
			String sss = "HEART";
			DatagramPacket dp = new DatagramPacket(sss.getBytes(), sss.getBytes().length);
			ds.send(dp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
