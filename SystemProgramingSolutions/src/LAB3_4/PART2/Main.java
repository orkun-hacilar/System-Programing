package LAB3_4.PART2;

import java.util.*;

public class Main {





    public static void main(String[] args) throws InterruptedException {





        Map<String, ArrayList<Integer>> year = new HashMap<>();
        Map<String, ArrayList<Integer>> synchronizedYear = Collections.synchronizedMap(year); // synchronized olmasına rağmen ? Ufuk hocaya sor!


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



        Thread t0 = new threadpasa(aylar.get(0), "th0", synchronizedYear);
        Thread t1 = new threadpasa(aylar.get(1), "th1", synchronizedYear);
        Thread t2 = new threadpasa(aylar.get(2), "th2", synchronizedYear);
        Thread t3 = new threadpasa(aylar.get(3), "th3" , synchronizedYear);
        Thread t4 = new threadpasa(aylar.get(4), "th4", synchronizedYear);
        Thread t5 = new threadpasa(aylar.get(5), "th5", synchronizedYear);
        Thread t6 = new threadpasa(aylar.get(6), "th6", synchronizedYear);
        Thread t7 = new threadpasa(aylar.get(7), "th7", synchronizedYear);
        Thread t8 = new threadpasa(aylar.get(8), "th8", synchronizedYear);
        Thread t9 = new threadpasa(aylar.get(9), "th9" , synchronizedYear);
        Thread t10 = new threadpasa(aylar.get(10) ,"th10", synchronizedYear);
        Thread t11 = new threadpasa(aylar.get(11), "th11", synchronizedYear);

        threadpasa threadpasa = new threadpasa();


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

        t0.join();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();
        t11.join();


        System.out.println("  ************ THREAD YEAR MAP YAPISININ İÇİ ************.");
        for (Map.Entry<String, ArrayList<Integer>> entry : synchronizedYear.entrySet()) {//gene for each yapısı kuruyorum aldım adamdan map yapısını.
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
        System.out.println("************ THREAD YEAR MAP YAPISININ İÇİ ************");



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


