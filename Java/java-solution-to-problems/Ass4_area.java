import java.util.Scanner;
abstract class Shape
{
    int length,breadth;
    Scanner sc = new Scanner(System.in);
    public void getdata()
    {
        System.out.println("Enter Length : ");
        length = sc.nextInt();
        System.out.println("Enter Breadth : ");
        breadth = sc.nextInt();
    }
    abstract public void printarea();
}
class Rectangle extends Shape
{
    int rec_area;
    public void printarea()
    {
        rec_area = length * breadth;
        System.out.println("Length of rectangle is: "+length );
        System.out.println("Breadth of rectangle is: "+breadth);
        System.out.println("Area of Rectangle = "+rec_area);
    }
}
class Triangle extends Shape
{
    double tri_area;
    public void printarea()
    {
        tri_area = 0.5 * length * breadth;
        System.out.println("Length of triangle is: "+length );
        System.out.println("Breadth of triangle is: "+breadth);
        System.out.println("Area of Triangle = "+tri_area);
    }
}
public class Ass4_area
{
    public static void main(String[] args)
    {
        Shape p;
        Scanner sc = new Scanner(System.in);
        int ch;

        do
        {
            System.out.println("\n1.Area of Rectangle\n2.Area of Triangle\n3.Exit");
                    System.out.println("\nEnter your choice : ");
            ch = sc.nextInt();

            switch(ch)
            {
                case 1 :
                    p = new Rectangle();
                    p.getdata();
                    p.printarea();
                    break;

                case 2 :
                    p = new Triangle();
                    p.getdata();
                    p.printarea();
                    break;

                case 3 :
                    System.out.println("Exit");
                    break;
            }
        }while(ch<3);
    }
}
