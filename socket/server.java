import java.io.*;
import java.net.*;

public class server {


	public static void main (String[] args) throws IOException{

	  ServerSocket servr=new ServerSocket(7000);
	Socket s =servr.accept();
     System.out.println ("client connected");
     InputStreamReader gt= new 
	InputStreamReader(s.getInputStream());
	BufferedReader ot = new BufferedReader(gt);

 	
       PrintWriter rp= new PrintWriter(s.getOutputStream());
       rp.println("whatup");
       rp.flush();

 	String Strg =ot.readLine ();
	System.out.println(Strg);


}	
}
