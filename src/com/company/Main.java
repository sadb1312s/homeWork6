package com.company;

import com.company.algorithms.Dijkstra;
import com.company.algorithms.Erathostenes;
import com.company.mycollections.MyLinkedList;
import com.company.mycollections.benchmarks.*;
import com.company.myutils.BookReader;
import com.company.myutils.Swaper;
import com.company.streams.StreamUtils;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //part 1 my Linked List and collection benchmark;
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(213);
        list.add(11);
        list.add(12123);
        list.set(2,777);
        list.remove(1);
        list.add(1);
        list.add(2);

        list.clear();
        System.out.println(list);

        System.out.println("=== my Linked List test ===");
        //ex 1 MyLinkedList vs java.util.LinkedList
        System.out.println("-- ex 1 --");
        BenchmarkUtils.myCollectionBenchmark(10_000_000);

        System.out.println("-- ex 2 --");
        //ex 2
        BenchmarkUtils.collectionBenchmark(10_000_000);


        //part 2 collections exercises;
        System.out.println("=== collections exercises ===");
        //ex1
        System.out.println("--- ex 1 ---");
        Erathostenes primeNumber = new Erathostenes(100);
        List<Integer> prime = primeNumber.getPrime();
        System.out.println(prime);
        System.out.println();

        //ex5
        System.out.println("--- ex 5 ---");
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        Swaper swaper = new Swaper();
        swaper.swap(list1,0,4);
        System.out.println(list1);
        System.out.println();


        //ex6
        System.out.println("--- ex 6 ---");
        HashMap<String, HashSet<Integer>> map = new HashMap<>();
        //this don't compile
        //foo(map);

        //see foo2, need do so
        foo2(map);
        System.out.println();


        //ex7
        System.out.println("--- ex 7 ---");
        BookReader bookReader = new BookReader("F:/NetCracker/homeWork6/voyna-i-mir-tom-1.txt");
        bookReader.parseBook();
        System.out.println();

        //ex8
        System.out.println("--- ex 8 ---");
        bookReader.parserStringBook();
        System.out.println();

        //ex10
        System.out.println("--- ex 10 ---");
        Dijkstra ways = new Dijkstra();
        System.out.println();


        //part 3 stream exercises;
        System.out.println("stream exercises");

        StreamUtils streamUtils = new StreamUtils("F:/NetCracker/homeWork6/voyna-i-mir-tom-1.txt");
        //ex 7
        System.out.println("--- ex 7 ---");

        streamUtils.readFile();
        System.out.println();


        //ex 8
        System.out.println();
        System.out.println("--- ex 8 ---");
        streamUtils.getFlatMapping();
        System.out.println();

        //ex 9
        System.out.println();
        System.out.println("--- ex 9 ---");
        streamUtils.getVowelsWords();
        System.out.println();

        //ex 10
        System.out.println();
        System.out.println("--- ex 10 ---");
        streamUtils.getAverageStringLen();
        System.out.println();

        //ex 11
        System.out.println();
        System.out.println("--- ex 11 ---");
        streamUtils.getMaxString();
        System.out.println();

        //ex 15
        System.out.println();
        System.out.println("--- ex 15 ---");
        streamUtils.getDoubleStreamAverage();
        System.out.println();

        //ex 16
        System.out.println();
        System.out.println("--- ex 16 ---");
        streamUtils.getPrime();
        System.out.println();



        //ex 17
        System.out.println("--- ex 17 ---");
        streamUtils.getLongestString();
        System.out.println();
    }


    public static void foo(Map<String, Set<Integer>> x){

    }

    public static void foo2(Map<String,? extends Set> x){

    }
}
