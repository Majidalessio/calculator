
package client;
import java.io.*;
import java.net.*;

public class Client 
{
    String nome="127.0.0.1";            //server ip
    int nPorta=2560;                    //port number
    Socket socket;
    BufferedReader input;               //keyboard input
    String number1;                     //input message, number 1
    String operando;                    //+-/x
    String number2;                     //input message, number 1
    String messOUT;                     //output message
    DataOutputStream clientData;        //data from the client to the server
    BufferedReader serverData;          //data from the server to the client
    
    
    public Socket connetti()
    {
        System.out.println("Client currently running...");
        try 
        {
            input=new BufferedReader(new InputStreamReader(System.in));
            socket=new Socket(nome,nPorta);
            clientData=new DataOutputStream(socket.getOutputStream());
            serverData=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException e)
        {
            System.err.println("Invalid Host.");
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Connection error.");
            System.exit(1);
        }
        return(socket);
    }
    
    public void comunica()
    {
        try 
        {
            System.out.println("Insert the first number");
            number1=input.readLine();
            clientData.writeBytes(number1+'\n');
            System.out.println("Insert one of the following symbols, +-/x");
            operando=input.readLine();
            clientData.writeBytes(operando+'\n');
            System.out.println("Insert the second number");
            number2=input.readLine();
            clientData.writeBytes(number2+'\n');
            System.out.println("Sending...");
            messOUT=serverData.readLine();
            System.out.println("The answer is "+messOUT);
            System.out.println("End of Service.");
            socket.close();
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Error communicating with the server-");
            System.exit(1);
        }
    }
}
