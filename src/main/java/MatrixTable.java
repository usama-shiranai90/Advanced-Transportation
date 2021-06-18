import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MatrixTable {

    private LinkedHashMap<Integer, ArrayList<Character>> table;
    private  int columnSizeByKey;
    private int rowSize;

    public MatrixTable(LinkedHashMap<Integer, ArrayList<Character>> table, int columnSizeByKey, int rowSize) {
        this.table = table;
        this.columnSizeByKey = columnSizeByKey;
        this.rowSize = rowSize;
    }

    protected void executeTable(String type, ArrayList<Integer> key) {
        StringBuilder output = new StringBuilder();

        int c = 0;

        if (type.equalsIgnoreCase("first")) {

            output.append("------- 1st Table ------- ").append("\n");
            for (Integer i : key) {
                if (c == 0) {
                    output.append(i).append("\t|");

                } else if (c == columnSizeByKey - 1) {
                    output.append(i);
                } else {
                    output.append(i).append("\t|");
                }
                c++;
            }

            c = 0;
            output.append("\n");

            for (int row = 0; row < rowSize; row++) {

                for (int col = 0; col < columnSizeByKey; col++) {

                    char value = table.get(key.get(col)).get(row);
                    output.append(value).append("\t ");

                }

                output.append("\n");
            }

        }
        else {
            output.append("------- Encrypted Sorted Table ------- ").append("\n");
            for (Integer i : table.keySet()) {
                if (c == 0) {
                    output.append(i).append("\t|");

                } else if (c == columnSizeByKey - 1) {
                    output.append(i);
                } else {
                    output.append(i).append("\t|");
                }
                c++;
            }

            c = 0;
            output.append("\n");

            for (int row = 0; row < rowSize; row++) {

                for (int col = 0; col < columnSizeByKey; col++) {

                    char value = table.get(key.get(col)).get(row);
                    output.append(value).append("\t ");

                }

                output.append("\n");
            }
        }

        System.out.println(output.toString());

    }



}
