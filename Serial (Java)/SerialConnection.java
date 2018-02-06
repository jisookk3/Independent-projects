import javax.comm.*;
import java.io.*;
import java.awt.event.*;
import java.util.TooManyListenersException;

public class SerialConnection implements SerialPortEventListener, CommPortOwnershipListener 
{
    private SerialParameters parameters;
    private OutputStream os;
    private InputStream is;

    // CommPortIdentifiers object is needed to gather the list of ports that are available 
    // for connection. by using its getPortIdentifiers() method.
    // SerialPort object is for storing the data for the port once a successful connection is made.
    private CommPortIdentifier portId;
    private SerialPort sPort;

    private boolean open;

    public SerialConnection(SerialParameters parameters) 
    {
		this.parameters = parameters;
		open = false;
   }

	public void openConnection() throws SerialConnectionException 
	{

		try 
		{
	    	portId =  CommPortIdentifier.getPortIdentifier(parameters.getPortName());
		} catch (NoSuchPortException e) {
	     throw new SerialConnectionException(e.getMessage());
		}

		try 
		{
		    sPort = (SerialPort)portId.open("SerialDemo", 30000);
		} catch (PortInUseException e) {
		    throw new SerialConnectionException(e.getMessage());
		}

		try 
		{
		    setConnectionParameters();
		} catch (SerialConnectionException e) {	
		    sPort.close();
		    throw e;
		}

	
		try 
		{
		    os = sPort.getOutputStream();
		    is = sPort.getInputStream();
		} catch (IOException e) {
		    sPort.close();
		    throw new SerialConnectionException("Error opening i/o streams");
		}
	
		try 
		{
		    sPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		    sPort.close();
		    throw new SerialConnectionException("too many listeners added");
		}

		sPort.notifyOnDataAvailable(true);

		sPort.notifyOnBreakInterrupt(true);

		try 
		{
	    	sPort.enableReceiveTimeout(30);
		} catch (UnsupportedCommOperationException e) {}

		// Add ownership listener to allow ownership event handling.
		portId.addPortOwnershipListener(this);
	
		open = true;
	}
	
	    
    public void setConnectionParameters() throws SerialConnectionException 
    {
		// Save state of parameters before trying a set.
		int oldBaudRate = sPort.getBaudRate();
		int oldDatabits = sPort.getDataBits();
		int oldStopbits = sPort.getStopBits();
		int oldParity   = sPort.getParity();
		int oldFlowControl = sPort.getFlowControlMode();
	
		// Set connection parameters, if set fails return parameters object
		// to original state.
		try {
		    sPort.setSerialPortParams(parameters.getBaudRate(),
					      parameters.getDatabits(),
					      parameters.getStopbits(),
					      parameters.getParity());
		} catch (UnsupportedCommOperationException e) {
		    parameters.setBaudRate(oldBaudRate);
		    parameters.setDatabits(oldDatabits);
		    parameters.setStopbits(oldStopbits);
		    parameters.setParity(oldParity);
		    throw new SerialConnectionException("Unsupported parameter");
		}
	
		// Set flow control.
		try {
		    sPort.setFlowControlMode(parameters.getFlowControlIn() 
				           | parameters.getFlowControlOut());
		} catch (UnsupportedCommOperationException e) {
		    throw new SerialConnectionException("Unsupported flow control");
		}
    }

    /**
    Close the port and clean up associated elements.
    */
    public void closeConnection() {
	// If port is alread closed just return.
	if (!open) {
	    return;
	}

	// Remove the key listener.


	// Check to make sure sPort has reference to avoid a NPE.
	if (sPort != null) {
	    try {
		// close the i/o streams.
	    	os.close();
	    	is.close();
	    } catch (IOException e) {
		System.err.println(e);
	    }

	    // Close the port.
	    sPort.close();

	    // Remove the ownership listener.
	    portId.removePortOwnershipListener(this);
	}

	open = false;
    }

    /**
    Send a one second break signal.
    */
    public void sendBreak() {
	sPort.sendBreak(1000);
    }

    /**
    Reports the open status of the port.
    @return true if port is open, false if port is closed.
    */
    public boolean isOpen() {
	return open;
    }

    /**
    Handles SerialPortEvents. The two types of SerialPortEvents that this
    program is registered to listen for are DATA_AVAILABLE and BI. During 
    DATA_AVAILABLE the port buffer is read until it is drained, when no more
    data is availble and 30ms has passed the method returns. When a BI
    event occurs the words BREAK RECEIVED are written to the messageAreaIn.
    */

    public void serialEvent(SerialPortEvent e) {
 	// Create a StringBuffer and int to receive input data.
	StringBuffer inputBuffer = new StringBuffer();
	int newData = 0;

	// Determine type of event.
	switch (e.getEventType()) {

	    // Read data until -1 is returned. If \r is received substitute
	    // \n for correct newline handling.
	    case SerialPortEvent.DATA_AVAILABLE:
		    while (newData != -1) {
		    	try {
		    	    newData = is.read();
			    if (newData == -1) {
			    	break;
			    	}
				    if ('\r' == (char)newData) {
				   	inputBuffer.append('\n');
				    } else {
				    	inputBuffer.append((char)newData);
				    }
		    	} catch (IOException ex) {
		    	    System.err.println(ex);
		    	    return;
		      	}
   		    }

		// Append received data to messageAreaIn.
		System.out.print(new String(inputBuffer));
		break;

	    // If break event append BREAK RECEIVED message.
	    case SerialPortEvent.BI:
	    System.out.println("\n--- BREAK RECEIVED ---\n");
	}

    }   

    /**
    Handles ownership events. If a PORT_OWNERSHIP_REQUESTED event is
    received a dialog box is created asking the user if they are 
    willing to give up the port. No action is taken on other types
    of ownership events.
    */
    public void ownershipChange(int type) {
	if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
	   // PortRequestedDialog prd = new PortRequestedDialog(parent);
	}
    }

    /*    
    A class to handle KeyEvents generated by the messageAreaOut. 
    When a KeyEvent occurs the char that is generated by the event is read, 
    converted to an int and writen to the OutputStream for the port.

    */
    class KeyHandler extends KeyAdapter {
	OutputStream os;

	/**
	Creates the KeyHandler.
	@param os The OutputStream for the port.
	*/
	public KeyHandler(OutputStream os) {
	    super();
	    this.os = os;
	}

	/*	
	Handles the KeyEvent. 
	Gets the char generated by the KeyEvent, converts it to an int, 
	writes it to the  OutputStream for the port.

	*/
    public void keyTyped(KeyEvent evt) {
        char newCharacter = evt.getKeyChar();
	    try {
	    	os.write((int)newCharacter);
	    } catch (IOException e) {
		System.err.println("OutputStream write error: " + e);
	    }
        }
    }
    public static void main(String args[])
    {
    	SerialConnection connection = new SerialConnection(new SerialParameters());
    	
    	try {
	    	connection.openConnection();
	    } catch (SerialConnectionException e2) {
			System.out.println("Error Connect Port!!");
	    }
    }
}
