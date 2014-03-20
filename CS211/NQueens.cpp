#include <iostream>
#include <cstdlib>
#include <cmath>
using namespace std;

bool ok(int q[], int col)
{
  for(int i=0; i<col; i++)
    {
      if(q[col]==q[i] || (col-i)==abs(q[col]-q[i])) 
	return false;
    } 
  return true;
}

void backtrack(int &col)
{
  col--;
  // if(col==-1)
  // exit(1);
}

int count(int &count)
{
  return count++;
}

void print(int count, int i)
{
  cout << "There are " << count << " solutions to " << i << " queens problem." << endl;  
}



int main()
{
  int n;
  cout << "Enter an integer: ";
  cin >> n;

  for(int i = 1; i <= n; i++)
    {
      int count = 0;
      int c=0;
      bool from_backtrack=false;
      int *q=new int [i]; 
      for(int k = 0 ; k < i; k++)
	{
	  q[k]=0;
	}
      while(true)
	{  
	  while(c<i)
	    {
	      if(!from_backtrack)
		q[c]=-1;
	      from_backtrack=false;
	      while(q[c]<i)
		{
		  q[c]++;
		  if(q[c]==i)
		    {
		      c--;	  
		      continue;
		    }
		  if(ok(q,c)) break;
		}
	      if(c==-1)
		break;
	      c++;
	    }
	  if(c==-1)
	    break;
    	  count++;
	  c--;	  
	  from_backtrack=true;
	  
	}
      print(count,i);
      continue;
      delete []q;  
    }  
  return 0; 
}
