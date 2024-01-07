import java.util.List;

import db.MySQLAccess;
import objects.Station;

public class TestBase {

	public static void main(String[] args) {
		System.out.println("stations");
	    MySQLAccess dao = new MySQLAccess();
	    try {
			List<Station> stations = dao.getStations();
			
			for(int i = 0; i < stations.size(); i++) {
				
				System.out.println(stations.get(i).getStation_Name());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
