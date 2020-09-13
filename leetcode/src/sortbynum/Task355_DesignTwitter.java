package sortbynum;
import java.util.*;


/**
 * 
 * @author xutian
 * Design a simplified version of Twitter where users can post tweets, 
 * follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. 
 * Your design should support the following methods:
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 
 * 		Each item in the news feed must be posted by users who the user followed or by the user herself. 
 * 		Tweets must be ordered from most recent to least recent.
 * 		// user's friends and user itself
 * 
 * follow(followerId, followeeId): Follower follows a followee.
 * 		
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * 
 * 
 * Tweet{
 * 	  tweetId;
 * 	  timestamp;
 * 	  Tweet next;
 * }
 * 
 * 
 * for each user
 * User{
 * 	  userId;
 *    Set<userId> followed;
 *    Tweet tweet_head; 
 * }
 * 
 */

public class Task355_DesignTwitter {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task355_DesignTwitter sln = new Task355_DesignTwitter();
		sln.postTweet(1, 100);
		List<Integer> param2 = sln.getNewsFeed(1);
		for(Integer i: param2) {
			System.out.println(i);
		}
		
	}
	public static int timeStamp = 0;
	
	public Map<Integer, User> userMap;
	
	public class User{
		public int id;
		public Set<Integer> followed;
		public Tweet tweet_head;
		
		public User(int id) {
			this.id = id;
			followed = new HashSet<Integer>();
			followed.add(id);  // follow itself
			tweet_head = null;
		}
		
		public void follow(int id) {
			followed.add(id);
		}
		
		public void unfollow(int id) {
			followed.remove(id);
		}
		
		public void post(int tweetId) {
			Tweet newTweet = new Tweet(tweetId);
			newTweet.next = tweet_head;
			tweet_head = newTweet;
		}
	}
	
	public class Tweet{
		public int id;
		public int time;
		public Tweet next;
		
		public Tweet(int id) {
			this.id = id;
			this.time = timeStamp ++;
			next = null;
		}
	}
	
	
	
	 /** Initialize your data structure here. */
    public Task355_DesignTwitter() {
        userMap = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
			userMap.put(userId, new User(userId));
		}
        userMap.get(userId).post(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. 
     * Each item in the news feed must be posted by users who the user followed or by the user herself. 
     * Tweets must be ordered from most recent to least recent. 
     */
    public List<Integer> getNewsFeed(int userId) {
    	// traverse the user's followed list
    	// user a priority queue to get the most recent tweed
    	Comparator<Tweet> myComp = new Comparator<Tweet>() {
			
			@Override
			public int compare(Tweet o1, Tweet o2) {
				// TODO Auto-generated method stub
				if (o1.time == o2.time) {
					return 0;
				}
				return o1.time > o2.time ? -1 : 1;
			}
		};
		
		Set<Integer> friends = userMap.get(userId).followed;
		
    	PriorityQueue<Tweet> maxHeap = new PriorityQueue<Tweet>(friends.size(), myComp);
    	
    	for(int friend: friends) {
    		Tweet t = userMap.get(friend).tweet_head;
    		if (t != null) {
				maxHeap.add(t);
			}
    	}
    	int n = 0;
    	List<Integer> result = new ArrayList<Integer>();
    	while( !maxHeap.isEmpty() && n < 10) {
    		Tweet curT = maxHeap.poll();
    		result.add(curT.id);
    		if (curT.next != null) {
				maxHeap.offer(curT.next);
			}
    		n ++;
    	}
    	
    	return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followeeId)) {
			userMap.put(followeeId, new User(followeeId));
		}
        if (!userMap.containsKey(followerId)) {
			userMap.put(followerId, new User(followerId));
		}
        userMap.get(followeeId).followed.add(followerId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId) {
			return ;
		}
        userMap.get(followerId).unfollow(followeeId);
    }

}
