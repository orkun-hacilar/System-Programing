package LAB3_4.PART4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {





    public static void main(String[] args) {
        HashMap<String, ArrayList<Integer>> year = new HashMap<>();


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
            Thread worker = new threadpasa(aylar.get(i), "th" + i, year);
            executor.execute(worker);
           try{
               worker.join();
           }catch (Exception e){
                   e.printStackTrace();
            }
        }






        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the product key you want to see:");
        String givenKey = scan.nextLine();

        if(year.containsKey(givenKey)){
            System.out.println("For the product "+ givenKey+":");
            System.out.println("In-store sales: " + year.get(givenKey).get(0) + " TL");
            System.out.println("Online sales: " + year.get(givenKey).get(1) + " TL");
            System.out.println("Total sales: " + (year.get(givenKey).get(0) + year.get(givenKey).get(1)) + " TL");
        } else if(givenKey.equals("X")){
            int sum = 0;
            for (Map.Entry<String, ArrayList<Integer>> entry: year.entrySet()) {
                sum += entry.getValue().get(0) + entry.getValue().get(1);
            }
            System.out.println("The sum of the all products:"+ sum );
        }else {
            System.out.println("Product " + givenKey + " does not exist!");
        }














    }
















}


