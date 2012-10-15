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

import java.net.*;
import java.io.*;
import java.util.*;

public class SimpleServer
{

	public ServerSocket theSocket;
	public BetterSocket theClient;

	//
	public boolean init(int port) throws Exception, IOException, Throwable{
			theSocket = new ServerSocket(port);
			return true;
	}
	
	//ativa o socket
	public void iniciaConexao(){
		try {
			theClient = new BetterSocket(theSocket.accept());
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			
			e.printStackTrace();
		}
	}
	//metodo que verifica se a conexão está ativa
	public boolean isConnected(){
		if(this.theClient.theSocket.isConnected()){
		return true;
		}else{
			return false;
		}
	}
	//metodo que fecha o socket
	public void finish() throws IOException {
		this.theClient.close();
		this.theSocket.close();
		this.theClient = null;
		this.theSocket = null;
	}

	public class BetterSocket
	{
		Socket theSocket;
		BufferedReader theReader;
		PrintWriter theWriter;

		public BetterSocket(String host, int port)
		throws UnknownHostException, IOException
		{
			this(new Socket(host, port));
		}

		public BetterSocket(Socket s)throws UnknownHostException, IOException
		{
			theSocket = s;
			theReader = new BufferedReader( new InputStreamReader( theSocket.getInputStream() ) );
			theWriter = new PrintWriter( theSocket.getOutputStream() , true );
		}

		public Socket getSocket()
		{
			return theSocket;
		}

		public void close()throws IOException
		{
			theSocket.close();
		}

		public String readLine()throws IOException
		{
			return theReader.readLine();
		}

		public void println(String s)throws IOException
		{
			theWriter.println(s);
		}

	}

}