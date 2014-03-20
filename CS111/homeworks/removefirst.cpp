#include <iostream>
using namespace std;

int removeFirst(int x)
{
  if(x<10)
    return 0;
  return removeFirst(x/10)*10+x%10;
}

int main(){
  cout << removeFirst(19683)<< endl;
  return 0;
}
