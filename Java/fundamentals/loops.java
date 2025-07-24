package fundamentals;
import java.util.Scanner;
public class loops {
    public static void main(String[] args) {
        
        // Check and run
        // For Loop
        // for( initilize, terminate, update){body;}

        System.out.println("For");
        for(int i = 10; i <= 20; i++){
            System.out.println(i);
        }
        System.out.println("next");
        for(int i = 10; i >= 1; i--){
            System.out.println(i);
        }

        //while
        // while(condition){body; updation}
        System.out.println("While");
        int j = 1;
        while(j <= 10){
            System.out.println(j);
            j++;
        }

        // Run atleast once and check
        // do{body; updation}while(condition);
        System.out.println("Do While");
        int k = 10;
        do{
            System.out.println(k);
            k++;
        }while(k < 20);
        System.out.println("next");
        int k1 = 30;
        do{
            System.out.println(k1);
            k1--;
        }while(k1 > 20);


        int number;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Enter a Number");
            number = sc.nextInt();
            System.out.println("Your number");
            System.out.println(number);

        }while(number >= 0);
        System.out.println("End Negative number input");
        sc.close();

    }
}
