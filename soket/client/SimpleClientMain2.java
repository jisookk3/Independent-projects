package soket.client;

import java.awt.Robot;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

public class SimpleClientMain extends TestCase{
	
	static Logger logger = Logger.getLogger(SimpleClientMain.class);
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		
		try{
			String result;
			
			SimpleClient sc1 = new SimpleClient();
			SimpleClient sc2 = new SimpleClient();
			SimpleClient sc3 = new SimpleClient();
			
			//[1] ==== Connection with the server ==================
			setTimerOn(1);
			logger.info("connect 1");
			sc1.connect("127.0.0.1", 5000);
			
			setTimerOn(1);
			logger.info("connect 2");
			sc2.connect("127.0.0.1", 5000);
			
			setTimerOn(1);
			logger.info("connect 3");
			sc3.connect("127.0.0.1", 5000);
			
			//[2] ==== Request to server ==================
			setTimerOn(2);
			logger.info("client 1 request ");
			result = sc1.sendString("socket 1 request. abc");
			logger.info("Result client 1 receives [" + result + "]");
			
			setTimerOn(3);
			logger.info("client 2 request ");
			result = sc2.sendString("socket 1 request. def");
			logger.info("Result client 2 receives  [" + result + "]");

			setTimerOn(3);
			logger.info("client 3 request ");
			result = sc3.sendString("Jay/Mary/Jiji:Mon:Dad");
			logger.info("Result client 3 receives [" + result + "]");
			
			setTimerOn(3);
			logger.info("client 1 request again ");
			result = sc3.sendString("re-request test 1");
			logger.info("Result client 1 receives [" + result + "]");
			
			setTimerOn(3);
			logger.info("client 2 request again ");
			result = sc2.sendString("re-request test 2 abc");
			logger.info("Result client 2 receives [" + result + "]");
			
			//[3] ==== Disconnection with the server! ==================
			setTimerOn(3);
			logger.info("connect close 1");
			sc1.close();
			
			setTimerOn(2);
			logger.info("connect close 2");
			sc2.close();	
			
			setTimerOn(2);
			logger.info("connect close 3");
			sc3.close();			
			
		}catch(Exception e){
			logger.info("Client error!");
			e.printStackTrace();
		}
	}
	
	  public static void setTimerOn(int nTimer)  throws Exception  // nTimer: second
	  {
	       int nDelayTime;
	       nDelayTime = nTimer * 1000; // 1 sec
	       
	       Robot tRobot = new Robot();
	       tRobot.delay(nDelayTime);   // make the process sleep during nDelayTime(1 sec)
	 }
}
