package com.company.mycollections.benchmarks;

import com.company.mycollections.MyLinkedList;

import java.util.*;

public class BenchmarkUtils {

    public static void myCollectionBenchmark(int size){
        System.out.println("== MY LIST BENCHMARK ==");
        LinkedList<Integer> linkedList = new LinkedList<>();
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        DataSet dataSet = new DataSet(size);

        MyListBenchmark myListBenchmark = new MyListBenchmark(linkedList,myLinkedList,dataSet);
        myListBenchmark.bench();
    }

    public static void collectionBenchmark(int size){

        System.out.println("== LIST BENCHMARK ==");

        DataSet dataSet1 = new DataSet(size);
        List<Integer> linkedList2 = new LinkedList<>();
        List<Integer> arrayList= new ArrayList<>();

        ListBenchmark benchmark = new ListBenchmark(linkedList2, arrayList,dataSet1);
        benchmark.addTest();

        System.out.println();
        benchmark.randomAddTest();

        System.out.println();
        benchmark.setTest();

        System.out.println();
        benchmark.removeTest();

        System.out.println("== SET BENCHMARK ==");
        DataSet dataSet2 = new DataSet(size);

        HashSet<Integer> set1 = new HashSet<>();
        LinkedHashSet<Integer> set2 = new LinkedHashSet<>();
        TreeSet<Integer> set3 = new TreeSet<>();

        SetBenchmark setBenchmark = new SetBenchmark(set1,set2,set3,dataSet2);
        setBenchmark.addTest();

        System.out.println();
        setBenchmark.removeTest();

        System.out.println("== Map BENCHMARK ==");
        DataSet dataSet3 = new DataSet(size);


        HashMap<Integer,Object> map = new HashMap<>();
        LinkedHashMap<Integer,Object> map2 = new LinkedHashMap<>();
        TreeMap<Integer,Object> map3 = new TreeMap<>();

        MapBenchmark mapBenchmark = new MapBenchmark(map,map2,map3,dataSet3);
        mapBenchmark.addTest();

        System.out.println();
        mapBenchmark.getTest();

        System.out.println();
        mapBenchmark.removeTest();

    }
}
