import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        boolean keyDuplication;
        String message;
        ArrayList<Integer> key = new ArrayList<>();

        System.out.print("Insert the message : ");
        message = new Scanner(System.in).nextLine().toUpperCase();

        System.out.print("Enter key ");
        do {
            String tempMessage = new Scanner(System.in).nextLine();

            boolean isAllNumeric = tempMessage.chars().allMatch(Character::isDigit);        // if all characters are Numeric
            boolean hasComma = tempMessage.chars().anyMatch(c -> c == ',');                 // if String has any Comma then return true.
            boolean isAllAlphabet = tempMessage.chars().allMatch(Character::isAlphabetic);  // if all characters are Non-Numeric


            if (isAllNumeric || (hasComma && tempMessage.chars().noneMatch(Character::isAlphabetic))) { //  numeric hai
                System.out.println("Data is Numeric");

                removeCommaAndSplit(tempMessage, key);
                keyDuplication = hasDuplicate(key);

                if (keyDuplication) {
                    System.err.println("Key should be Unique , No duplication should be found !");
                } else
                    break;


            } else if (isAllAlphabet && !hasComma) {  //  agr alphabets hai.
                System.out.println("Data is Non Numeric");
                changeAlphabetIntoKey(tempMessage, key);

                keyDuplication = hasDuplicate(key);

                if (keyDuplication) {
                    System.err.println("Key should be Unique , No duplication should be found !");
                    System.exit(0);
                } else
                    break;

            } else {
                System.err.println("Key should either be Numeric or Alphabet , not both !");

                System.out.print("Enter key again : ");

            }


        } while (true);

        System.out.println("\n---------------------Encryption---------------------\n");
        Encryption encryption = new Encryption(key, message);
        encryption.doEncryption();

        String cipherText = encryption.getCipherText();


        System.out.println("\n---------------------Decryption---------------------\n");
        Decryption decryption = new Decryption(key, cipherText);
        decryption.doDecryption();

    }

    private static void changeAlphabetIntoKey(String temp, ArrayList<Integer> key) {

        for (int index = 0; index < temp.length(); index++) {

            char c = temp.charAt(index);
            key.add((Character.getNumericValue(c) - 10) % (26) + 1);
        }


    }


    private static void removeCommaAndSplit(String temp, ArrayList<Integer> key) {
        String[] value = temp.split("[^\\d]");

        for (String s : value) {
            key.add(Integer.parseInt(s));
        }

        /*        String finalTemp = temp;
        IntStream.iterate(0, i -> i + 1).limit(temp.length()).forEach(val -> {

            int keyPerIndex = Integer.parseInt(String.valueOf(finalTemp.charAt(val)));
            key.add(keyPerIndex);
        });*/

    }

    public static <T> boolean hasDuplicate(Iterable<T> KeyList) {
        Set<T> set = new HashSet<>();
        for (T keyItems : KeyList)
            if (!set.add(keyItems))
                return true;

        return false;
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
