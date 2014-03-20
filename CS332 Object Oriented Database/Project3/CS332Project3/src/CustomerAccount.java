import java.util.HashSet;
@javax.jdo.annotations.PersistenceCapable

public class CustomerAccount {
	
	private int _accountID;
	private String _dOB;
	private int _sSN;
	private int _accountPIN;
	private String _dateOfCreation;
	public HashSet<Ticket> _ticketPurchased = new HashSet<Ticket>();
	
	public CustomerAccount(int accountID, String dob, int ssn, int accountPIN, String dateOfCreation){
		
		_accountID = accountID;
		_dOB = dob;
		_sSN = ssn;
		_accountPIN = accountPIN;
		_dateOfCreation = dateOfCreation;
		
	}
	
}