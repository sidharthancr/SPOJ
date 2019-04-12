package com.SPOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Sieve of Eratosthenes
public class Prime1 {
    public static void main(String[] args) {
        int maxRange = 32000; // Square root of 100000000
        List < Integer > primes = generatePrimes(maxRange);
        Scanner scan = new Scanner(System.in);
        int step = scan.nextInt();
        for (int i = 0; i < step; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            printPrimes(n, m, primes);
        }
    }

    private static void printPrimes(int n, int m, List < Integer > primes) {
        //create an array with n=2,m-50 so size =50-2+1=59[Includes first values\]
        boolean[] primeRange = new boolean[m - n + 1];
        //Reset starting to first prime number
        if (n < 2) n = 2;
        //Mark the whole range to true
        for (int i = 0; i < m - n + 1; i++) {
            primeRange[i] = true;
        }
        for (int i = 0; i < primes.size(); i++) {

            int p = primes.get(i);

            int start;
            //isPrimeNumber is bigger than start value
            if (p >= n) {
                //move the start to next prime multiple ex if its 2 start and 2prime we need to move the start to 4. another example start 2 prime is 7 need to move to 14;
                start = p * 2;
            } else {
                //if the prime is lesser we need to fins next divisible value
                //Is n is divisible by p
                if (n % p == 0) {
                    //start with n
                    start = n;
                } else {
                    //calculate  n%p gives reminder
                    //n-n%p gives how much we need to add
                    start = n + (p - (n % p));
                }

            }

            //Mark all the multiples to false
            for (int j = start; j <= m; j += p) {
                primeRange[j - n] = false;
            }


        }
        //Print the values
        for (int i = 0; i < primeRange.length; i++) {
            if (primeRange[i]) {
                System.out.println(i + n);
            }
        }
    }

    //Create a list of consecutive integers from 2 to n: (2, 3, 4, …, n).
    //Initially, let p equal 2, the first prime number.
    //Starting from p2, count up in increments of p and mark each of these numbers greater than or equal to p2 itself in the list. These numbers will be p(p+1), p(p+2), p(p+3), etc..
    //Find the first number greater than p in the list that is not marked. If there was no such number, stop. Otherwise, let p now equal this number (which is the next prime), and repeat from step 3.


    private static List < Integer > generatePrimes(int maxRange) {
        List < Integer > primeList = new ArrayList < > (maxRange);
        boolean[] primeInd = new boolean[maxRange + 1];
        //First Prime
        primeInd[2] = true;
        //Set true to only odd numbers :)
        for (int i = 3; i < maxRange; i = i + 2) {
            primeInd[i] = true;
        }
        //Set check if value is true  then set its multiples into false
        //Check only to value till square value because if it exceeds max range, all the values below already have been marked. [We use square because below square are already marked]
        for (int i = 3; i * i <= maxRange; i = i + 2) {
            if (primeInd[i]) {
                // start from square because below value already marked by lower multipliers. ex: 3 will start with 9 because lesser values 6 done by prime number 2.
                for (int j = i * i; j <= maxRange; j += i) {
                    primeInd[j] = false;
                }
            }
        }
        //Add all prime to values
        for (int i = 2; i < maxRange; i++) {
            if (primeInd[i]) {
                primeList.add(i);
            }
        }

        return primeList;
    }
}