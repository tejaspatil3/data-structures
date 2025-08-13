// OOPS stands for Object-Oriented Programming System. It is a programming approach that organizes code into objects and classes and makes it more structured and easy to manage. A class is a blueprint that defines properties and behaviors, while an object is an instance of a class representing real-world entities.

package introClass;

// A Class is a user-defined blueprint or prototype from which objects are created. It represents the set of properties or methods that are common to all objects of one type. Using classes, you can create multiple objects with the same behavior instead of writing their code multiple times. This includes classes for objects occurring more than once in your code. In general, class declarations can include these components in order: 

// Modifiers: A class can be public or have default access (Refer to this for details).
// Class name: The class name should begin with the initial letter capitalized by convention.
// Body: The class body is surrounded by braces, { }.

// Java Object
// An Object is a basic unit of Object-Oriented Programming that represents real-life entities. A typical Java program creates many objects, which as you know, interact by invoking methods. The objects are what perform your code, they are the part of your code visible to the viewer/user. An object mainly consists of: 

// State: It is represented by the attributes of an object. It also reflects the properties of an object.
// Behavior: It is represented by the methods of an object. It also reflects the response of an object to other objects.
// Identity: It is a unique name given to an object that enables it to interact with other objects.


public class introclass {

    // Method: A method is a collection of statements that perform some specific task and return the result to the caller. A method can perform some specific task without returning anything. Methods allow us to reuse the code without retyping it, which is why they are considered time savers. In Java, every method must be part of some class, which is different from languages like C, C++, and Python. 
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
