package constructors;

public class constructors {
    public static void main(String[] args) {

         
        // new for memory allocation inside heap for the object
        // Student() -  is constructor function - constructs object of class
        // called only once when obj is created
        // does not return anything


    // A constructor in Java is a special method that is used to initialize objects. The constructor is called when an object of a class is created. It can be used to set initial values for object attributes:

    // Constructors can also take parameters, which is used to initialize attributes.

    // The following example adds an int y parameter to the constructor. Inside the constructor we set x to y (x=y). When we call the constructor, we pass a parameter to the constructor (5), which will set the value of x to 5:

        //// Non Parameterized
        Shop std1 = new Shop();
        std1.name = "Tejas";
        std1.age = 23;
        std1.printInfo();

        //// Parameterized
        /// // Have parameters can be passed 
        /// 
        Bakery b1 = new Bakery("Markas",12);
        b1.printInfo();
        // super b2 = new super("Maras",132);

        // Copy Constructors
        // user defined only
        // copy one object and put in another object

        Super s1 = new Super();
        s1.name = "Rajgaru Market";
        s1.Number = 44; 
        s1.printInfo();
        Super s2 = new Super(s1);
        s2.printInfo();   
        
        
        // need not to write destructor 
        // auto garbage collector
        // Step1 Marks all variables with new as garbage 
        // deallocate all which cannot be reached with refrence pointer
        // Due to auto garbage collector java is slower than cpp 
        // people*


        


    }
}
class Shop{
    String name;
    int age;

    //// Non Parameterized
    /// If Written then it is called 
    /// if not writted then java makes on its one 
    Shop(){
        System.out.println("this is from the constructor");
    }

    public void printInfo(){
        System.out.println(this.name);
        System.out.println(this.age);
    }
}

class Bakery{
    String name;
    int cakeNumber;
    
    //// Parameterized
    Bakery(String name,int cakeNumber){
        this.name = name;
        this.cakeNumber = cakeNumber;
        // this.object = parameter passsed
    }

    public void printInfo(){
        System.out.println(name);
        System.out.println(cakeNumber);
    }
}

class Super{
    String name;
    int Number;
    
    //// Copy
    Super(Super s2){
        this.name = s2.name;
        this.Number = s2.Number;
        // this.object = parameter passsed
    }
    Super(){
        // Need to define default if other constructor is created
    }

    public void printInfo(){
        System.out.println(name);
        System.out.println(Number);
    }
}