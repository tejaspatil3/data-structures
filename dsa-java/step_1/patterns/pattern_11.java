//pattern_11

// 0
// 10
// 010
// 1010
// 01010

public class pattern_11 {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            int start;
            if (i%2 == 0){
                start = 0;
            }
            else start = 1;

            for(int j=0;j<=i;j++){
                System.out.print(start);
                start = 1 - start;
            }
            System.out.println();
        }
    }
}
