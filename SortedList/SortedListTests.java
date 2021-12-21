import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.FixMethodOrder;

/*
 * You must include the Junit4 library to use this test unit.
 *
 * To add it, select the project and then choose Properties... from the File menu.
 * Click on "Java Build Path" in the panel at the left.
 * Select Libraries from the list at the top.
 * Select Add Library... from the right side of the panel.
 * Select JUnit and click on Next.
 * Change the version to JUnit 4 and click on Finish.
 * Click on OK in the properties panel.
 * 
 * You can then run these tests using "Run As JUnit Test" instead of "Runs As Java Application".
 */

/**
 * JUnit tests for SortedList.
 *
 * @author Chad Hogg
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortedListTests {

	/**
	 * Creates a default SortedList and confirms that it was initialized correctly.
	 */
	@Test
	public void test01Constructor() {
		SortedList list = new SortedList();
		assertEquals("New list had wrong size.", 0, list.size());
		assertEquals("New list toString was wrong.", "( )", list.toString());
	}

	/**
	 * Inserts one element into a SortedList.
	 */
	@Test
	public void test02InsertOne() {
		SortedList list = new SortedList();
		list.insert(4.0);
		assertEquals("Inserting 4.0 to empty list yielded wrong size.", 1, list.size());
		assertEquals("Inserting 4.0 to empty list yielded wrong string.", "( 4.0 )", list.toString());
	}

	/**
	 * @Test Its private so I cant test it? public void test03GetPrevNode() {
	 *       SortedList list = new SortedList(); list.insert(1.0); list.insert(2.0);
	 *       list.insert(3.0); list.getPrecedingNode(2.5); assertEquals("Getting the
	 *       previous node of 2.5 returned the wrong node.", 2.0,
	 *       list.getPrecedingNode(2.5)); }
	 **/

	/**
	 * Inserts two
	 */
	@Test
	public void test03InsertTwoInOrder() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		assertEquals("Inserting 1.0 and 2.0 yielded wrong size.", 2, list.size());
		assertEquals("Inserting 1.0 and 2.0 yielded wrong string.", "( 1.0 2.0 )", list.toString());
	}

	/**
	 * Insert two out of order
	 */
	@Test
	public void test04InsertTwoInOrder() {
		SortedList list = new SortedList();
		list.insert(2.0);
		list.insert(1.0);
		assertEquals("Inserting 2.0 and 1.0 yielded wrong size.", 2, list.size());
		assertEquals("Inserting 2.0 and 1.0 yielded wrong string.", "( 1.0 2.0 )", list.toString());
	}

	/**
	 * Tests that the correct index is returned when the number exists in the list
	 */

	@Test
	public void test05FindValue() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		assertEquals("The wrong index is returned when finding 2.0", 1, list.find(2.0));
	}

	/**
	 * Tries to find a value that is not in the list
	 */
	@Test
	public void test06FindMissingValue() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		assertEquals("Wrong index returned when trying to find 4.0 when it is not in the list", -1, list.find(4.0));
	}

	/**
	 * Find value in an empty list!
	 */
	@Test
	public void test07FindValueInEmptyList() {
		SortedList list = new SortedList();
		assertEquals("Wrong index returned when trying to find 4.0 when the list is empty", -1, list.find(4.0));

	}

	/**
	 * Remove the only element
	 */
	@Test
	public void test08RemoveOnly() {
		SortedList list = new SortedList();
		list.insert(5.0);
		assertEquals("Should have been able to remove at index 0 when listLength is 1", true, list.removeAt(0));
		assertEquals("Wrong string when removing index 0 when listLength is 1", "( )", list.toString());
		assertEquals("Wrong size when removing index 0 when listLength is 1", 0, list.size());

	}

	/**
	 * Remove the first element
	 */
	@Test
	public void test09RemoveFirst() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		list.insert(4.0);
		list.insert(5.0);
		assertEquals("Should have been able to remove at index 0", true, list.removeAt(0));
		assertEquals("Wrong string when removing index 0", "( 2.0 3.0 4.0 5.0 )", list.toString());
		assertEquals("Wrong size when removing index 0 when listLength is 5", 4, list.size());

	}

	/**
	 * Remove the second element
	 */
	@Test
	public void test10RemoveSecond() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		list.insert(4.0);
		list.insert(5.0);
		assertEquals("Should have been able to remove at index 1", true, list.removeAt(1));
		assertEquals("Wrong string when removing index 1", "( 1.0 3.0 4.0 5.0 )", list.toString());
		assertEquals("Wrong size when removing index 1 when listLength is 5", 4, list.size());

	}

	/**
	 * Remove the last element
	 */
	@Test
	public void test11RemoveLast() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		list.insert(4.0);
		list.insert(5.0);
		assertEquals("Should have been able to remove at index 4", true, list.removeAt(4));
		assertEquals("Wrong string when removing index 4", "( 1.0 2.0 3.0 4.0 )", list.toString());
		assertEquals("Wrong size when removing index 4 when listLength is 5", 4, list.size());

	}
	
	
	/**
	 * Remove at index out of bounds
	 */
	@Test
	public void test12RemoveBadIndex() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		list.insert(4.0);
		list.insert(5.0);
		assertEquals("Should not have been able to remove at index 8", false, list.removeAt(8));
		assertEquals("Wrong string when trying to remove index 8", "( 1.0 2.0 3.0 4.0 5.0 )", list.toString());
		assertEquals("Wrong size when trying to remove index 8", 5, list.size());

	}
	
	/**
	 * Remove negative index
	 */
	@Test
	public void test13RemoveNegIndex() {
		SortedList list = new SortedList();
		list.insert(1.0);
		list.insert(2.0);
		list.insert(3.0);
		list.insert(4.0);
		list.insert(5.0);
		assertEquals("Should not have been able to remove at index -2", false, list.removeAt(-2));
		assertEquals("Wrong string when trying to remove index 2", "( 1.0 2.0 3.0 4.0 5.0 )", list.toString());
		assertEquals("Wrong size when trying to remove index -2", 5, list.size());

	}
	
	
}
