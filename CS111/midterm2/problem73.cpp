#include <iostream>
using namespace std;

int twoPart(int x){
  if(x%2==1) return 1;
  return 2*twoPart(x/2);
}

int main(){
  cout << twoPart(16) << endl;
  cout << twoPart(666) << endl;
  cout << twoPart(1991) << endl;
  cout << twoPart(201239) << endl;
  return 0;
}
