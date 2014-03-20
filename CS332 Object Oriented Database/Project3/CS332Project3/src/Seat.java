import java.util.HashSet;
@javax.jdo.annotations.PersistenceCapable

public class Seat {
	
	private int _seatNumber;
	private Event available;
	public HashSet<Ticket> _forSeat = new HashSet<Ticket>();
	public HashSet<Price> _seatPrice = new HashSet<Price>();
	
}