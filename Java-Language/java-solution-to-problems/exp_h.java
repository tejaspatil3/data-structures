import java.util.*;
class Exception
{
    Scanner sc = new Scanner(System.in);
    void Arithmetic()
    {
        System.out.println("\n**Perform Division**");
        System.out.println("Enter First Number : ");
        int num1 = sc.nextInt();
        System.out.println("Enter Second Number : ");
        int num2 = sc.nextInt();
        try {
            int ans = num1/num2;
            System.out.println("The quotient is : "+ans);
        }
        catch(ArithmeticException e)
        {

            System.out.println("Divisible by 0 is Invalid "+e);
        }
    }
    void NumberFormat()
    {
        System.out.println("\n**Perform Division**");
        System.out.println("Enter First Number : ");
        String num1 = sc.next();
        System.out.println("Enter Second Number : ");
        String num2 = sc.next();
        try {
            System.out.println("The First num is : "+Integer.parseInt(num1));
                    System.out.println("The Second num is : "+Integer.parseInt(num2));
        }
        catch(NumberFormatException e)
        {
            System.out.println("Wrong input format, Please enter an Integer");
        }
    }
    void ArrayIndex()
    {
        System.out.println("\nEnter size of array : ");
        int n = sc.nextInt();
        int[] array = new int[n];
        System.out.println("Enter elements of the array : ");
        for(int i=0;i<n;i++)
        {
            array[i] = sc.nextInt();
        }
        try {
            System.out.println("Enter index of element you want to access : ");
            int index = sc.nextInt();
            System.out.println("Element present at index "+index+" is "+array[index]);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Desired index can not be accessed : "+e);
        }
    }
}
public class exp_h
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int ch;
        Exception ex = new Exception();
        do
        {
            System.out.println("\n1.Arithmetic Exception\n2.Number Format Exception \n3.Array Index Exception");
                    System.out.println("Enter your choice : ");
            ch = sc.nextInt();
            switch(ch)
            {

                case 1:
                    ex.Arithmetic();
                    break;
                case 2:
                    ex.NumberFormat();
                    break;
                case 3:
                    ex.ArrayIndex();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }while(ch<4);
    }

}

