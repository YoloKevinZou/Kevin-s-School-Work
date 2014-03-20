public class visitors extends Thread {

	private boolean attended = false;
	private volatile boolean endOfDay = false;
	private static volatile int allArriveLobby = 0;

	public visitors(int i) {
		// TODO Auto-generated constructor stub
		super("Visitor-" + i);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// message ("entered ellis island.");
		enterLobby();

		// busy waits until the clock notifies to get into lobby
		while (!Driver.clock.isAlive()) {
		}
		
		// to make output look neat
		littleSleep(50);

		// take the seats for session 1
		Driver.s.takeSeat(this);

		// if they got a seat for session 1 they go watch movie
		if (attended == true) {

			// busy wait for presentation 1 to finish for those ppl that are
			// attending session 1
			while (!clock.presentationFinish()) {
			}

			watchMovie();

			// for the visitors that got into session 1,
			// it busy waits until the session 1 is finish then go to museum
			while (!clock.session1Finish) {
			}

			gotoMuseum();
			

		} else {

			setPriority(10);

			// busy waits for session 1 to finish and session 2 to start
			// then the visitors check for seat availability for session 2
			while (!clock.session1Finish || !Driver.clock.showStartStatus()) {
				System.out.print("");
			}

			Driver.s.takeSeat(this);
			setPriority(NORM_PRIORITY);
			
			littleSleep(100);

			// if second show did not start busy waits
			while (!Driver.clock.showStartStatus()) {
			}

			// if they got into session two display they are watching movie
			if (attended == true) {

				while (!clock.presentationFinish()) {
				}
				
				watchMovie();
				
				// busy waits until session 2 finishes
				while (!clock.session2Finish) {
				}
				
				gotoMuseum();
			}

		}

		// busy waits in the museum till the end of the day
		while (!endOfDay) {
		}

	}

	// a sleep method so i don't have to keep on
	// using try and catch
	private void littleSleep(int x) {
		try {
			sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//prints out the thread that enter lobby and increment the allArriveLobby int
	private synchronized void enterLobby() {
		
		allArriveLobby++;
		System.out.println("[" + clock.age() + "] " + getName()
				+ " arrives Ellis Island and waits in the lobby.");
	}
	
	//prints out the thread that goes to museum
	private void gotoMuseum() {
		System.out.println("[" + clock.age() + "] " + getName()
				+ " goes to the museum");
	}
	
	//prints out the thread that are watching the movie
	private void watchMovie() {
		System.out.println("[" + clock.age() + "] " + getName()
				+ " is watching the movie");
	}

	//set the attend status to see if they hvae attend the session
	public void setAttended(boolean x) {
		attended = x;
	}

	//sets the end of day boolean
	public synchronized void setEndOfDay(boolean x) {
		endOfDay = x;
	}
	
	//checks if all visitors has arrive into lobby
	public boolean allArrivedLobby() {
		if (allArriveLobby == 15) {
			return true;
		}
		return false;
	}



}
