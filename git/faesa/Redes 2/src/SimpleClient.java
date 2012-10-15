/*
 * Created on 16/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author gsudre
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class SimpleClient
{
	
	
	BetterSocket theSocket;
	
	
		//cria o socket
	public boolean init(String ip, int port) throws UnknownHostException, IOException
	{
			theSocket = new BetterSocket( ip, port);
			return true;
	}
	//metodo que fecha o socket
	public void finish() throws IOException{
		theSocket.close();
		
		
	}
	public void fatalError(Exception ex)
	{
		ex.printStackTrace();
		
		try
		{
			theSocket.close();
		}
		
		catch (Exception ex1) { ; }
	}
	//metodo que verifica se a conexão está ativa
	public boolean isConnected(){
		if(this.theSocket.theSocket.isConnected()){
		return true;
		}else{
			return false;
		}
	}

	public class BetterSocket
	{
		Socket theSocket;
		BufferedReader theReader;
		PrintWriter theWriter;
		
		public BetterSocket( String host, int port )
		throws UnknownHostException, IOException
		{
			this(new Socket( host , port ) );
			
		}
		
		public BetterSocket(Socket s)
		throws UnknownHostException, IOException
		{
			theSocket = s;
			theReader = new BufferedReader( new InputStreamReader( theSocket.getInputStream() ) );
			theWriter = new PrintWriter( theSocket.getOutputStream() , true );
		}
		
		public Socket getSocket()
		{
			return theSocket;
		}
		
		public void close()
		throws IOException
		{
			theSocket.close();
		}

		public String readLine()
		throws IOException
		{
			return theReader.readLine();
		}
		
		public void println(String s)
		throws IOException
		{
			theWriter.println(s);
		}
		
	}
	
}