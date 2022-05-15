import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author Adaora Arinze
 *  @version 5/11/21 2:33pm
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
    }

    @Test
    public void testEmpty()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        boolean testBool = testDLL.isEmpty();
        assertTrue("Checks if the list is empty", testBool);
        boolean testDelete = testDLL.deleteAt(1);
        assertFalse("Checks if nothing was deleted form an empty list", testDelete);
        testDLL.makeUnique();
        assertEquals( "Checks if nothing happens from making the list unique", "", testDLL.toString() );
        testDLL.reverse();
        assertEquals( "Checks if nothing happens from reversing the list", "", testDLL.toString() );
        Object testNull = testDLL.getNode(-1);
        assertNull("Checks getting a node from an empty list", testNull);
    }

    @Test
    public void testDelete()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);

        testDLL.deleteAt(2);
        assertEquals( "Checks deleting an element from the list", "1,2,4", testDLL.toString() );
    }

    @Test
    public void testMultipleDelete()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);

        testDLL.deleteAt(2);
        assertEquals( "Checks deleting an element from the list", "1,2,4", testDLL.toString() );
        testDLL.deleteAt(0);
        assertEquals( "Checks deleting an element from the list", "2,4", testDLL.toString() );
        testDLL.deleteAt(1);
        assertEquals( "Checks deleting an element from the list", "2", testDLL.toString() );
        boolean testNull = testDLL.deleteAt(1);
        assertFalse( "Checks deleting an element from the list", testNull );
        testDLL.deleteAt(0);
        assertEquals( "Checks deleting an element from the list", "", testDLL.toString() );
    }

    @Test
    public void testReverse()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);

        testDLL.reverse();
        assertEquals( "Checks reversing a list containing 4 elements", "4,3,2,1", testDLL.toString() );
    }

    @Test
    public void testReverseOne()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);

        testDLL.reverse();
        assertEquals( "Checks reversing a list containing 1 element", "1", testDLL.toString() );
    }

    @Test
    public void testDuplicates()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,1);
        testDLL.insertBefore(4,1);
        testDLL.insertBefore(5,1);
        testDLL.insertBefore(6,1);

        testDLL.makeUnique();
        assertEquals( "Checks a duplicate list", "2,1", testDLL.toString() );
    }

    @Test
    public void testDuplicatesAndGet()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(1,1);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,1);
        testDLL.insertBefore(4,1);
        testDLL.insertBefore(5,2);
        testDLL.insertBefore(6,1);

        Object test1 =testDLL.get(1);
        assertEquals( "Checks a duplicate list", 1, test1 );
        Object test2 =testDLL.get(4);
        assertEquals( "Checks a duplicate list", 1, test2 );
        testDLL.makeUnique();
        assertEquals( "Checks a duplicate list", "2,1", testDLL.toString() );
        Object test3 =testDLL.get(0);
        assertEquals( "Checks a duplicate list", 2, test3 );
        Object test4 =testDLL.get(3);
        assertNull("Checks a duplicate list", test4);
    }

    @Test
    public void testDuplicatesTriples()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,3);
        testDLL.insertBefore(4,1);
        testDLL.insertBefore(5,3);

        testDLL.makeUnique();
        assertEquals( "Checks a duplicate list", "1,2,3", testDLL.toString() );
    }

    @Test
    public void testPushAndPop()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(1);
        testDLL.push(2);
        testDLL.push(3);
        testDLL.push(4);
        testDLL.pop();

        assertEquals( "Checks a stack", "3,2,1", testDLL.toString() );
    }

    @Test
    public void testNullPop()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        Object test = testDLL.pop();

        assertNull("Checks a stack", test);
    }

    @Test
    public void testQueue()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(1);
        testDLL.enqueue(2);
        testDLL.enqueue(3);
        testDLL.enqueue(4);
        testDLL.dequeue();
        testDLL.enqueue(5);
        testDLL.dequeue();

        assertEquals( "Checks a queue", "3,4,5", testDLL.toString() );
    }

    @Test
    public void testNullDequeue()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        Object test = testDLL.dequeue();

        assertNull("Checks a queue", test);
    }

}
