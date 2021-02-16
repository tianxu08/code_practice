package tag_linkedlist;

import ds.ListNode;
import ds.Debug;
import java.util.*;

public class LinkedListSet {
    /*
     * list
     * 1 number of nodes
     * 2 reverse linked list
     * 3 middle of list node
     * 4 Check If Linked List Has A Cycle
     * 5 Insert In Sorted Linked List
     * 6 Merge Two Sorted Linked Lists
     */
    /*
     * task1 Number Of Nodes
     * Easy Data Structure
     *
     * Return the number of nodes in
     * the linked list.
     *
     * Examples
     *
     * L = null, return 0 L = 1 -> null, return 1 L = 1 -> 2 -> null, return 2
     */
    public static int count(ListNode head) {
        if (head == null) {
            return 0;
        }
        int counter = 0;
        while (head != null) {
            counter ++;
            head = head.next;
        }
        return counter;
    }


    /*
     * task2 Reverse Linked List
     * Easy Data Structure
     * Reverse a singly-linked list.
     *
     * Examples
     *
     * L = null, return null L = 1 -> null, return 1 -> null L = 1 -> 2 -> 3 ->
     * null, return 3 -> 2 -> 1 -> null
     */
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverse(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    /*
     *      1 -> 2 -> 3 -> 4 -> 5
     *  p   c
     */
    public static ListNode reverseIter(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null) {
            // store the cur.next
            ListNode next = cur.next;
            cur.next = prev;
            // update prev
            prev = cur;
            // update cur
            cur = next;
        }
        return prev;
    }

    public static void test1() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Debug.printList(n1);
        ListNode r = reverseIter(n1);
        System.out.println("-------------");
        Debug.printList(r);
    }


    /*
     * task3 Middle Node Of Linked List
     * Find the middle node
     * of a given linked list.
     *
     * Examples
     *
     * L = null, return null L = 1 -> null, return 1 L = 1 -> 2 -> null, return
     * 1 L = 1 -> 2 -> 3 -> null, return 2 L = 1 -> 2 -> 3 -> 4 -> null, return
     * 2
     */
    public static void test3() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        ListNode mid = task3_mid(n1);
        System.out.println("mid = " + mid.val);

    }

    public static ListNode task3_mid(ListNode head) {
        ListNode f = head.next; // !!! remember, f = head.next
        ListNode s = head;
        while(f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }
        return s;
    }


    /*
     * task4 Check If Linked List Has A Cycle
     * Easy Data Structure
     *
     * Check if a
     * given linked list has a cycle. Return true if it does, otherwise return
     * false.
     */


    /*
     * task5 Insert In Sorted Linked List
     * Easy Data Structure Insert a value in
     * a sorted linked list.
     *
     * Examples
     *
     * L = null, insert 1, return 1 -> null L = 1 -> 3 -> 5 -> null, insert 2,
     * return 1 -> 2 -> 3 -> 5 -> null L = 1 -> 3 -> 5 -> null, insert 3, return
     * 1 -> 3 -> 3 -> 5 -> null L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3
     * -> null
     */


    /*
     * task6 Merge Two Sorted Linked Lists Easy Data Structure Merge two sorted
     * lists into one large sorted list.
     *
     * Examples
     *
     * L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2
     * -> 4 -> 5 -> 6 -> null L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to
     * 1 -> 2 -> null L1 = null, L2 = null, merge L1 and L2 to null
     * user dummy head
     */
    public static ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode p1 = h1, p2 = h2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                ListNode n = p1.next;
                p1.next = null;
                cur.next = p1;
                p1 = n;
            } else {
                ListNode n = p2.next;
                p2.next = null;
                cur.next = p2;
                p2 = n;
            }
            cur = cur.next;
        }
        if (p1 != null) {
            cur.next = p1;
        }
        if (p2 != null) {
            cur.next = p2;
        }
        return dummy.next;
    }

    /*
     * task7 Stack With min() Fair Data Structure Java: Enhance the stack
     * implementation to support min() operation. min() should return the
     * current minimum value in the stack. If the stack is empty, min() should
     * return null.
     *
     * C++: Enhance the stack implementation to support min() operation. min()
     * should return the current minimum value in the stack. If the stack is
     * empty, min() should return -1.
     *
     * pop() - remove and return the top element, if the stack is empty, return
     * -1 push(int element) - push the element to top top() - return the top
     * element without remove it, if the stack is empty, return -1 min() -
     * return the current min value in the stack.
     */

    /*
     * task8 Queue By Two Stacks Fair Data Structure Java: Implement a queue by
     * using two stacks. The queue should provide size(), isEmpty(), offer(),
     * poll() and peek() operations. When the queue is empty, poll() and peek()
     * should return null.
     *
     * C++: Implement a queue by using two stacks. The queue should provide
     * size(), isEmpty(), push(), front() and pop() operations. When the queue
     * is empty, front() should return -1.
     *
     * Assumptions
     *
     * The elements in the queue are all Integers. size() should return the
     * number of elements buffered in the queue. isEmpty() should return true if
     * there is no element buffered in the queue, false otherwise.
     */
    public static class QueueByTwoStacks {
        private LinkedList<Integer> st1;
        private LinkedList<Integer> st2;

        public QueueByTwoStacks() {
            this.st1 = new LinkedList<>();
            this.st2 = new LinkedList<>();
        }

        public void offer(Integer elem) {
            st1.offerFirst(elem);
        }

        public Integer poll() {
            if (!st2.isEmpty()) {
                return st2.pollFirst();
            } else {
                while (!st1.isEmpty()) {
                    st2.offerFirst(st1.poll());
                }
                return st2.pollFirst();
            }
        }
        public int size() {
            return st1.size() + st2.size();
        }

        public boolean isEmpty() {
            return st1.isEmpty() && st2.isEmpty();
        }

        public Integer peek() {
            if (st2.isEmpty()) {
                while (!st1.isEmpty()) {
                    st2.offerFirst(st1.pollFirst());
                }
            }
            return st2.peekFirst();
        }
    }

    /*
     * task9 Partition Linked List Fair Data Structure Given a linked list and a
     * target value T, partition it such that all nodes less than T are listed
     * before the nodes larger than or equal to target value T. The original
     * relative order of the nodes in each of the two partitions should be
     * preserved.
     *
     * Examples
     *
     * L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4
     * -> 3 -> 5 -> null
     */


    /**
     * task 10
     * stack with min
     */
    public static class Stack_With_Min {

        private LinkedList<Integer> stack;
        private LinkedList<Integer> min;
        public Stack_With_Min() {
            stack = new LinkedList<Integer>();
            min = new LinkedList<Integer>();
        }
        public Integer pop() {
            if (stack.isEmpty()) {
                return null;
            } else {
                Integer elem = stack.pollFirst();
                min.pollFirst();
                return elem;
            }

        }

        public void push(int element) {
            stack.offerFirst(element);
            if (min.isEmpty() || min.peekFirst() > element) {
                min.offerFirst(element);
            } else {
                min.offerFirst(min.peekFirst());
            }
        }

        public Integer top() {
            if (stack.isEmpty()) {
                return null;
            } else {
                return stack.peekFirst();
            }
        }

        public Integer min() {
            return min.peekFirst();
        }

    }


}
