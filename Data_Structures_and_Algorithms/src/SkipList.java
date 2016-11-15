import java.util.Random;


public class SkipList {
	public SkipListEntry head; //first elem. of top level
	public SkipListEntry tail; //last elem. of top level
	
	public int n; //number of entries in SkipList
	
	public int h; //height of skip list
	public Random r; //coin toss
	
	public SkipList() { 
		SkipListEntry p1, p2; 
		
		p1 = new SkipListEntry(SkipListEntry.negInf, Integer.MIN_VALUE); 
		p2 = new SkipListEntry(SkipListEntry.posInf, Integer.MIN_VALUE); 
		
		p1.right = p2; 
		p2.left = p1; 
		
		head = p1; 
		tail = p2; 
		
		n = 0; 
		h = 0; 
		
		r = new Random(); 
	}
	
	/**
	 * finds the Entry with value = to requested, or floor of requested
	 * @param value
	 * @return
	 */
	public SkipListEntry search(String key) { 
		SkipListEntry current = head; 
		
		while(true) { 
			/**
			 * (1) Go right until you find the key, or until your reach the tail.
			 * (2) If you reach the tail, go down a level(next method).
			 * (3) Repeat first two steps on lower level in successive iteration of greater
			 * while loop.
			 */
			while(current.right.key != SkipListEntry.posInf &&
					current.right.key.compareTo(key) <= 0) { 
				current = current.right; 
			}
			/**
			 * Go down a level if you can...
			 */
			if(current.down != null) { 
				current = current.down; 
			} else { 
				break; // Can't go down any further
			}
		}
		return current; //return current SkipListEntry with value <= requested value
	}
	
	public Integer get(String key) { 
		SkipListEntry current = search(key); 
		
		if(key.equals(current.key)) { 
			return current.value; 
		} else { 
			return null; 
		}
	}
	
	public Integer put(String key, Integer value) { 
		SkipListEntry p, q; 
		int i = 0; 
		
		p = search(key); 
		
		if(key.equals(p.key)) { 
			Integer old = p.value; 
			p.value = value; 
			return old; 
		}
		
		q = new SkipListEntry(key, value);
		
		q.left = p; 
		q.right = p.right; 
		p.right.left = q; 
		p.right = q; 
		
		i = 0; 
		
		while(r.nextDouble() < 0.5) { 
			if( i >= h) { 
				addLayer(); 
			}
			while(p.up == null) { 
				 p = p.left; 
			}
			
			p = p.up; 
			
			SkipListEntry e = new SkipListEntry(key, null); 
			
			e.left = p; 
			e.right = p.right; 
			e.down = q; 
			p.right.left = e; 
			p.right = e; 
			q.up = null; 
			
			q = e; 
			
			i = i + 1; 
		}
		
		n = n + 1; 
		
		return null; 
	}
	
	public void addLayer() { 
		SkipListEntry p1 = new SkipListEntry(SkipListEntry.negInf, null); 
		SkipListEntry p2 = new SkipListEntry(SkipListEntry.posInf, null);
		
		p1.right = p2; 
		p1.down = head; 
		
		p2.left = p1; 
		p2.down = tail; 
		
		head.up = p1; 
		tail.up = p2; 
		
		head = p1; 
		tail = p2; 
		
		h = h + 1; 
	}
	
	  public void printVertical()
	  {
	     String s = "";

	     SkipListEntry p;

	     p = head;

	     while ( p.down != null )
	        p = p.down;

	     while ( p != null )
	     {
	        s = getOneColumn( p );
		System.out.println(s);

	        p = p.right;
	     }
	  }


	  public String getOneColumn( SkipListEntry p )
	  {
	     String s = "";

	     while ( p != null )
	     {
	        s = s + " " + p.key;

	        p = p.up;
	     }

	     return(s);
	  }

	  public void printHorizontal()
	  {
	     String s = "";
	     int i;

	     SkipListEntry p;

	     /* ----------------------------------
		Record the position of each entry
		---------------------------------- */
	     p = head;

	     while ( p.down != null )
	     {
	        p = p.down;
	     }

	     i = 0;
	     while ( p != null )
	     {
	        p.pos = i++;
	        p = p.right;
	     }

	     /* -------------------
		Print...
		------------------- */
	     p = head;

	     while ( p != null )
	     {
	        s = getOneRow( p );
		System.out.println(s);

	        p = p.down;
	     }
	  }

	  public String getOneRow( SkipListEntry p )
	  {
	     String s;
	     int a, b, i;

	     a = 0;

	     s = "" + p.key;
	     p = p.right;


	     while ( p != null )
	     {
	        SkipListEntry q;

	        q = p;
	        while (q.down != null)
		   q = q.down;
	        b = q.pos;

	        s = s + " <-";


	        for (i = a+1; i < b; i++)
	           s = s + "--------";
	 
	        s = s + "> " + p.key;

	        a = b;

	        p = p.right;
	     }

	     return(s);
	  }

	
	public class SkipListEntry { 
		public String key; 
		public Integer value; 
		
		public int pos; 
		
		public SkipListEntry up, down, left, right;
		
		public static final String negInf = "-oo"; 
		public static final String posInf = "+oo"; 
		
		public SkipListEntry(String key, Integer value) { 
			this.key = key; 
			this.value = value; 
			
			up = down = left = right = null; 
		}
	}

}
