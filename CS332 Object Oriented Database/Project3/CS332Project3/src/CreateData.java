import java.util.*;
import javax.jdo.*;
import com.objectdb.Utilities;
@javax.jdo.annotations.PersistenceCapable

public abstract class CreateData
{
	public static void main(String argv[])
	{
		
		Performer p1 = new Performer(1, "718-000-0000", "Male", 43, "Kevin", "Zou");
		Performer p2 = new Performer(2, "718-111-1111", "Male", 44, "Haiqiang", "Zou");
		Performer p3 = new Performer(3, "718-222-2222", "Male", 45, "Tommy", "Zou");
		Performer p4 = new Performer(4, "718-333-3333", "Male", 46, "Kenneth", "Zou");
		Performer p5 = new Performer(5, "718-444-4444", "Male", 47, "Danny", "Zou");
		
		PersistenceManager pm = Utilities.getPersistenceManager("test.odb");
		
		pm.currentTransaction().begin();
		pm.makePersistent(p1);
		pm.makePersistent(p2);
		pm.makePersistent(p3);
		pm.makePersistent(p4);
		pm.makePersistent(p5);
		pm.currentTransaction().commit();
		
		Event e1 = new Event(1, "Rockets vs Laker", "Sports");
		Event e2 = new Event(2, "Lady Gaga Concert", "Music");
		Event e3 = new Event(3, "Fast and Furious", "Movie");
		Event e4 = new Event(4, "Magic Show", "Shows");
		Event e5 = new Event(5, "Talk Show", "Shows");
		
		pm.currentTransaction().begin();
		pm.makePersistent(e1);
		pm.makePersistent(e2);
		pm.makePersistent(e3);
		pm.makePersistent(e4);
		pm.makePersistent(e5);
		pm.currentTransaction().commit();
		
		Venue v1 = new Venue(1, "Madison square garden");
		Venue v2 = new Venue(2, "Lincoln Center");
		Venue v3 = new Venue(3, "Rockefeller Center");
		Venue v4 = new Venue(4, "Radio City");
		Venue v5 = new Venue(5, "Queens College Auditorium");
		
		pm.currentTransaction().begin();
		pm.makePersistent(v1);
		pm.makePersistent(v2);
		pm.makePersistent(v3);
		pm.makePersistent(v4);
		pm.makePersistent(v5);
		pm.currentTransaction().commit();
		
		pm.currentTransaction().begin();
		v1._holds.add(e1);
		v1._holds.add(e2);
		v1._holds.add(e3);
		v1._holds.add(e4);
		v1._holds.add(e5);
		
		p1._performs.add(e1);
		p1._performs.add(e2);
		p1._performs.add(e3);
		p1._performs.add(e4);
		p1._performs.add(e5);
		pm.currentTransaction().commit();
		
	}
}