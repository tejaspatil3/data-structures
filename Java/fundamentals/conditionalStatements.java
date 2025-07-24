package fundamentals;
import java.util.Scanner;

public class conditionalStatements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // if(condition){ body;}
        int a = 10;
        int b = 5;
        if(a<b){
            System.out.println("a is not less than b");
        }

        // if(condition){ body;}else{body;}
        if(a<b){
            System.out.println("a is not less than b");
        }else{
            System.out.println("a is greater than b");
        }
        // shorthand 
        // variable = (condition) ? expressionTrue :  expressionFalse;
        String result = (a == 10) ? "yes" : "no";
        System.out.println(result);

        sc.close();
    }
}
