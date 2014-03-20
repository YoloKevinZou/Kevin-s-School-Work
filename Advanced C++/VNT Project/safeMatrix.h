#include<iostream>
#include <cstdlib>
#include<cassert>
#include "safearray.h"

using namespace std;

template <class T> class safeMatrix;

template <class T>
ostream& operator<<(ostream& os,  safeMatrix<T> s);

template <class T>
class safeMatrix{

  private: 
           int lowerrow, upperrow, lowercol, uppercol;
           SA < SA<T> > internal;
  public:
         
         //default constructor for the safeMatrix
         //allow to write things like safeMatrix a;
         safeMatrix(){
                      cout << "Empty Matrixs. " << endl;
                      lowerrow=0;
                      upperrow=-1;
                      internal=NULL;
         }
         
         // single parameter constructor lets us
         // create a square safeMatrix with the number of rows = columns
         // safeMatrix x(10); sets up a matrix with index 0 to 9 for column and rows
         safeMatrix(int square_size) : lowercol(0), uppercol(square_size-1), lowerrow(0), upperrow(square_size-1){
         internal = SA< SA<T> > (square_size);
         for(int i = 0; i < square_size; i++){
                 internal[i]= SA<T>(square_size);       
                 }
         }  
         
         // 2 parameter constructor lets us write
         // safeMatrix x(10,20), the first parameter represent the number of rows in the matrix and the second parameter represents the number of columns
         safeMatrix(int rows, int cols) : lowercol(0),uppercol(cols-1),lowerrow(0),upperrow(rows-1){
         
         internal = SA< SA<T> > (rows);
         for (int i = 0; i < rows; i++){
             internal[i]= SA<T>(cols);
             }
         }
         
         // 4 parameter constructor let us write
         // safeMatrix x(10,20,30,40), 
         // the amount of rows will be represented by rmax-rmin+1
         // the amount of columns will be represented by cmax-cmin+1
         safeMatrix(int rmin, int rmax, int cmin, int cmax) : lowerrow(rmin),upperrow(rmax),lowercol(cmin),uppercol(cmax){
                        
                        internal = SA< SA<T> > (rmin,rmax);
                        for(int i = rmin; i < rmax+1 ; i++){
                                internal[i]=SA<T>(cmin,cmax);
                                }
                        }
             
       //display "Destructor" whenever the safematrix destructor is called                           
       ~safeMatrix(){
                     cout << "Destructor being called";
                     cout << endl << endl;
        }
         
         //overload the [] operator for safeMatrix class which returns a safearray
         // let us write safeMatrix x(10,20), x[10][20]=15;
         // it will return the safearray that x[10] is located and do safearray[20] with it
         SA<T>& operator[](int i){
		 	if(i < lowerrow || i > upperrow){
			cout<< "index "<< i <<" out of range first bracket"<<endl;
			system("PAUSE");
            exit(1);
            }
            return internal[i];
            }
            
            //overloads the * operator so we can multiply 2 matrixs together
            // let us write safeMatrix x(10), safeMatrix y(10); x*y;
            safeMatrix<T> operator*(safeMatrix<T> s){ 
                          
                          int lcol = uppercol-lowercol+1;
                          int rrow = s.upperrow-s.lowerrow+1;
                                                    
                          if(lcol!=rrow){
                                         cout << "Matrix cannot be multiplied." << endl;
                                         system("PAUSE");
                                         exit(1);
                          }
                          
                          safeMatrix<T> x(lowerrow, upperrow, s.lowercol, s.uppercol);
                          
                          for(int l=lowerrow; l < upperrow+1; l++){
                           for (int m=s.lowercol; m < s.uppercol+1; m++){
                           x.internal[l][m] =0;
                           for (int k=lowercol; k < uppercol+1; k++)
                           x.internal[l][m] +=internal[l][k] * s.internal[k][m];
                            }
                           }                           
            return x;
            }
                          
    // overloads << so we can print out the entire matrix directly
    friend ostream& operator<<<T>(ostream& os,  safeMatrix<T> s);         
      
};

template <class T>
//overloads << so we can print the entire matrix directly
ostream& operator<<(ostream& os, safeMatrix<T> s){
         for(int i = s.lowerrow; i < s.upperrow+1; i++){
                         cout << s.internal[i];
         }
         return os;        
};
