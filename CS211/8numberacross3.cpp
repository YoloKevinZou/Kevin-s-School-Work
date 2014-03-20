#include <iostream>
#include <cmath>
#include <cstdlib>
using namespace std;

bool ok(int array[],int test[][5], int c)
{

  for(int i = 0; i < 5; i++)
    {
      if(test[c][i]!=-1)
	if(array[test[c][i]]==array[c]||abs(array[test[c][i]]-array[c])==1)
	  return false;
    }   
  
  for(int i = 0; i < c; i++)
    {
      if(array[c]==array[i])
	return false;
    } 
  return true;
}

void backtrack(int &c)
{
  c--;
  if(c==-1)
    exit(1);
}

void print(int q[])
{
  for(int i = 0; i < 8; i++)
    cout << q[i] << " ";
  cout << endl << endl;
}

int main()
{
  int count =0;
  int test[8][5]={{-1,9,9,9,9},{0,-1,9,9,9},{1,-1,9,9,9},{0,1,2,-1,9},{1,2,3,-1,9},{2,4,-1,9,9},{0,3,4,-1,9},{3,4,5,6,-1}};
  int array[8]={0};
  int c = 0;
  int i = 0;
  bool from_backtrack=false;

  while(true)
    {
      while(c<8)
	{
	  if(!from_backtrack)
	    array[c]=0;
	  from_backtrack=false;
	  while(array[c]<9)
	    {
	      array[c]++;
	      if(array[c]==9)
		{
		  backtrack(c);
		  continue;
		}
	      if(ok(array,test,c)) break;
	    }
	  c++;
	}
      count++;
      cout << "Solution: " << count << endl;
      print(array);
      backtrack(c);
      from_backtrack=true;
    }
  return 0;
}
