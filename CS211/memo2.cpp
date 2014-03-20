# include <iostream>
using namespace std;

long memoized_fib(long n){
  long static memo[50]={0};
  memo[1]=1;
  
  if (memo[n]>0) return memo[n];
  if (memo[n]==0)
    { //fill missing spots in memo table up to and including n
      for(int i = 2; i<=n; i++)
	{
	  if(memo[i]==0) memo[i] = (memoized_fib(i-1)+memoized_fib(i-2));
	}
      return memo[n];
    }
}

int main(){
  long n;
  cin >> n;
  //to print out fib numbers:
  for(int i=1; i<=n; i++)
    cout<< "fib(" << i << ") = " <<memoized_fib(i)<<endl;
  return 0;
}
