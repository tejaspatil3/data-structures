package staticWord;

public class StaticWord {
    // Static
    /// Accessible to all
    ///  Properties that are common to all( class )
    /// Reated to class instead of objects 
    /// accessible without Objects
    /// 
    /// 
    ///  Can be used before 
    /// properties 
    /// functions
    /// Blocks
    /// nested classes
    /// 
    /// Memory is Allowed only once
    /// 
    public static void main(String[] args) {
        Student.school = "PES";
        Student s1 = new Student();
        s1.name = "Piranbu";
        System.out.println(s1.school);
        
    }
}
class Student{
    String name;
    static String school;
}