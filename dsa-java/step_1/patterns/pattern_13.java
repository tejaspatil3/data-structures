//pattern_13

// 1
// 23
// 456
// 78910

public class pattern_13 {
    public static void main(String[] args) {

        int start=1;
        for(int i = 1;i<5;i++){
            for(int j =0;j<i;j++){
                System.out.print(start);
                start++;
            }
            System.out.println();
        }
    }
}
