
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

    public Node reverseLinkedList(Node temp_head12){
        Node current = temp_head12;
        Node prev = null;

        while (current.next != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        head = current;
        current.next = prev;
        return current;
    }

    public Node return_kth_last_element(int k){
        //reverse list
        reverseLinkedList(head);
        Node kth = traverse_list(k);

        return kth;
    }

    public Node traverse_list(int k){
        Node temp = head;
        for(int i = 1;i<k;i++){
            temp = temp.next;
        }
        return temp;
    }

    //single logic

    public int kthLastElement(int k){
        //Complete this function to return kth last element
        int count = 0;
        int i = 0;
        Node temp = head;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        // return count;
        Node temp1 = head;
        while(i<(count-k)){
            temp1 = temp1.next;
            i++;
        }
        return temp1.data;
    }
};

public class codeing_exercise_kth_last_element {

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
        list1.push_front(10);
        list1.push_front(9);

        //printlist
        Node temp_head = list1.head;
        print_linked_list(temp_head);

        // Node element = list1.return_kth_last_element(2);
        
        // System.out.println("\n"+element.data);

        System.out.println("\nkth last element");

        int logicElement = list1.kthLastElement(3);
        System.out.println(logicElement);

        
    }
}
