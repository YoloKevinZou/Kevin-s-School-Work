#include <iostream>
using namespace std;

int main(){
  int array[10];
  int sum=0;
  for(int i = 0; i < 10; i++)
    {
      cout << "Enter an integer: ";
      cin >> array[i];
    }
  for ( int i = 0; i < 10; i++)
    {
      sum+=array[i];
    }
  
  double average1=sum/10.0;
  cout << "Average for those number is: " << average1 << endl;
 
  int sum2=0;int count; 
  for ( int i = 0; i < 10; i++)
    {
      if(array[i]>=average1)
	{
	  sum2+=array[i];
	  count++;
	}
    }
  double average2=sum2/count;
  cout << "The final average is: " << average2 << endl;
  return 0;
}
