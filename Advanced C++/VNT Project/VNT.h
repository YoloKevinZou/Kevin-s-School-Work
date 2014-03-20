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
          int row, col, min;
          
 public: 
         
         //2 parameter constructor, allow us to do vnt x(2,5)
         // vnt x(2,5) will create a safematrix with 2 rows 5 column
         vnt(int m, int n): a(m,n), row(m-1), col(n-1)
         {
         for(int r = 0; r < m; r++){
                 for(int c = 0; c < n; c++){
                         a[r][c]= max-1;
                         }
                 }
         }
         
         //sorts the VNT everytime when new elements get added
         void addSort(safeMatrix<int> &s, int lastrow, int lastcol)
         {
         
         int temp;    
         int self = s[lastrow][lastcol];
         
         //move up when its at the first column
         if(lastrow>0&&lastcol==0&&s[lastrow][lastcol]<s[lastrow-1][lastcol]){
             temp=s[lastrow-1][lastcol];
             s[lastrow-1][lastcol]=s[lastrow][lastcol];
             s[lastrow][lastcol]=temp;
             lastrow=lastrow-1;
             addSort(s,lastrow,lastcol);
         }
         
         //move left when at first row   
         else if(lastrow==0&&lastcol>0&&s[lastrow][lastcol]<s[lastrow][lastcol-1]){
             temp=s[lastrow][lastcol-1];
             s[lastrow][lastcol-1]=s[lastrow][lastcol];
             s[lastrow][lastcol]=temp;
             lastcol=lastcol-1;
             addSort(s,lastrow,lastcol);   
           }
         
         
         //move up if the upper element is greater than the left one
         else if(lastrow>0&&lastcol>0&&s[lastrow][lastcol-1]<=s[lastrow-1][lastcol]&&self<s[lastrow-1][lastcol]){
             temp=s[lastrow-1][lastcol];
             s[lastrow-1][lastcol]=s[lastrow][lastcol];
             s[lastrow][lastcol]=temp;
             lastrow=lastrow-1;
             addSort(s,lastrow,lastcol);
         } 
         //move left if the left element is greater than the upper one
         else if(lastrow>0&&lastcol>0&&s[lastrow][lastcol-1]>s[lastrow-1][lastcol]&&self<s[lastrow][lastcol-1]){
             temp=s[lastrow][lastcol-1];
             s[lastrow][lastcol-1]=s[lastrow][lastcol];
             s[lastrow][lastcol]=temp;
             lastcol=lastcol-1;
             addSort(s,lastrow,lastcol);  
         }
         }
         
         //sorts the VNT everytime when first element gets remove
         void removeSort(safeMatrix<int> &s, int startx, int starty)
         {
         
         int temp;    
         int self = a[startx][starty];
         
         //move down when at right
         if(startx<row&&starty==col&&a[startx][starty]>a[startx+1][starty]){
             temp=a[startx+1][starty];
             a[startx+1][starty]=a[startx][starty];
             a[startx][starty]=temp;
             startx=startx+1;
             removeSort(a,startx,starty);
         }
         
         //move right when at bottom   
         else if(startx==row&&starty<col&&a[startx][starty]>a[startx][starty+1]){
             temp=a[startx][starty+1];
             a[startx][starty+1]=a[startx][starty];
             a[startx][starty]=temp;
             starty=starty+1;
             removeSort(a,startx,starty);   
           }
         
         
         //move down
         else if(startx<row&&starty<col&&a[startx][starty+1]>=a[startx+1][starty]&&self>a[startx+1][starty]){
             temp=a[startx+1][starty];
             a[startx+1][starty]=a[startx][starty];
             a[startx][starty]=temp;
             startx=startx+1;
             removeSort(a,startx,starty);
         } 
         //move right
         else if(startx<row&&starty<col&&a[startx][starty+1]<a[startx+1][starty]&&self>a[startx][starty+1]){
             temp=a[startx][starty+1];
             a[startx][starty+1]=a[startx][starty];
             a[startx][starty]=temp;
             starty=starty+1;
             removeSort(a,startx,starty);  
             }
         }
         
         //gets the first element of the VNT which is always the min
         int getMin(){
             min=a[0][0];
             a[0][0]=max;
             removeSort(a, 0 , 0);
             return min;
             }
         
         //puts an array into a VNT and sorts it
         void sort(int k[], int size)
         {
         for(int i = 0; i < size; i++){
                 add(k[i]);
                 }
         cout << a << endl;
         for(int i = 0; i < size; i++){
                 k[i]=getMin();
                 }
         }
         
         //checks to see if a specific integer is in the VNT
         bool find(int x)
         {
          int i=row;
          int j=0;
          while(i!=-1&&j!=col+1){
                      if(x==a[i][j])
                      return true;
                      else if(i==0 && x<a[i][j]){
                           return false;
                           }
                      else if( j==col && x>a[i][j]){
                      return false;
                      }
                      else if(x>a[i][j]&&j!=col){
                      j++;
                      }
                      else if(x<a[i][j]&&i!=0){
                      i--;
                      }
                      else if(x<a[0][0])
                      return false;
          }          
          return false;
         }
         
         //adds the new element into the bottom right of the VNT
         //sorts it after adding the new element
         void add(int x)
         {
         a[row][col]=x;      
         addSort(a,row,col);
         }
        
         friend ostream& operator<<(ostream& os,  vnt s); 
         
 };
 
 //overloading the << so we can write like cout << x; when x is a VNT
 ostream& operator<<(ostream& os,  vnt s){
 
 for(int r = 0; r < s.row+1; r++){
         for(int c = 0; c < s.col+1; c++){
                 os << s.a[r][c] << " ";
                 }
                 os << endl;
                 }
 return os;         
 }; 
                 
 
