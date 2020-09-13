package small_yan;

import java.util.HashMap;
import java.util.PriorityQueue;


public class Class7_ScoreQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node7 n1 = new Node7(1, "A", 50);
		Node7 n2 = new Node7(2, "B", 70);
		Node7 n3 = new Node7(3, "C", 80);
		Node7 n4 = new Node7(4, "D", 60);
		Node7 n5 = new Node7(5, "E", 40);
		
		Class7_ScoreQuery inst = new Class7_ScoreQuery();
		inst.addNode(n1);
		inst.addNode(n2);
		System.out.println(inst.queryMaxScore());
		inst.addNode(n3);
		inst.addNode(n4);
		inst.addNode(n5);
		System.out.println(inst.queryMaxScore());
		inst.updateScore(3, 20);
		System.out.println(inst.queryMaxScore());
		
		
	}
	
	/*
	 * task8
	 * id, name, score, 
	 * 1, A, 50
	 * 2, B, 70
	 * 3, C, 80→20 
	 * 4, D, 60
	 * 5, E, 40
	 * Find the max score in O(1) Update the score in O(logn).
	 * 
	 * maintain a maxHeap. 
	 * Priority Queue + HashMap  
	 *
	 * we can NOT do this. 
	 *  
	 * HashHeap
	 */
	
	HashMap<Integer, Node7> map;
	PriorityQueue<Node7> maxHeap;
	
	public Class7_ScoreQuery() {
		map =new HashMap<Integer, Node7>();
		maxHeap = new PriorityQueue<Node7>();
	}
	
	public boolean addNode(Node7 node) {
		if (map.containsKey(node.id)) {
			return false;
		} else {
			int id = node.id;
			map.put(id, node);
			maxHeap.offer(node);
		}
		return true;
	}
	
	
	public boolean updateScore(int id, int newscore) {
		if (!map.containsKey(id)) {
			return false;
		}
		Node7 node = map.get(id);
		node.score = newscore;
		return true;
	}
	
	public int queryMaxScore() {
		// sanity check
		if (maxHeap.isEmpty()) {
			return -1;
		}
		return maxHeap.peek().score;
	}
}

class Node7 implements Comparable<Node7>{
	int id;
	String name;
	int score;
	public Node7(int _id, String _name, int _score) {
		this.id = _id;
		this.name = _name;
		this.score = _score;
	}
	@Override
	public int compareTo(Node7 o) {
		// TODO Auto-generated method stub
		if (this.score == o.score) {
			return 0;
		}
		return this.score > o.score ? -1 : 1;
	}	
};
