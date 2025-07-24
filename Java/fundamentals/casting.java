package fundamentals;
public class casting {
    public static void main(String[] args){
        // casting
        // changing datatype

        // Implicite casting
        // small sized can be casted to a bigger size 
        //java does automatically
        // without loss of data
        
        float price = 100.00F;
        System.out.println(price);
        double total = 100 + price;
        System.out.println(total);

        // Explicite casting 
        // changing to smaller size datatype
        double c = 758355456;
        float d = (float)c;
        System.out.println(d);

        int fp = (int)324.1322;
        System.out.println(fp);


    }
}
