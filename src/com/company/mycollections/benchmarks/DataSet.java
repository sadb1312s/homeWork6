package com.company.mycollections.benchmarks;

import java.util.*;

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

        for(int i = 0; i < size; i++){
            x[i] = random.nextInt(size);
        }

        return x;
    }

    public int[] getDataSet() {
        return dataSet;
    }

    public int[] getRandomIndexes(){
        return randomIndexes;
    }
}
