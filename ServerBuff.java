import java.io.*;
import java.net.*;


 public class smtptestServer {

     //
     // Java main application for the SMTP test.
     //
     public static void main( String[] args ) {


       // Create a new smtpc object
       smtpc recvMail = new smtpc();

      recvMail.send();

     }
   }


   class smtpc {
     private String s_initial;
    private String s_initialnext;
     private String s_recipient;
     private String s_content_type;
     private String s_contents;
     private PrintStream out;
     private BufferedReader inp;



	// Date date = new Date();
	// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  

     public smtpc() {

       s_initial= "220 mailsite.com ESMTP 9.8.3/9.9.7 ; ";
       s_initialnext = "250 mailsite.com Hello , pleased to meet you";
       s_recipient = "250 Sender ok.";
      s_content_type = "250 recepient ok.";
       s_contents = "354 Enter mail, end with . on a line by itself";
     }

     //
     //  A new exception used to identify SMTP 
     //  communication errors
    //
     class MyException extends Exception {
      public MyException() { super(); }
     }


     //
     //  Perform a transaction to the SMTP server
     //
     private void dialog( String command, 
                            String expected_response )
                  throws MyException {

       // If a command is available, send it to the server
       if ( command != null ) {

         out.print( command );

       }

       // If a response is expected, get it and test it
       // against the desired response.

       if ( expected_response != null ) {

         try {
           String line = inp.readLine();

            System.out.println(line);
             return ;

           } else {
            throw new MyException();

           }

         } catch( IOException e ) {
             throw new MyException();

         }

       }

     }


     public void send () {
        ServerSocket ss ;
       Socket sock;

       try {
         ss =new ServerSocket(25);
         sock = ss.accept();     
         inp = new BufferedReader(
                     new InputStreamReader( 
                           sock.getInputStream() ) );
         out = new PrintStream( sock.getOutputStream() );


         try {

           this.dialog( s_initial,"HELO" );

           this.dialog( s_initialnext,"MAIL" );
           this.dialog(s_recipient , "RCPT" );
           this.dialog(s_content_type , "DATA" );
           this.dialog( s_contents, "From:" );
           
           this.dialog( null, "To" );
           
           this.dialog( null, "Subject" );
           
           this.dialog( null, "Content-Type:" );

           this.dialog( null, "This" );
           this.dialog( null, "." );
           this.dialog( "250 WAA14066 Message accepted for delivery", "QUIT" );
        this.dialog( "221 mailsite.com closing connection", null );

           sock.close();

         } catch( MyException e ) {

           System.out.println( e );

         }

       } 
       catch( UnknownHostException e ) {}
       catch( IOException e ) {}

     }

   }