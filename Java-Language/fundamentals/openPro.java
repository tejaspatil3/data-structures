package fundamentals;
import java.util.Scanner; 
public class openPro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //pen-10 notebook-40

        System.out.println("Enter Amount of cash");
        int cash = sc.nextInt();
        sc.close();  //Closing Scanner Obj
        if (cash<10) {
            System.out.println("Cannot buy anything\nNeed more cash");            
        }
        else if(cash >= 10 && cash < 40){
            System.out.println("You can buy one thing");
        }
        else{
            System.out.println("can get both");
        }
        
    }
}
