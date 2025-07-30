// import java.util.Arrays;
class Smallest_Subarrays_With_Maximum_Bitwise_OR{

    static int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int[] last = new int[31];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k < 31; k++) {
                if (((nums[i] >> k) & 1) == 1) {
                    last[k] = i;
                }
            }

            int farthestEndpoint = i;
            for (int k = 0; k < 31; k++) {
                farthestEndpoint = Math.max(farthestEndpoint, last[k]);
            }

            answer[i] = farthestEndpoint - i + 1;
        }

        return answer;
    }
        

    public static void main(String[] args) {
        // int[] nums = {1,0,2,1,3};
        int[] nums = {1,2};

        nums = (smallestSubarrays(nums));
        for(int i =0;i<nums.length;i++){
            System.err.print(nums[i] + " ");
        }
            
        


    }
}
     