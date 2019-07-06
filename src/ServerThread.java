import java.io.*;
import java.net.*;

class ServerThread extends Thread
{
	private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
   	
   	private double step;
   	private int start;
   	private int stop;
   	private double sum = 0;
	

   	public ServerThread(Socket socket, double step, int start, int stop)
   	{
   			this.step = step;
   			this.start = start;
   			this.stop = stop;
   		
      		dataSocket = socket;
      		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
			
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
      		}
    }
   	
   	public double getSum() {
   		return sum;
   	}
   	
   	

	public void run()
	{
   		String inmsg;
   		String outmsgStep = Double.toString(step);
   		String outmsgStart = Integer.toString(start);
   		String outmsgStop = Integer.toString(stop);
   		
   		ServerProtocol app = new ServerProtocol();
		
		try {
			out.println(outmsgStep);
			out.println(outmsgStart);
			out.println(outmsgStop);
			
			inmsg = in.readLine();
			
			sum = app.processRequest(inmsg);
			
			dataSocket.close();
		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}	
