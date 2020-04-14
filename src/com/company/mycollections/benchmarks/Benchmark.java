package com.company.mycollections.benchmarks;

public class Benchmark {
    private Long start;
    public DataSet dSet;

    public Benchmark(){

    }

    public Benchmark(DataSet dSet) {
        this.dSet = dSet;
    }

    public void start(){
        start = System.nanoTime();
    }

    public void finish(String className, String actionName){
        Long finish = System.nanoTime();
        System.out.println(className+" test "+actionName+" finish, time = "+((finish - start) / 1_000_000)   + " ms");
        start = 0L;
    }

    public void startBench(){
        //override this in child
    }
}
