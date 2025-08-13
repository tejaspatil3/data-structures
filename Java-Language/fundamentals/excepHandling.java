package fundamentals;
// import java.util.Scanner;

public class excepHandling {
    public static void main(String[] args) {
        // Exception Handling
        // try catch block
        // try{
        //    Statements where exception can occur
        //  }
        // catch(Exception exception){
        //      do things after exception
        //  }

        int[] marks = {91,92,93};
        // System.out.println(marks[5]);
        try{
            System.out.println(marks[5]);
        }catch(Exception exp){
            // do
            System.out.println(exp);
        }
        System.out.println("--by teja");
    }
}
