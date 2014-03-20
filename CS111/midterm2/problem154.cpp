#include <iostream>
using namespace std;

int main(){
  int students, quiz;
  cout << "How many students: ";
  cin >> students;
  cout << "How many quizzes: ";
  cin >> quiz;
  int scores[students][quiz];
  for ( int r = 1; r <= quiz; r++)
    {
      cout << "Enter scores for Quiz " << r   << ": ";
      for( int c = 1; c <= students;c++)
	{
	  cin >> scores[r][c];
	}
    }
  
  for ( int r = 1; r <= quiz; r++)
    {
      for (int c = 1; c <= students; c++)
	{
	  cout << scores[r][c] << " ";
	}
      cout << endl;
    }

 
  int colSum[students];
  for(int c =1; c <=students; c++)
    colSum[c]=0;
  
  for ( int r =1; r <= quiz; r++)
    {
      for ( int c = 1; c <= students; c++)
	{	
	  colSum[c]+=scores[r][c];  
	}
    }
  
  for (int i =1; i<=students; i++)
    {
      cout << colSum[i] << " ";
    }

  cout << endl;

  int topStudent = 1;
  for (int c = 1; c <= students; c++)
    if (colSum[c] > colSum[topStudent])
      topStudent = c;
  cout << "Student " << topStudent << " got the highest total." << endl; 
  return 0;
  
}
