package system_design;

import java.util.HashMap;
import java.util.Map;



public class Memcache {
	
	public class Resource {
	    public int value;
	    public int expired;
	    public Resource(int value, int expired) {
	        this.value = value;
	        this.expired = expired;
	    }
	}

    Map<Integer, Resource> client = null;

    public Memcache() {
        // Initialize your data structure here.
        client = new HashMap<Integer, Resource>();
    }

    public int get(int curtTime, int key) {
        // Write your code here
        if (!client.containsKey(key))
            return Integer.MAX_VALUE;

        Resource res = client.get(key);
        if (res.expired >= curtTime || res.expired == -1)
            return res.value;
        else
            return Integer.MAX_VALUE;
    }

    public void set(int curtTime, int key, int value, int ttl) {
        // Write your code here
        int expired;
        if (ttl == 0)
            expired = -1;
        else
            expired = curtTime + ttl - 1;
        client.put(key, new Resource(value, expired));
    }

    public void delete(int curtTime, int key) {
        // Write your code here
        if (!client.containsKey(key))
            return;
        client.remove(key);
    }
    
    public int incr(int curtTime, int key, int delta) {
        // Write your code here
        if (!client.containsKey(key))
            return Integer.MAX_VALUE;
        client.get(key).value += delta;
        return get(curtTime, key);
    }

    public int decr(int curtTime, int key, int delta) {
        // Write your code here
        if (!client.containsKey(key))
            return Integer.MAX_VALUE;
        client.get(key).value -= delta;
        return get(curtTime, key);
    }
}
