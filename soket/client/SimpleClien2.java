package soket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class SimpleClient {
	static Logger logger = Logger.getLogger(SimpleClientMain.class);
	private Socket socket;
	
	public SimpleClient(){}
	
	public void connect(String ip, int port) throws UnknownHostException, IOException{
		socket = new Socket( ip, port );
			
	}
	
	public String sendString( String msg ) throws Exception{
		
		String retMsg = null;
		
		try{
			// send Byte[] stream to the server
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			// 1. change stream data to byte[]
			byte[] sendWhat = msg.getBytes("ms949");
			// 2. get the length of data
			int writeLen= sendWhat.length;
			// 3. send the length first
			dos.writeInt(writeLen);
			dos.flush();
			// 4. send the data
			dos.write(sendWhat, 0, writeLen);
			dos.flush();
			
			// server receives Byte[] stream data
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			// 1. read the data size
			int size = dis.readInt();
		  // 2. create byte[] according to the data size
			byte[] receiveWhat = new byte[size];
			// 3. store data to byte[] 
			dis.read(receiveWhat); //OR dis.read(receiveWhat, 0, size);
			// 4. change the data to String
			retMsg = new String(receiveWhat, "ms949");
		
			return retMsg;
			
			/* Simple way:
			dos.writeUTF(msg);
			return dis.readUTF();
			*/
		}catch(Exception e){
			logger.info("Client sendString error ", e);
			e.printStackTrace();
			throw e;
		}
	}

	public void close() throws IOException{
		socket.close();
	}
}
