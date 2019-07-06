import java.net.*;
import java.io.*;
public class ClientTCP {
    private static final String HOST = "localhost";
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		//InetAddress address = InetAddress.getByName(HOST);
            	Socket dataSocket = new Socket(HOST, PORT);
        	
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		System.out.println("Connection to " + HOST + " established");

		String inmsgStep;
		String inmsgStart;
		String inmsgStop;
		double outmsg;
		ClientProtocol app = new ClientProtocol();
		
		inmsgStep = in.readLine();
		inmsgStart = in.readLine();
		inmsgStop = in.readLine();
        outmsg = app.prepareRequest(inmsgStep,inmsgStart,inmsgStop);
		out.println(outmsg);
		
		dataSocket.close();
		System.out.println("Data Socket closed");
	}
}			

