package fundamentals;
import java.util.Arrays;


//Run  javac -d classfiles arrays.java
// java -cp classfiles fundamentals.arrays 


public class arrays{
    public static void main(String[] args){
        System.out.println("hello world");
        System.out.println(" ");

        //Arrays list
        int[] studMarks = new int[3];
        studMarks[0] = 28;
        studMarks[1] = 29;
        studMarks[2] = 26;

        System.out.println(studMarks);   //will print a jargan of hash code and characters
        System.out.println(studMarks[2]);

        //Variable initialized as null
        String a = null;
        System.out.println(a+ "   hello");

        //length
        System.out.println(studMarks.length);

        //sort
        System.out.println(studMarks[0]);
        Arrays.sort(studMarks);
        System.out.println(studMarks[0]);

        //
        int[] roll = {2,3,4,1,3};
        System.out.println(roll);

        //
    }
}
