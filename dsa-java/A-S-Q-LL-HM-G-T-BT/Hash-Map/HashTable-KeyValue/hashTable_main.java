// designing a hash

// 1 . Hash Function hash(key) - reduces collision - uniform key distribution over table
// 2 . Hash Table  (A table with limited memory and key value pairs)
// 3 . collision handling

// Handling collision - 
// open hashing - no fixed size of hashTable - Seperate Chaining
// Closed hashing - fixed size of hashTable - 

class Node<T> {
    String key;
    T value;
    Node<T> next;

    public Node(String key, T value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
};

class hashTable<T>{
    int cs;
    int ts;

    Node<T>[] table;
    // This is because of type erasure — Java doesn't keep track of the type T at runtime, so it doesn't know how to create a true Node<T>[].
    // declare first
     @SuppressWarnings("unchecked")
    public hashTable(){
        this.cs = 0;
        this.ts = 10;

        table = (Node<T>[]) new Node[ts];

        for(int i = 0;i<ts;i++){
            table[i] = null;
        }
    }

    private int hashFunction(String key){
        int idx = 0;
        int power = 1;
        for(char ch : key.toCharArray()){
            int ascii = (int) ch;
            idx = idx + (ascii*power)%7;
            power = (power*11)%7;
        }
        return idx;
    }

    public void insert(String key, T value){
        int idx = hashFunction(key);

        Node<T> n = new Node<T>(key, value);

        n.next = table[idx];
        table[idx] = n;

        cs++;

        float load_factor = (float) cs/(float) ts;
        if(load_factor > 0.7){
            reHash();
        }
    }

    public void reHash(){
        Node<T>[] oldTable = table;
        // int oldTable_ts = ts;

        ts = ts*2+1;
        // Casts the raw array to a Node<T>[] (needed because Java doesn’t allow generic arrays directly)
        table = (Node<T>[]) new Node[ts];
        // use of same ols_name of table
        for(int i=0;i<ts;i++){
            table[i]= null;
        }

        for(int i=0;i<ts;i++){
            Node<T> temp = oldTable[i];

            while (temp != null) {
                String key = temp.key;
                T value = temp.value;
                // on new table-null
                insert(key, value);
                temp = temp.next;
                   
            }
            // destroying oldtable and links
            if(oldTable[i] != null) {
                oldTable[i] = null;
            } 
        }
    }

    public void printHash(){

        for(int i =0;i<ts;i++){
            Node<T> temp = table[i];
            System.out.print("Bucket "+ i + " -> ");

            while(temp != null){
                System.out.print(temp.key+" -> ");
                temp = temp.next;
            }
        }
    }

};

public class hashTable_main {
    public static void main(String[] args) {

        @SuppressWarnings("rawtypes")
        hashTable h1 = new hashTable();

        h1.insert("Apple", 120);
        h1.insert("Mango", 100);
        h1.insert("papaya", 100);
        h1.insert("bangen", 100);
        h1.insert("jaam", 100);
        h1.insert("sitafal", 100);
        h1.insert("ramfal", 100);
        
    }
}
