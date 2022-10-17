package Orkun;
//   This codes are from Blackboard.

import javax.swing.plaf.TableHeaderUI;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(6666);


        HashMap<String, ArrayList<Integer>> total = new HashMap<>();

        ArrayList<String> months = new ArrayList<String>();
        months.add("Datasets/01-January.csv");
        months.add("Datasets/02-February.csv");
        months.add("Datasets/03-March.csv");
        months.add("Datasets/04-April.csv");
        months.add("Datasets/05-May.csv");
        months.add("Datasets/06-June.csv");
        months.add("Datasets/07-July.csv");
        months.add("Datasets/08-August.csv");
        months.add("Datasets/09-September.csv");
        months.add("Datasets/10-October.csv");
        months.add("Datasets/11-November.csv");
        months.add("Datasets/12-December.csv");


        Thread pool[] = new Thread[12];
        for (int i = 0; i < 12; i++) {
            try (Socket s = ss.accept()) {
                pool[i] = new Thread(new ClientThread(months.get(i), s, total));
            }catch (Exception e){
                System.out.println(e);
            }
            pool[i].start();


        }
        for (int i = 0; i < 12; i++) {
            pool[i].join();
        }
        ss.close();
        System.out.println(total);
        System.out.println("Server Stopped");
        //udp baslat


         // ---------UDP BAŞLIYOR------------


        DatagramSocket socket = new DatagramSocket(8888);
        System.out.println("sa");

        byte data[] = new byte[150];
        byte[] receiveData = new byte[150];
        byte[] sendData = new byte[150];
        String yolla;
        InetAddress IPAddress = InetAddress.getByName("localhost");



        //******************************************************************//
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.receive(packet);
        //******************************************************************//


        byte packetData[] = packet.getData();
        int packetSize = packet.getLength();
        String s2 = new String(packetData, 0, packetSize);

        System.out.println(new Date() + "  " + packet.getAddress() + " : " + packet.getPort() + "  __ > " + s2);

        String[] arrOfStr = s2.split(",");
        System.out.println(" Product name on which we will process : ---> " + arrOfStr[0]);
        System.out.println("sale type on which we will process : ---> " + arrOfStr[1]);
        int number = Integer.parseInt(arrOfStr[1]);



         for (Map.Entry<String, ArrayList<Integer>> entry : total.entrySet()) {
            if(arrOfStr[0] == entry.getKey()){
                ArrayList<Integer> values = entry.getValue();
                int wantedValue = values.get(number);
                System.out.println("we have to send to client this value -->" + wantedValue);

                String Result= String.valueOf(wantedValue);

                System.out.println("********WANTED VALUE İS******** -- >" + Result);
                yolla = Result;
                sendData = yolla.getBytes();
            }
        }

         //*********************************************************************************************//
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
        socket.send(sendPacket);
        //*********************************************************************************************//












    }
}
