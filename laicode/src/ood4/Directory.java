package ood4;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Entry {

	protected List<Entry> contents; // children list
	
	public Directory(String n, Directory p) {
		super(n, p);
		contents = new ArrayList<Entry>();
	}
	
	public List<Entry> getContents() {
		return contents;
	}
	
	public int size() {
		int size = 0;
		for(Entry e : contents) {
			size += e.size();
		}
		return size;
	}
	
	public int numberOfFiles() {
		int count = 0;
		for(Entry e : contents) {
			if (e instanceof Directory) {
				count ++;  // directory counts as a file
				Directory d = (Directory)e;
				count += d.numberOfFiles(); // recursion to get d's file number
			} else if (e instanceof File) {
				count ++;
			}
		}
		return count;
	}
	
	public boolean deleteEntry(Entry entry) {
		return contents.remove(entry);
	}
	
	public void addEntry(Entry entry) {
		contents.add(entry);
	}
	
	
}
