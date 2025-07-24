//pattern_16

// A
// BB
// CCC
// DDDD
// EEEEE

public class pattern_16 {
    public static void main(String[] args) {
        int start = 65;
        for(int i=0;i<5;i++){
            
            for(int j=0;j<=i;j++){
                char ascii = (char) start;
                System.out.print(ascii);
                
            }
            start+=1;
            System.out.println();
        }
    }
}
