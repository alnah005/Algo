import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.math.BigInteger;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */      Scanner input = new Scanner(System.in);
        BigInteger b1=input.nextBigInteger();
        BigInteger j = new BigInteger("1");
        BigInteger s= new BigInteger("0");
        BigInteger i = b1.subtract(j);
        while(i.compareTo(s)== 1){
            b1 = b1.multiply(i);
            i = i.subtract(j);
        }
        System.out.println(b1.toString());
    }
}
