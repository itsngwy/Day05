package vttp2022.day05.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Server2 is incomplete

public class Server2 {
    
    public static void main(String[] args)
        throws IOException{

        // Create a server socket and listen to a port
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Waiting for connection on port 3000...");
        Socket sock = server.accept();
        System.out.println("Connection accepted");

        // Get the input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));){

            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
                // Read request from client
                String request = dis.readUTF();

                System.out.printf("Received request: %s\n", request);

                // Perform some operation on the request
                request = request.toUpperCase();

                // Write it back to client
                dos.writeUTF(request);
                
                if (inputLine.equals("exit"))
                    break;
            }
        
        }

        // Close the streams
        is.close();
        os.close();

        // Close the sockets
        sock.close();
    }
}
