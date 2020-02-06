package Server.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class ObjectByteConvertor implements Serializable{
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3067615238568711226L;

	public static void main(String[] args) {
		try {
		ObjectByteConvertor s = new ObjectByteConvertor();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream  oos = new ObjectOutputStream(baos);
		oos.writeObject(s);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		//bais.close();
		ObjectInputStream ois = new ObjectInputStream(bais);
				
		Object o = ois.readObject();
		System.out.println(o);
		
		
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

}
