public class clock extends Thread {

	final static long startTime = System.currentTimeMillis();
	public volatile static boolean session1Finish = false;
	public volatile static boolean session2Finish = false;
	private volatile static boolean showStart = false;
	private volatile static boolean presentationFinish = false;

	// private boolean endOfDay = false;

	public clock() {
		super("clock");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//movie.littleSleep(50);
		
		System.out.println("[" + age() + "] " + "Clock sets session " + movie.getSession()
				+ " and notifies all visitor to enter the room");
		
		//wait for the seats to get full for session 1
		while(!movie.getSeatFull()){
		}
		
		//interrupt speaker to give presentation 1
		Driver.s.interrupt();

		// clock thread is sleeping until the session 1 finishes
		movie.littleSleep(5000);
		
		//prints out session 1 has been finished
		System.out.println("[" + age() + "] " + getName() + ": Session "
				+ movie.getSession() + "'s show has finished");
		System.out.println("------------------------Session " + movie.getSession() + " Finishes-------------------");
		movie.sessionInc();
		session1Finish = true;
		showStart = false;
		
		//reset seatfull status for session 2
		movie.setSeatFull(false);
		
		//reset the current seat Number to 1
		//so visitors can take seat for session 2
		Driver.s.setSeatCount(1);

		// break time between shows
		movie.littleSleep(15000);

		System.out.println("[" + age() + "] " + "Clock sets session " + movie.getSession()
				+ " and notifies all visitor to enter the room");
		showStart = true;
		presentationFinish = false;
		
		//wait for the seats to get full for session 2
		while(!movie.getSeatFull()){
		}
		
		//interrupt speaker to give presentation 2
		Driver.s.interrupt();

		// clock sleeps for 2nd session
		movie.littleSleep(5000);
		
		System.out.println("[" + age() + "] " + getName() + ": Session "
				+ movie.getSession() + "'s show has finished");
		System.out.println("------------------------Session " + movie.getSession() + " Finishes-------------------");
		session2Finish = true;

		// a little time period till end of the day
		movie.littleSleep(5000);
		
		System.out.println("------------------------End of Day-------------------");
		endOfDay();



	}
	
	
	private void endOfDay(){
		
		// checks if each thread is still alive
		// if they are it tell them that it is the end of the day
		// and leaves the museum in descending order
		for (int i = 14; i >= 0; i--) {

			if (Driver.x[i].isAlive()) {
				Driver.x[i].setEndOfDay(true);
			}

			System.out.println("[" + age() + "] " + Driver.x[i].getName()
					+ " Leaves the museum");
			try {
				Driver.x[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		// speaker leaves the museum
		// after all visitors have left
		try {
			System.out
					.println("[" + age() + "] " + "Speaker leaves the museum");
			Driver.s.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//check if the presentation is finish 
	public static boolean presentationFinish() {
		return presentationFinish;
	}
	
	//set the presentation status 
	public synchronized void setPresentationFinish(boolean x) {
		presentationFinish = x;
	}
	
	//checks if the movie has been started after the presentation
	public boolean showStartStatus() {
		return showStart;
	}
	
	//set the movie start status
	public synchronized void setShowStatus(boolean x) {
		showStart = x;
	}
	
	//return clock time
	public static long age() {
		return System.currentTimeMillis() - startTime;
	}

}
