package LAB_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

     static HashMap<String, ArrayList<Integer>> year = new HashMap<>();



    public static void main(String[] args) {

        ArrayList<HashMap<String, ArrayList<Integer>>> monthlySales = new ArrayList<>();


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
        Thread t3 = new threadpasa(aylar.get(3), "th3", year);
        Thread t4 = new threadpasa(aylar.get(4), "th4", year);
        Thread t5 = new threadpasa(aylar.get(5), "th5", year);
        Thread t6 = new threadpasa(aylar.get(6), "th6", year);
        Thread t7 = new threadpasa(aylar.get(7), "th7", year);
        Thread t8 = new threadpasa(aylar.get(8), "th8", year);
        Thread t9 = new threadpasa(aylar.get(9), "th9", year);
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





        for( int x = 0; x<aylar.size(); x++){
            HashMap<String, ArrayList<Integer>> readdata =  readFiles(aylar.get(x));
            HashMap<String, ArrayList<Integer>> calculatedmonthlysales = calculateMonthlySales(readdata);
            calculateYearlySales(calculatedmonthlysales);
        }





        System.out.println("  ************YEAR MAP YAPISININ İÇİ ************.");
        for (Map.Entry<String, ArrayList<Integer>> entry :  year.entrySet()) {//gene for each yapısı kuruyorum aldım adamdan map yapısını.
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
        System.out.println("************YEAR MAP YAPISININ İÇİ ************");


        System.out.println("PART 2 BAŞLIYOR ............ ");
        Scanner scan = new Scanner(System.in);
        System.out.println("Which product do you want search?");
        String userSelect = scan.nextLine();


        if(year.containsKey(userSelect)){
            System.out.println("For the product "+ userSelect);
            System.out.println("In-store sales: " + year.get(userSelect).get(0) + " ₺");
            System.out.println("Online sales: " + year.get(userSelect).get(1) + " ₺");
            System.out.println("Total sales: " + (year.get(userSelect).get(0) + year.get(userSelect).get(1)) + " ₺");
        } else if(userSelect.equals("X") || userSelect.equals("x")){
            //TODO

            int sum = 0;

            for (Map.Entry<String, ArrayList<Integer>> entry :  year.entrySet()) {
                 sum += entry.getValue().get(0) +  entry.getValue().get(1);
            }


            System.out.println("The sum of in-store and online sales for all products: "+ sum );
        }else {
            System.out.println("Product " + userSelect + " not found!");
        }




    }







    static HashMap<String, ArrayList<Integer>> readFiles(String month) { // https://www.web-gelistirme-sc.com/tr/java/next-ve-nextline-yontemleri-arasindaki-scanner-sinifindaki-fark-nedir/1044541919/#:~:text=Bir%20Scanner%20%2C%20varsayılan%20olarak%20boşlukla,ilerletir%20ve%20atlanan%20girişi%20döndürür.
        File file = new File(month); //file okumak için yazdığım kod bildiğim gibi her zaman File file = new file("blabla.csv") ile aynı sadece parametreli hali.
        HashMap<String, ArrayList<Integer>> data = new HashMap<>(); // bir hashmap döndürceği için bu fonksyon bir map tanımladım.
        try {
            Scanner scanner = new Scanner(file); // değerleri okumak istiyorum. Scaner kulanıp içine file instancemi verirsem o dosyayı okuyabilirim.
            scanner.nextLine(); // imleci bir sonraki satıra yerleştirdim. Çünkü split yapacağım.

            while (scanner.hasNextLine()) { // ilk cümleden sonra satır altı veri olup olmadığı kontrol etmek için kullandığımız fonksiyon.
                String readLine = scanner.nextLine(); // Tüm satırı okumak için nextLine() işlevini kullanabilirsiniz.

                // D,35,10,33
                String[] splitLine = readLine.split(","); // okuduğu veriyi tek tek parçala.String instance'ından split kulanıyorum. , gördünmü onu kes ve string arrayine onu kaydet dedim.

                String name = splitLine[0]; //okuduğun ilk kısım isim olsun ve onu name'in içinde tutayım.
                int price = Integer.parseInt(splitLine[1]); //okuduğun string valuesunu int bir değere değiştirmek için böyle bir şey yapıyoruz.
                int storeP = Integer.parseInt(splitLine[2]); //okuduğun string valuesunu int bir değere değiştirmek için böyle bir şey yapıyoruz.
                int onlineP = Integer.parseInt(splitLine[3]); //okuduğun string valuesunu int bir değere değiştirmek için böyle bir şey yapıyoruz.

                ArrayList<Integer> values = new ArrayList<>(); // şimdi ismi kolayca gömebiliyorum de mi ? neden çünkü key zaten string demek ki value için benim o int değerleri tutmam için ne yapamam gerek arraylist oluşturmam gerek ki mape ekliyim.
                values.add(price);
                values.add(storeP); //array listime okuduğum değerleri tek tek ekledim. 0 da price var bla bla.
                values.add(onlineP);

                data.put(name, values); //döndürceğimiz map yapısının içine string kısmı (name) ve arraylist (ıntleri) values ile atıyoruz.
            }

        } catch (FileNotFoundException e) {
            System.out.println("EXCEPTION!!!"); // bi terslik olursa döndürsün diye try catch attık.
        }

        return data; // map yapımızı geri döndürdüm.
    }





    static void calculateYearlySales(HashMap<String, ArrayList<Integer>>  monthlySales) {


        for (Map.Entry<String, ArrayList<Integer>> entry :  monthlySales.entrySet()) {//gene for each yapısı kuruyorum aldım adamdan map yapısını.
            String product = entry.getKey();  // A değerimiz geldi ilk çalıştıgında. // bence buna gerek yok
            int store = 0;
            int online = 0;
            int mstore = 0, monline = 0; //Toplama yapcağım için 0 dan başlattım

            if(year.containsKey(product)){
                           ArrayList<Integer>  data    =  year.get(product);
                           store = data.get(0);
                           online = data.get(1);
            }

                 mstore = entry.getValue().get(0);
                 monline = entry.getValue().get(1);



                 store = store + mstore;
                 online = online + monline;


            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(store);
            arrayList.add(online);

            year.put(product, arrayList);

        } // for each
    }



    static HashMap<String, ArrayList<Integer>> calculateMonthlySales(HashMap<String, ArrayList<Integer>> sales) {
        HashMap<String, ArrayList<Integer>> ans = new HashMap<>(); //döndürceğim map yapısı

        for (Map.Entry<String, ArrayList<Integer>> entry : sales.entrySet()) {
            String month = entry.getKey(); // A kı aldık key olarak.
            ArrayList<Integer> values = entry.getValue(); // A nın arraylistinide elimde tutup işlem yapmak için onuda aldım ve kendi açtıgım arraylistin içine attım.

            int storeSales = values.get(0) * values.get(1); //labda verdiği gibi çarpma işlemleri yapıldı
            int onlineSales = values.get(0) * values.get(2); //labda verdiği gibi çarpma işlemleri yapıldı

            ArrayList<Integer> monthlySales = new ArrayList<>();  // yeni bir yapı kuracağımız için bu sefer arrlistimiz 0 ve 1 den oluşcak.
            monthlySales.add(storeSales); //eklemeleri yaptım.
            monthlySales.add(onlineSales); //eklemeleri yaptım.

            ans.put(month, monthlySales);  //tekrar ismini ve yanınada yeni arraylistini koydum.
        }

        return ans; // ve artık çarpılmış değerler ile yeni map yapımızı döndürmüş oldum.
    }


}


