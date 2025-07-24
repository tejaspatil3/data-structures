import java.util.Scanner;

interface  Vehicle
{
	/* public static final int gear = 1;
	public static final int brake = 1;
	public static final int speed = 1; */
	
	abstract void gearChange(int a);
	abstract void applyBreak(int a);
	abstract void speedUp(int a);
	Scanner sc = new Scanner(System.in);
	
}

class Bicycle implements Vehicle
{
	public void gearChange(int a) 
	{
		System.out.println("gear number : "+a);

	}
	
	public void applyBreak(int a) {
		if(a==1)
		{
			System.out.println(" Break Applied !!");
		}
		
		else
		{
			System.out.println(" Break Not Applied !!"); 
		}
		
	}
	
	public void speedUp(int a)
	{
		System.out.println("Speed is : "+a);

		
	}
}

class Cycle implements Vehicle{
	
	
	
	public void gearChange(int a) {
		System.out.println("Gear Number is : "+ a);

		
	}

	
	public void applyBreak(int a) {
		if(a==1)
		{
			System.out.println(" Break Applied !!");
		}
		
		else
		{
			System.out.println(" Break Not Applied !!"); 
		}
		
		
	}

	
	public void speedUp(int a) {
		System.out.println("Speed is : "+ a);

		
	}
	
}


class Car implements Vehicle{
	
	
	
	public void gearChange(int a) {
		
		System.out.println("Gear Number is : "+ a);
		

	}

	
	public void applyBreak(int a) {
		
		if(a==1)
		{
			System.out.println(" Break Applied !!");
		}
		
		else
		{
			System.out.println(" Break Not Applied !!"); 
		}
		
		
	}

	
	public void speedUp(int a) {
		
		System.out.println("Speed is : "+ a);
		

	}
	
}


public class Ass5_interface {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int choice;
		
		 System.out.println("STATUS of Vehicle\n1.Bicycle\n2.Cycle\n3.Car");
		do
		{
		    System.out.println("Enter the choice :");
		    choice=sc.nextInt();

		switch(choice)
		{
		    case 1:
		    	Bicycle b=new Bicycle();
		b.applyBreak(1);
		b.speedUp(20);
		b.gearChange(1);
		break;
		    case 2:
		
		
		Cycle c=new Cycle();
		c.applyBreak(1);
		c.speedUp(10);
		c.gearChange(2);
		break;
		    case 3:		
		Car ca=new Car();
		ca.applyBreak(0);
		ca.speedUp(40);
		ca.gearChange(3);
		break;

		 case 4:System.out.println("exit");
	break;
 default:System.out.println("wrong choice");
		    return;
	}
		}while(choice!=5);	
}
}
