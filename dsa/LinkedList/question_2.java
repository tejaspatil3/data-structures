// Question 2:
// What is the output of the following code?


class Node{
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
};

class LinkedList{
    Node head;
    Node tail;

    public LinkedList(){
        head = null;
        tail = null;
    }

    public Node begin(){
        return head;
    }

    public void push_front(int data){
        if(head == null){
            Node n = new Node(data);
            head = tail = n;
        }else{
            Node n = new Node(data);
            n.next = head;
            head=n;
        }
    }

    void print(Node head){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp= temp.next;
        }
        System.out.println();
    }

    void increment(Node head){
        Node temp= head;
        while(temp != null){
            temp.data++;
            temp=temp.next;
        }
    }

};

 
public class question_2 {

    static void print_linked_list(Node temp_head){
        while(temp_head != null){
            System.out.print(temp_head.data+ " -> ");
            temp_head = temp_head.next;
        }
    }
    public static void main(String[] args) {
         LinkedList list1 = new LinkedList();

        // list1.push_front(13);
        // list1.push_front(12);
        // list1.push_front(11);
        // list1.push_front(10);
        // list1.push_front(9);

        // //printlist
        // Node temp_head = list1.head;
        // print_linked_list(temp_head);

        //question 2
        Node nodel = new Node(10);
        Node node2 = new Node(20);
        nodel.next = node2;
        // increment(list1.node1);
        // print(list1.node1);

        
    }
}
