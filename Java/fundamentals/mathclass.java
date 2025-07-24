package fundamentals;
public class mathclass {
        //javac -d classfiles mathclass.java
        //java -cp classfiles mathclass 
    public static void main(String[] args) {
        //javac -d classfiles mathclass.java
        //java -cp classfiles mathclass 

        //Math class
        
        //max(a,b)
        System.out.println(Math.max(5,6));

        //random()
        //returns random values between (0,1) excluding 1
        // returns long
        System.out.println(Math.random());
        System.out.println((int)Math.random());
        System.out.println((int)(Math.random()*100));
        
    }
}
