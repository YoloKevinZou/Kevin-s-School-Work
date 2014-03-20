import java.util.Date;
@javax.jdo.annotations.PersistenceCapable

public class Ticket {
	
	private int _ticketNumber;
	private Date _soldDate;
	public CustomerAccount _ticketPurchased;
	public Seat _ticketSeat;
	public Price _price;
	public Date _date;
	public Event _forEvent;
	public Seat _forSeat;
	
	public Ticket(CustomerAccount ca, Seat s, Event e){
		
		_ticketPurchased = ca;
		_ticketSeat = s;
		_forEvent = e;
		
	}
	
}