package com.company.mycollections.benchmarks;

import com.company.mycollections.MyLinkedList;

import java.util.*;

public class BenchmarkUtils {

    public static void myCollectionBenchmark(int size){

        DataSet dataSet = new DataSet(size);

        LinkedList<Integer> linkedList = new LinkedList<>();
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        MyListBenchmark myListBenchmark = new MyListBenchmark(linkedList,myLinkedList,dataSet);
        myListBenchmark.startBench();
    }

    public static void collectionBenchmark(int size){

        DataSet dataSet = new DataSet(size);
        System.out.println("== LIST BENCHMARK ==");

        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList= new ArrayList<>();

         ListBenchmark test = new ListBenchmark(dataSet,linkedList,arrayList);
         test.startBench();



        System.out.println("== SET BENCHMARK ==");

        HashSet<Integer> set1 = new HashSet<>();
        LinkedHashSet<Integer> set2 = new LinkedHashSet<>();
        TreeSet<Integer> set3 = new TreeSet<>();

        SetBenchmark setBenchmark = new SetBenchmark(dataSet,set1,set2,set3);
        setBenchmark.startBench();


        System.out.println("== Map BENCHMARK ==");

        HashMap<Integer,Object> map = new HashMap<>();
        LinkedHashMap<Integer,Object> map2 = new LinkedHashMap<>();
        TreeMap<Integer,Object> map3 = new TreeMap<>();

        MapBenchmark mapBenchmark = new MapBenchmark(dataSet,map,map2,map3);
        mapBenchmark.startBench();



    }
}
