package LAB3_4.PART5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {





    public static void main(String[] args) {



        Map<String, ArrayList<Integer>> year = new HashMap<>();
        Map<String, ArrayList<Integer>> cyear = new ConcurrentHashMap<>(year);


        ArrayList<String> aylar = new ArrayList<String>();
        aylar.add("Datasets/01-January.csv");
        aylar.add("Datasets/02-February.csv");
        aylar.add("Datasets/03-March.csv");
        aylar.add("Datasets/04-April.csv");
        aylar.add("Datasets/05-May.csv");
        aylar.add("Datasets/06-June.csv");
        aylar.add("Datasets/07-July.csv");
        aylar.add("Datasets/08-August.csv");
        aylar.add("Datasets/09-September.csv");
        aylar.add("Datasets/10-October.csv");
        aylar.add("Datasets/11-November.csv");
        aylar.add("Datasets/12-December.csv");



        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 12; i++) {
            Thread worker = new threadpasa(aylar.get(i), "th" + i, cyear);
            executor.execute(worker);
            try{
                worker.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }



















    }
















}


