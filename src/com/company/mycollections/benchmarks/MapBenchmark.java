package com.company.mycollections.benchmarks;

import java.util.*;

public class MapBenchmark extends Benchmark{
    private List<Map<Integer,Object>> maps;

    public MapBenchmark(DataSet dataSet,Map<Integer,Object>... maps) {
        super(dataSet);

        this.maps = new ArrayList<>();
        this.maps.addAll(Arrays.asList(maps));
    }

    public void startBench(){
        for(Map<Integer,Object> map : maps){

            bench(dSet.getData(),dSet.getSize(),map,(int x)->map.put(x,new Object()),"put");
            bench(dSet.getData(),dSet.getSize(),map, map::get,"get");
            bench(dSet.getData(),dSet.getSize(),map, map::remove,"remove");
            System.out.println();
        }
    }

    private void bench(int[] data, int size,Map map, Action action, String actionName){
        start();
        for (int i = 0;i < size; i++){
            action.doAction(data[i]);
        }
        finish(map.getClass().getName(),actionName);
    }
}
