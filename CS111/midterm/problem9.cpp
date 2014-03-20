#include <iostream>
using namespace std;

int main(){
  int q,d,n,p,changes;
  cout << "enter the number of Quarters you are carrying : ";
  cin >> q;
  cout << "enter the number of Dimes you are carrying : ";
  cin >> d;
  cout << "enter the number of Nickels you are carrying :";
  cin >> n;
  cout << "enter the number of Pennies you are carrying :";
  cin >>p;
  changes=(q*25)+(d*10)+(n*5)+(p*1);
  cout << "That makes "<<changes<< " cents in change "<< endl;
  return 0;
}
