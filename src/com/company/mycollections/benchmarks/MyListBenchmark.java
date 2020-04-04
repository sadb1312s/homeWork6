package com.company.mycollections.benchmarks;

import com.company.mycollections.MyLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyListBenchmark {
    private List linkedList;
    private MyLinkedList myLinkedList;
    private int[] dataSet;
    private int[] indexes;
    private Long start;
    private Long finish;

    public MyListBenchmark(List linkedList, MyLinkedList myLinkedList, DataSet dataSet) {
        this.linkedList = linkedList;
        this.myLinkedList = myLinkedList;
        this.dataSet = dataSet.getDataSet();
        this.indexes = dataSet.getRandomIndexes();
    }



    public void bench(){
        System.out.println("==== BENCHMARK ====");
        System.out.println("data set size = "+dataSet.length);
        if(dataSet.length <= 10){
            System.out.println(Arrays.toString(dataSet));
        }


        System.out.println("-- add test --");
        System.out.println("test "+linkedList.getClass());
        start();
        for(int i = 0; i < dataSet.length; i++){
            linkedList.add(dataSet[i]);
        }
        finish();


        System.out.println("test "+myLinkedList.getClass());
        start();
        for(int i = 0; i < dataSet.length; i++){
            myLinkedList.add(dataSet[i]);
        }
        finish();
        System.out.println();


        System.out.println("-- random add test --");

        System.out.println("test "+linkedList.getClass());
        start();
        for(int i = 0; i < indexes.length / 100; i++){
            linkedList.add(indexes[i],dataSet[i]);
        }
        finish();


        System.out.println("test "+myLinkedList.getClass());
        start();
        for(int i = 0; i < indexes.length / 100; i++){
            myLinkedList.add(indexes[i],dataSet[i]);
        }
        finish();
        System.out.println();



        System.out.println("-- get test --");

        System.out.println("test "+linkedList.getClass());
        start();
        for(int i = 0; i < indexes.length / 100; i++){
            linkedList.get(i);
        }
        finish();


        System.out.println("test "+myLinkedList.getClass());
        start();
        for(int i = 0; i < indexes.length / 100; i++){
            myLinkedList.get(i);
        }
        finish();
        System.out.println();



        System.out.println("-- remove test -- ");

        System.out.println("test "+linkedList.getClass());
        start();
        start = System.nanoTime();
        for(int i = 0; i < indexes.length / 100; i++){
            linkedList.remove(indexes[i]);
        }
        finish();



        System.out.println("test "+myLinkedList.getClass());
        start();
        for(int i = 0; i < indexes.length / 100; i++){
            myLinkedList.remove(indexes[i]);
        }
        finish();



        checkCollections();
    }

    private void checkCollections(){
        System.out.println("size check "+(myLinkedList.size() == linkedList.size()));
        System.out.println("elements check");


        if(myLinkedList.size() == linkedList.size()){
            Iterator iterator1 = myLinkedList.iterator();
            Iterator iterator2 = linkedList.iterator();


            while (iterator1.hasNext()){
                int x1 = (int) iterator1.next();
                int x2 = (int) iterator2.next();

                if(myLinkedList.size() <= 10){
                    System.out.println(x1 + " " + x2);
                }
                if(x1 != x2){
                    System.out.println("DONT EQUALS ELEMENT!!!!");
                    break;
                }
            }
        }
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
