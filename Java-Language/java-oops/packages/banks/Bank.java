package oops.packages.banks;

// class Account{
//     public String name;
// }

public class Bank {
    public static void main(String[] args) {
        Account acc1 = new Account();
        acc1.name = "tejas";  //public
        acc1.middleName = "kailas"; // default
        acc1.email = "tejaspatil@email.com"; // protected
        // acc1.age = 23;//error // private
    }
}