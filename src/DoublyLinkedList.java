import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Adaora Arinze
 *  @version 5/11/21 2:33pm
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data:
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 *
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
        // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode)
        {
            data = theData;
            prev = prevNode;
            next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    int size = 0;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: O(1)
     *
     * Justification:
     *  it checks only 3 values.
     */
    public boolean isEmpty()
    {
        return head == null & tail == null || size == 0;
    }


    /**
     * Returns the node stored at a particular position
     * I referenced the Java LinkedList original source code when I wrote this.
     * @param pos : the position
     * @return the DLLNode at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  The code checks the middle of the code to see whether the required nod is in the first or second half of the list.
     *  It then iterates until it finds said node. The while loops run in O(N) time because N/2 is simplified to O(N)
     *  in asymptotic notation, and only one will be used each time the method is called. The rest of the code are all
     *  assignment statements which run in O(1) time, so the run time for this method is O(N) because O(1) + O(N) x O(1)
     *  is O(N) when simplified.
     *
     */
    public DLLNode getNode(int pos){

        if(pos < size & 0 <= pos) {
            DLLNode tempNode;
            if (pos < size / 2) {
                tempNode = head;
                // n less than size/2, iterate from start
                while (pos-- > 0) {
                    tempNode = tempNode.next;
                }
            } else {
                tempNode = tail;
                // n greater than size/2, iterate from end
                while (++pos < size) {
                    tempNode = tempNode.prev;
                }
            }

            return tempNode;
        }
        return null;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  the first three if statements all run in constant time, so we can ignore those for the calculation. The method
     *  getNode runs in O(N) time, so this method will run in O(N) time because O(1) + O(N) simplifies to O(N).
     */
    public void insertBefore( int pos, T data )
    {
        if (isEmpty()){
            DLLNode newNode = new DLLNode(data, null, null);
            head = newNode;
            tail = newNode;
            size++;
        }

        else {
            if (pos <= 0){
                DLLNode newNode = new DLLNode(data, null, head);
                head.prev = newNode;
                head = newNode;
                size++;
            }

            else if (pos >= size){
                DLLNode newNode = new DLLNode(data, tail, null);
                tail.next = newNode;
                tail = newNode;
                size++;
            }

            else{
                DLLNode tempNode = getNode(pos);
                DLLNode newNode = new DLLNode(data, tempNode.prev, tempNode);
                tempNode.prev.next = newNode;
                tempNode.prev = newNode;
                size++;
            }
        }
    }

    /**
     * Returns the data stored at a particular position
     * I referenced the Java Linked list original source code when I wrote this.
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  getNode runs in O(N) time, so this method will also run in O(N) time. All other code run in O(1) time and O(1)
     *  + O(N) simplifies to O(N).
     *
     */
    public T get(int pos)
    {
        if(pos < size & 0 <= pos) {

            DLLNode item=getNode(pos);
            return item.data;
        }
        return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     * The first 4 if statements all run in constant time, because all the code in them are just assignment statements.
     * the final else statement that deals with nodes that are not the head or tail, runs in O(N) time. This is because
     * getNode() runs in O(N) time. The rest if the else statement is just assignment statements, so O(1) + O(N) is O(N)
     */
    public boolean deleteAt(int pos)
    {
        if(pos < 0 || pos >= size) {
            return false;
        }

        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return true;
        }

        if (pos == 0) {
            DLLNode tempNode;
            tempNode = head;
            head = tempNode.next;
            tempNode.next.prev = null;
            size--;
            return true;
        }

        else if (pos == size-1) {
            DLLNode tempNode;
            tempNode = tail;
            tail = tempNode.prev;
            tempNode.prev.next = null;
            size--;
            return true;
        }

        else {
            DLLNode tempNode = getNode(pos);
            tempNode.next.prev = tempNode.prev;
            tempNode.prev.next = tempNode.next;
            size--;
            return true;
        }
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     * The while loop runs N times. This is because after the head, it has to reassign each element of the list to be
     * before the one behind it. All other code in the method is an assignment so to calculate the run time you get:
     * O1 + O(N) x O(1), which is just O(N).
     */
    public void reverse()
    {
        if (size > 1){
            DLLNode currentNode = head;
            DLLNode tempNode = null;

           while (currentNode != null) {
                tempNode = currentNode.prev;
                currentNode.prev = currentNode.next;
                currentNode.next = tempNode;
                currentNode = currentNode.prev;
           }

           if (tempNode != null) {
               head = tempNode.prev;
           }
        }
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: O(N^3)
     *
     * Justification:
     * The first while loop will start at the head and iterate throughout the rest of the list. So it runs in O(N) time.
     * The second while loop will also iterate throughout the whole list. So it runs in O(N) time as well. The method
     * call to deleteAt() will run in O(N) time. The rest of the code includes assignment statements, so when calculating
     * this we get: O(1) + O(N) ( O1 + O(N) ( O1 + O(N)) ) which can be simplified to O(N) x O(N) x O(N) which is O(N^3).
     */
    public void makeUnique()
    {
        if(size > 1){
            DLLNode tempNode;
            DLLNode currentNode = head;
            int i;
            boolean uniqueNumber = false;
            int uniqueNumbers = -1;
            int numberDeleted = 0;
            while(currentNode != null){

                if(uniqueNumber){
                    uniqueNumbers++;
                    uniqueNumber = false;
                }

                i = 1;
                if(uniqueNumbers>0) {
                    i = i + uniqueNumbers;
                }

                tempNode = currentNode.next;
                while (tempNode != null) {
                    if (currentNode.data == tempNode.data) {
                        deleteAt(i);
                        numberDeleted++;
                    }

                    tempNode = tempNode.next;
                    i++;
                    i = i - numberDeleted;
                    numberDeleted = 0;
                    uniqueNumber = true;
                }
                currentNode = currentNode.next;
            }
        }
    }


    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: O(1)
     *
     * Justification:
     * This method is only assignment statements.
     */
    public void push(T item)
    {
        {
            DLLNode oldHead = head;
            head = new DLLNode(item,null,oldHead);
            size++;
        }
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: O(1)
     *
     * Justification:
     * This method is only assignment statements.
     */
    public T pop()
    {
        if(!isEmpty()) {
            DLLNode item = head;
            head = head.next;
            size--;
            return item.data;
        }
        return null;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: O(1)
     *
     * Justification:
     * This method is only assignment statements.
     */
    public void enqueue(T item)
    {
        if(isEmpty()){
            head = new DLLNode(item,null,null);
        }

        else {
            if (size == 1) {
                tail = new DLLNode(item, head, null);
                head.next = tail;
            }

            else {
                DLLNode oldTail = tail;
                tail = new DLLNode(item, oldTail, null);
                oldTail.next = tail;
            }

        }
        size++;
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an enqueue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: O(1)
     *
     * Justification:
     * This method is only assignment statements.
     */
    public T dequeue()
    {
        if(!isEmpty()) {
            DLLNode item = head;
            head = head.next;
            size--;
            return item.data;
        }
        return null;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;

        // iterate over the list, starting from the head
        for (DLLNode iter = head; iter != null; iter = iter.next)
        {
            if (!isFirst)
            {
                s.append(",");
            } else {
                isFirst = false;
            }
            s.append(iter.data.toString());
        }

        return s.toString();
    }


}


