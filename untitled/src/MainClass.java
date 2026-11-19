//import java.io.*;
//import java.util.Scanner;
//
//public class MainClass {
//    public static void main1(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        String[] arr = new String[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = scanner.next();
//        }
//        System.out.println("输入的字符串数组为：");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]);
//        }
//    }
//
//    public static void main2(String[] args) {
//        Scanner scanner;
//        try{
//            scanner=new Scanner(new File("demo.txt"));
//            String str=scanner.next();
//            int x=scanner.nextInt();
//            double y=scanner.nextDouble();
//
//            System.out.println(y);
//        } catch(FileNotFoundException e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main3(String[] args) {
//        Scanner scanner;
//        try{
//            scanner=new Scanner(new File("demo.txt"));
//            String str= scanner.nextLine();
//            String[] str1=str.split("");
//
//            String name=str1[0]+""+str1[1];
//            int x=Integer.parseInt(str1[2]);;
//            double y=Double.parseDouble(str1[3]);
//
//            System.out.println(name);
//            System.out.println(x);
//            System.out.println(y);
//            fs.close();
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch(IOException e){}
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        FileInputStream fs = new FileInputStream("demo.text");
//        DataInputStream ds = new DataInputStream(fs);
//    }
//}
