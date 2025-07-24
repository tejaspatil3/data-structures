// Delete Tail
// Given a pointer to the root of the linklist, your task is to delete the 
// tail of the linklist and return the head of the updated list.

// Input

// 1->2->3->4->5->6->7->8

// Output

// 1->2->3->4->5->6->7

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

    public Node deleteTail(Node head){
        Node temp= head;
        if(temp.next == null){
            return null;
        }
        else{
            while (temp.next != null) {
                Node n= temp.next;
                if(n.next == null){
                    temp.next = null;
                    return head;
                }else{
                    temp = temp.next;
                }
            }
        }
        return head;
    }

};

public class coding_excersise_1_deleteTail {
    public static void main(String[] args) {
        System.out.println("helo world");
        LinkedList list1 = new LinkedList();

        Node temp_head = list1.begin();

        list1.deleteTail(temp_head);



    }
    
}
