// pattern_10

//main
// *    
// ***
// *****
// *******
// *********
// *******
// *****
// ***
// *


//even - two center
// *
// ***
// *****
// *******
// *********
// *********
// *******
// *****
// ***
// *

public class pattern_10 {
    static void even(int N){
        for(int i=0;i<5;i++){
            // for(int j=0;j<5-i-1;j++){
            //     System.out.print(" ");
            // }
            for(int j=0;j<2*i+1;j++){
                System.out.print("*");
            }
            for(int j=0;j<5-i-1;j++){
                System.out.print(" ");
            }
            System.out.println();
            
        }

        for(int i=4;i>=0;i--){
            // for(int j=0;j<5-i-1;j++){
            //     System.out.print(" ");
            // }
            for(int j=0;j<2*i+1;j++){
                System.out.print("*");
            }
            for(int j=0;j<5-i-1;j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }

   public static void main(String[] args){
        for(int i=0;i<5;i++){
            // for(int j=0;j<5-i-1;j++){
            //     System.out.print(" ");
            // }
            for(int j=0;j<2*i+1;j++){
                System.out.print("*");
            }
            for(int j=0;j<5-i-1;j++){
                System.out.print(" ");
            }
            System.out.println();
            
        }

        for(int i=3;i>=0;i--){
            // for(int j=0;j<5-i-1;j++){
            //     System.out.print(" ");
            // }
            for(int j=0;j<2*i+1;j++){
                System.out.print("*");
            }
            for(int j=0;j<5-i-1;j++){
                System.out.print(" ");
            }
            System.out.println();
        }

        even(5);

    }
}
