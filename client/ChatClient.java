// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;
import java.util.Scanner;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String loginId, String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	if(message.startsWith("#")) {
    		handleCommand(message);
    	}
    	else {
    		sendToServer(message);
    	}
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  
  
  private void handleCommand(String command) {
	  String message ="";
	  int port;
	  Scanner fromConsole = new Scanner (System.in);
	  if (command.equals("#quit")) {
		  quit();
	  }
	  else if(command.equals("#logoff")) {
		  try {
			closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else if(command.equals("#sethost")) {
		  if(!isConnected()) {
			  System.out.println("Enter the name of the host");
			  message = fromConsole.nextLine();
			  setHost(message);
		  }else {
			  System.out.println("The client is still logged in!");
		  }
	  }
	  else if(command.equals("#setport")) {
		  if(!isConnected()) {
			  System.out.println("Enter the name of the port");
			  port = fromConsole.nextInt();
			  setPort(port);
		  }else {
			  System.out.println("The client is still logged in!");
		  }
	  }
	  else if(command.equals("#login")) {
		  if(!isConnected()) {
			  try{
				  openConnection();
			  }catch (Exception e) {
				  e.printStackTrace();
			  }
		  }
		  
	  }
	  else if(command.equals("#gethost")) {
		  System.out.println("The host is: "+getHost());
	  }
	  else if(command.equals("#getport")) {
		  System.out.println("The port is: "+getPort());
	  }
	  else {
		  try {
			sendToServer("Incorrect Command, please use the correct set of commands!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			clientUI.display
	        ("Could not send message to server.  Terminating client.");
			quit();
		}
	  }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
  /**
	 * Implements the Hook method called each time an exception is thrown by the client's
	 * thread that is waiting for messages from the server. The method may be
	 * overridden by subclasses.
	 * 
	 * @param exception
	 *            the exception raised.
	 */
  @Override
	protected void connectionException(Exception exception) {
	  clientUI.display("The server is Down!");
	  quit();
	}
  
  /**
	 * Implements the hook method called after the connection has been closed. The default
	 * implementation does nothing. The method may be overriden by subclasses to
	 * perform special processing such as cleaning up and terminating, or
	 * attempting to reconnect.
	 */
  @Override
	protected void connectionClosed() {
	  clientUI.display("Connection is now closed");
	  
	}
}
//End of ChatClient class
