package StudentClass;  // Pakage ==  filename to save the Class files into a folder of same name in the classfiles folder

public class StudentClass {
    public static void main(String[] args) {
        Student std1 = new Student();
        std1.name = "Aman Prajapati";
        std1.age = 23;
        Student std2 = new Student();
        // new for memory allocation inside heap for the object
        // Student() -  is constructor function - constructs object of class
        std2.name = "Akash Gupta";
        std2.age = 22;

        std1.printInfo();
    System.out.println("calling 2nd obj");
        std2.printInfo();
        
    }
}
class Student{
    String name;
    int age;
    
    public void printInfo(){
        System.out.println("Student Name is "+this.name);
        System.out.println("Student age is "+this.age);
    }
}