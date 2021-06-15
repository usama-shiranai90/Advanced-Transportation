import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String message ="";
        int keySize  =0;
        ArrayList<Integer> key = new ArrayList<>() ;

/*        System.out.print("Insert the message : ");
        message = new Scanner(System.in).nextLine().toUpperCase();

       do {
            System.out.print("Enter Key Size (must be numeric) :");
            keySize = new Scanner(System.in).nextInt();

            System.out.println("Enter Key :");
            for (int i = 0; i < keySize; i++) {
                int k = new Scanner(System.in).nextInt();
                if (key.contains(k)){
                    System.out.println("Key should be unique. (No Repeating ) ");
                    break;
                }
                else
                key.add(i, k);

            }
            break;
        }while (true);*/

        message = "Quick brown fox jumps";
        key.add(4);
        key.add(2);
        key.add(5);
        key.add(3);
        key.add(1);

        Encryption encryption = new Encryption( key , message);
        encryption.doEncryption();

    }

}
