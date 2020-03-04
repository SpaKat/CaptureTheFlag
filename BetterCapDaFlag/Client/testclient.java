package Client;

import java.util.ArrayList;

import CaptureTheFlagGame.Statistics;
import Server.Message.Action;
import Server.Message.setupPlayer;

public class testclient {

	
public static void main(String[] args) {
		
		ArrayList<fun> threads = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
		
			fun f = new fun(i);
		
			threads.add(f);
		}
		do {
		
			System.out.println(threads.size());
			Thread[] thre = new Thread[threads.size()];
			threads.toArray(thre );
			for (int i = 0; i < thre.length; i++) {
				if(thre[i].getState().compareTo(Thread.State.TERMINATED) ==0){
					threads.remove(thre[i]);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(threads.size()>0);
		System.out.println("ALL THREADS DIED");
	}
}
class fun extends Thread{
	
	int i;
	public fun(int i) {
		setName(i+"");
		this.i = i;
		this.start();
	}

	@Override
	public void run() {
		try {
			try {
				Thread.sleep(i*10 );
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("cilent "+ i +"building");

			ClientAPI test = new ClientAPI("127.0.0.1", 8008);
			System.out.println("cilent "+ i +" is sening setup player");
			test.sendMessage(new setupPlayer(test.getId(), new Statistics(20, 20, 20, 20) , i%4,"") );
			double heading =0;
			long clock = System.currentTimeMillis();
			while(System.currentTimeMillis()-clock< 600000) {
				test.sendMessage(new Action(test.getId(), heading,true));
				if(Math.random()<.1) {
				heading += Math.PI*Math.random()*2;
				}
				Thread.sleep(100);
			//	System.out.println(test.getId());
				test.checkForConnection();
			}

				//test.close();
				System.out.println("cilent "+ i +" is dies");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
