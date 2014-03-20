#include<iostream>
#include <cstdlib>
#include<cassert>
#include <climits>
#include "safematrix.h"
#include <time.h>  
using namespace std;
//template <class T>
//ostream& operator<<(ostream& os,  vnt<T> s);

class vnt
{
 private: 
          
          const static int max = INT_MAX;      
          safeMatrix<int> a;
          int lastrow, lastcol, min;
          
 public: 

         vnt(int m, int n): a(m,n), lastrow(m-1), lastcol(n-1)
         {
         srand(time(0));
         int count = 10;
         for(int r = 0; r < m; r++){
                 for(int c = 0; c < n; c++){
                         a[r][c]= max-1;
                         }
                 }
                 
         for(int r = 0; r < m; r++){
                 for(int c = 0; c < n; c++){
                         cout << a[r][c] << " ";
                         }
                         cout << endl;
                 }
         
         }
         
         void addSort(safeMatrix<int> &s, int lastrow, int lastcol)
         {
         
         int temp;    
         int self = a[lastrow][lastcol];
         
         /*cout << s;
         cout << "lastrow: " << lastrow << endl;
         cout << "lastcol: " << lastcol << endl;
         cout << "self: " << self << endl;*/
         
         //move up when at left
         if(lastrow>0&&lastcol==0&&a[lastrow][lastcol]<a[lastrow-1][lastcol]){
             temp=a[lastrow-1][lastcol];
             a[lastrow-1][lastcol]=a[lastrow][lastcol];
             a[lastrow][lastcol]=temp;
             lastrow=lastrow-1;
             addSort(a,lastrow,lastcol);
         }
         
         //move left when at top   
         else if(lastrow==0&&lastcol>0&&a[lastrow][lastcol]<a[lastrow][lastcol-1]){
             temp=a[lastrow][lastcol-1];
             a[lastrow][lastcol-1]=a[lastrow][lastcol];
             a[lastrow][lastcol]=temp;
             lastcol=lastcol-1;
             addSort(a,lastrow,lastcol);   
           }
         
         
         //move up
         else if(lastrow>0&&lastcol>0&&a[lastrow][lastcol-1]<=a[lastrow-1][lastcol]&&self<a[lastrow-1][lastcol]){
             temp=a[lastrow-1][lastcol];
             a[lastrow-1][lastcol]=a[lastrow][lastcol];
             a[lastrow][lastcol]=temp;
             lastrow=lastrow-1;
             addSort(a,lastrow,lastcol);
         } 
         //move left
         else if(lastrow>0&&lastcol>0&&a[lastrow][lastcol-1]>a[lastrow-1][lastcol]&&self<a[lastrow][lastcol-1]){
             temp=a[lastrow][lastcol-1];
             a[lastrow][lastcol-1]=a[lastrow][lastcol];
             a[lastrow][lastcol]=temp;
             lastcol=lastcol-1;
             addSort(a,lastrow,lastcol);  
         }
         }
         
         void removeSort(safeMatrix<int> &s, int startx, int starty)
         {
         
         int temp;    
         int self = a[startx][starty];
         
         /*cout << s;
         cout << "startx: " << startx << endl;
         cout << "lastrow: " << lastrow << endl;
         cout << "starty: " << starty << endl;
         cout << "lastcol: " << lastcol << endl;
         cout << "self: " << self << endl;*/
         
         //move down when at right
         if(startx<lastrow&&starty==lastcol&&a[startx][starty]>a[startx+1][starty]){
             temp=a[startx+1][starty];
             a[startx+1][starty]=a[startx][starty];
             a[startx][starty]=temp;
             startx=startx+1;
             removeSort(a,startx,starty);
         }
         
         //move right when at bottom   
         else if(startx==lastrow&&starty<lastcol&&a[startx][starty]>a[startx][starty+1]){
             temp=a[startx][starty+1];
             a[startx][starty+1]=a[startx][starty];
             a[startx][starty]=temp;
             starty=starty+1;
             removeSort(a,startx,starty);   
           }
         
         
         //move down
         else if(startx<lastrow&&starty<lastcol&&a[startx][starty+1]>=a[startx+1][starty]&&self>a[startx+1][starty]){
             temp=a[startx+1][starty];
             a[startx+1][starty]=a[startx][starty];
             a[startx][starty]=temp;
             startx=startx+1;
             removeSort(a,startx,starty);
         } 
         //move right
         else if(startx<lastrow&&starty<lastcol&&a[startx][starty+1]<a[startx+1][starty]&&self>a[startx][starty+1]){
             temp=a[startx][starty+1];
             a[startx][starty+1]=a[startx][starty];
             a[startx][starty]=temp;
             starty=starty+1;
             removeSort(a,startx,starty);  
             }
         }
         
         int getMin(){
             min=a[0][0];
             a[0][0]=max;
             removeSort(a, 0 , 0);
             return min;
             }
             
         void sort(int k[], int size)
         {
         for(int i = 0; i < size; i++){
                 add(k[i]);
         }
         
        
         for(int i = 0; i < size; i++){
                 k[i]=getMin();
         }
         }
         
         bool find(int x)
         {
          for(int r = 0; r < lastrow+1; r++){
                  for(int c = 0; c < lastcol+1; c++){
                          if(a[r][c]==x)
                          return true;        
                          }
                  }
          return false;
         }
                         
         
         void add(int x)
         {

         a[lastrow][lastcol]=x;
         
         cout << endl;
         
         addSort(a,lastrow,lastcol);
         
         cout << endl;
         cout << "After sort: " << endl;
         cout << a;
         }
            
         
 };
                 
 
