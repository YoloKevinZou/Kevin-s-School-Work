#include <iostream>
using namespace std;

int number_of_primes(int from, int to)
{
  int count=0;
  for (int i = from; i <= to; i++)
    {
      int primeCount = 0;
      for(int factor = 2; factor < i; factor++)
	{
	  if(i%factor!=0)
	    primeCount++;
	}
      if(primeCount==i-2)
	count++;
    }
  return count;
}

int main()
{
  
  cout << number_of_primes(2,20) << endl;
  return 0;
}
