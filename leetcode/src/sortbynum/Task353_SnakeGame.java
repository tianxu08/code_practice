package sortbynum;
import java.util.*;
public class Task353_SnakeGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int width = 3, height = 2;
		int[][] food = {
				{1,2},
				{0,1}
		};
		Task353_SnakeGame sln = new Task353_SnakeGame(width, height, food);
		int score = sln.move("R");
		System.out.println(score);
		score = sln.move("R");
		System.out.println(score);
		score = sln.move("D");
		System.out.println(score);
	}

	// 2D position info is encoded to 1D and stored as two copies
	
	// Convert the 2D position to 1D position
	private Set<Integer> set; // this set is to store the check whether the head hit its body 
	private Deque<Integer> body;  // use to store the body of snake
	private int score;  
	private int[][] food;
	
	// the index of food
	private int foodIndex;
	
	// matrix dimension. 
	private int width;  
	private int height;

	/**
	 * Initialize your data structure here.
	 * 
	 * @param width
	 *            - screen width
	 * @param height
	 *            - screen height
	 * @param food
	 *            - A list of food positions E.g food = [[1,1], [1,0]] means the
	 *            first food is positioned at [1,1], the second is at [1,0].
	 */
	public Task353_SnakeGame(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		set = new HashSet<Integer>();
		set.add(0); //the snake is initially at [0][0]
		body = new LinkedList<Integer>();
		body.offerLast(0);
	}

	/**
	 * Moves the snake.
	 * 
	 * @param direction
	 * 'U' = Up, 
	 * 'L' = Left, 
	 * 'R' = Right, 
	 * 'D' = Down
	 * @return 
	 * The game's score after the move. 
	 * Return -1 if game over. Game over when snake crosses the screen boundary or bites its body.
	 */

	public int move(String direction) {
		// case 0: game already over: do nothing
		if (score == -1) {
			return -1;
		}
		
		// compute new head
		int rowHead = body.peekFirst() / width;
		int colHead = body.peekFirst() % width;
		switch (direction) {
		case "U":
			rowHead--;
			break;
		case "D":
			rowHead++;
			break;
		case "L":
			colHead--;
			break;
		default:
			colHead++;
		}
		// get the new rowHead and colHead
		
		// get the new position of head
		int head = rowHead * width + colHead;

		// case 1: out of boundary or eating body
		/*
		 * remove the body.peekLast() from set 
		 */
		set.remove(body.peekLast());
		// new head is legal to be in old tail's
		// position, remove from set temporarily
		
		// if the position of newHead is out of bound
		// or the head already in set
		if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width
				|| set.contains(head)) {
			return score = -1;
		}
		
		// add the newHead into head
		// add head for case2 and case3
		set.add(head);
		// add the head into body
		body.offerFirst(head);

		// case2: eating food, keep tail, add head
		if (foodIndex < food.length && 
			rowHead == food[foodIndex][0] && 
			colHead == food[foodIndex][1]) {
			
			set.add(body.peekLast()); 
			// old tail does not change, so add it back to set
			foodIndex++;
			return ++score;
		}
		
		// case3: normal move, remove tail, add head
		body.pollLast();
		return score;

	}

}
