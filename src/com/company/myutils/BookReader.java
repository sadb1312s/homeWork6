package com.company.myutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BookReader {
    private String path;
    private File file;

    public BookReader(String path) {
        this.path = path;
        this.file = new File(this.path);
    }

    //ex7
    public void parseBook(){
        try {
            Scanner scanner = new Scanner(file);
            TreeMap<String,Integer> wordsMap = new TreeMap<>();

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                str = str.replaceAll("\\p{P}", "");

                String[] words = str.split(" ");

                for(String o : words){
                    if(wordsMap.containsKey(o)){
                        int old = wordsMap.get(o)+1;
                        wordsMap.replace(o,old);
                    }else {
                        wordsMap.put(o,1);
                    }
                }

            }


            wordsMap.forEach((String k,Integer v)->{
                System.out.println(k+" "+v);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //ex8
    public void parserStringBook(){

        try {
            Scanner scanner = new Scanner(file);

            HashMap<String,HashSet<String>> wordsMap = new HashMap<>();

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                str = str.replaceAll("\\p{P}", "");

                String[] words = str.split(" ");

                for(String o : words){
                    if(wordsMap.containsKey(o)){
                        wordsMap.get(o).add(str);
                    }else {
                        HashSet<String> set = new HashSet<>();
                        set.add(str);
                        wordsMap.put(o,set);
                    }
                }

            }

            wordsMap.forEach((String s,HashSet<String> set)->{
                System.out.println(s+" "+set);
            });






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
