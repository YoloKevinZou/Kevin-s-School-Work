#include <iostream>
using namespace std;

int digitSum(int x)
{
  if(x<10)
    return x;
  return digitSum(x/10) + (x%10);
}

int main(){
  int n;
  cout << "Enter a positive integer that is at most 100. ";
  cin >> n;
  for (int i = 1; i < 1000; i++)
    {
      if(digitSum(i)==n)
	cout << i <<" ";
    }
  cout << endl;
  return 0;
}
