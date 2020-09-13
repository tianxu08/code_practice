package ds;

public class Debug {
	public static void printList(ListNode head) {
		while(head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}
	
	public static ListNode createList(int[] array) {
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;
		
		for(int i = 0; i < array.length; i ++) {
			ListNode node = new ListNode(array[i]);
			tail.next = node;
			tail = tail.next;
		}
		
		ListNode head = dummy.next;
		dummy.next = null;
		return head;
	}
	
	public static void printMatrix(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void printMatrix(boolean[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printMatrix(char[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void printArray(int[] array){
		for(int i = 0; i < array.length ; i ++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static TreeNode createTree(int[] a) {
		return	createTreeHelper(a, 0, a.length - 1);
	}
	
	public static TreeNode createTreeHelper(int[] a, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = (right + left)/2;
		TreeNode root = new TreeNode(a[mid]);
		root.left = createTreeHelper(a, left, mid - 1);
		root.right = createTreeHelper(a, mid + 1, right);
		
		return root;
	}
	
	public static void preOrderTraversal(TreeNode root) {
		if (root == null) {
			return ;
		}
		System.out.print(root.val + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	public static void inOrderTraversal(TreeNode root) {
		if (root == null) {
			return ;
		}
		inOrderTraversal(root.left);
		System.out.print(root.val + " ");
		inOrderTraversal(root.right);
	}
	
	public static void postOrderTraversal(TreeNode root) {
		if (root == null) {
			return ;
		}
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.print(root.val + " ");
	}
}
