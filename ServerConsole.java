import java.io.IOException;
import java.util.Scanner;

import common.ChatIF;

public class ServerConsole implements ChatIF  {
	Scanner fromConsole = new Scanner (System.in);
	EchoServer server;
	final public static int DEFAULT_PORT = 5555;
	

	//Constructors ****************************************************

	  /**
	   * Constructs an instance of the ClientConsole UI.
	   *
	   * @param host The host to connect to.
	   * @param port The port to connect on.
	 * @throws IOException 
	   */
	  public ServerConsole(int port) throws IOException 
	  {
		  server = new EchoServer(port, this);
		  // Create scanner object to read from console
		  fromConsole = new Scanner(System.in); 
	  }
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int port = 0; //Port to listen on

	    try
	    {
	      port = Integer.parseInt(args[0]); //Get port from command line
	    }
	    catch(Throwable t)
	    {
	      port = DEFAULT_PORT; //Set port to 5555
	    }
		
	    EchoServer sv = new EchoServer(port);
	    
	    try 
	    {
	      sv.listen(); //Start listening for connections
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println("ERROR - Could not listen for clients!");
	    }
	    ServerConsole chat= new ServerConsole(port);
	    chat.accept();
	}
	
	/**
	   * This method overrides the method in the ChatIF interface.  It
	   * displays a message onto the screen.
	   *
	   * @param message The string to be displayed.
	   */
	public void display (String message) {
		System.out.println("SERVER MSG> " + message);
	}
	
	
	public void accept() 
	  {
	    try
	    {
	      String message;
	      while (true) 
	      {
	        message = fromConsole.nextLine();
	        server.handleMessageFromServerUI(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
}
