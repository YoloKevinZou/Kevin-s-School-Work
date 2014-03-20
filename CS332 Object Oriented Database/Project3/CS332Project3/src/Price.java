import java.util.HashSet;
@javax.jdo.annotations.PersistenceCapable

public class Price {
	
	private int _cost;
	public HashSet<Event> _eventPrice = new HashSet<Event>();
	
}