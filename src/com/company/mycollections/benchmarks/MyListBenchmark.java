package com.company.mycollections.benchmarks;

import com.company.mycollections.MyLinkedList;
import java.util.Iterator;
import java.util.List;

public class MyListBenchmark extends Benchmark{
    private List linkedList;
    private MyLinkedList myLinkedList;

    public MyListBenchmark(List linkedList, MyLinkedList myLinkedList, DataSet dataSet) {
        super(dataSet);
        this.linkedList = linkedList;
        this.myLinkedList = myLinkedList;
    }



    public void startBench(){
        System.out.println("==== MY COLLECTION BENCHMARK ====");
        System.out.println("data set size = "+dSet.getSize());


        ListBenchmark listBenchmark = new ListBenchmark(dSet,linkedList);
        listBenchmark.startBench();

        bench(dSet.getData(),dSet.getSize(),(int x)->myLinkedList.add(x),"sequential add");
        bench(dSet.getIndexes(),dSet.getSize()/100,(int x)->myLinkedList.add(x,0),"random add");
        bench(dSet.getIndexes(),dSet.getSize()/100,(int x)->myLinkedList.set(x,0),"random set");
        bench(dSet.getIndexes(),dSet.getSize()/100,(int x)->myLinkedList.remove(x),"random remove");
        System.out.println();



        checkCollections();
    }

    private void bench(int[] data, int size, Action action, String actionName){
        start();
        for (int i = 0;i < size; i++){
            action.doAction(data[i]);
        }
        finish(myLinkedList.getClass().getName(),actionName);
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
}
