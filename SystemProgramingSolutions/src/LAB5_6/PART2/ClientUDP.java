package LAB5_6.PART2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        System.out.println("Which product name do you want to search ?");
        Scanner input = new Scanner(System.in);
        String productName = input.nextLine();
        System.out.println("Which sale type do you want to search ?");
        System.out.println("Press 0 --> FOR IN STORE");
        System.out.println("Press 1 --> FOR ONLINE");
        String saleType = input.nextLine();
        InetAddress address;
        DatagramSocket socket;
        DatagramPacket packet;

        try {
                address = InetAddress.getByName("localhost");
                socket = new DatagramSocket(8888);
                String message1 = productName+","+saleType;
                byte data[] = message1.getBytes();
                packet = new DatagramPacket(data, data.length, address, 8887);
                socket.send(packet); // send the packet
                System.out.println("message sent");

            byte recivedData[] = new byte[10];
            DatagramPacket recivedPacket = new DatagramPacket(recivedData, recivedData.length);
            socket.receive(recivedPacket);
            String message2 = new String(recivedPacket.getData());
            String output = new String(message2).replaceAll("\0", "");
            System.out.println(output);

        } catch (UnknownHostException e) {
        } catch (SocketException e) {
        } catch (IOException e) {}
    }
    }


