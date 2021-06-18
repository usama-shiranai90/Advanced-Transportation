import java.util.*;

/**
 * HASHMAP ma column ap ka key jo represent kary ga.
 * row will tell us ka kitny ma add krna hai.
 */

public class Encryption {


    private HashMap<Integer, ArrayList<Character>> table;
    private ArrayList<Integer> key;
    private String plainText;
    private final int columnSizeByKey; //  size of Key , for column
    private final int rowSize;    //  size of row.
    private final int messageSize; // message to pass length.

    private final HashMap<Integer, Character> characterHashMap;
    private String cipherText;

    public Encryption(ArrayList<Integer> key, String plainText) {
        this.key = key;
        this.plainText = plainText;
        cipherText = "";
        table = new HashMap<>();
        characterHashMap = new HashMap<>();
        columnSizeByKey = key.size();
        messageSize = countCipherMessageLength(plainText);
        rowSize = (int) Math.ceil(((float) messageSize / (float) columnSizeByKey));

        setMyMessageIntoMap(characterHashMap, plainText);

    }

    private void setMyMessageIntoMap(HashMap<Integer, Character> characterHashMap, String plainText) {

        int space = 0;
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (c == ' ') {
                space++;
            } else {
                characterHashMap.put((i - space), c);
            }
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

        int count = columnSizeByKey - 1;
        int listIndex = 0;



        for (int col = 0; col < columnSizeByKey; col++) {

            ArrayList<Character> arrayList = new ArrayList<>();
            for (int row = 0; row < rowSize; row++) {

                int value = col + row + ((count) * row);

                if (value > messageSize - 1)
                    arrayList.add('*');
                else
                    arrayList.add(characterHashMap.get(value));
            }
            table.put(key.get(col), arrayList);

        }

        LinkedHashMap<Integer , ArrayList<Character>> tempTable = new LinkedHashMap<>(table);

        MatrixTable matrixTable = new MatrixTable( tempTable, columnSizeByKey, rowSize);

        matrixTable.executeTable("first" ,key);
        ArrayList<Integer> tmp  = new ArrayList<>(table.keySet());
        matrixTable.executeTable("final" , tmp);

        createCipher();

        System.out.println("cipherText = " + cipherText);

    }

    private void createCipher() {

        for (Map.Entry<Integer, ArrayList<Character>> entry : table.entrySet()) {
            for (Character c : entry.getValue() ){
                cipherText = cipherText.concat(String.valueOf(c)) ;
            }

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

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

/**  Use in the beginning of doEncryption
 *
 *   for (int row = 0; row < columnSizeByKey; row++) {
 *             ArrayList<Character> arrayList = new ArrayList<>();
 *             for (int j = 0; j < rowSize; j++) {
 *                 arrayList.add('0');
 *             }
 *             table.put(key.get(row), arrayList);
 *
 *         }
 * */

}
