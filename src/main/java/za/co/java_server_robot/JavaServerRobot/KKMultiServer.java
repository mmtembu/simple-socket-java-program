package za.co.java_server_robot.JavaServerRobot;

import java.net.*;
import java.io.*;
import java.util.Enumeration;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java KKMultiServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        InetAddress ip;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                ip = NetworkInterface.getNetworkInterfaces().nextElement().getInterfaceAddresses().get(0).getAddress();
                System.out.println("Show me the address: "+ip.getHostName());
                new KKMultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}