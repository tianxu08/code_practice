package mj_bb;

import java.util.LinkedList;


import ds.Debug;
import ds.ListNode;

public class BB_Mianjing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	/*
	 * aaaabbbccdddd => a4bbbccd4
	 * if duplicates more than 3, encoding
	 */
	public static void test1() {
		String s = "aaaaabbccddddd";
		String rev1 = task1_string_encoding(s);
		System.out.println(rev1);
		String rev2 = task1_string_encode2(s);
		System.out.println(rev2);
	}
	
	public static String task1_string_encoding(String s) {
		if (s == null || s.length() <= 3) {
			return s;
		}
		
		StringBuilder stb = new StringBuilder();
		stb.append(s.charAt(0));
		int count = 1;

		for(int i = 1; i < s.length(); i ++) {
			char cur = s.charAt(i);
			if (cur == stb.charAt(stb.length() - 1)) {
				count ++;
			} else {
				// cur != stb[lastIdx]
				if (count > 3) {
					stb.append(count);
					// reset count
					count = 1;
				} else {
					while(count > 1) {
						char prev = stb.charAt(stb.length() - 1);
						stb.append(prev);
						count --;
					}
				}
				stb.append(cur);
			}
		}
		// don't forget the last one
		if (count > 3) {
			stb.append(count);
			// reset count
			count = 1;
		} else {
			while(count > 1) {
				char prev = stb.charAt(stb.length() - 1);
				stb.append(prev);
				count --;
			}
		}
		
		return stb.toString();
	}
	
	
	public static String task1_string_encode2(String s) {
		if (s == null || s.length() <= 3) {
			return s;
		}
		
		int idx = 0;
		StringBuilder stb = new StringBuilder();
		
		while(idx < s.length()) {
			stb.append(s.charAt(idx));
			idx ++;
			int count = 1;
			while(idx < s.length() && s.charAt(idx) == stb.charAt(stb.length() - 1)){
				idx ++;
				count ++;
			}
			if (count > 3) {
				stb.append(count);
			} else {
				char pre_ch = stb.charAt(stb.length() - 1); 
				while(count > 1) {
					stb.append(pre_ch);
					count --;
				}
			}
		}
		return stb.toString();
	}
	
	public static void test2() {
		ListNode n11 = new ListNode(4);
		ListNode n12 = new ListNode(5);
		ListNode n13 = new ListNode(6);
		n11.next = n12;
		n12.next = n13;
		
		ListNode n21 = new ListNode(7);
		ListNode n22 = new ListNode(8);
		ListNode n23 = new ListNode(9);
		ListNode n24 = new ListNode(6);
		n21.next = n22;
		n22.next = n23;
		n23.next = n24;
		
		Debug.printList(n11);
		Debug.printList(n21);
		ListNode rev = task2_addTwoList(n11, n21);
		System.out.println("=====");
		Debug.printList(rev);
	}
	
	public static ListNode task2_addTwoList(ListNode l1, ListNode l2) {
		LinkedList<ListNode> s1 = new LinkedList<ListNode>();
		LinkedList<ListNode> s2 = new LinkedList<ListNode>();
		LinkedList<ListNode> res = new LinkedList<ListNode>();
		
		while(l1 != null) {
			s1.offerFirst(l1);
			l1 = l1.next;
		}
		while(l2 != null) {
			s2.offerFirst(l2);
			l2 = l2.next;
		}
		
		System.out.println(s1.size());
		System.out.println(s2.size());
		int carry = 0;
		int sum = 0;
		while(!s1.isEmpty() && !s2.isEmpty()) {
			ListNode n1 = s1.pollFirst();
			ListNode n2 = s2.pollFirst();
			sum = n1.val + n2.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode n = new ListNode(sum);
			res.offerFirst(n);
		}
		
		while(!s1.isEmpty()) {
			ListNode n1 = s1.pollFirst();
			sum = n1.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode n = new ListNode(sum);
			res.offerFirst(n);
		}
		
		while(!s2.isEmpty()) {
			ListNode n2 = s2.pollFirst();
			sum = n2.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode n = new ListNode(sum);
			res.offerFirst(n);
		}
		if (carry != 0) {
			res.offerFirst(new ListNode(carry));
		}
		
		
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		
		while(!res.isEmpty()) {
			tail.next = res.pollFirst();
			tail = tail.next;
		}
		return dummy.next;
	}

	
}
