package powermod;

public class driver {

	public static int powermod(int e, int z){
		
		int i = 1;
		
		while(true){
			if((i*e)%z==1)
				return i;
			i+=1;
		}
		
	}
	
	public static void main(String args[]){
		
		System.out.println(powermod(3,49000));
		
		System.out.println(Math.pow(2,3));
		
	}
	
}
