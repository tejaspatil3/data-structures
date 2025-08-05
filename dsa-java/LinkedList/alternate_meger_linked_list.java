
// Alternate Merge
// Given two linked lists, insert nodes of second list into first list at alternate positions of first list.

// Input Format

// In the function pointer to the start of the two linklists is passed.

// Output Format

// Return a pointer to the new merged list.

// Sample Input

// 5->7->17->13->11
// 12->10->2->4->6

// Sample Output

// 5->12->7->10->17->2->13->4->11->6


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

};

public class alternate_meger_linked_list {

    static Node alternate_merge_linked_list(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node current1 = head1;
        Node current2 = head2;
        Node next1;
        Node next2;

        while (current1 != null && current2 != null) {
            next1 = current1.next;
            next2 = current2.next;

            current1.next = current2;

            if (next1 != null) {
                current2.next = next1;
            } else {
                current2.next = next2; 
            }
            
            current1 = next1;
            current2 = next2;
        }

        return head1;
    }
    
    static void print_linked_list(Node temp_head){
        while(temp_head != null){
            System.out.print(temp_head.data+ " -> ");
            temp_head = temp_head.next;
        }
    }
    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();

        list1.push_front(13);
        list1.push_front(12);
        list1.push_front(11);
        // list1.push_front(10);
        // list1.push_front(9);

        //printlist
        Node temp_head = list1.head;
        print_linked_list(temp_head);

        System.out.println();

        LinkedList list2 = new LinkedList();

        list2.push_front(20);
        list2.push_front(19);
        list2.push_front(18);
        list2.push_front(17);
        list2.push_front(16);

        temp_head = list2.head;
        print_linked_list(temp_head);

        alternate_merge_linked_list(list1.head, list2.head);
         System.out.println();

        temp_head = list1.head;
        print_linked_list(temp_head);

        

    }
   
}
