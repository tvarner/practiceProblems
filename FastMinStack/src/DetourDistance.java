
/**
 * Lyft Programming Challenge: 
 * Calculate the detour distance between two different rides.
 * Given four latitude/longitude pairs, where driver one is 
 * traveling from point A to point B and driver 2 is traveling
 * from point C to point D, write a function (in your language 
 * of choice) to calculate the shorter of the detour distances 
 * the drivers would need to take to to pick-up and drop-off the 
 * other driver.
 * 
 * 
 * @author thomasvarner
 *
 */
public class DetourDistance {
	
	Coordinate src1;
	Coordinate src2;
	Coordinate dest2;
	Coordinate dest1;
	
	double earth_radius = 6371; // in kilometers
	
	/*
	 * For this problem, we know that there are two 
	 * paths to compare (ACDB and CABD), and thus four
	 * distances needed to calculate the shortest detour
	 * (A to B, C to D, A to C, and B to D)
	 */
	double distSrc1ToDest1;
	double distSrc2ToDest2;
	double distSrc1ToSrc2;
	double distDest1ToDest2;
	
	String bestDetour = "";
	double bestDetourLength; // in kilometers
	
	public DetourDistance(Coordinate src1, Coordinate dest1, 
			Coordinate src2, Coordinate dest2) { 
		
		this.src1 = src1;
		this.src2 = src2;
		this.dest1 = dest1;
		this.dest2 = dest2;
		
		/*
		 * Calculate the four needed distances
		 */
		distSrc1ToDest1 = getDistance(this.src1, this.dest1);
		distSrc2ToDest2 = getDistance(this.src2, this.dest2);
		distSrc1ToSrc2 = getDistance(this.src1, this.src2);
		distDest1ToDest2 = getDistance(this.dest1, this.dest2);
		
		findBestDetour();
	}
	
	/*
	 * Calculates distance using Haversine formula
	 */
	private double getDistance(Coordinate A, Coordinate B) { 
		
		double delta_lat = Math.toRadians(B.latitude - A.latitude);
		double delta_lon = Math.toRadians(B.longitude - A.longitude);	
		
		double lat1 = Math.toRadians(A.latitude);
		double lat2 = Math.toRadians(B.latitude);

		double a = Math.sin(delta_lat / 2) * Math.sin(delta_lat / 2) + 
							Math.cos(lat1) * Math.cos(lat2) * 
							Math.sin(delta_lon / 2) * Math.sin(delta_lon / 2);	
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		return earth_radius * c;
	}
	
	/*
	 * Calculates best path (String), and length of the best path
	 * in kilometers (double)
	 */
	private void findBestDetour() { 
		
		double car1Detour = distSrc1ToSrc2 + distSrc2ToDest2 + 
							distDest1ToDest2;
		double car2Detour = distSrc1ToSrc2 + distSrc1ToDest1 + 
							distDest1ToDest2;
		
		if (car1Detour <= car2Detour) { 
			bestDetourLength = car1Detour;
			bestDetour = src1.name + src2.name + 
					dest2.name + dest1.name;
		} else { 
			bestDetourLength = car2Detour;
			bestDetour = src2.name + src1.name + 
					dest1.name + dest2.name;
		}
	}
	
	public String getBestDetour() { 
		return bestDetour;
	}
	
	public double getBestDetourLength() { 
		return bestDetourLength;
	}
	
	static class Coordinate { 
		
		private String name; 
		private double latitude; 
		private double longitude;
		
		public Coordinate(String name, double latitude, 
				double longitude) { 
			this.name = name;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		
		public String getName() { 
			return name;
		}
		
		public double getLatitude() { 
			return latitude;
		}
		
		public double getLongitude() { 
			return longitude;
		}
		
		public void setName(String name) { 
			this.name = name;
		}
		
		public void setLatitude(double lat) { 
			latitude = lat;
		}
		
		public void setLongitude(double lon) { 
			longitude = lon;
		}
	}
	
	public static void main(String[] args) { 
		
		Coordinate A = new Coordinate("A", 38.898556, -77.037852);
		Coordinate B = new Coordinate("B", 40.897147, -77.043934);
		Coordinate C = new Coordinate("C", 39.896734, -77.143609);
		Coordinate D = new Coordinate("D", 38.924657, -77.056893);
		
		DetourDistance dt = new DetourDistance(A, B, C, D);
		
		/*
		 * Print the best path and its length
		 */
		System.out.println("Best detour path: " + dt.getBestDetour());
		System.out.println("Best detour length: " + dt.bestDetourLength);
	}
}
