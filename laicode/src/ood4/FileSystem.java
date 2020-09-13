package ood4;

import java.util.List;

public class FileSystem {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSystem sln = new FileSystem();
		String path = "/foo/bar/baz";
		sln.resolve(path);
	}
	
	private final Directory root;
	
	public FileSystem() {
		root = new Directory("/", null);
	}
	
	
	/*
	 * return a list of Entry
	 */
	public List<Entry> resolve(String path) {
		// TODO: write a program to solve path like '/foo/bar/baz'
//		String[] pathArr = path.trim().split("/");
//		List<Entry> list = new ArrayList<Entry>();
//		list.add(root);
//		for(int i = 1; i < pathArr.length; i++ ) {
//			Entry parent = list.get(list.size() - 1);
//			if (parent) {
//				
//			}
//			System.out.println(pathArr[i]);
//			System.out.println("--------");
//		}
//		System.out.println(pathArr.length);
		
		
		return null;
	}
	
	public void mkdir(String path) {
		// TODO: create a new directory with the given path
		List<Entry> list = resolve(path);
		
	}
	
	public void createFile(String path) {
		// TODO: create 
		
	}
	
	public void delete(String path) {
		// TODO: delete the file/directory with the given path
		
	}
	
	public Entry[] list(String path) {
		// TODO: list all the immediate children of the directory specified by the given path 
		List<Entry> list = resolve(path);
		Directory curDirectory = (Directory)list.get(list.size() - 1);
		List<Entry> contents = curDirectory.contents;
		int size = curDirectory.contents.size();
		Entry[] entries = new Entry[size];
		for(int i = 0; i < size; i ++) {
			entries[i] = contents.get(i);
		}
		return entries;
	}
	
	public int count() {
		// TODO: return the total number of files/directories in the FileSystem
		
		return 0;
	}
}
