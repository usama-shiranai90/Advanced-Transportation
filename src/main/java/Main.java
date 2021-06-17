import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

/*       do {
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


        String message;
        ArrayList<Integer> key = new ArrayList<>();

        System.out.print("Insert the message : ");
        message = new Scanner(System.in).nextLine().toUpperCase();

        do {
            System.out.print("Enter key ");
            String temp = new Scanner(System.in).nextLine();

            boolean flag = temp.chars().allMatch(Character::isDigit);
            System.out.println(flag);
            if (flag){ //  numeric hai

                if (temp.contains(",")){
                    removeCommaAndSplit(temp , key);
                }
                else {
                 removeCommaAndSplit(temp ,key);
                }


            }
            else if (temp.chars().allMatch(Character::isAlphabetic)){  //  agr alphabets hai.

            }

            else {
                System.err.println("Key should either be Numeric or Alphabet , not both !");
                System.exit(0);
            }



            break;
        } while (true);


        Encryption encryption = new Encryption(key, message);
        encryption.doEncryption();

        String cipherText = encryption.getCipherText();


        System.out.println("\n\n---------------------Decryption---------------------\n");

        Decryption decryption = new Decryption(key, cipherText);
        decryption.doDecryption();

    }

    private static void removeCommaAndSplit(String temp, ArrayList<Integer> key) {
        temp = temp.replaceAll("[^\\d]", "");

        String finalTemp = temp;
        IntStream.iterate(0, i -> i+1).limit(temp.length()).forEach(value -> {

            int keyPerIndex = Integer.parseInt(String.valueOf(finalTemp.charAt(value)));
            key.add(keyPerIndex);
        });

        System.out.println(key);

    }


    private static void sampleData(String message, ArrayList<Integer> key) {
        message = "Quick brown fox jumps";
        key.add(4);
        key.add(2);
        key.add(5);
        key.add(3);
        key.add(1);
    }


}
