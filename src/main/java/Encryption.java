import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HASHMAP ma column ap ka key jo represent kary ga.
 * row will tell us ka kitny ma add krna hai.
 */

public class Encryption {


    private HashMap<Integer, ArrayList<Character>> table;
    private ArrayList<Integer> key;
    private String plainText;
    private int columnSizeByKey;
    private int rowSize;

    private HashMap<Integer, Character> characterHashMap;

    public Encryption(ArrayList<Integer> key, String plainText) {
        this.key = key;
        this.plainText = plainText;
        table = new HashMap<>();
        characterHashMap = new HashMap<>();
        columnSizeByKey = key.size();
        rowSize = countCipherMessageLength(plainText);

        setMyMessageIntoMap(characterHashMap, plainText);

    }

    private void setMyMessageIntoMap(HashMap<Integer, Character> characterHashMap, String plainText) {

        int index = 0;
        int space = 0;
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (c == ' ') {
                space++;
            } else {
                characterHashMap.put( (i - space) ,  c );
            }
        }

        for (Map.Entry<Integer, Character> map : characterHashMap.entrySet()){
            System.out.print(map.getKey() +"  " + map.getValue());
        }


    }

    private int countCipherMessageLength(String plainText) {
        int spaces = plainText == null ? 0 : plainText.replaceAll("[^ ]", "").length();
        if (Objects.requireNonNull(plainText).length() != 0) {
            return plainText.length() - spaces;
        }
        return 0;
    }

    public void doEncryption() {

        int count = 0;
        for (int row = 0; row < rowSize; row++) {
            ArrayList<Character> temp = new ArrayList<>();

            for (int col = 0; col < columnSizeByKey; col++) {

                if (count != plainText.length()) {
                    if (plainText.charAt((count)) == ' ') {
                        count++;
                    }
                    char c = plainText.charAt((count));
//                    System.out.println("c = " + c);
                    temp.add(c);
                    table.put(key.get(col), temp);
                    count++;
                } else
                    break;

            }

        }


        for (Map.Entry m : table.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }

    }


    public HashMap<Integer, ArrayList<Character>> getTable() {
        return table;
    }

    public void setTable(HashMap<Integer, ArrayList<Character>> table) {
        this.table = table;
    }

    public ArrayList<Integer> getKey() {
        return key;
    }

    public void setKey(ArrayList<Integer> key) {
        this.key = key;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}
