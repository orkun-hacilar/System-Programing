package LAB5_6.PART1;
//   This codes are from Blackboard.

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
        for (int i=0; i<12;i++){
            Socket s = ss.accept();
            pool[i] = new Thread(new ClientThread(months.get(i),s,total));
            pool[i].start();




        }
        for (int i=0; i<12;i++){
            pool[i].join();
        }
        ss.close();
        System.out.println(total);
        System.out.println("Server Stopped");
        //udp baslat
    }
}
