package polymorphism;


// Polymorphism means "many forms", and it occurs when we have many classes that are related to each other by inheritance.

// Like we specified in the previous chapter; Inheritance lets us inherit attributes and methods from another class. Polymorphism uses those methods to perform different tasks. This allows us to perform a single action in different ways.

// For example, think of a superclass called Animal that has a method called animalSound(). Subclasses of Animals could be Pigs, Cats, Dogs, Birds - And they also have their own implementation of an animal sound (the pig oinks, and the cat meows, etc.):

public class Polymorphism {
    public static void main(String[] args) {
        
        //Polymorphism --  many forms 
        /// same works in different ways
        /// 
        /// Compile Time polymorphism (function OverLoading)
        /// Different functions With Same name
        /// // Differentiating factors
        /// Return type 
        /// number of parameters
        /// type of parameters
        
        
        Student1 s1 = new Student1();
        s1.name = "marco";
        s1.age = 41;
        s1.printInfo(s1.name);
        s1.printInfo(s1.age);
        s1.printInfo(s1.name,s1.age);

        // Run time Polymorphism
        // takes palce at execution of program
    }
}
class Student1{
    String name;
    int age;

    //Based on the input it will decide which to execute
    public void printInfo(String name){
        System.out.println(name);
    }
    public void printInfo(int age){
       System.out.println(age); 
    }
    public void printInfo(String name,int age){
        System.out.print(name);
        System.out.print(age);
    }
}