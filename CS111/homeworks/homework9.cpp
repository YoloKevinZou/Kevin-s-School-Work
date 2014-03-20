#include <iostream>
using namespace std;

int main(){
  
  // identify variable size and ask user to input the variable
  int size;
  cout << "Enter an integer greater than 5: ";
  cin >> size;

  //when the size input is less then 5 or if the size is not odd it ask the user to input the size again.
  while(size<5||(size%2)==0)
    {    
      cout <<"Enter an integer greater than 5: ";
      cin >>size;  
    }     
 
  //initialize variable mid as (size+1)/2
  int mid = (size+1)/2;

  //creates the number of row base on the size entered
  for(int r=1;r<=size;r++)
    { 
      
      //creates the number of columns base on the size entered
      for(int c=1;c<=size;c++)
       {
	 //find the midpoint of the size and print a + mark on it
	 if(mid==r&&mid==c)
	   cout << "+";	
	 //prints the mark - in the first , middle and last row.
	 else if(r==1||r==size||mid==r)
	   cout <<"-";
	 //draws | mark on column 1, mid and last column
	 else if(c==1||c==mid||c==size)
		 cout <<"|";
	 //prints space is non of the condition above applies.
	 else 
	   cout << " ";
       }
      //goes to a different line after every row loop
     cout<<endl;
   }
 return 0;
}     
