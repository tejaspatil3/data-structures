package fundamentals;
import java.util.Scanner;

// Java User Input
// The Scanner class is used to get user input, and it is found in the java.util package.

// To use the Scanner class, create an object of the class and use any of the available methods found in the Scanner class documentation. In our example, we will use the nextLine() method, which is used to read Strings:

public class Scannerclass {
    public static void main(String[] args) {
        // Scanner to input 
        Scanner sc = new Scanner(System.in);  // Create object of Scanner class
                
        
        // Scanner sc = new Scanner(System.in);  // Create object of Scanner class

        //String input
        System.out.println("Enter Name");
        String name = sc.next();
        System.out.println(name);

        //interger
        System.out.println("enter a number");
        int a1 = sc.nextInt();
        System.out.println(a1);
        
        //line
        System.out.println("enter a line");
        String sen = sc.nextLine();
        System.out.println(sen);
    }
}
