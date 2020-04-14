package com.company.algorithms;

import java.util.ArrayList;
import java.util.List;

//ex 1 from book
public class Erathostenes {
    private int nMax;
    private int[] numbers;
    private List<Integer> prime;

    public Erathostenes(int nMax) {
        this.nMax = nMax;
        this.numbers = fillArray();
        prime = new ArrayList<>();
    }

    private int[] fillArray(){
        int[]  x = new int[nMax-1];

        for(int i = 0; i < x.length; i++){
            x[i] = i + 2;
        }

        return x;
    }

    public List<Integer> getPrime(){
        fillPrime();

        for(int o : numbers){
            if(o != 0){
                prime.add(o);
            }
        }


        if(!check()){
            System.out.println("MISTAKE!!!");
        }

        return prime;
    }

    private void fillPrime(){

        int p = 2;
        for(int i = p; i <= numbers.length; i+=p ){
            numbers[i] = 0;

            if((i+p) > numbers.length){
                p = getNextNotNull(p);
                if(p == -1 || Math.pow(p,2)>numbers.length){
                    break;
                }
                i =  p*2 - 2 - p;
            }
        }

    }

    private int getNextNotNull(int t){
        for(int i = t - 1; i < numbers.length; i++){
            if(numbers[i] != 0){
                return numbers[i];
            }
        }

        return -1;
    }

    private boolean check(){
        for(int o : prime){
            for(int i = 2; i < o; i++) {
                if(o % i==0)
                    return false;
            }
        }
        return true;
    }
}
