package core.helper;

import java.util.List;

public class JsonParser {
    public static Object[][] convertListJsonToObject2d(List list) {
        Object[][] object2d;
        // Split words
        String column = list.get(0).toString();
        String[] value = column.split(",");
        // Get column size
        int columnSize = value.length;
        // Get row size
        int rowSize = list.size();
        object2d = new Object[rowSize][columnSize];
        // Add value to 2d array
        // Data block
        for (int i = 0; i < rowSize; i++) {
            String s = list.get(i).toString().replace("{", "").replace("}", "");
            // Attribute
            for (int j = 0; j < columnSize; j++) {
                String[] values = s.split(",");
                object2d[i][j] = values[j].substring(values[j].indexOf('=') + 1);
            }
        }
        return object2d;
    }
}
