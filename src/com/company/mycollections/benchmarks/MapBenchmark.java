package com.company.mycollections.benchmarks;

import java.util.Map;

public class MapBenchmark {
    private Map collection1;
    private Map collection2;
    private Map collection3;
    private int[] dataSet;
    private int[] indexes; // random indexes < collection.size();
    private Long start;
    private Long finish;

    public MapBenchmark(Map collection1, Map collection2,Map collection3, DataSet data) {
        this.collection1 = collection1;
        this.collection2 = collection2;
        this.collection3 = collection3;
        this.dataSet = data.getDataSet();
        this.indexes = data.getRandomIndexes();
    }

    //maybe do it with lambda?
    public void addTest(){
        System.out.println("add test");

        bench(dataSet,dataSet.length,collection1,(int x)->{collection1.put(x,new Object());});

        bench(dataSet,dataSet.length,collection2,(int x)->{collection2.put(x,new Object());});

        bench(dataSet,dataSet.length,collection3,(int x)->{collection3.put(x,new Object());});
        System.out.println();
    }

    public void getTest(){
        System.out.println("get test");

        bench(dataSet,dataSet.length,collection1,(int x)->{collection1.get(x);});

        bench(dataSet,dataSet.length,collection2,(int x)->{collection2.get(x);});

        bench(dataSet,dataSet.length,collection3,(int x)->{collection3.get(x);});
        System.out.println();
    }

    public void removeTest(){
        System.out.println("remove test");

        bench(indexes,indexes.length,collection1,(int x)->{collection1.remove(x);});

        bench(indexes,indexes.length,collection2,(int x)->{collection2.remove(x);});

        bench(indexes,indexes.length,collection3,(int x)->{collection3.remove(x);});
        System.out.println();
    }

    private void bench(int[] data, int size,Map set, Action action){
        System.out.println("test "+set.getClass());
        start();
        for (int i = 0;i < data.length; i++){
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
