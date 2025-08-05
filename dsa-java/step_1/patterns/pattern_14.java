///pattern_14
/// 
// A
// AB
// ABC
// ABCD
// ABCDE


public class pattern_14 {
    public static void main(String[] args) {
        
        int start = 65;

        for(int i=0;i<5;i++){
            start = 65;
            for(int j=0;j<=i;j++){
                
                char ascii = (char) start;
                System.out.print(ascii);
                start+=1;
            }
            System.out.println();
        } 

        // for(int i =1;i<5;i++){
        //     for(int j=0;j<i;j++){
        //         char ascii = (char) start;
        //         System.out.print(ascii);
        //         start+=1;
        //     }
        //     System.out.println();
        // }
    }
    
}
