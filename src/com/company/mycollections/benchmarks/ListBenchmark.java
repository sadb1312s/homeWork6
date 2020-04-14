package com.company.mycollections.benchmarks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBenchmark extends Benchmark {
    private List<List<Integer>> lists;

    public ListBenchmark(DataSet dataSet, List<Integer>... lists) {
        super(dataSet);
        this.lists = new ArrayList<>();
        this.lists.addAll(Arrays.asList(lists));
    }

    public void startBench(){
        for(List<Integer> list : lists){

            bench(dSet.getData(),dSet.getSize(),list, list::add,"sequential add");
            bench(dSet.getIndexes(),dSet.getSize()/100,list,(int x)->list.add(x,0),"random add");
            bench(dSet.getIndexes(),dSet.getSize()/100,list,(int x)->list.set(x,0),"random set");
            bench(dSet.getIndexes(),dSet.getSize()/100,list, list::remove,"random remove");
            System.out.println();
        }
    }


    private void bench(int[] data, int size,List list, Action action, String actionName){
        start();
        for (int i = 0;i < size; i++){
            action.doAction(data[i]);
        }
        finish(list.getClass().getName(),actionName);
    }
}
