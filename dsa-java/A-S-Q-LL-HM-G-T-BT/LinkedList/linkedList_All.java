// package tufdsa.dsa.LinkedList;

class Node{
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
    public int getData(){
        return data;
    }
};

class LinkedList{
    Node head;
    Node tail;

    public LinkedList(){
        head = null;
        tail = null;
    }

    Node begin(){
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

    public void push_back(int data){
        Node n = new Node(data);
        if(head == null){
            head = tail = n;
        }else{
            tail.next = n;
            tail = n;
        }
    }

    public void insert(int data, int pos){
        if(pos == 0){
            push_front(data);
            return;
            //
        }
        Node temp_head = head;
        for( int jump =1 ;jump <pos-1;jump++){
            temp_head= temp_head.next;
        }
        Node new_insert = new Node(data);
        new_insert.next = temp_head.next;
        temp_head.next = new_insert;


    }
    // liner search -- memory not continous direct access is not possible
    public boolean search_list(Node head, int key){
        int idx = 0 ;
        Node temp = head;
        while(temp!= null){
            if(temp.data == key){
                idx ++;
                System.out.println(idx);
                return true;
            }
            else{
                idx++;
                temp = temp.next;
            }
        }
        return false;
    }

    //recursive search
    public int resursiveSearch(Node head, int key){
        int idx = searchHelper(head, key);

        if(idx == -1){
            System.err.println("\nElement found - " + idx );
        }else{
            System.out.println("\nNot found - last Index reached - " + idx);
        }
        return idx;
    }

    public int searchHelper(Node head,int key){
        // int subIndex;
        if(head == null){
            return -1;
        }
        if(head.data == key){
            return 1;
        }
        int subIndex = searchHelper(head.next, key);
        if(subIndex == -1){
            return -1;
        }
        else{
            subIndex++;
            return subIndex;
            // return subIndex + 1;
        }
    }

    // deleting whole list
    public void deleteAll() {
        this.head = null; 
        System.out.println("Linked List set for deletion by GC.");
    }

    // delete specific value node or specific number node

    public Node pop_front(){
        Node temp = head;
        head = head.next;
        temp.next = null;
        return head;
    }

    public Node pop_back(Node head1){
        Node temp = head1;
        while(temp.next != null){
            Node n = temp.next;
            if(n.next == null){
                temp.next = null;
                return temp;
            }
            else{
                temp = temp.next;
            }
        }
        return temp; 
    }

    public void removeNodeByPos(int pos){
        Node temp = head;
        if(pos == 0){
            pop_front();
        }
        for(int jump = 1;jump < pos;jump++){
            temp=temp.next;
        }
        Node n = temp.next;
        // System.out.println(n.data);
        temp.next = n.next;
        n.next = null;

        // return n;
    }

    public void removeNodeByValue(int key){
        // int idx = resursiveSearch(head,key);
        // removeNodeByPos(idx);
        Node temp = head;
        while(temp != null){
            if(temp.data == key){
                Node n = temp.next;
                temp.next = n.next;
                n.next = null;
                break;
            }
            else{
                temp = temp.next;
            }
        }
    }

    public Node reverseLinkedList(Node temp_head12){
        Node current = temp_head12;
        Node prev = null;

        while (current.next != null) {
            Node temp = current.next;
            // System.out.println(temp.data);
            current.next = prev;
            prev = current;
            // System.out.println(prev.data);
            current = temp;
            // System.out.println(current.data);
        }
        head = current;
        current.next = prev;
        // System.out.println(current.data);
        // System.out.println(current.next);
        return current;
    }

};

public class linkedList_All{

    static void print_linked_list(Node temp_head){
        while(temp_head != null){
            System.out.print(temp_head.getData() + " -> ");
            temp_head = temp_head.next;
        }
    }

    public static void main(String[] args) {
        // Node head = null;
        LinkedList list1 = new LinkedList();
        list1.push_front(12);
        list1.push_front(11);
        list1.push_front(10);
        list1.push_front(9);
        list1.push_back(13);

        System.out.println();

        // print_list();
        Node temp_head = list1.begin();
        print_linked_list(temp_head);

        // System.out.println();

        // // No output as head is local to main
        // Node temp_head = list1.begin();
        // print_linked_list(temp_head);

        // System.err.println();

        // // insert list
        // list1.insert(10, 0);
        // list1.insert(13, 4);
        // list1.insert(14, 5);

        //  System.out.println();

        // //Print list
        // Node temp_head2 = list1.begin();
        // print_linked_list(temp_head2);

        System.out.println();

        // Node temp_head3 = list1.begin();
        // int key1 = 13;
        // if(list1.search_list(temp_head3, key1)){
        //     System.out.println("\nElement Found ");
        // }else{
        //     System.out.println("\nNot Found ");
        // }

        // System.out.println();

        // Node temp_head4 = list1.begin();
        // int key2 = 15;
        // list1.resursiveSearch(temp_head4, key2);

        // //Print list
        // // list1.deleteAll();  //
        // Node temp_head5 = list1.begin();
        // if(temp_head5 == null){
        //     System.out.println("Linked list deleted");
        // }


        // System.out.println();

        // //original
        // Node temp_head8 = list1.begin();
        // print_linked_list(temp_head8);

        // System.out.println();
        
        // //pop fornt
        // Node temp_head6 = list1.begin();
        // // System.out.println(temp_head6.data);
        // // System.out.println(temp_head6.next);

        // System.out.println();

        // list1.pop_front();

        // Node temp_head9 = list1.begin();
        // // System.out.println(temp_head9.data);
        // print_linked_list(temp_head9);

        // System.out.println();

        // //pop back
        // Node temp_head7 = list1.begin();
        // System.out.println(temp_head7.data);
        // list1.pop_back(temp_head7);

        // System.out.println();

        // Node temp_head10 = list1.begin();
        // System.out.println(temp_head10.data);
        // print_linked_list(temp_head10);

        // System.out.println();

        // list1.removeNodeByPos(1);
        // Node temp_head11 = list1.begin();
        // print_linked_list(temp_head11);

        // System.out.println();

        // list1.removeNodeByValue(11);
        // Node temp_head11 = list1.begin();
        // print_linked_list(temp_head);

        // System.out.println();
        // Node temp_head12 = list1.head;

        // temp_head12 = list1.reverseLinkedList(temp_head12);
        // System.out.println("Linked List Reversed");

        // list1.reverseLinkedList(temp_head12);
        // temp_head12 = list1.head;
        // System.out.println(list1.head);
        // System.out.println(temp_head12);

        // temp_head12 = list1.head;

        // // print list
        // print_linked_list(temp_head12);
 
    }
};