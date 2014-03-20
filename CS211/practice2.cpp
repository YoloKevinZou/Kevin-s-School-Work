#include <iostream>
using namespace std;

int main(){
  int array[20];
  srand(time(0));
  int count[20]={0};   

  for( int i = 0; i < 20; i++)
    {
      cout << count[i] << " ";
    }
  cout << endl; 
 for(int i = 0; i < 20; i++)
    {
      array[i]=rand()%20+1;
      count[array[i]]++;
    
    }
  
  
  for (int i = 0; i < 20; i++)
    {
      cout << array[i] << " ";
    }
  cout << endl;
  for (int i = 0; i<20; i++)
    {
      cout << count[i] << " ";
    }
  cout << endl;
  return 0;
}
