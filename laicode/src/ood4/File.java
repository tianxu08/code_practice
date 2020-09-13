package ood4;

public class File extends Entry {

	private String content;  // content in the file
	private int size;        // size
	
	public File(String n, Directory p, int sz) {
		// TODO Auto-generated constructor stub
		super(n, p);
		size = sz;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	
	public String getContents() {
		return content;
	}
	
	public void setContents(String c) {
		content = c;
	}

}
