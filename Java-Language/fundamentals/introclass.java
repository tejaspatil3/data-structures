package fundamentals;
public class introclass {
    public static void main(String[] args) {
        System.out.println("introClass");
        // Primative
        //byte - 1 byte {-128 to 127}
        // short - 2
        // int - 4
        // float - 4
         float a2 = 3.14F;
        // long - 8
        long b = 12345678902L;
        // double - 8
        // char - 4 { a, b,A,C}
        // boolean - 1 {true/false}
        //
        //Non-premative
        //have some methods , to be declared by new keyword
        //e.g 

        //Strings 
        // Immutable
        String name = "hello";
        String address = new String("Maximum");

        // length();
        System.out.println(name.length()+" "+ address);

        //charAt();
        String n = new String("Tejas");
        System.out.println(n.charAt(0));

        //replace
        String a = new String("Tejas");
        String a1 = a.replace("a", "m");
        System.out.println(a1+ " "+ a);

        //substring
        String c = new String("Max@3ke");
        System.out.println(c.substring(0,4));



    }
}
