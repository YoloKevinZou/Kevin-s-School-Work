#include <iostream>
using namespace std;

int sevenUp(int x) {
  if (x == 7) return 8;
  if (x < 10) return x;
  return 10*sevenUp(x / 10)+sevenUp(x%10);
}

int main() {
  cout << sevenUp(777) << " " << sevenUp(471) << " " << sevenUp(50) << endl;
  return 0;
}
