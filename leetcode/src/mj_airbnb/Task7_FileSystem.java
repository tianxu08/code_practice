package mj_airbnb;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Task7_FileSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task7_FileSystem sln = new Task7_FileSystem();
		sln.create("/a", 1);
		int rev = sln.get("/a");
		System.out.println("==>> rev: " + rev);
		sln.create("/a/b", 2);
		int rev2 = sln.get("/a/b");
		System.out.println("==>> rev2: " + rev2);
		
		
		sln.create("/a/c/d", 3);
		int rev3 = sln.get("/a/c/d");
		System.out.println("==>> rev3: " + rev3);
		
		sln.watch("/a", () -> System.out.println("hello world"));
		
		
		for (Entry<String, Integer> entry : sln.pathMap.entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
		
		for (Entry<String, Runnable> entry: sln.callbackMap.entrySet()) {
			System.out.println(entry.getKey() + " --> " + entry.getValue());
		}
	}

	// path, value
	public Map<String, Integer> pathMap;
	// path, callback function
	public Map<String, Runnable> callbackMap;
	
	public Task7_FileSystem() {
		this.pathMap = new HashMap<>();
		this.callbackMap = new HashMap<>();
		pathMap.put("", 0);
	}
	
	public boolean create(String path, int value) {
		if (pathMap.containsKey(path)) return false;// already exist
		
		int lastSlashIndex = path.lastIndexOf("/");
		
		String prefix = path.substring(0, lastSlashIndex);
				
		if (!pathMap.containsKey(prefix)) {
			return false;
		}
		pathMap.put(path, value);
		return true;
	}
	
	
	public boolean set(String path, int value) {
		if (!pathMap.containsKey(path)) {
			return false;
		}
		pathMap.put(path, value);
		
		String curPath = path;
		while(!curPath.isEmpty()) {
			if (callbackMap.containsKey(curPath)) {
				callbackMap.get(curPath).run();
			}
			int lastSlashIndex = path.lastIndexOf("/");
			curPath = path.substring(0, lastSlashIndex);
		} 
		return true;
	}
	
	/**
	 * 
	 * @param: path
	 * @return: value
	 */
	public int get(String path) {
		if (pathMap.containsKey(path)) {
			return pathMap.get(path);
		}
		return -1;
	}
	
	public boolean watch(String path, Runnable callback) {
		if (!pathMap.containsKey(path)) {
			return false;
		}
		callbackMap.put(path, callback);
		return true;
	}
	
	
	
	
}
