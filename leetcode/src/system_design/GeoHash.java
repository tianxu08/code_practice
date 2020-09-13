package system_design;

public class GeoHash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeoHash sln = new GeoHash();
		double latitude = 0.00;
		double longitude = 90.00;

		int precision = 12;
		String rev = sln.encode(latitude, longitude, precision);
		System.out.println("rev = " + rev);
	}

	public static String encode(double latitude, double longitude, int precision) {

		char[] map = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'b',
				'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		// Write your code here

		StringBuilder latStr = new StringBuilder();
		StringBuilder longStr = new StringBuilder();

		double lat_min = -90.0;
		double lat_max = 90.0;
		double long_min = -180.0;
		double long_max = 180.0;

		int len = precision * 5 / 2;

		while (len > 0) {
			double lat_mid = (lat_min + lat_max) / 2.0;
			double long_mid = (long_min + long_max) / 2;
			// for latitude
			if (latitude < lat_mid) {
				// lower
				latStr.append("0");
				lat_max = lat_mid;
			} else {
				// upper
				latStr.append("1");
				lat_min = lat_mid;
			}
			// for longitude
			if (longitude < long_mid) {
				// left
				longStr.append("0");
				long_max = long_mid;
			} else {
				// right
				longStr.append("1");
				long_min = long_mid;
			}
			len--;
		}

		System.out.println(latStr.toString());
		System.out.println(longStr.toString());

		StringBuilder stb = new StringBuilder();
		int i = 0;
		while (i < latStr.length()) {
			stb.append(longStr.charAt(i));
			stb.append(latStr.charAt(i));
			i++;
		}

		System.out.println(stb.toString());

		System.out.println("s.size = " + stb.length());

		StringBuilder rev = new StringBuilder();
		// compose the base32 string
		for (i = 0; i < stb.length(); i += 5) {
			String cur5Digits = i + 5 >= stb.length() ? stb.substring(i,
					stb.length()) : stb.substring(i, i + 5);
			System.out.println(cur5Digits);
			int curVal = Integer.parseInt(cur5Digits, 2);
			rev.append(map[curVal]);

		}

		System.out.println(rev.toString());

		return rev.toString();

	}
	
	public static double[] decode(String geohash) {
        // Write your code here
        String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        int[] mask = {16, 8, 4, 2, 1};
        double[] lon = {-180, 180};
        double[] lat = {-90, 90};
        boolean is_even = true;

        for (int i = 0; i < geohash.length(); ++i) {
            int val = _base32.indexOf(geohash.charAt(i));
            for (int j = 0; j < 5; ++j) {
                if (is_even) {
                    refine_interval(lon, val, mask[j]);
                } else {
                    refine_interval(lat, val, mask[j]);
                }
                is_even = ! is_even;
            }
        }
        double[] location = {(lat[0] + lat[1]) / 2.0, (lon[0] + lon[1]) / 2.0};
        return location;
    }

    public static void refine_interval(double[] interval, int val, int mask) {
        if ((val & mask) > 0) {
            interval[0] = (interval[0] + interval[1]) / 2.0;
        } else {
            interval[1] = (interval[0] + interval[1]) / 2.0;
        }
    }


}
