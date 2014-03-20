import java.util.Date;
import java.util.HashSet;
@javax.jdo.annotations.PersistenceCapable

public class Event {
	
	private int _eID;
	private String _eventName;
	private String _eventType;
	private Seat available;
	public HashSet<Performer> _performs = new HashSet<Performer>();
	public HashSet<Price> _eventPrice = new HashSet<Price>();
	public HashSet<Venue> _holds = new HashSet<Venue>();
	public HashSet<Ticket> _forEvent = new HashSet<Ticket>();
	public HashSet<Date> _date = new HashSet<Date>();
	
	public Event(int eID, String eventName, String eventType){
		
		_eID = eID;
		_eventName = eventName;
		_eventType = eventType;
		
	}
	
}