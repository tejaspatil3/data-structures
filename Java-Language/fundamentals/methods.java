package fundamentals;
public class methods {
    
        // Method: A method is a collection of statements that perform some specific task and return the result to the caller. A method can perform some specific task without returning anything. Methods allow us to reuse the code without retyping it, which is why they are considered time savers. In Java, every method must be part of some class, which is different from languages like C, C++, and Python. 

    //Method name - Start with Lower case

    public static void printjava(){
        System.out.println("Hello java");
    }

    public static void printName(String name){
        System.out.println(name);  
    }

    public static void printAdd(int a,int b){
        System.out.println(a+b);
    }
    public static void main(String[] args) {
        //Methods
        // take input or null 
        // returns output of does process

        printjava();
        printName("Tejas");
        printName("Arav");
        printAdd(10,20);
        
    }
}
