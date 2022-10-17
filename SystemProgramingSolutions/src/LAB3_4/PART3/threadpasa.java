package LAB3_4.PART3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class threadpasa extends Thread {
    private static final Lock lock = new ReentrantLock();


    HashMap<String, ArrayList<Integer>> year;
    String monthname;
    String threadname;

    public threadpasa(String monthname, String threadname, HashMap<String, ArrayList<Integer>> year) {
        this.monthname = monthname;
        this.threadname = threadname;
        this.year = year;
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


    void calculateYearlySales(HashMap<String, ArrayList<Integer>>  monthlySales) {

        lock.lock();
        try {

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

            System.out.println("  ************ THREAD YEAR MAP YAPISININ İÇİ ************.");
            for (Map.Entry<String, ArrayList<Integer>> entry :  year.entrySet()) {//gene for each yapısı kuruyorum aldım adamdan map yapısını.
                System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
            System.out.println("************ THREAD YEAR MAP YAPISININ İÇİ ************");



        }catch (Exception e){
            System.out.println("Patladı kod");

        }finally {
            lock.unlock();
        }


    }



    @Override
    public void run() {

        System.out.println("THREAD : " + threadname +"   " + "Working on  --->  " + monthname);

        HashMap<String, ArrayList<Integer>> readdata = readFiles(monthname);
        HashMap<String, ArrayList<Integer>> calculatedmonthlysales = calculateMonthlySales(readdata);
        calculateYearlySales(calculatedmonthlysales);

    }


}
