import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringToInt {
    private Map<String, Integer> stringToIntMap;
    private List<Integer> intList;

    public StringToInt(List<String> strings) {
        stringToIntMap = new HashMap<String, Integer>();
        intList = new ArrayList<Integer>();

        // Assign a unique integer code to each string in the list
        int code = 0;
        for (String s : strings) {
            if (!stringToIntMap.containsKey(s)) {
                stringToIntMap.put(s, code);
                intList.add(code);
                code++;
            } else {
                intList.add(stringToIntMap.get(s));
            }
        }
    }

    public int getCode(String s) {
        // Return the integer code for a given string
        return stringToIntMap.get(s);
    }

    public String getString(int code) {
        // Return the string for a given integer code
        for (Map.Entry<String, Integer> entry : stringToIntMap.entrySet()) {
            if (entry.getValue() == code) {
                return entry.getKey();
            }
        }
        return null;
    }

    public int size() {
        // Return the size of the string list
        return intList.size();
    }

    public List<Integer> getIntList() {
        // Return the integer list
        return intList;
    }
}