#include <iostream>
using namespace std;

int evens(int x)
{
  if (x%2==1)
    return evens(x/10);
  if(x<10) 
    return x;
  return evens(x/10)*10+evens(x%10);
}


int main(){
  cout << evens(6528451) << endl;
  cout << evens(666) <<  endl;
  cout << evens(777) << endl;
  return 0;
}
