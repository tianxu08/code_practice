package mj_airbnb;
import java.util.*;

public class Airbnb_2_DS {
    public static void main(String[] args) {
//        T1_test();
//        T4_test();
        T5_test();
    }

    /**
     * 1 Design Queue with Limited Size of Array
     */

    public static void T1_test() {
        QueueWithFixedArray q = new QueueWithFixedArray(3);
        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        System.out.println(q.size());
        q.test();
        System.out.println(q.poll());
        q.test();
        System.out.println(q.poll());
        q.test();
        System.out.println(q.poll());
        q.test();
    }

    public static class ListNode {
        List<Integer> list;
        ListNode next;

        public ListNode(List<Integer> l) {
            this.list = l;
        }
    }

    public static class QueueWithFixedArray {
        private int fixedSize;
        private int count;
        private int head;
        private int tail;

        private ListNode listHaed;
        private ListNode listTail;

        private ListNode test;


        public QueueWithFixedArray(int fixedSize) {
            this.fixedSize = fixedSize;
            this.count = 0;
            this.head = 0;
            this.tail = 0;
            this.listHaed = new ListNode(new ArrayList<>());
            this.listTail = this.listHaed;
        }

        public void offer(int num) {
            listTail.list.add(num);
            tail++;

            if (tail == fixedSize) {
                ListNode newNode = new ListNode(new ArrayList<>());
                // append newNode and update listTail
                listTail.next = newNode;
                listTail = listTail.next;
                tail = 0;
            }
            count ++;
        }

        public Integer poll() {
            if (count == 0) {
                return null;
            }
            Integer res = listHaed.list.get(head);
            head ++;
            if (head == fixedSize) {
                // the last element in this array
                ListNode next = listHaed.next;
                listHaed.next = null;
                listHaed = next;
                // reset head
                head = 0;
            }
            count --;
            return res;
        }

        public int size() {
            return count;
        }

        public void test() {
            ListNode p = listHaed;
            while(p != null) {
                if (p == listHaed) {
                    System.out.print("[");
                    for (int i = head; i< p.list.size(); i++) {
                        System.out.print(p.list.get(i) + " ");
                    }
                    System.out.println("]");
                } else if (p == listTail) {
                    System.out.print("[");
                    for (int i = 0; i< tail; i++) {
                        System.out.print(p.list.get(i) + " ");
                    }
                    System.out.println("]");
                } else {
                    System.out.println(p.list);
                }

                p = p.next;
            }
        }
    }

    /**
     * 2 List of List (2D List) Iterator
     */
    public static class ListIterator2D implements Iterator<Integer> {
        private Iterator<List<Integer>> rowIter;
        private Iterator<Integer> colIter;

        public ListIterator2D(List<List<Integer>> vec2d) {
            rowIter = vec2d.iterator();
            colIter = Collections.emptyIterator();
        }

        @Override
        public Integer next() {
            return colIter.next();
        }

        @Override
        public boolean hasNext() {
            while((colIter == null || !colIter.hasNext()) && rowIter.hasNext()) {
                colIter = rowIter.next().iterator();
            }
            return colIter != null && colIter.hasNext();
        }

        @Override
        public void remove() {
            while(colIter == null && rowIter.hasNext()) {
                colIter = rowIter.next().iterator();
            }
            if (colIter != null) {
                colIter.remove();
            }
        }
    }


    /**
     * 3 Display Page (Pagination)
     */

    public static List<String> displayPages1(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return res;
        }
        List<String> visited = new ArrayList<>();
        Iterator<String> iter = input.iterator();
        boolean reachEnd = false;
        while (iter.hasNext()) {
            String curr = iter.next();
            String hostId = curr.split(",")[0];
            if (!visited.contains(hostId) || reachEnd) {
                res.add(curr);
                visited.add(hostId);
                iter.remove();
            }

            if (visited.size() == pageSize) {
                visited.clear();
                reachEnd = false;
                if (!input.isEmpty()) {
                    res.add(" ");
                }
                iter = input.iterator();
            }

            if (!iter.hasNext()) {
                iter = input.iterator();
                reachEnd = true;
            }
        }


