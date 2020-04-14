package com.company.mycollections.benchmarks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSet {
    private int size;
    private int[] dataSet;
    private int[] randomIndexes;
    private Random random;

    public DataSet(int size) {
        this.size = size;
        random = new Random();
        dataSet = formDataSet();
        randomIndexes = getRandomArray();

        //System.out.println(dataSet.length+" "+randomIndexes.length);
        //System.out.println(Arrays.toString(dataSet));
        //System.out.println(Arrays.toString(randomIndexes));
    }

    private int[] formDataSet(){
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = random.nextInt();
        }
       return arr;
    }

    private int[] getRandomArray(){
        int[] x = new int[size];

        List<Integer> r =Stream.iterate(0, n->n+1).limit(size).collect(Collectors.toList());
        Collections.shuffle(r);

       for(int i = 0; i < size; i++){
           x[i] = r.get(i);
       }

        return x;
    }

    public int[] getData() {
        return dataSet;
    }

    public int[] getIndexes(){
        return randomIndexes;
    }

    public int getSize() {
        return size;
    }
}
