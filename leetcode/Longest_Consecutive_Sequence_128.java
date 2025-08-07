import java.util.HashSet;
import java.util.Set;

public class Longest_Consecutive_Sequence_128{
        public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        for (int i : numSet) {
            if (!numSet.contains(i - 1)) {
                int length = 1;
                while (numSet.contains(i + length)) {
                    length += 1;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}

