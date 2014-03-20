#include <iostream>
#include <cmath>
using namespace std;

int main(){
  int test[8][8]={{-1,9,9,9,9,9,9,9},{0,-1,9,9,9,9,9,9},{1,-1,9,9,9,9,9,9},{0,1,2,-1,9,9,9,9},{1,2,3,-1,9,9,9,9},{2,4,-1,9,9,9,9,9},{0,3,4,-1,9,9,9,9},{3,4,5,6,-1,9,9,9}};
  int array[8];
  array[0]=1;
  int c=0;
  int count=0;
  
 NC:c++;
  if(c==8)
    {
      count++;
      goto print;
    }
  
  array[c]=0;
 NR:array[c]++;
  if(array[c]==9)
    goto backtrack;
  
  //consecutive number test
  for(int i = 0; i < c; i++)
    {
      if(test[c][i]!=-1)
	{
	  if(array[test[c][i]]==array[c]||abs(array[test[c][i]]-array[c])==1)
	    goto NR;
	}    
    }
  
  //same number test 
  for(int i =0; i < c; i++)
    {
      if(test[c][i]!=-1)
	if(array[c]==array[i])
	  goto NR;
    }
  
  goto NC;
  
 backtrack:
  c--;
  if(c==-1)
    return 0;
  goto NR;
  
 print:
  cout <<"Solution: " << count << endl;    
  for(int i = 0; i < 8; i++)
    {
      cout << array[i] << " ";
    }
  cout << endl;
  goto backtrack;
  return 0;
}
