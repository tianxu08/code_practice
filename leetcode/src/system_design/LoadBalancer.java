package system_design;
import java.util.*;

public class LoadBalancer {

    private Map<Integer, Integer> dict = null;
    private List<Integer> servers = null;

    public LoadBalancer() {
        // Initialize your data structure here.
        dict = new HashMap<Integer, Integer>();
        servers = new ArrayList<Integer>();
    }

    // @param server_id add a new server to the cluster 
    // @return void
    public void add(int server_id) {
        // Write your code here
        int m = servers.size();
        dict.put(server_id, m);
        servers.add(server_id);
    }

    // @param server_id server_id remove a bad server from the cluster
    // @return void
    public void remove(int server_id) {
        // Write your code here
        int index = dict.get(server_id);
        int m = servers.size();
        dict.put(servers.get(m - 1), index);
        servers.set(index, servers.get(m - 1));
        servers.remove(m - 1);
        dict.remove(server_id);
    }

    // @return pick a server in the cluster randomly with equal probability
    public int pick() {
        // Write your code here
        Random r = new Random();
        int m = servers.size();
        int index = Math.abs(r.nextInt()) % m;
        return servers.get(index);
    } 
}