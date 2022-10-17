package Orkun;
//   This codes are from Blackboard.
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client extends Thread{

    private Socket s;
    String fileName;
    HashMap<String, ArrayList<Integer>> total;

    public Client(String fileName, HashMap<String, ArrayList<Integer>> total, Socket s) {
        this.fileName = fileName;
        this.total = total;
        this.s = s;
    }

    public static void main(String[] args) {
        int portNumber = 6666;
        String host = "localhost";


        // Open a socket on a given host and port. Open input and output streams.

        try {
            Socket clientSocket = new Socket(host, portNumber); // host and port number like local host , 6666 //**************************
            System.out.println("connected");
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inp = new ObjectInputStream(clientSocket.getInputStream());

            try {
                String files = (String) inp.readObject();                   // METODLARIMIZ   inp.readObject();  ,   out.writeObject();
                System.out.println(files);
                HashMap<String, ArrayList<Integer>> readData = readFiles(files);

                HashMap<String, ArrayList<Integer>> monthlyData =  calculateMonthlySales(readData);

                out.writeObject(monthlyData);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host " + host);
        }

    }



    static HashMap<String, ArrayList<Integer>> calculateMonthlySales(HashMap<String, ArrayList<Integer>> sales){
        HashMap<String, ArrayList<Integer>> calcData = new HashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : sales.entrySet()) {
            String month = entry.getKey();//product name
            ArrayList<Integer> values = entry.getValue();//values
            ArrayList<Integer> monthlySales = new ArrayList<>();
            monthlySales.add(values.get(0) * values.get(1));//instore
            monthlySales.add(values.get(0) * values.get(2));//online
            calcData.put(month, monthlySales);  //key, values(monthlysales arraylistimiz)
        }
        return calcData;
    }


    static HashMap<String, ArrayList<Integer>> readFiles(String month){
        File readMonth = new File(month);
        HashMap<String, ArrayList<Integer>> data = new HashMap<>();

        try {
            Scanner scanner = new Scanner(readMonth);
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String readLine = scanner.nextLine();
                String[] splitLine =  readLine.split(",");
                String productName = splitLine[0];
                int price = Integer.parseInt(splitLine[1]);
                int storeP = Integer.parseInt(splitLine[2]);
                int onlineP = Integer.parseInt(splitLine[3]);

                ArrayList<Integer> values = new ArrayList<>();
                values.add(price);
                values.add(storeP);
                values.add(onlineP);

                data.put(productName, values);
            }
        } catch (FileNotFoundException e){
            System.out.println("Exception occur");
        }
        return data;
    }



























}
