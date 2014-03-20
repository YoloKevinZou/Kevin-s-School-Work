#include <iostream>
using namespace std;

double maxMid(double x[][5], int rows, int cols) {
  double ans = x[0][cols / 2];
  for (int r = 0; r < rows; r++)
    if (x[r][cols / 2] > ans) ans = x[r][cols / 2];
  return ans;
}

int main() {
  double x[4][5] = {{0,1,2,3,4}, {1,2,3,4,5}, {2,3,4,5,6}, {5,6,7,8,9}};
  double maxValues[5];
  cout <<  maxMid(x, 4, 5) << endl; // prints 7.0
  return 0;
}
