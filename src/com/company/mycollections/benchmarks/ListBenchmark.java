package com.company.mycollections.benchmarks;

import java.util.*;

//time delta
public class ListBenchmark {
    private List collection1;
    private List collection2;
    private int[] dataSet;
    private int[] indexes; // random indexes < collection.size();
    private Long start;
    private Long finish;

    public ListBenchmark(List collection1, List collection2, DataSet data) {
        this.collection1 = collection1;
        this.collection2 = collection2;
        this.dataSet = data.getDataSet();
        this.indexes = data.getRandomIndexes();

        //System.out.println(Arrays.toString(dataSet));
        //System.out.println(Arrays.toString(indexes));
    }

    //maybe do it with lambda?
    public void addTest(){
        System.out.println("sequential add");

        bench(dataSet,dataSet.length,collection1,(int x)->{collection1.add(x);});

        bench(dataSet,dataSet.length,collection2,(int x)->{collection2.add(x);});

        System.out.println();
    }

    public void randomAddTest(){
        System.out.println("random add");

        bench(indexes,indexes.length/100,collection1,(int x)->{collection1.add(x,dataSet[x]);});

        bench(indexes,indexes.length/100,collection2,(int x)->{collection2.add(x,dataSet[x]);});

        System.out.println();
    }

    public void setTest(){
        System.out.println("set test");

        bench(indexes,indexes.length/100,collection1,(int x)->{collection1.set(x,dataSet[x]);});

        bench(indexes,indexes.length/100,collection2,(int x)->{collection2.set(x,dataSet[x]);});

        System.out.println();
    }

    public void removeTest(){
        System.out.println("remove test");

        bench(indexes,indexes.length/100,collection1,(int x)->{collection1.remove(x);});

        bench(indexes,indexes.length/100,collection2,(int x)->{collection2.remove(x);});

        System.out.println();
    }

    private void bench(int[] data, int size,List set, Action action){
        System.out.println("test "+set.getClass());
        start();
        for (int i = 0;i < size; i++){
            action.doAction(data[i]);
        }
        finish();
    }

    private void start(){
        start = System.nanoTime();
    }

    private void finish(){
        finish = System.nanoTime();
        System.out.println("time = "+((finish - start) / 1_000_000)   + " ms");
        start = finish = 0l;
    }
}
