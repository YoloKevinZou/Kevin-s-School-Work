#include <iostream>
using namespace std;

/* this function askes the user to enter a card value from 1 to 13.
 *Parameter: None - this function does not need any parameter.
 *Return: num - value enter by user
 */

int getCardValue()
{
  int num;
  cout << "Enter a card value from 1 to 13: ";
  cin >> num;
  while(num<1||num>13)
    {
      cout <<"Invalid value, Please enter a card value from 1 to 13: ";
      cin >> num;
    }
  return num;
}

/* This function will swap the two values enter by the user
 * Parameter: The 2 value from user's input
 * Return: this function doesn't return anything
 */

void determineHighCard(int &max, int &min)
{
      int t=max;
      max=min;
      min=t;
}

/* The main function initialize the card 1 and card 2 variable by calling into the getcardvalue, if the card 1 and card 2 is the same the system will display an output telling the user that is a tie. if the user enter two different numbers. The system will display the Higher card value which 1 is highest.
 */

int main() {
  int card1=getCardValue();
  int card2=getCardValue();
  
  if(card1==card2)
    cout << "That is a tie." << endl;
  else
    {
      int max=card1;
      int min=card2;
      if(max==1&&min!=1)
        {
          cout <<"Higher card is " << max << endl;
        }
      else if(min>max||min==1)
      	{
	  determineHighCard(max,min);
	  cout << "Higher card is " << max << endl;
	}
    }
  return 0;
}
