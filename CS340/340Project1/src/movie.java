public class movie extends Thread {

	private static int session = 1;
	private static int seatCount = 1;
	private static volatile boolean seatFull = false;
	
	public movie(String x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// littleSleep(100);
		System.out.println("[" + clock.age()
				+ "] Speaker arrives and waits for the show to start.");

		// waiting to be interrupted for presentation 1
		littleSleep(50000);
		
		//starts presentation 1
		presentation();

		// sleep for speaker presentation 2
		littleSleep(500);

		//presentation 1 finishes
		presentationFinish();

		// sleep until it gets interrupt to start presentation 2
		littleSleep(50000);

		//starts presentation 2
		presentation();

		//sleep for speaker presentation 2
		littleSleep(500);
		
		//presentation 2 finishes
		presentationFinish();

	}

	//sleep method so I don't have to 
	//write try and catch over and over
	public static void littleSleep(int x) {
		try {
			sleep(x);
		} catch (InterruptedException e) {
		}
	}

	public void presentationFinish() {
		System.out.println("[" + clock.age() + "] Session " + session
				+ " Presentation is finished and Show starts");
		Driver.clock.setPresentationFinish(true);
		
	}

	public void presentation() {
		littleSleep(200);
		System.out.println("------------------------Session " + session + "----------------------------");
		System.out.println("[" + clock.age() + "] "
				+ "Speaker is giving a presentation for Session " + session);
	}
	
	//function for visitors to take seat for each session
	public synchronized void takeSeat(visitors x) {
		
		//if seat is not full the visitors can take the seat
		if (!seatFull) {
			System.out
					.println("["
							+ clock.age()
							+ "] "
							+ x.getName()
							+ " enters the room and takes an available seat for Session: "
							+ session);
			seatCount++;
			
			//if 6 seats are taken
			//set the seatFull boolean to true
			if(seatCount==7){
				seatFull=true;
			}
				
			x.setAttended(true);

		} else {

			// if seats are full for session 2,
			// visitors go directly to the museum
			if (session == 2) {
				System.out.println("[" + clock.age()
						+ "] No more seats available " + x.getName()
						+ " goes to the museum");
				x.yield();
				return;
			}

			System.out.println("[" + clock.age() + "] No more seats available "
					+ x.getName() + " leaves the Lobby");

		}

	}

	//set the seatCount boolean
	public synchronized void setSeatCount(int x) {
		seatCount = x;
	}
	
	//increment the session variable
	public synchronized static void sessionInc() {
		session++;
	}
	
	//gets the session number that it is in
	public static int getSession() {
		return session;
	}
	
	//return the boolean seatFull
	public static boolean getSeatFull() {
		return seatFull;
	}
	
	public synchronized static void setSeatFull(boolean x){
		seatFull = x;
	}

}
