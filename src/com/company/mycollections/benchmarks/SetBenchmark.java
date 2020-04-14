package com.company.mycollections.benchmarks;

import java.util.*;

public class SetBenchmark extends Benchmark{
    private List<Set<Integer>> sets;


    public SetBenchmark(DataSet dataSet,Set<Integer>... sets) {
        super(dataSet);
        this.sets = new ArrayList<>();
        this.sets.addAll(Arrays.asList(sets));
    }

    public void startBench(){
        for(Set<Integer> set : sets){
            bench(dSet.getData(),dSet.getSize(),set, set::add,"add");
            bench(dSet.getData(),dSet.getSize(),set, set::remove,"remove");
            System.out.println();
        }
    }

    private void bench(int[] data, int size,Set set, Action action, String actionName){
        start();
        for (int i = 0;i < size; i++){
            action.doAction(data[i]);
        }
        finish(set.getClass().getName(),actionName);
    }
}
