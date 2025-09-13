
/*
================================================================================
|| THEORY: UNDERSTANDING CONSTRUCTORS IN JAVA
================================================================================

1.  WHAT IS A CONSTRUCTOR?
    - A constructor is a special method that is automatically called when an object of a class is created.
    - Its primary purpose is to **initialize the state** of the newly created object. Think of it as setting up the object's initial properties.
    - Analogy: If a class is a blueprint for a house 🏠, the constructor is the process of building the foundation and walls, ensuring the house is in a valid, usable state from the very beginning.

2.  RULES FOR CREATING A CONSTRUCTOR
    - A constructor's name **must be exactly the same** as the class name.
    - A constructor **must not have an explicit return type**, not even `void`. The compiler implicitly knows it returns an instance of the class.

3.  TYPES OF CONSTRUCTORS

    a) DEFAULT CONSTRUCTOR
    - If you do **not** define any constructor in your class, the Java compiler will automatically provide one for you.
    - This is called the **default constructor**. It takes no arguments and has an empty body.
    - It initializes instance variables to their default values (e.g., `0` for numbers, `false` for booleans, `null` for objects).

    b) NO-ARGUMENT CONSTRUCTOR
    - This is a constructor that you write yourself, but it takes no parameters.
    - You can use it to set specific default values for your object's fields.
    - **Important**: Once you define *any* constructor in your class (like a parameterized one), the compiler **will not** provide the default constructor anymore. If you still need a no-argument constructor, you must write one yourself.

    c) PARAMETERIZED CONSTRUCTOR
    - This is a constructor that accepts one or more parameters.
    - This is the most common type of constructor because it allows you to create an object and initialize its fields with specific values right away.

4.  CONSTRUCTOR OVERLOADING
    - Just like methods, constructors can be **overloaded**.
    - This means a class can have multiple constructors, as long as they have different parameter lists (i.e., a different number of parameters or different types of parameters).
    - This provides flexible ways to create objects of your class.

5.  THE `this` KEYWORD IN CONSTRUCTORS
    - The `this` keyword has two main uses inside a constructor:

    a) To Disambiguate Instance Variables from Parameters:
    - If a parameter has the same name as an instance variable, you can use `this.variableName` to refer to the instance variable.
    - Example: `this.name = name;` // Assigns the parameter 'name' to the instance variable 'name'.

    b) To Call Another Constructor (Constructor Chaining):
    - You can call one constructor from another within the same class using `this(...)`.
    - This is useful for reducing code duplication.
    - Rule: The `this(...)` call **must be the very first statement** in the constructor.
*/

import java.time.Year;

// This class will contain our main method to run the examples.
public class ConstructorConcept {
    public static void main(String[] args) {

        System.out.println("--- 1. Using the No-Argument Constructor ---");
        // Creating a Car object using the no-argument constructor.
        // It gets initialized with default values we defined.
        Car defaultCar = new Car();
        defaultCar.displayInfo();

        System.out.println("\n--- 2. Using the Parameterized Constructor ---");
        // Creating a Car object with specific initial values.
        Car myCar = new Car("Toyota", "Camry", 2024);
        myCar.displayInfo();

        System.out.println("\n--- 3. Using an Overloaded Constructor ---");
        // Creating a Car object using the overloaded constructor that only takes make and model.
        // It automatically sets the year to the current year by calling the other constructor.
        Car newCar = new Car("Honda", "Civic");
        newCar.displayInfo();
    }
}


/**
 * An example class `Car` to demonstrate how constructors work.
 */
class Car {
    // Instance variables (properties of a Car)
    String make;
    String model;
    int year;

    // 1. No-Argument Constructor
    // This is called when you create an object like: new Car();
    public Car() {
        System.out.println("-> No-Argument Constructor Called");
        // We can initialize fields with default values.
        this.make = "Unknown";
        this.model = "Unknown";
        this.year = 0;
    }

    // 2. Parameterized Constructor
    // This is called when you create an object like: new Car("Toyota", "Camry", 2024);
    public Car(String make, String model, int year) {
        System.out.println("-> Parameterized Constructor (String, String, int) Called");
        // Using 'this' to distinguish instance variables from parameters
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // 3. Overloaded Parameterized Constructor (Constructor Chaining)
    // This is called when you create an object like: new Car("Honda", "Civic");
    public Car(String make, String model) {
        // Calling another constructor using this(). MUST be the first line.
        // This avoids duplicating code by reusing the more detailed constructor.
        this(make, model, Year.now().getValue());
        System.out.println("-> Overloaded Constructor (String, String) Called");
    }

    // A helper method to display the car's state
    public void displayInfo() {
        System.out.println("   Car Details: " + year + " " + make + " " + model);
    }
}