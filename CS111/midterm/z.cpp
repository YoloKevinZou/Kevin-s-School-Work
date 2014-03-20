#include <iostream>
using namespace std;

int main(){
  int size=5;
  for(int r=1;r<=size;r++)
    {
      for(int c=1;c<=3*size;c++)
	{
	  //letter z
	  if(r==1&&c<=size)
	    cout << "*";
	  else if((r+c)==(size+1))
	    cout << "*";
	  else if(r==size&&c<=size)
	    cout << "*";
	  else cout << " ";

	  //letter o
	  if((r==1)&&(c>(size+1)&&(c<=size+3)))
	    cout << "*";
	  else if((r>1)&&(r<size)&&(c==size+1))
	    cout << "*";
	  else if((r>1)&&(r<size)&&(c==size+4))
	    cout << "*";	  
	  else if(r==size&&c<=(size+3)&&c>(size+1))
	    cout << "*";
	  else cout << " ";
	 
	  //letter U
	  if(r<size&&c==size+5)
	    cout << "*";
	  else if(r<size&&c==size+8)
	    cout << "*";
	  else if(r==size&&c>=size+6&&c<=size+7)
	    cout << "*";
	  else 
	    cout << " ";
	}
      cout << endl;
    }
    system("PAUSE");
  return 0;
  
}
