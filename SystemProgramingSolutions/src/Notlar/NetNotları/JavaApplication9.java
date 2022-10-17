package Notlar.NetNotları;

import java.io.IOException;
import java.net.*;

public class JavaApplication9 {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException  {
        // TODO code application logic here
        byte[] buffer = {10,23,12,31,43,32,24};
        byte [] IP = {-64,-88,1,106};
        InetAddress address = InetAddress.getByAddress(IP);

        DatagramPacket packet = new DatagramPacket( buffer, buffer.length, address, 57 );

        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}

//public void run() {
  //      try {
    //    DatagramSocket serverSocket = new DatagramSocket(port);
      //  byte[] receiveData = new byte[8];
        //byte[] sendData = new byte[8];

        //while (true) {
        //DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        //serverSocket.receive(receivePacket);

        //String sentence = new String( receivePacket.getData());
        //System.out.println("RECEIVED: " + sentence);

        //InetAddress IPAddress = receivePacket.getAddress();
        //String sendString = "polo";
        //sendData = sendString.getBytes();
        //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        //serverSocket.send(sendPacket);
        //}
        //} catch (IOException e) {
        //e.printStackTrace();
        //}
        //}