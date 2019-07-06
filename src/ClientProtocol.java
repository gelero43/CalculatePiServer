import java.net.*;
import java.io.*;

public class ClientProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
	
	public double prepareRequest(String step, String start, String stop) throws IOException {
	
     	double step2 = Double.parseDouble(step);
     	int start2 = Integer.parseInt(start);
     	int stop2 = Integer.parseInt(stop);
     	
     	double sum =0;
     	for (int i=start2; i < stop2; i++) {
            double x = ((double)i+0.5)*step2;
            sum += 4.0/(1.0+x*x);
        }
     	return sum;
    }

	public void processReply(String theInput) throws IOException {
	
		System.out.println("Message received from server: " + theInput);
	}
}