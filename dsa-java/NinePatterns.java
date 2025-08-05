package javaYoutube;

public class NinePatterns {
    public static void main(String[] args) {
        System.out.println("one");
        NinePatterns m1 = new NinePatterns();
        m1.one();
        m1.two();
        m1.three();
        m1.four();
        m1.five();
        m1.six();
        m1.seven();
        m1.eight();
        m1.nine();
    }

    public void one(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                System.out.print(" * ");
            }
            System.out.println();
        }
        System.out.println("two");
    } 
    // output
    // *  *  *  *  * 
    // *  *  *  *  *
    // *  *  *  *  *
    // *  *  *  *  *
    // *  *  *  *  *

    public void two(){
        for(int i = 1; i<=5; i++){
            for(int j = 1; j<=5; j++){
                if(i==1 || j == 1 || i == 5 || j == 5){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println("three");
    }
    // output
    
    public void three(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<i; j++){
                    System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("four");
    }
    //output

    public void four(){
        for(int i = 5; i>0; i--){
            for(int j = 0; j<i; j++){
                    System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("five");
    }
    //output

    public void five(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5-i; j++){
                    System.out.print(" ");
            }
            for(int j = 0; j<i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("six");
    }
    //output

    public void six(){
        for(int i = 1; i<=5; i++){
            for(int j = 1; j<=i; j++){
                    System.out.print(j+ " ");
            }

            System.out.println();
        }
        System.out.println("seven");
    }
    //output

    public void seven(){
        for(int i = 1; i<=5; i++){
            for(int j = 1; j<=5-i+1; j++){
                    System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("eight");
    }
    //output

    //Floyds Triang;e
    int num = 1;
    public void eight(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<i; j++){
                    System.out.print(num + " ");
                    num++;
            }
            System.out.println();
        }
        System.out.println("nine");
    }
    //output

    // 0-1 triangle
    public void nine(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<i; j++){
                int sum = i+j ;
                if(sum % 2 == 0){
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }     
            }
            System.out.println();
        }
    }
    //output
   /*
    one
     *  *  *  *  * 
     *  *  *  *  *
     *  *  *  *  *
     *  *  *  *  *
     *  *  *  *  *
    two
    * * * * *
    *       *
    *       *
    *       *
    * * * * *
    three
    
    *
    * *
    * * *
    * * * *
    four
    * * * * *
    * * * *
    * * *
    * *
    *
    five
    
        *
       **
      ***
     ****
    six
    1 
    1 2
    1 2 3
    1 2 3 4
    1 2 3 4 5
    seven
    1 2 3 4 5
    1 2 3 4
    1 2 3
    1 2
    1
    eight
    
    1
    2 3
    4 5 6
    7 8 9 10
    nine
    
    0
    1 0
    0 1 0
    1 0 1 0
    */
    

    
}
