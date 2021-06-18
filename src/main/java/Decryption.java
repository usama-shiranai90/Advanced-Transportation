import java.util.*;
import java.util.stream.IntStream;

public class Decryption {

    private ArrayList<Integer> key;
    private final ArrayList<Integer> sortedKey;
    private String message;
    private String cipherText;

    private final LinkedHashMap<Integer, ArrayList<Character>> table;
    private final int columnSizeByKey;
    private final int rowSize;

    private final LinkedHashMap<Integer, Character> characterHashMap;


    public Decryption(ArrayList<Integer> key, String cipherText) {
        this.key = key;
        this.cipherText = cipherText;
        table = new LinkedHashMap<>();
        characterHashMap = new LinkedHashMap<>();
        sortedKey = new ArrayList<>();
        columnSizeByKey = key.size();
        int cipherTextLength = cipherText.length();
        rowSize = (int) Math.ceil(((float) cipherTextLength / (float) columnSizeByKey));

        sortedKey.addAll(key);
        Collections.sort(sortedKey);
        System.out.println("sortedKey = " + sortedKey);
        setMyMessageIntoMap(characterHashMap, cipherText);
        message = "";
    }


    public void doDecryption() {

        int listIndex = 0;

        for (int col = 0; col < columnSizeByKey; col++) {

            ArrayList<Character> characterArrayList = new ArrayList<>();

            for (int row = 0; row < rowSize; row++) {

                int value = listIndex;
                char c = cipherText.charAt(value);

                if (c == '*')
                    characterArrayList.add(' ');
                else
                    characterArrayList.add(row, characterHashMap.get(value));

                listIndex++;
            }
            table.put(sortedKey.get(col), characterArrayList);

        }

        System.out.println("table = " + table);

        for (int row = 0; row < rowSize; row++) {

            for (int col = 0; col < columnSizeByKey; col++) {
                int currentKey = key.get(col);
                message = message.concat(String.valueOf(table.get(currentKey).get(row)));
            }

        }
        ArrayList<Integer> tmp  = new ArrayList<>(table.keySet());

        MatrixTable matrixTable = new MatrixTable(table, columnSizeByKey, rowSize);

        matrixTable.executeTable("final", tmp);
        matrixTable.executeTable("first", key);

        System.out.println("message = " + message);

    }


    private void setMyMessageIntoMap(LinkedHashMap<Integer, Character> characterHashMap, String cipherText) {

        IntStream.range(0, cipherText.length()).forEach(i -> characterHashMap.put((i), cipherText.charAt(i)));

        for (Map.Entry<Integer, Character> map : characterHashMap.entrySet()) {
            System.out.print(map.getKey() + " = " + map.getValue() + "   ");
        }
        System.out.println();

    }






    public ArrayList<Integer> getKey() {
        return key;
    }

    public void setKey(ArrayList<Integer> key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }
}
