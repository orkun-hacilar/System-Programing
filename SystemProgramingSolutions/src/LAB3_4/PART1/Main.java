package LAB3_4.PART1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Handler;

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



        Thread t0 = new threadpasa(aylar.get(0), "th0", year);
        Thread t1 = new threadpasa(aylar.get(1), "th1", year);
        Thread t2 = new threadpasa(aylar.get(2), "th2", year);
        Thread t3 = new threadpasa(aylar.get(3), "th3" , year);
        Thread t4 = new threadpasa(aylar.get(4), "th4", year);
        Thread t5 = new threadpasa(aylar.get(5), "th5", year);
        Thread t6 = new threadpasa(aylar.get(6), "th6", year);
        Thread t7 = new threadpasa(aylar.get(7), "th7", year);
        Thread t8 = new threadpasa(aylar.get(8), "th8", year);
        Thread t9 = new threadpasa(aylar.get(9), "th9" , year);
        Thread t10 = new threadpasa(aylar.get(10) ,"th10", year);
        Thread t11 = new threadpasa(aylar.get(11), "th11", year);


        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();


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


