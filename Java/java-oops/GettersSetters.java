package GettersSetters;
import bank;

public class GettersSetters {
    public static void main(String[] args) {
        /// accesing private members
        /// //Getters
        /// gives info return of private
        /// 
        
        Account acc1 = new Account();
        acc1.name = "tejas";  //public
        acc1.middleName = "kailas"; // default
        acc1.email = "tejaspatil@email.com"; // protected
        acc1.age = 23;//error // private // 
        acc1.setAge(43); // resolved with getter
        System.out.println(acc1.getAge());
    }
}
class Account{
    public String name;
    String middleName;
    protected String email;
    private int age;

    //geter function
    public String getAge(){
        return this.age;
    }

    //setter
    public void setAge(int age){
        this.age = age;
    }

}

