package fundamentals;
public class breakContinue {
    public static void main(String[] args) {
        // You have already seen the break statement used in an earlier chapter of this tutorial. It was used to "jump out" of a switch statement.

        // The break statement can also be used to jump out of a loop.
        int i = 0;
        while (true) {
            System.out.println(i);
            i++;
            if(i>5){
                break;
            }
        }

        // The continue statement breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.
        int i1 = 0;
        while (true) {
            if(i1 == 3){
                i1++;
                continue;
            }
            System.out.println(i1);
            i1++;
            if(i1>5){
                break;
            }
        }
    }
}
