package inheritance;

import javax.sound.sampled.SourceDataLine;

// Java Inheritance (Subclass and Superclass)
// In Java, it is possible to inherit attributes and methods from one class to another. We group the "inheritance concept" into two categories:

// subclass (child) - the class that inherits from another class
// superclass (parent) - the class being inherited from
// To inherit from a class, use the extends keyword.

// In the example below, the Car class (subclass) inherits the attributes and methods from the Vehicle class (superclass):

public class Inheritance {
    public static void main(String[] args) {
        

        /// Inheritance
        /// getting Properties from parent class
        /// with having its own too
        // 4 types - Single Level  parent-child
         //         - multilevel  Parent-child-child
         //         - Hiererchial   same parent for two or more childs
         //         -  Hybrid -- mix of all
        /// Multiple Inheritance in java is done using - Interfaces
        Triangle t1 = new Triangle();
        t1.color = "Yellow";
    }
}
// base Parent
class Shape{
    String color;
    public void area(){
        System.out.println("displays area");
    }
}
// child
class Triangle extends Shape{
    // will not get all 
    // gets based on access modifires
    public void area(int b,int h){
        System.out.println(1/2*b*h);
    }
}

class EquilateralTriangle extends Triangle{
    public void area(int l){
        System.out.println(1/2*l*l);
    }
}

class Circle extends Shape{

}