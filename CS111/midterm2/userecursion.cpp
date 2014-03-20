#include <iostream>
using namespace std;

int userecursion(int x)
{
  if ( x<10 )
    return x;
  if (x<100)
    return (x/10)+(x%10);
  return userecursion(x/10);
}

int main(){
  cout << userecursion(230844) << endl;
  cout << userecursion(21431451) << endl;
  cout << userecursion(2) << endl;
  return 0;
}
