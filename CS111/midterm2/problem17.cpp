#include <iostream>
using namespace std;

int removeFirst(int x){
  if (x<10) return 0;
  return removeFirst(x/10)*10+(x%10);
}

int main() {
  int n = 19683;
  int m = removeFirst(n);
  cout << m << endl; // output 9683
  cout << removeFirst(1024); // output 24
  return 0;
}
