package LAB5_6.PART2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientThread implements Runnable{
    Socket socket;
    String fileName;
    HashMap<String, ArrayList<Integer>> total;

    public ClientThread(String fileName,Socket socket, HashMap<String, ArrayList<Integer>> total) {
        this.fileName = fileName;
        this.socket = socket;
        this.total = total;
    }
    void calculateYearlySales(HashMap<String, ArrayList<Integer>> monthlySales){
        for(Map.Entry<String, ArrayList<Integer>> entry : monthlySales.entrySet()){
            String productName = entry.getKey();
            int store= 0, online = 0;

            if(total.containsKey(productName)){
                ArrayList<Integer>  data    =  total.get(productName);
                store = data.get(0);
                online = data.get(1);
            }

            store += entry.getValue().get(0);
            online += entry.getValue().get(1);

            ArrayList<Integer> yearlyValues = new ArrayList<>();
            yearlyValues.add(store);
            yearlyValues.add(online);

            total.put(productName, yearlyValues);
        }
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());

            out.writeObject(fileName);
            HashMap<String, ArrayList<Integer>>  data = (HashMap<String, ArrayList<Integer>>) inp.readObject();
            calculateYearlySales(data);
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
