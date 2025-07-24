package ClassesObjects;

public class ClassesObjects{
    public static void main(String[] args) {
        // Class - blueprint of the object
        // Info about objects
        // className - Start with upper case

        // Object - instance of class

        // Creating object
        // className objectName = new className();
        Pen pen1 = new Pen();  // Using the corrected class name

        pen1.color = "blue";
        pen1.type = "gel";

        // Calling method on class
        pen1.write();
        pen1.printColor();
        
        Pen pen2 = new Pen();
        pen2.color = "black";
        pen2.type = "ballpoint";
        pen2.write();
        pen2.printColor();
    }
}


class Pen {  // Class name should start with a capital letter
    String color;
    String type;

    public void write() {
        System.out.println("Writing something");
        System.out.println(color + " " + type + " pen");
    }
    public void printColor(){
        System.out.println("Pen color "+this.color);
        // this keyword -- "this" a reference variable that refers to the current object
    }
}
