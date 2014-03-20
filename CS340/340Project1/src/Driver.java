
public class Driver {

	public static volatile movie s;
	// public static movie s2;
	public static clock clock;
	final static int NumberVisitors = 15;
	public static visitors[] x = new visitors[NumberVisitors];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("WELCOME TO ELLIS ISLAND");
		
		s = new movie("Speaker");
		clock = new clock();

		// initialize the 15 visitors
		for (int i = 0; i < NumberVisitors; i++) {
			x[i] = new visitors(i + 1);
			x[i].start();
		}
		
		//starts the movie and clock thread
		s.start();
		clock.start();

	}

}