        return  res;
    }

    public static List<String> displayPages2(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        Iterator<String> iter = input.iterator();
        Set<String> set = new HashSet<>();
        boolean reachEnd = false;
        int couter = 0;
        while (iter.hasNext()) {
            String cur = iter.next();
            String id = (cur.split(",")[0]);
            if (!set.contains(id) || reachEnd) {
                res.add(cur);
                set.add(id);
                iter.remove();
                couter ++;
            }

            if (couter == pageSize) {
                if (!input.isEmpty()) {
                    res.add(" ");
                }
                set.clear();
                couter = 0;
                reachEnd = false;
                iter = input.iterator();
            }

            if (!iter.hasNext()) {
                reachEnd = true;
                iter = input.iterator();
            }
        }
        return res;
    }


    /**
     * 4 Calculator
     */

    public static void T4_test() {
        String input = "(1+(4+5+2)-3)+(6+8)";
        int res = T4_calculator(input);
        System.out.println("res = " + res);
    }

    // space is also fine
    public static int T4_calculator(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        Stack<Integer> numSt = new Stack<>();
        Stack<Character> opSt = new Stack<>();

        int idx = 0;
        while (idx < input.length()) {
            char cur = input.charAt(idx);
            if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                // basic op, prev op cannot be '('
                while (!opSt.isEmpty() &&
                        opSt.peek() != '(' &&
                        hasHigherOrSamePrecedence(opSt.peek(), cur) &&
                        numSt.size() >= 2) {
                    char prevOp = opSt.pop();
                    int num2 = numSt.pop();
                    int num1 = numSt.pop();
                    int res = applyOp(prevOp, num1, num2);
                    numSt.push(res);
                }
                opSt.push(cur);
                idx ++;
            } else if (cur == '(') {
                opSt.push(cur);
                idx ++;
            } else if (Character.isDigit(cur)) {
                // get the number
                int ptr = idx;
                int num = 0;
                while(ptr < input.length() && Character.isDigit(input.charAt(ptr))) {
                    int curNum = (int)(input.charAt(ptr) - '0');
                    num = num * 10 + curNum;
                    ptr ++;
                }
                numSt.push(num);
                idx = ptr;
            } else if (cur == ')') {
                // apply the ops in opSt until meet the '('
                while (!opSt.isEmpty() && opSt.peek() != '(') {
                    char curOp = opSt.pop();
                    int num2 = numSt.pop();
                    int num1 = numSt.pop();

                    System.out.println(">>>>> curOp = " + curOp);
                    int res = applyOp(curOp, num1, num2);
                    numSt.push(res);
                }
                if (!opSt.isEmpty() && opSt.peek() =='(') {
                    opSt.pop();
                }
                idx ++;
            } else {
                idx ++;
            }
        }

        while (!opSt.isEmpty()) {
            char op = opSt.pop();
            int num2 = numSt.pop();
            int num1 = numSt.pop();
            int res = applyOp(op, num1, num2);
            numSt.push(res);
        }

        if (!numSt.isEmpty()) {
            return numSt.peek();
        }
        return -1;
    }

    public static boolean hasHigherOrSamePrecedence(char a, char b) {
        if ((a == '+' || a == '-') && (b == '*' || b == '/')) {
            return false;
        }
        return true;
    }

    public static int applyOp(char op, int a, int b) {
        System.out.println("op = " + op);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else if (op == '/') {
            return a / b;
        } else {
            return -1;
        }
    }


    /**
     * 5 Travel Buddy (Buddy List)
     */


    public static void T5_test() {
        Map<String, Set<String>> friendWishList = new HashMap<>();
        String[] b1Des = {"a", "b", "e", "f"};
        friendWishList.put("b1", new HashSet<>(Arrays.asList(b1Des)));

        String[] b2Des = {"a", "c", "d", "g"};
        friendWishList.put("b2", new HashSet<>(Arrays.asList(b2Des)));

        String[] myList = {"a", "b", "c", "d"};
        Set<String> myWishList = new HashSet<>(Arrays.asList(myList));

        T5_Travel_Buddy t5 = new T5_Travel_Buddy(myWishList, friendWishList);
        List<Buddy> buddies = t5.getBuddies();
        System.out.println(buddies);
        List<String> recomm = t5.recommendCities(10);
        System.out.println(recomm);

    }
    public static class T5_Travel_Buddy {
        private List<Buddy> buddies;
        private Set<String> myWishList;

        public T5_Travel_Buddy(Set<String> myWishList, Map<String, Set<String>> friendsWishList) {
            this.buddies = new ArrayList<>();
            this.myWishList = myWishList;
            for (String name: friendsWishList.keySet()) {
                Set<String > wishList = friendsWishList.get(name);
                Set<String> intersection = new HashSet<>(wishList);
                intersection.retainAll(myWishList);
                int similarity = intersection.size();
                if (similarity >= wishList.size() / 2) {
                    buddies.add(new Buddy(name, similarity, wishList));
                }
            }
        }

        public List<Buddy> getBuddies() {
            return this.buddies;
        }

        public List<Buddy> getSortedBuddies() {
            Collections.sort(buddies);
            List<Buddy> res = new ArrayList<>(buddies);
            return res;
        }

        public List<String> recommendCities(int k) {
            List<String> res = new ArrayList<>();
            List<Buddy> sortedBuddies = getSortedBuddies();

            int i = 0;
            while (k > 0 && i < sortedBuddies.size()) {
                Set<String> diff = new HashSet<>(sortedBuddies.get(i).wishList);
                diff.removeAll(myWishList);
                if (diff.size() <= k) {
                    // push all elems in diff to k
                    res.addAll(diff);
                    k = k - diff.size();
                    i++;
                } else {
                    Iterator<String> it = diff.iterator();
                    while (k > 0) {
                        res.add(it.next());
                        k --;
                    }
                }
            }
            return res;
        }
    }

    public static class Buddy implements Comparable<Buddy>{
        String name;
        int simarility;
        Set<String> wishList;

        Buddy(String name, int simarility, Set<String> wishList) {
            this.name = name;
            this.simarility = simarility;
            this.wishList = wishList;
        }
        @Override
        public int compareTo(Buddy that) {
            if (that.simarility < this.simarility) {
                return -1;
            } else if (that.simarility < this.simarility) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return name + " : " + simarility + " => " + wishList;
        }
    }


    /**
     * 6 Trie: File System
     */


    /**
     * 7 Trie: Palindrome Pairs
     */

}
