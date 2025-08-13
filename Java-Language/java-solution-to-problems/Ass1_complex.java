import java.util.*;
class Complex_Op
{
    float real,imag;

    Complex_Op()	//Default Constructor
    {
        real=0;
        imag=0;
    }
    Complex_Op(float r,float i)  //Parameterized Constructor
    {
        real = r;
        imag = i;
    }
    public void  AddNumbers(Complex_Op C1,Complex_Op C2)
    {
        float real, imag;

        real = (C1.real + C2.real);

        imag = (C1.imag + C2.imag);

        System.out.println("Addition is:("+ real + ") + (" + imag + ")i" );
    }
    public void  SubNumbers(Complex_Op C1,Complex_Op C2)
    {
        float real, imag;

        real = (C1.real - C2.real);

        imag = (C1.imag - C2.imag);

        System.out.println("Subtraction is:("+ real + ") + (" + imag + ")i" );
    }
    public void  MulNumbers(Complex_Op C1,Complex_Op C2)
    {

        float real, imag;

        real = (C1.real * C2.real)-(C1.imag*C2.imag);

        imag = (C1.real*C2.imag)+(C1.imag*C2.real);

        System.out.println("Multiplication is:("+ real + ") + (" + imag + ")i" );
    }

    public void  DivNumbers(Complex_Op C1,Complex_Op C2)
    {
        float real, imag;

        real = ((C1.real * C2.real)+(C1.imag*C2.imag))/((C2.real * C2.real)+(C2.imag*C2.imag));

        imag = ((C2.real*C1.imag)-(C2.imag*C1.real))/((C2.real * C2.real)+(C2.imag*C2.imag));

        System.out.println("Division is:("+ real + ") + (" + imag + ")i" );
    }


}
public class Ass1_complex {
    //public static void main(String args[])
    public static void main(String []args)

    {

        float real, imag;
        Complex_Op cal = new Complex_Op () ;

        Scanner input =  new Scanner(System.in);


        System.out.println("Enter the first no.: \n");
        System.out.println("enter real part: ");
        real = input.nextInt(); //Real part
        System.out.println("enter imaginary part: ");
        imag = input.nextInt(); //Imaginary Part
        Complex_Op Object1  = new Complex_Op(real, imag);

        System.out.println("Enter the Second Number\\n");
        System.out.println("enter real part: ");
        real = input.nextInt(); //Real Part
        System.out.println("enter imaginary part: ");
        imag = input.nextInt(); //Imaginary Part
        Complex_Op Object2  = new Complex_Op(real, imag);
        int c;
        String ans;
        do
        {
            System.out.println("\n1)addition \n2)subtraction \n3)multiplication \n4)division \n5)exit");
            System.out.println("Enter your choice");
            c=input.nextInt();
            switch(c)
            {
            case 1:
            cal.AddNumbers(Object1 , Object2);
            break;
            case 2:
                cal.SubNumbers(Object1 , Object2);
                break;
            case 3:
                cal.MulNumbers(Object1 , Object2);
                break;
            case 4:
                cal.DivNumbers(Object1 , Object2);
                break;

                case 5:
                    System.out.println("exit\n");
                    break;
            default:
                System.out.println("Wrong choice");
        }

        System.out.println("Do you want to continue");
        ans=input.next();
        }


        while(ans!="Y"||ans!="y");
    }
}
//end of program
