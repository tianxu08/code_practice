package sortbynum;
import java.util.*;


import ds.*;
import mj_linkedin.MyIntervalLinkedIn4.ITNode;


public class task751_800 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test752();
//		test759();
//		test784();
//		test785();
//		test773();
	}
	
	
	/**
	 * 752 open the lock
	 * 
	 * 4 digits
	 * 
	 *  using BFS
	 *  
	 *  initial state: 0000
	 *  next states: 1000 0100 0010 0001 9000 0900 0090 0009
	 *  
	 *	return the minimum num of steps to open the lock  
	 *
	 */
	
	public static void test752() {
		String[] deadends = {"0000"};
		String target = "8888";
		int rev = task752_openLock(deadends, target);
		System.out.println("rev = " + rev);
	}
	
	 public static int task752_openLock(String[] deadends, String target) {
		 
		 Queue<String> q = new LinkedList<>();
		 Set<String> visited = new HashSet<>();
		 // change the deadends to hashSet
		 Set<String> deadendsSet = new HashSet<>();
		 for (String s: deadends) {
			 deadendsSet.add(s);
		 }
		 if (deadendsSet.contains("0000")) {
			 return -1;
		 }
		 q.offer("0000");
		 visited.add("0000");
		 int step = 0;
		 while(!q.isEmpty()) {
			 int size = q.size();
			 System.out.println(q);
			 for (int i = 0; i < size; i++) {
				 String cur = q.poll();
				 if (cur.equals(target)) {
					 return step;
				 }
				 List<String> nextStates = task752_getNextStates(cur, visited, deadendsSet);
//				 System.out.println(nextStates);
				 for (String next: nextStates) {
					 q.offer(next);
					 visited.add(next);
				 }
			 }
			 step++;
		 }
		 
		 return -1;
	 }
	 
	 public static List<String> task752_getNextStates(String s, Set<String> visited, Set<String> deadends) {
		 List<String> nextStates = new ArrayList<>();
		 char[] arr = s.toCharArray();
		 for (int i = 0; i < arr.length; i++) {
			 char ch = arr[i];
			 int diff = (int) (ch - '0');
			 char next = (char)('0' + (diff + 1) % 10);
			 char prev = (char)('0' + (diff - 1 + 10) % 10);
			 
			 arr[i] = next;
			 String nextStr = new String(arr);
			 if (!visited.contains(nextStr) && !deadends.contains(nextStr)) {
				 nextStates.add(nextStr);
			 }
			 arr[i] = prev;
			 String prevStr = new String(arr);
			 if (!visited.contains(prevStr) && !deadends.contains(prevStr)) {
				 nextStates.add(prevStr);
			 }
			 
			 // reset arr[i]
			 arr[i] = ch;
		 }
		 
		 return nextStates;
	 }
	 
	
	/**
	 * task753
	 * 
	 */
	
	/**
	 * task759
	 */
	public static void test759() {
		Interval i1 = new Interval(1, 2);
		Interval i1_1 = new Interval(5, 6);
		List<Interval> l1 = new ArrayList<>();
		l1.add(i1);
		l1.add(i1_1);
		
		Interval i2 = new Interval(1, 3);
		List<Interval> l2 = new ArrayList<>();
		l2.add(i2);
		
		Interval i3 = new Interval(4, 10);
		List<Interval> l3 = new ArrayList<>();
		l3.add(i3);
		
		List<List<Interval>> schedule = new ArrayList<>();
		schedule.add(l1);
		schedule.add(l2);
		schedule.add(l3);
		
		List<Interval> res1 = task759_employeeFreeTime(schedule);
		
		
		
	}
	

	/**
	 * 	swipe line 
	 * @param schedule
	 * @return
	 * 
	 * Time: O(C * log C) C is the number of intervals across all employee
	 * Space: O(C)
	 */
	public static List<Interval> task759_employeeFreeTime(List<List<Interval>> schedule) {
		int OPEN = 0, CLOSE = 1;
		List<int[]> events = new ArrayList<>();
		for (List<Interval> employee: schedule) {
			for (Interval iv: employee) {
				events.add(new int[]{iv.start, OPEN});
				events.add(new int[] {iv.end, CLOSE});
			}
		}
		
		Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
		for (int[] i : events) {
			System.out.println(Arrays.toString(i));
		}
		
		List<Interval> result = new ArrayList<>();
		int prev = -1, bal = 0;
		
		for (int[] event: events) {
			if (bal == 0 && prev > 0) {
				result.add(new Interval(prev, event[0]));
			}
			if (event[1] == OPEN) {
				bal ++;
			} else {
				bal --;
			}
			
			prev = event[0];
		}
		
		return result;
        
    }
	
	
	/**
	 * Intuition
	 * Say we are at some time where no employee is working. That work-free period will last until the next time some employee has to work.
	 * So let's maintain a heap of the next time an employee has to work, and it's associated job. When we process the next time from the heap, we can add the next job for that employee.
	 * 
	 * Algorithm
	 * 
	 * Keep track of the latest time anchor that we don't know of a job overlapping that time.
	 * 
	 * When we process the earliest occurring job not yet processed, it occurs at time t, by employee e_id, and it was that employee's e_jx'th job. If anchor < t, then there was a free interval Interval(anchor, t).
	 */
	
	public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> ans = new ArrayList<>();
        PriorityQueue<Job> pq = new PriorityQueue<Job>((a, b) ->
            avails.get(a.eid).get(a.index).start -
            avails.get(b.eid).get(b.index).start);
        int ei = 0, anchor = Integer.MAX_VALUE;

        for (List<Interval> employee: avails) {
            pq.offer(new Job(ei++, 0));
            anchor = Math.min(anchor, employee.get(0).start);
        }

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval iv = avails.get(job.eid).get(job.index);
            if (anchor < iv.start)
                ans.add(new Interval(anchor, iv.start));
            anchor = Math.max(anchor, iv.end);
            if (++job.index < avails.get(job.eid).size())
                pq.offer(job);
        }

        return ans;
    }
	
	static class Job {
	    int eid, index;
	    Job(int e, int i) {
	        eid = e;
	        index = i;
	    }
	}
	
	
	
	/**
	 * 773. Sliding Puzzle
	 * 
	 * 
	 * 1 2 3
	 * 4 5 0
	 * 
	 *             x - 1, y
	 *   x, y - 1  x,  y    x, y + 1
	 *             x + 1, y    
	 */ 
	
	public static void test773() {
		int[][] board = {
				{1, 2, 3},
				{5, 0, 4}
		};
		int rev = task773_slidingPuzzle(board);
		System.out.println("=>> rev: " + rev);
		
	}
	public static int task773_slidingPuzzle(int[][] board) {
		String target = "123450";
		Queue<String> q = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				stb.append(board[i][j]);
			}
		}
		q.offer(stb.toString());
		visited.add(stb.toString());
		int result = 0;
		while (!q.isEmpty()) {
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				String cur = q.poll();
				if (cur.equals(target)) return result;
				int index = cur.indexOf('0');
				int rowIdx = index / 3;
				int colIdx = index % 3;
				
				for (int k = 0; k < 4; k++) {
					int nextRowIdx = rowIdx + dx[k];
					int nextColIdx = colIdx + dy[k];
					System.out.println("nextRowIdx: " + nextRowIdx);
					System.out.println("nextColIdx: " + nextColIdx);
					if (nextRowIdx >= 0 && 
						nextRowIdx < 2 && 
						nextColIdx >= 0 && 
						nextColIdx < 3) {
						System.out.println("hello");
						// next position is valid
						int nextIdx = nextRowIdx * 3 + nextColIdx;
						char[] arr = cur.toCharArray();
						// swap
						char temp = arr[index];
						arr[index] = arr[nextIdx];
						arr[nextIdx] = temp;
						String nextString = new String(arr);
						
						if (!visited.contains(nextString)) {
							visited.add(nextString);
							q.offer(nextString);
						}
					}
				}
			}
			result ++;
		}
		return -1;
    }
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	
	/**
	 * task784: 
	 * 784. Letter Case Permutation
	 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  
	 * Return a list of all possible strings we could create.
	 * 
	 * Examples:
	 * Input: S = "a1b2"
	 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
	 * 
	 * Input: S = "3z4"
	 * Output: ["3z4", "3Z4"]
	 * 
	 * Input: S = "12345"
	 * Output: ["12345"]
	 */
	public static void test784() {
		String s1 = "a1b2";
		List<String> rev = task784_letterCasePermutation(s1);
		System.out.println(rev);
	}
	
    public static List<String> task784_letterCasePermutation(String S) {
        List<StringBuilder> ans = new ArrayList();
        ans.add(new StringBuilder());

        for (char c: S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n+i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; ++i)
                    ans.get(i).append(c);
            }
        }

        List<String> finalans = new ArrayList();
        for (StringBuilder sb: ans)
            finalans.add(sb.toString());
        return finalans;
    }
    
    /**
     * 785 
     * is graph bipartite
     * input: graph
     * 
     * e.g 
     *    0       1       2       3
     * [[1, 3], [0, 2], [1, 3], [0, 2]]
     * 
     * edge: [0, 1], [0, 3], [1, 0], [1, 2]....
     */
    
    public static void test785() {
    		int[][] graph = {
    				{1,2,3}, {0,2}, {0,1,3}, {0,2}
    		};
    		
    		boolean rev = task785_isBipartite(graph);
    		System.out.println("==>>> rev: " + rev);
    		
    }
    public static boolean task785_isBipartite(int[][] graph) {
    		Map<Integer, Integer> map = new HashMap<>();  		 		
    		for (int i = 0; i < graph.length; i++) {
    			if (!task785_BFS(graph, map, i)) {
    				return false;
    			}
    		}
    		return true;
    }
    
    public static boolean task785_BFS(int[][] graph, Map<Integer, Integer> map, int node) {
    		if (map.containsKey(node)) {
    			return true;
    		}
    		
    		Queue<Integer> q = new LinkedList<>();
    		q.offer(node);
    		map.put(node, 0); // put the flag
    		
    		while (!q.isEmpty()) {
    			int cur = q.poll();
    			int curSign = map.get(cur);
    			int[] neis = graph[cur];
    			for (Integer nei: neis) {
    				int nextSign = curSign == 0 ? 1 : 0;
    				if (!map.containsKey(nei)) {
    					map.put(nei, nextSign);
    					q.offer(nei);
    				} else {
    					if (nextSign != map.get(nei)) {
    						return false;
    					}
    				}
    			}
    		}
    		return true;
    }
    
    
    

}
