#include <iostream>
using namespace std;

/**
 * This function will ask the user for the size of the U shape.
 * Size has to be
 *
 * Parameter: None - function doesn't need any parameters.
 * Return: size - the size enter by the user.
 */

int getSize()
{
  int size;
  cout << "Enter the size of the U shape: ";
  cin >> size;
  while(size%2!=0)
    {
      cout << "Enter the size of the U shape: ";
      cin >> size;
    } 
 return size;
}

/**
 * This function will draw the U shape to the screen base on the provided size.
 *
 * Parameter: size - the size of the U shape to draw.
 * Return: void - because function does not need to return anything back to main.
 */

void printUShape( int size )
{
  for(int r=1;r<=size;r++)
    {
      for(int c=1;c<=size;c++)
	{
	  if(c==1)
	    cout << "*";
	  else if(c==size)
	    cout << "*";
	  else if(r==size)
	    cout <<"*";
	  else
	    cout << " ";
	}
      cout << endl;
    }
  return; 
}

int main()
{
  int size = getSize();
  printUShape( size );
  return 0;
}
