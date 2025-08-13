package fundamentals;
public class operaters {
    public static void main(String[] args) {

        // Variables are storage spaces 
        // each variable need to have unique identifier names 
        // Names can not begin with a number cna use ($,_,letters)
        // data types
        // Premitive data types - lowercase(int,float,long,short,double,)
        // Non- premitive - not included in language (except String) - first letter-Captalized(String,Stack,Array,etc)
        // we programmers make them in programs(stack,Arrays,list,queue,trees,etc)
        
        // Operaters

        // // Arithmatic 

        // Addition + 
        int a = 5;
        int b = 3;
        int c = a + b;
        System.out.println(c);

        // Substraction - 
        int a1 = 5;
        int b1 = 3;
        int c1 = a1 - b1;
        System.out.println(c1);


        // Multiplication *
        int a2 = 5;
        int b2 = 3;
        int c2 = a2 * b2;
        System.out.println(c2);


        // Division /
        float a3 = 5;
        float b3 = 3;
        float c3 = a3 / b3;
        System.out.println(c3);


        // Mod %
        float a4 = 5;
        float b4 = 3;
        float c4 = a4 % b4;
        System.out.println("modulo" + c4);

        // Mod %
        int a6 = 5;
        int b6 = 3;
        int c6 = a6 % b6;
        System.out.println("modulo float" + c6);

        // // Assignment

        String z = "100";
        System.out.println(z);

        // /// Unary
        //plus
        int a5 =5;
        // a5 = a5+ 1;
        System.out.println(++a5);
        // ++a5;  // inc and print
        System.out.println(a5++);
        // a5++;   // print and increase
        System.out.println(a5);

        //minus
        // --a5;  // dec and print
        System.out.println(--a5);
        // a5--;   // print and decrease
        System.out.println(a5--);

        System.out.println(a5);

        // comparision
        // a < b
        // a > b
        // a == b
        // a != b
        // a <= b
        // a >= b
        // a === b

        // Logical

        // And &&

        // Or ||

        // negation (not) !

        // Switch case
        int day=3;

        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("tuesday");
                break;
        
            default:System.out.println("max");
                break;
        }

 


    }
}
