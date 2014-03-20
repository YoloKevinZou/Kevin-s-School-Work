// Triangle via top down dynamic programming
#include<fstream>
#include<sstream>
#include<iostream>
using namespace std;

const int max_rows=100;

int memo[max_rows][max_rows+1];

void init_memo(){
 
 for(int i=0; i<max_rows; i++)
   for(int j=0; j<max_rows+1; j++)
     memo[i][j]=-1;
}

int max_value(int t[][max_rows+1], int i, int j){
  
  if(i==0)
    return t[i][1];
  
  if(memo[i][j]!=-1)
    return memo[i][j];
  
  return memo[i][j] = t[i][j] + max(max_value(t,i-1,j-1),max_value(t,i-1,j));
  
}

int main(){
  
  init_memo(); // set all entries = -1

  ifstream in("triangle.txt");

  string line;

  int t[max_rows][max_rows+1]={0};

  for(int i=0; i<max_rows;i++){
    
    getline(in,line);
    
    stringstream si(line);
    
    t[i][0]=0;
   
    for(int j=1;j< i+2;j++)
      si>>t[i][j];
    
  }
 /*
 for(int i = 0; i < max_rows; i++)
    {
      for(int j = 0; j < max_rows+1 ; j++)
	cout << t[i][j] << " ";
      cout << endl;
    }
  
 cout << endl;
 */
 //int result=max_value(t,max_rows-1,1);
 
 
 for(int i=max_rows-1,j=0; j < max_rows-1; j++){
   int k=max_value(t,max_rows-1,j);
 }
 /*
 for(int i = 0; i < max_rows; i++)
   {
     for(int j = 0; j < max_rows+1 ; j++)
       cout << memo[i][j] << " ";
      cout << endl;
   }
 */
 int result = 0;

  for(int i = max_rows-1, j=1; j < max_rows-1; j++){
    if (memo[i][j] > result){
      result = memo[i][j];
    }
 }
 cout<<"the result is: "<<result<<endl;
 
  return 0;

}
