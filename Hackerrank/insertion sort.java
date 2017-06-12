import java.io.*;
import java.util.*;

public class Solution {

    public static void insertionSortPart2(int[] ar, int s)
    {       
      int counter = 0;
        while (counter < s){
            if (((counter+1)<s)&& (ar[counter] <= ar[counter+1])){
                printArray(ar);
                counter++;
            }
            else if((counter+1)==s){
                counter++;
            }
            else{
                int c = counter;
                while(c>=0  && c+1< s && ar[c] > ar[c+1]){
                    int temp = ar[c];
                    ar[c] = ar[c+1];
                    ar[c+1]= temp;
                    c--;
                }
                printArray(ar);
                counter++;
            }
        }
    }  
    
    
      
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
       int s = in.nextInt();
       int[] ar = new int[s];
       for(int i=0;i<s;i++){
            ar[i]=in.nextInt(); 
       }
       insertionSortPart2(ar, s);    
                    
    }    
    private static void printArray(int[] ar) {
      for(int n: ar){
         System.out.print(n+" ");
      }
        System.out.println("");
   }
}
