import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int adjacentAdd(int row, int column, int n){
        int total=0;
        for (int i = column+1; i<n; i++){
            total++;
        }
        for (int i = row+1; i<n;i++){
            total++;
        }
        for (int i = row-1; i>-1 ;i--){
            total++;
        }
        for (int i = column-1; i>-1;i--){
            total++;
        }
        int i = column+1;
        int h = row +1;
        while (i<n && h<n){
            total++;
            h++;
            i++;
        }
        i = column-1;
        h = row -1;
        while (i>-1 && h>-1){
            total++;
            h--;
            i--;
        }    
     
        i = column-1;
        h = row +1;
        while (i>-1 && h<n){
            total++;
            h++;
            i--;
        }
        i = column+1;
        h = row -1;
        while (h>-1 && i<n){
            total++;
            i++;
            h--;
        }
        return total;
    }
    public static int obstacle(int ro, int co, int rq, int cq, int n){
        int total=0;
        if((ro==rq) && (co>cq)){
          for (int i = co; i<n; i++){
            total--;
            }  
        } 
        if((ro==rq) && (co<cq)){
            for (int i = co; i>-1;i--){
            total--;
            }
        }
        if((co==cq) && (ro>rq)){
            for (int i = ro; i<n;i++){
            total--;
            }
        }
        if((co==cq) && (ro<rq)){ 
            for (int i = ro; i>-1 ;i--){
            total--;
            }
        }
        int i = 0;
        int h = 0;
        if(((cq-co)<0) && ((cq-co)==(rq-ro))){
            i = co;
            h = ro;
            while (i<n && h<n){
                total--;
                h++;
                i++;
            }   
        }
        
        if(((cq-co)>0) && ((cq-co)==(rq-ro))){
            i = co;
            h = ro;
            while (i>-1 && h>-1){
                total--;
                h--;
                i--;
            }
        }
        if(((cq-co)>0) && ((cq-co)==(ro-rq))){
            i = co;
            h = ro;
            while (i>-1 && h<n){
                total--;
                h++;
                i--;
            }
        }
        if(((cq-co)<0) && ((cq-co)==(ro-rq))){
            i = co;
            h = ro;
            while (h>-1 && i<n){
                total--;
                i++;
                h--;
            }
        }   
        return total;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int rQueen = in.nextInt()-1;
        int cQueen = in.nextInt()-1;
        int total =0;
        total=adjacentAdd(rQueen,cQueen,n);
        int subtraction=0;
        for(int a0 = 0; a0 < k; a0++){
            int rObstacle = in.nextInt()-1;
            int cObstacle = in.nextInt()-1;
            subtraction = subtraction + obstacle(rObstacle,cObstacle,rQueen,cQueen,n);
        }
        total = total + subtraction;
         System.out.println(total);
    }
}
