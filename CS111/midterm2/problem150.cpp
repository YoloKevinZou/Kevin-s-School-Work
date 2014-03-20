#include <iostream>
using namespace std;

int main(){
  srand(time(0));
  int x=rand()%99+1;
  int y=rand()%99+1;
  int secret= x+y;
  cout << "X: " << x << " Y: " << y << " Secret: " << secret;
  cout << endl; 
  return 0;
}
