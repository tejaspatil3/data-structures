//patern_15

// ABCDE
// ABCD 
// ABC  
// AB   
// A  

public class pattern_15 {
    public static void main(String[] args) {
        for(int i=5;i>0;i--){
            int start = 65;
            for(int j=0;j<i;j++){
                char ascii = (char) start;
                System.out.print(ascii);
                start+=1;
            }
            System.out.println();
        }
    }
}
