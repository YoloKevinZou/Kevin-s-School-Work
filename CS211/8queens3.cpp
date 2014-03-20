#include <cmath>
#include <iostream>
using namespace std;

int main(){
  int q[8], count=0, i;
  q[0]=0;
  int c=0;
  
 NC: 
  c++;
  if(c==8)
    {
      count++;
      goto print;
    }
  
  q[c]=-1;
 NR:
  q[c]++;
  if(q[c]==8)
    goto backtrack;
  
  for(i=0; i < c; i++)
    {   
      if(q[i]==q[c]||(c-i)==abs(q[c]-q[i]))
	{      
	  goto NR;
	}       
    }
 
  goto NC;
  
 backtrack:
  c--;
  if(c==-1)
    return 0;
  goto NR;

 print:
  cout << "Solution " << count << endl;
  for(i=0; i < 8; i++)
    cout << q[i] << " ";
  cout << endl;
  for(int row = 1; row < 9; row++)
    {
      for(int col = 1; col < 9; col++)
       	{    
	  if(q[col-1]==row)
	    cout << "Q" << " ";
	  else
	    cout << "-" << " ";
	}  
      cout << endl;
    }
  
  goto backtrack;

}
