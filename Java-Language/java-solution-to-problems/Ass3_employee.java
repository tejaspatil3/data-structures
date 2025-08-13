import java.util.Scanner;
class Employee
{
    String name,add,mail;
    int id;
    float mob,basic,net;

    Scanner sc=new Scanner(System.in);
    void salary()
    {
        float da,hra,pf,cf,gross;
        System.out.println("Enter Basic Salary : ");
        basic=sc.nextFloat();
        da = basic * 97/100;
        hra = basic * 10/100;
        pf = basic * 12/100;
        cf = basic * 0.1f/100;
        gross = basic+da+hra+pf+cf;
        net=basic+da+hra-pf-cf;

        System.out.println("Emp id : "+id);
        System.out.println("Name : "+name);
        System.out.println("Mailid : "+mail);
        System.out.println("Address : "+add);
        System.out.println("Basic Salary : "+basic);
        System.out.println("Gross Salary : "+gross);
        System.out.println("net Salary : "+net);

        System.out.println("\n");
    }
}
class Programmer extends Employee
{
    Programmer(int id,String name, String mail, String add)
    {
        System.out.println("\nProgrammer Salary Details:");
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.add = add;
    }
}
class TeamLead extends Employee
{
    TeamLead(int id,String name, String mail, String add)
    {
        System.out.println("\nTeamLead Salary Details:");
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.add = add;
    }
}
class APM extends Employee //Assitant Project Manager
{
    APM(int id,String name, String mail, String add)
    {
        System.out.println("\nAPM Salary Details:");
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.add = add;
    }
}
class PM extends Employee //Project Manager
{
    PM(int id,String name, String mail, String add)
    {
        System.out.println("\nPM Salary Details:");
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.add = add;
    }
}
public class Ass3_employee
{
    public static void main(String[] args)
    {
        int ch;
        Scanner sc=new Scanner(System.in);

        System.out.println("1.Progrmmer");
        System.out.println("2.Team Lead");
        System.out.println("3.Assitant Poject Manager");
        System.out.println("4.Project Manager");
        System.out.println("5.exit");

        do
        {

            System.out.println("Enter your choice");
            ch=sc.nextInt();

            switch(ch)
            {
                case 1:
                    Programmer p= new Programmer(123,"RohitMusale","rohitmusale@gmail.com","Nashik");
                    p.salary();
                    break;

                case 2:
                    TeamLead t = new
                            TeamLead(301,"Jaydeep","jaydeep12@gmail.com","Jalgaon");
                    t.salary();
                    break;

                case 3:
                    APM AP=new APM(402,"Kishan","kishan.gmail.com","Dhule");
                    AP.salary();
                    break;

                case 4:
                    PM p1=new
                            PM(205,"Aditya","aditya.gmail.com","Chalisgaon");
                    p1.salary();
                    break;

                case 5: System.out.println("exit");
                    break;

                default:System.out.println("wrong choice");
                    return;
            }
        }while(ch!=5);
    }
}