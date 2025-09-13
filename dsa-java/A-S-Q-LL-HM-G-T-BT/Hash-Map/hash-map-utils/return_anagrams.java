import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class return_anagrams {
    public static void main(String[] args){
        System.out.println("Hello Word");

        String[] words = {"ate","menu", "eat", "listen", "tan", "silent", "bat", "nat", "tea","nume"};


        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String word : words) {
            char[] charArray = word.toCharArray();
            // System.out.println(charArray);
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            if (!anagramMap.containsKey(sortedWord)) {
                anagramMap.put(sortedWord, new ArrayList<>());
            }
            anagramMap.get(sortedWord).add(word);
        }

        List<List<String>> anagramGroups = new ArrayList<>();
        for (List<String> group : anagramMap.values()) {
            if (group.size() > 1) {
                anagramGroups.add(group);
            }
        }

        System.out.println("Input: " + Arrays.toString(words));
        System.out.println("Anagrams Found: " + anagramGroups);
        
    }
}