#include <iostream>
using namespace std;

void print(int b[][8],int count){
  cout << "This is solution: " << count << endl;
  for ( int r = 0; r < 8; r++)
    {
      for (int c = 0; c < 8; c++)
	{
	  cout << b[r][c]<< " ";
	}
      cout << endl;
    }
  cout << endl;
}

bool ok(int b[][8])
{
  for(int i = 0; i < 8; i++)
    {
      int r = 0;
      while(b[r][i]!=1)
	r++;
      
      //horizon test
      for(int c =0; c < i; c++)
	if(b[r][c]==1)
	  return false;

      //up diagonal test
      for(int c =1; (r-c)>=0&&(i-c)>=0; c++)
	if(b[r-c][i-c]==1)
	  return false;
      
      //down diagonal test
      for(int c = 1; (r+c)<8&&(i-c)>=0; c++)
	if(b[r+c][i-c]==1)
	  return false;
    }    
  return true;
  
}


int main(){
  int board[8][8]={0};
  int count=0;
  for ( int a = 0; a < 8; a++)
    for ( int b = 0; b < 8; b++)
      for( int c = 0; c < 8; c++)
	for ( int d = 0; d < 8; d++)
	  for( int e = 0; e < 8; e++)	   
	    for( int f = 0; f < 8; f++)
	      for ( int g = 0; g < 8; g++)
		for ( int h = 0; h < 8; h++){
		  board[a][0]=1;
		  board[b][1]=1;
		  board[c][2]=1;
		  board[d][3]=1;
		  board[e][4]=1;
		  board[f][5]=1;
		  board[g][6]=1;
		  board[h][7]=1;
		  if(ok(board))
		    print(board, ++count);
		  board[a][0]=0;
		  board[b][1]=0;
		  board[c][2]=0;
		  board[d][3]=0;
		  board[e][4]=0;
		  board[f][5]=0;
		  board[g][6]=0;
		  board[h][7]=0;
		}
  return 0;
  
}
