import java.net.*;
import java.io.*;

public class MultiThreadedServerTCP {
	private static final int PORT = 1234;

	public static void main(String args[]) throws IOException {


		ServerSocket connectionSocket = new ServerSocket(PORT);
		
		int numSteps = 10000000;
		double step = 1.0 / (double)numSteps;
		double sum = 0;
		
		int numOfClients = 4;			
		
		ServerThread[] sthread = new ServerThread[numOfClients];
		
		int block = numSteps / numOfClients;
		
		int stop = block;
		for(int i = 0, start = 0; i < numOfClients; i++,start += block){	

			System.out.println("Server is listening to port: " + PORT);
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Received request from " + dataSocket.getInetAddress());

			sthread[i] = new ServerThread(dataSocket, step,start,stop);
			sthread[i].start();
			stop += block;
		}
		
		for(int i = 0; i < numOfClients; i++) {
			try {
				sthread[i].join();
           		} catch (InterruptedException e) {}
		}
		
		for(int i = 0; i<numOfClients; i++) {
			sum += sthread[i].getSum();
		}
		System.out.println(sum);
		
			
		
	}
}

