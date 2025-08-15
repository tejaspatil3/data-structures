import java.util.*;

public class priority_queue {
     public static void main(String args[]){
        // Creating empty priority queue
        Queue<Integer> pq = new PriorityQueue<Integer>();

        // Adding items to the pQueue using add()
        pq.add(10);
        pq.add(20);
        pq.add(15);

        // Printing the top element of the PriorityQueue
        System.out.println(pq.peek());

        // Printing the top element and removing it the PriorityQueue container
        System.out.println(pq.poll());

        // Printing the top element again
        System.out.println(pq.peek());

        // String Priority-Queue
        System.out.println("Adding Element");
        Queue<String> pq2 = new PriorityQueue<>();

        pq2.add("Geeks");
        pq2.add("For");
        pq2.add("Geeks");

        System.out.println(pq2);

        System.out.println("Removing Elements");

         Queue<String> pq3 = new PriorityQueue<>();

        pq3.add("Geeks");
        pq3.add("For");
        pq3.add("Geeks");

        System.out.println("Initial Queue: " + pq3);

        pq3.remove("Geeks");

        System.out.println("After Remove: " + pq3);

        System.out.println("Poll Method: " + pq3.poll());

        System.out.println("Final Queue: " + pq3);


        System.out.println("Iterating Queue");

        Iterator iterator = pq.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}

