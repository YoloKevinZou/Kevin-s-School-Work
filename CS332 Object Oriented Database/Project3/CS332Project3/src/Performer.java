import java.util.HashSet;
@javax.jdo.annotations.PersistenceCapable

public class Performer extends Person {
	
	public HashSet<Event> _performs = new HashSet<Event>();

	public Performer(int pIDNum, String HomePhone, String pGender, int age, String fName, String lName){
		
		_pIDNum = pIDNum;
		_pHomePhone = HomePhone;
		_pGender = pGender;
		_age = age;
		_pFirst = fName;
		_pLast = lName;
		
		
	}

}