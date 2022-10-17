package LAB5_6.PART2;

import java.net.*;
import java.util.ArrayList;
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
        for (int i=0; i< 2; i++){
            Socket soc = ss.accept();
            System.out.println("accepted");
            pool[i] = new Thread(new ClientThread(months.get(i),soc,total));
            System.out.println("thread added");
            pool[i].start();
            System.out.println("thread runs");
        }
        for (int i=0; i< 2; i++){
            pool[i].join();
        }
        ss.close();
        System.out.println(total);
        System.out.println("Server2 Stopped");


            //udp baslat
            String tempData;
            byte[] sendData = new byte[150];
            DatagramSocket datagramSocket = new DatagramSocket(8887);
            byte data[] = new byte[150];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            datagramSocket.receive(packet);
            byte packetData[] = packet.getData();
            int packetSize = packet.getLength();
            String receivedMessage = new String(packetData, 0, packetSize);
            String[] arrOfStr = receivedMessage.split(",");
            String keyName = arrOfStr[0];
            int number = Integer.parseInt(arrOfStr[1]);
            System.out.println(" Product name on which server process : ---> " + keyName);
            System.out.println("Sale type on which server process : ---> " + number);

            int globalSum = 0;

        for (Map.Entry<String, ArrayList<Integer>> entry : total.entrySet()) {
            if (arrOfStr[0].equals(entry.getKey())) {
                ArrayList<Integer> values = entry.getValue();
                int wantedValue = values.get(number);
                System.out.println("Server will be send: " + wantedValue);
                String Result = String.valueOf(wantedValue);
                System.out.println("********WANTED VALUE IS******** -- >" + Result);

                sendData = Result.getBytes();
            } else if (arrOfStr[0].equals("X") || arrOfStr[0].equals("x")) {
                int sum = 0;
                if (number == 0) {
                    for (Map.Entry<String, ArrayList<Integer>> entry2 : total.entrySet()) {
                        sum += entry2.getValue().get(0);
                    }

                } else if (number == 1) {
                    for (Map.Entry<String, ArrayList<Integer>> entry3 : total.entrySet()) {
                        sum += entry3.getValue().get(1);
                    }
                }
                globalSum = sum;
            }
        }
        if (number == 0){
            System.out.println("The sum of  all in-store products: " + globalSum);
            String Result = String.valueOf(globalSum);
            System.out.println("********WANTED VALUE IS******** -- >" + Result);
            sendData = Result.getBytes();
        } else if (number == 1){
            System.out.println("The sum of online sales for all products: " + globalSum);
            String Result = String.valueOf(globalSum);
            System.out.println("********WANTED VALUE IS******** -- >" + Result);
            sendData = Result.getBytes();
        }
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packetToSend = new DatagramPacket(sendData,sendData.length,address,8888);
            System.out.println("  " + packet.getAddress() + " : " + packet.getPort() + " " + receivedMessage);
            datagramSocket.send(packetToSend);
    }
}
