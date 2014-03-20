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

void backtrack(int &c)
{
  c--;
}

void print(int count, int i)
{
 cout << "There are " << count << " solutions to " << i << " queens problem." << endl;

 //  for(int i=0; i<8; i++)
 // cout << q[i] << " ";
 // cout << endl << endl;
}

int main()
{
  int count = 0;
  int n;  
  cin >> n;
  int temp;
  for(int i = 1; i <= n; i++)
     {
      temp=i;
      int *q=new int[i];
      for(int j = 0; j < i; j++)
	{	
	  q[i]=0;
	}
      int c=0;
      bool from_backtrack=false;
      
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
		      backtrack(c);
		      continue;
		    }
		  if(ok(q,c))
		    {
		      count++;
		      break;
		    }
		}
	      if(c==-1)
		break;
	      c++;
	    }
	  print(count,i);
	  backtrack(c);
	  from_backtrack=true;
	}
      delete []q;
      }
  return 0; 
}
