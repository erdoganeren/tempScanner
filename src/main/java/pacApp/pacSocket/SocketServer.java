package pacApp.pacSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	  private InetAddress addr; 
	  private ServerSocket ss; 
	
	  public SocketServer() {
		  setupSocketServer();
	  }
	 
    private void reinRaus(Socket socket) throws IOException {
        BufferedReader rein = new BufferedReader(new InputStreamReader(socket
                .getInputStream()));
        PrintStream raus = new PrintStream(socket.getOutputStream());
        String s;
        
        while(rein.ready()) {
            s = rein.readLine();
            System.out.print(s);
            raus.println(s);
        }
    }

	
	 public static void main(String[] args) throws IOException {
		try {
		 InetAddress addr = InetAddress.getByName("172.20.10.7");
		 ServerSocket ss = new ServerSocket(8002, 50, addr);	        
		 while (true) {
			 Socket s=ss.accept();//establishes connection   	 
			 BufferedReader rein = new BufferedReader(new InputStreamReader(s.getInputStream()));
			 String rets = rein.readLine();	 
			 System.out.println(rets);
	     }
		}catch(Exception e) {
			System.out.println(e);
		}			
	 }
	 	 
	 public String getSocketTempValue() {
		 try {
			 while (true) {
				 Socket s=ss.accept(); //establishes connection   	 
				 BufferedReader rein = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 String rets = rein.readLine();	 
				 System.out.println(rets);
				 return rets;
		     }
			}catch(Exception e) {
				System.out.println(e);
			}
		 return "0";
	 }
	 
	 private void setupSocketServer() {
		 try {
			  addr = InetAddress.getByName("172.20.10.7");
			  ss = new ServerSocket(8002, 50, addr);	        
		
		 }catch(Exception e) {
				System.out.println(e);
		 }
	 }
}
