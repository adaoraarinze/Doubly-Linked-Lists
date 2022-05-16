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


