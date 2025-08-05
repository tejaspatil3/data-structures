//pattern_12

// 1        1
// 12      21
// 123    321
// 1234  4321
// 1234554321


public class pattern_12 {
    public static void main(String[] args) {
        int N= 5;
        int spaces  = 2*(N-1);
        for(int i = 1;i<=N;i++){
            for (int j = 1;j<=i;j++){
                System.out.print(j);
            }
            for (int j = 1;j<=spaces;j++){
                System.out.print(" ");
            }
            for(int j=i;j>=1;j--){
                System.out.print(j);
            }
            System.out.println();
            spaces-=2;
        }

    }
}
