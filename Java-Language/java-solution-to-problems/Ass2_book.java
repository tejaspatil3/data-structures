import java.util.*;

class publication 
{
   String Title;     //instance variable
   int price,copies;
   static int total = 0;   //static
   Scanner sc = new Scanner(System.in);
   void salecopy()                 //   overriding
   {
       System.out.println("Total sale of publication is" +total); //7500
   }
}
class Book1 extends publication
{  
   String Auther;
   Scanner sc = new Scanner(System.in);
 public void getdata()
   {
       System.out.println("Enter Book details:");
       System.out.println("Enter Title of the Book:");
       Title =sc.nextLine();
       System.out.println("Enter Auther of the Book:");
       Auther = sc.nextLine();
       System.out.println("Enter Price of the Book:");//500
       price = sc.nextInt();
       System.out.println("Enter number of copies:" ); //5
       copies=sc.nextInt();
   }
     void ordercopies() {
       System.out.println("number of copies ordered:"+copies );

   }
   void salecopy()
   {
       int sale = price*copies;
       System.out.println("sale is:"+sale); //2500
       total = total + sale; // 2500=0+2500
   }
}
class Magzine extends publication
{
   int Orderq;
   String Currentiss;

   void getdata1()
   {
       System.out.println("Enter Magzine details:");
       System.out.println("Enter Title of the Magzine:");
       Title =sc.nextLine();
       System.out.println("Enter Current issues:" );
       Currentiss = sc.nextLine();

       System.out.println("Enter Order quantities:" );
       Orderq = sc.nextInt();
       System.out.println("Enter  price of Magzine:" );
       price=sc.nextInt();


   }

   void receiveissue()
   {
       //Scanner sc = new Scanner(System.in);
       System.out.println("Current issue:" +Currentiss);


   }
   void salecopy()
   {
       int sale = price*Orderq;
       System.out.println("sale is:"+sale); //5000
       
       total = total + sale;   //total=2500+5000
   }
}


public class Ass2_book
{

   public static void main(String[] args)
   {
       int choice;
       Scanner sc =new Scanner(System.in);

       do
       {
            System.out.println("Enter the choice :");
           choice=sc.nextInt();

           
           switch(choice)
           {
               case 1:
                   Book1 b = new Book1();
                   b.getdata();
                   b.ordercopies();
                   b.salecopy();    //500*5=2500
                   break;

               case 2:
                   Magzine m = new Magzine();
                   m.getdata1();
                   m.receiveissue();
                   m.salecopy();     //1000*5=5000
                   break;

               case 3:
                   publication p = new publication();
                   p.salecopy();                         //7500
                   break;

                   case 4: System.out.println("exit");
                   break;

               default:System.out.println("wrong choice");
                   return;
           }
       }while(choice!=4);
   }
 {
    
 }

}
