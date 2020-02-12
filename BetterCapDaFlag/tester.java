import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class tester {


	public static void main(String[] args)  {
		try {
			Socket s = new Socket("127.0.0.1", 8008);
			System.out.println(s.getPort());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println(ois.available());
			byte[] id = new byte[200];
			ois.read(id);
			
			System.out.println(new String(id));
			DatagramSocket ds = new DatagramSocket();
			ds.connect(s.getInetAddress(), 8009);
//			Thread.sleep(1000);
			FUN sss = new FUN();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(sss);
			oos.flush();
			DatagramPacket dp = new DatagramPacket(os.toByteArray(), os.toByteArray().length);
			ds.send(dp);
//			Thread.sleep(1000);
//			while(true) {
//				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//				oos.flush();
//			}
			//test1();
//			ArrayList<String> as = new ArrayList<String>();
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			as.add("asdasdadsad");
//			
//			String[] a = new String[as.size()]; 
//					as.toArray(a);
//			for (int i = 0; i < a.length; i++) {
//				System.out.println(a[i]);
//			}
			Thread.sleep(10000);
			ds.close();
			s.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("DIED");
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
		new client();
	}

}
class FUN implements Serializable{
	
}

class server extends Thread{
	public server() {
		setName("server");
		start();
	}

	@Override
	public void run() {
		try {
			//ServerSocket ss = new ServerSocket(8008);
			try {
			//	Socket s = ss.accept();
			//	s.close();
				DatagramSocket ds = new DatagramSocket(8008);
				//Thread.sleep(1000);
				byte[] b = new byte[5000];
				DatagramPacket dp = new DatagramPacket(b , b.length);
				
				int threads = 2;
				for (int i = 0; i < threads*10000; i++) {
					System.out.println(i);
					ds.receive(dp);
					String v = new String(b);
					System.out.println("   message: "+ v);
				}
				
				
			} catch (Exception e) {
				//	System.err.println("GameRunnable woke up");
				e.printStackTrace();
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

class client extends Thread{
	public client() {
		setName("client");

		start();
	}
	@Override
	public void run() {
		try {
//			Socket s = new Socket("127.0.0.1", 8008);
			DatagramSocket ds = new DatagramSocket();
		//	ds.connect(s.getInetAddress(), 8008);
			ds.connect(InetAddress.getByName("127.0.0.1"),8008);
			double r = Math.round(Math.random()*890);
			for (int i = 0; i < 10000; i++) {
				String sss = i + "  HEART " + r;
				DatagramPacket dp = new DatagramPacket(sss.getBytes(), sss.getBytes().length);
				ds.send(dp);
				Thread.sleep(10);

			}
			System.out.println("Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
