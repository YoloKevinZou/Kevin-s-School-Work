#include <iostream>
using namespace std;

int main()
{
  //do not change the following line, it setups up random value
  srand(time(0));

  //get upper bound
  int max;
  cout << "Specify a upper bound: ";
  cin >> max;

  //generate secret number
  int secret = rand()%max +1;
 
  //askes the user to enter their guess number and enter 0 to quit the game.
  int guess;  
  cout << "Enter your guess number, enter 0 to quit ";
  cin >> guess;

  // in the condition when the guess number does or does not equal to the secret number it will execute the while loop.   

  while(guess!=secret||guess==secret)
    {
      //if the user enter 0 for the guess, the program will exit
      if(guess==0)
	exit(1);
      
      //if the user's guess is less then secret number, it will give a hint to user saying the number is higher.
      if(guess<secret)
	{
	  cout << "The secret number is higher, enter 0 to quit: ";
	  cin >> guess;
	}   
      
      //if the user's guess is higher then secret, it will give a hint to user saying the number is lower.
      if(guess>secret)
	{
	  cout << "The secret number is lower, enter 0 to quit: ";
	  cin >> guess;
	}

      //when user guess the secret number correctly, it will congradulates the user and ask if the user to play the game again by typing yes or no.
      while(guess==secret)
	{
	  cout << "Congradulations, you got the number!! " << endl;
	  string yes = "yes"; string no = "no";
	  string answer;
	  cout << "Do you want to play the game again? yes/no? ";
	  cin >> answer;
	  if(answer==yes)
	    {
	      cout <<"Specify an upper bound ";
	      cin >> max;
	      cout << "Enter your guess number, enter 0 to quit: ";
	      cin >> guess;
	      secret=rand()%max+1;
	    }
	  if(answer==no)
	    exit(1);
	  
	}
    }
  return 0;
}
