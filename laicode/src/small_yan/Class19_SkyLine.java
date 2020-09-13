package small_yan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Class19_SkyLine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static class Building {
		int x1;
		int x2;
		int height;
		public Building(int _x1, int _x2, int _height) {
			this.x1 = _x1;
			this.x2 = _x2;
			this.height = _height;
		}
	}

	public static class BuildingSep {
		int x;
		int height;
		int type; //-1 stands for start, -2 stands for end
		public BuildingSep(int _x, int _height, int _type) {
			this.x = _x;
			this.height = _height;
			this.type = _type;
		}
	}

	public static class OutlineSpot {
		int x;
		int y;
		public OutlineSpot(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}

	public static final int BUILDING_START = -1;
	public static final int BUILDING_END = -2;
	
	/*
	 * Given a set of 2D coordinates for the 4 corners of each building in a
	 * city skyline (as if in a photograph), how would you determine the outline
	 * of the silhouette of all buildings, where buildings may or may not
	 * overlap?
	 */
	
	/*
	 * Swiping Line Algorithm
	 * (1)Separate(x1, x2, height) to (x1, height, BUILDINGI_START), (x2, height, BUILDING_END)
	 * (2)sort all items by x's increasing order, if x are same, sort via height's decreasing order
	 * (3)swipe all the items from left to right. keep a max heap store height.
	 *    when meet a curElement having BUILDING_START, 
	 *    {
	 *    	first, get the curMaxHeight from the maxHeap. 
	 *    		   curMaxHeight = maxHeap.isEmpty =>0
	 *    							  otherwise =>maxHeap.peek()
	 *    		   if curElement.height <= curMaxHeight, this element is under or on the outline. 
	 *    			  do nothing.
	 *    		   if curElement.height > curMaxHeight, this element is above the outline. 
	 *    			  we need to manipulate two spot. 
	 *    			  one is its intersection with the curMaxHeight horizontal line
	 *    				spot(curElement.x, curMaxHeight) 	   //the lower one
	 *                the other is its own spot
	 *                  spot(curElement.x, curElement.height)  //the higher one
	 *      insert curElement.height into heap.
	 *    }
	 *    
	 *    when meet a curElement having BUILDING_END
	 *    {
	 *    	first, remove curElement.height from heap.
	 *    	  	   get curMaxHeight = maxHeap.isEmpty =>0
	 *    							  otherwise, maxHeap.peek();
	 *    		   if curElement.height <= curMaxHeight, this element is under or on the outline. 
	 *    			  do nothing.
	 *    		   if curElement.height > curMaxHeight, this element is above the outline. 
	 *     			  we need to manipulate two spot. 
	 *     		 	  one is its own spot 
	 *                  spot(curElement.x, curElement.height) //(the higher one)
	 *                  
	 *    			  the other one is its intersection with the curMaxHeight horizontal line
	 *    				spot(curElement.x, curMaxHeight)  //the lower one      
	 *    }
	 */
	
	public static ArrayList<OutlineSpot> buildingOutline(ArrayList<Building> buildings) {
		ArrayList<OutlineSpot> outline = new ArrayList<OutlineSpot>();
		
		ArrayList<BuildingSep> buildingSeps = transfer(buildings);
		
		//for debug:
		for (BuildingSep sep: buildingSeps) {
			System.out.println(sep.x + "  " + sep.height + "  " + sep.type);
		}
		System.out.println("-----------------------------------");
		
		//implement a Max heap
		Comparator<Integer> maxComparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (o1 == o2) {
					return 0;
				}
				return o1 > o2 ? -1 : 1;
			}
		}; 
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(1, maxComparator);
		
		// traverse all the element in buildingSep
		for (int i = 0; i < buildingSeps.size(); i++) {
			// get the cur separator. 
			BuildingSep curSep = buildingSeps.get(i);
			// if this is a start separator
			if (curSep.type == BUILDING_START) {
				int curHeight = heap.isEmpty() ? 0 : heap.peek();
				if (curSep.height > curHeight ) {
					OutlineSpot spot1 = new OutlineSpot(curSep.x, curHeight);
					OutlineSpot spot2 = new OutlineSpot(curSep.x, curSep.height);
					outline.add(spot1);
					outline.add(spot2);
				} 
				heap.add(curSep.height);
			}
			if (curSep.type == BUILDING_END) {
				heap.remove(curSep.height);
				int curHeight = heap.isEmpty() ? 0: heap.peek();
				if (curSep.height > curHeight) {
					OutlineSpot spot1 = new OutlineSpot(curSep.x, curSep.height);
					OutlineSpot spot2 = new OutlineSpot(curSep.x, curHeight);
					outline.add(spot1);
					outline.add(spot2);
				}
			}	
		}
		
		//for debug
		for (OutlineSpot spot : outline) {
			System.out.println(spot.x + " " + spot.y);
		}
		return outline;
		
	} 
	
	// return an arrayList of BuildingSep that sorting by BuildingStep.x
	// if buildingSep1.x == buildingSep2.x, compare their height
	public static ArrayList<BuildingSep> transfer(ArrayList<Building> buildings) {
		ArrayList<BuildingSep> buildingSeps = new ArrayList<BuildingSep>();
		for(Building bld: buildings) {
			BuildingSep start = new BuildingSep(bld.x1, bld.height, BUILDING_START);
			BuildingSep end = new BuildingSep(bld.x2, bld.height, BUILDING_END);
			buildingSeps.add(start);
			buildingSeps.add(end);
		}
		
		//compare by the BuildingSep.x, increasing order
		//if o1.x == o2.x, compare by o1.height in decreasing order
		Comparator<BuildingSep> bldComparator = new Comparator<BuildingSep>() {

			@Override
			public int compare(BuildingSep o1, BuildingSep o2) {
				// TODO Auto-generated method stub
				if (o1.x > o2.x) {
					return 1;
				} else if (o1.x < o2.x) {
					return -1;
				} else {
					return o2.height - o1.height;
				}
			}
		};
		Collections.sort(buildingSeps, bldComparator);
		return buildingSeps;
	}
	public static void test() {
		Building bld1 = new Building(1, 4, 3);
		Building bld2 = new Building(2, 5, 4);
		Building bld3 = new Building(6, 7, 1);
		Building bld4 = new Building(8, 11,5);
		Building bld5 = new Building(8,15,4);
		Building bld6 = new Building(9, 12, 3);
		Building bld7 = new Building(13, 16, 5);
		
		ArrayList<Building> buildings = new ArrayList<Building>();
		buildings.add(bld3);
		buildings.add(bld1);
		buildings.add(bld2);
		buildings.add(bld4);
		buildings.add(bld5);
		buildings.add(bld6);
		buildings.add(bld7);
		ArrayList<OutlineSpot> result = new ArrayList<OutlineSpot>();
		result = buildingOutline(buildings);
		//for debug:
		System.out.println("================print the output==============");
		for (OutlineSpot spot : result) {
			System.out.println(spot.x + " " + spot.y);
		}	
	}
	
	public static void test2() {
		Building bld1 = new Building(1, 4, 3);
		Building bld2 = new Building(2, 5, 4);
		Building bld3 = new Building(6, 7, 1);
		Building bld4 = new Building(8, 11,5);
		Building bld5 = new Building(8,15,4);
		Building bld6 = new Building(9, 12, 3);
		Building bld7 = new Building(13, 16, 5);
		
		ArrayList<Building> buildings = new ArrayList<Building>();
		buildings.add(bld3);
		buildings.add(bld1);
		buildings.add(bld2);
		buildings.add(bld4);
		buildings.add(bld5);
		buildings.add(bld6);
		buildings.add(bld7);
		
		Comparator<Building> bldComparator = new Comparator<Building>() {
			@Override
			public int compare(Building o1, Building o2) {
				// TODO Auto-generated method stub
				if (o1.x1 > o2.x1) {
					return 1;
				} else if (o1.x1 < o2.x1) {
					return -1;
				} else {
					//o1.x1 == o2.x1
					return o1.x2 - o2.x2;
				}
			}
		};
		Collections.sort(buildings, bldComparator);
		for (Building bld: buildings) {
			System.out.println(bld.x1 +" " +  bld.x2+" " + bld.height);
		}
	}
}

