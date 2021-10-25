
import java.io.*;


public class Solution {
    static String input;
    static String file1;
    static String file2;
    static int separator;
    static Long[] shop_id = new Long[10 ^ 6];
    static String[] shop_name = new String[10 ^ 6];
    static int counter = 0;
    static Long[] order_id = new Long[10 ^ 6];
    static Long[] shop_idB = new Long[10 ^ 6];
    static Long[] cost = new Long[10 ^ 6];
    static String fullname1;
    static String fullname2;
    static String path="C:\\Code\\YandexInnerJoin\\";
    //static String fullname1="C:\\Code\\YandexInnerJoin\\market_1.csv";
    //static String fullname2="C:\\Code\\YandexInnerJoin\\billing_1.csv";

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            input = reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        separator = input.indexOf(" ");
        file1= input.split(" ")[0];
        file2= input.split(" ")[1];

        fullname1=path+file1;
        fullname2=path+file2;

        if(file1.charAt(0)=='m'){
            readMarket();
            counter = 0;
            readBilling();
        } else if (file1.charAt(0)=='b') {
            readBilling();
            counter = 0;
            readMarket();
        }


        System.out.println("order_id,shop_name,shop_id,cost");

        for(int i=0;i<shop_id.length;i++){
            if (shop_id[i]!=null){
                Long marketShop1 = shop_id[i];
                for(int j=0;j<shop_idB.length;j++){
                    if (shop_idB[j]!=null && shop_id[i].equals(shop_idB[j]) ){
                        if ("null".equals(shop_name[i])){
                            System.out.println(order_id[j]+","+" "+","+shop_idB[j]+","+cost[j]);
                        } else
                        System.out.println(order_id[j]+","+shop_name[i]+","+shop_idB[j]+","+cost[j]);
                    }
                }

            } else{
                continue;
            }

        }

    }

    public static void readMarket()  {

        BufferedReader readerFile1 = null;
        try {
            readerFile1 = new BufferedReader(new InputStreamReader(new FileInputStream(fullname1)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //readerFile1=new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
        //readerFile1=new BufferedReader(new InputStreamReader(new FileInputStream(fullname1)));
        try {
            String header = readerFile1.readLine();

            if (header.endsWith("name")) {
                while (readerFile1.ready()) {
                    String buffer = readerFile1.readLine();

                    int comma = buffer.indexOf(",");
                    try {
                        shop_id[counter] = Long.parseLong(buffer.substring(0, comma));
                    } catch (NumberFormatException e) {
                        counter++;
                        continue;
                    }
                    //shop_id[counter] = Long.parseLong(buffer.substring(0, comma));
                    shop_name[counter] = buffer.substring(comma + 1, buffer.length());
                    counter++;
                }
            }
            if (header.endsWith("id")) {
                while (readerFile1.ready()) {
                    String buffer = readerFile1.readLine();
                    int comma = buffer.indexOf(",");

                    shop_name[counter] = buffer.substring(0, comma);
                    try {
                        shop_id[counter] = Long.parseLong(buffer.substring(comma + 1, buffer.length()));
                    } catch (NumberFormatException e) {
                        counter++;
                        continue;
                    }
                    counter++;
                }
            }

            //for (int i = 0; i < shop_id.length; i++) {
            //    System.out.println(shop_id[i]);
            //}

        } catch (IOException e) {
        e.printStackTrace();
        } finally {
            try {
                readerFile1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void readBilling()  {

        BufferedReader readerFile2=null;

        try {
            readerFile2 = new BufferedReader(new InputStreamReader(new FileInputStream(fullname2)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //readerFile2=new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            //readerFile2=new BufferedReader(new InputStreamReader(new FileInputStream(fullname2)));
            String header = readerFile2.readLine();

            int order = header.indexOf("order");
            int shop = header.indexOf("shop");
            int costC = header.indexOf("cost");

            while (readerFile2.ready()) {
                String buffer = readerFile2.readLine();
                int comma1 = buffer.indexOf(",");
                int comma2 = buffer.indexOf(",", comma1 + 1);

                try {
                    order_id[counter] = Long.parseLong(buffer.substring(0, comma1));
                    shop_idB[counter] = Long.parseLong(buffer.substring(comma1+1, comma2));
                    cost[counter] = Long.parseLong(buffer.substring(comma2+1));
                } catch (NumberFormatException e) {
                    counter++;
                    continue;
                }
                counter++;
            }

            /*System.out.println("order_id:");
            for (int i = 0; i < order_id.length; i++) {
                if(order_id[i]!=null){
                    System.out.println(order_id[i]);
                }
            }*/


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readerFile2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}
