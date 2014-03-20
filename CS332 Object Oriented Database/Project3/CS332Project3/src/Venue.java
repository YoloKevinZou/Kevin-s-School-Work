import java.util.HashSet;
@javax.jdo.annotations.PersistenceCapable

public class Venue {
	
	private int _venueID;
	private String _venueName;
	public HashSet<Event> _holds = new HashSet<Event>();
	
	public Venue(int venueID, String venueName){
		
		_venueID = venueID;
		_venueName = venueName;
		
	}
	
}