package Abstraction;

// // Abstract Classes and Methods
// Data abstraction is the process of hiding certain details and showing only essential information to the user.
// Abstraction can be achieved with either abstract classes or interfaces (which you will learn more about in the next chapter).

// The abstract keyword is a non-access modifier, used for classes and methods:

// Abstract class: is a restricted class that cannot be used to create objects (to access it, it must be inherited from another class).

// Abstract method: can only be used in an abstract class, and it does not have a body. The body is provided by the subclass (inherited from).
// An abstract class can have both abstract and regular methods:



public class Abstraction {
    public static void main(String[] args) {

        ///   Abstraction using Abstract keyword

        // some useless data isnot hidden
        // CLASS blue print is parent class
        // abstract keyword is used for abstraction
        ///  declared with abstract keyword
    Horse h1 = new Horse();
    h1.walk();

    
    // Cannot instantiate the type Animal
    // Animal a1 = new Animal();
    // a1.walk();

    // can have abstract and non abstract methods
        h1.eats();

    // Can have constructors in static methods
        // Constructor chaining
        // constructor of base class is first run on making object of child class(in inheritance)
    
    // final methods not letting to change body 


    ///  Interface Pure Abstraction
    /// // all useless info is hidden
        Cock c1 = new Cock();
    }

}
abstract class Animal {
    abstract void walk();
    public void eats(){
        System.out.println("Animal Eats");
    }
    Animal(){
        System.out.println("creating new animal");
    }
}
class Horse extends Animal {
    public void walk(){
        System.out.println("Walks on four legs");
    }
    Horse(){
        System.out.println("created Horse");
    }
}
class Cock extends Animal {
    public void walk(){
        System.out.println("Walks on two legs");
    }
}

