package Orkun;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientUdp {

    public static void main(String args[]) {
        InetAddress address;
        DatagramSocket socket;
        DatagramPacket packet;


        try {
            address = InetAddress.getByName("localhost");
            socket = new DatagramSocket(8888);

            System.out.println("Which product name do you want to search ?");
            Scanner ınput = new Scanner(System.in);
            String productName = ınput.nextLine();
            System.out.println("Which sale type do you want to search ?");
            System.out.println("Press 1 --> FOR IN STORE");
            System.out.println("Press 2 --> FOR ONLINE");
            String saleType = ınput.nextLine();

            String allWantedThings = (productName + "," + saleType);

            byte data[] = allWantedThings.getBytes();

            //************************************************************************//
            packet = new DatagramPacket(data, data.length, address, 8888);
            socket.send(packet); // send the packet
            //************************************************************************//

            Date sendTime = new Date(); // note the time of sending the message


            //************************************************************************//
            DatagramPacket receivedpacket = new DatagramPacket(data, data.length);
            socket.receive(receivedpacket);
            //************************************************************************//


            String value = new String(receivedpacket.getData());
            Date receiveTime = new Date(); // note the time of receiving the message

            System.out.println("RECEIVED TIME : --> " +(receiveTime.getTime() - sendTime.getTime())+ " <---:  RECEIVED TIME ***** "+ " Our expected Value Is -- >  " + value);

        } catch (UnknownHostException e) {
        } catch (SocketException e) {
        } catch (IOException e) {
        }
    }


}
