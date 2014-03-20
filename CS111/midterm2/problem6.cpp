#include <iostream>
using namespace std;
void mystery(int x[][4], int a, int b, int k) {
  for (int r = a; r <= b; r++) for (int c = a; c <= b; c++)
    x[r][c] = k;
}
void print(int x[][4], int s) {
  for (int r = 0; r < s; r++) {
    for (int c = 0; c < s; c++) cout << x[r][c];
    cout << endl;
  }
  cout << endl;
}
int main() {
  int x[4][4] = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
  mystery(x, 3, 2, 1); print(x, 4); // line (a)
  mystery(x, 0, 1, 2); print(x, 4); // line (b)
  mystery(x, 1, 2, 3); print(x, 4); // line (c)
  mystery(x, 1, 3, 4); print(x, 4); // line (d)
  mystery(x, 0, 3, 5); print(x, 2); // line (e)
  return 0;
}
