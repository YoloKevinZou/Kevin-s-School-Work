@javax.jdo.annotations.PersistenceCapable

public abstract class Customer extends Person {
	
	protected int _customerID;
	protected String _startDate;
	protected CustomerAccount account;
	
}