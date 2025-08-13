package fundamentals;
import java.util.Scanner;;
public class minPro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int random = (int)(Math.random()*100);

        System.out.println("Guess the Number");
        System.out.println("to exit enter -ve number");
        
        // System.out.println(random);
        int userInput;
        do{
            System.out.print("Enter Here -- ");
            userInput = sc.nextInt();
            if(userInput == random){
                System.out.println("Woooh .... correct guess");
                break;
            }
            else if(userInput > random){
                System.out.println("your number is too big");
            }else{
                System.out.println("your number is too small");
            }
        }while(userInput >= 0);
        System.out.println("the number was "+random);
        sc.close();


    }
}
