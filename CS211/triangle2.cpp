#include <iostream>
using namespace std;

const int max_rows = 100;
int memo[max_rows][max_rows];

void init_memo(){
 
 for(int i=0; i<max_rows; i++)
   for(int j=0; j<max_rows; j++)
     memo[i][j]=-1;
}


int result(int t[][max_rows], int i, int j){

	if(i==0){
		return memo[i][j] = t[i][j];
	}

	if (memo[i][j]!=-1){
		return memo[i][j];
	}

	if(j==0){
		return memo[i][j] = t[i][j] + result(t,i-1,j);
	}

	else {
		return memo[i][j] = t[i][j] + max(result(t, i-1, j),result(t,i-1, j-1));
	}

}


int main(){
	
	init_memo();

	int t[max_rows][max_rows]={0};

	//read the values of the input into array t
	for(int i=0; i<max_rows;i++){
	   
	    for(int j=0;j < i+1;j++){
			cin >> t[i][j];
	  }
	}

	//compute all the paths to the bottom
  	for (int i = max_rows-1, j =0; j < max_rows; j++){
  			result(t,max_rows-1,j);
  	}

  	int max = 0;

  	//print out the largest path
  	for (int i = max_rows-1, j=0; j < max_rows; j++){

		if(memo[i][j]>max)
			max = memo[i][j];

	}

	cout << "max: " << max << endl;




	return 0;
}