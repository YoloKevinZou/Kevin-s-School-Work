#include <iostream>
using namespace std;

int main(){
  int n;srand(time(0));
  cout << "Enter a positive integer: ";
  cin >> n;
  int array[100];
  for( int i = 0; i < n; i++)
    {
      array[i]=rand()%30-15;
    }
  
  for ( int i = 0;i < n; i++)
    {
      cout << array[i] << " ";
    }
  cout << endl; 
  
  for ( int i = 0; i < n; i++)
    {
      if(array[i]<0)
	cout << array[i]<< " ";
    }
  cout << endl;
  for ( int i = n - 1;i >= 0; i--)
    {
      if (array[i]>0) 	
	cout << array[i] << " ";
    }
  
  
  cout << endl;
  return 0;
}
