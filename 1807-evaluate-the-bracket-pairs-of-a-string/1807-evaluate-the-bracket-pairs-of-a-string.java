import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // 1. Map knowledge pairs for O(1) retrieval
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean insideBracket = false;

        // 2. Parse string character by character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                insideBracket = true;
            } else if (c == ')') {
                insideBracket = false;
                String key = currentKey.toString();
                result.append(map.getOrDefault(key, "?"));
                currentKey.setLength(0); // Clear key builder
            } else if (insideBracket) {
                currentKey.append(c);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}