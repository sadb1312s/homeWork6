package com.company.streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {
    private String path;
    private File file;
    private Scanner scanner;
    //ex 7

    public StreamUtils(String path) {
        this.path = path;
        this.file = new File(this.path);


    }

    //Turning a file into a stream of tokens, list the first 100 tokens that are
    //words in the sense of the preceding exercise. Read the file again and list
    //the 10 most frequent words, ignoring letter case.
    public void readFile(){
        try{
            scanner = new Scanner(file);
            Stream<String> words = scanner.tokens().filter(s -> {
                for (char o : s.toCharArray()) {
                    if (!Character.isAlphabetic(o)) {
                        return false;
                    }
                }
                return true;
            }).limit(100);

            List<String> strings = words.collect(Collectors.toList());
            System.out.println(strings.size());

            strings.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //ex 8

    //Find a realistic use for the Collectors.flatMapping method. Consider some
    //class with a method yielding an Optional . Then group by some characteristic
    //and, for each group, collect the nonempty optional values by using
    //flatMapping and Optional.stream .

    private class Person{
        private String name;

        public Person(){
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getOptional(){
            return Optional.ofNullable(name == null || name.equals("") ? null : toString());
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private class Boss extends Person{
        private int salary;

        public Boss(){

        }

        public Boss(String name, int salary) {
            super(name);
            this.salary = salary;
        }


        public Optional<String> getOptional(){
            return Optional.ofNullable(super.getName() == null || super.getName().equals("") ? null : toString());
        }

        @Override
        public String toString() {
            return "Boss{" +
                    "name='" + super.getName() + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    public void getFlatMapping(){
        //stream of person.getOptional();. if person name equals null or "" return empty optional
        List<Object> list =Stream.of(
                List.of(new Person().getOptional(),
                        new Person("Oleg").getOptional(),
                       new Person("").getOptional(),
                       new Person("Андрей").getOptional()
                ),
                List.of(
                        new Boss().getOptional(),
                        new Boss("Albert",10).getOptional()
                )
        ).collect(Collectors.flatMapping(l -> l.stream().filter(i -> !i.isEmpty()
           ), Collectors.toList()));


        list.forEach(System.out::println);
    }

    // ex 9
    //Read the words from /usr/share/dict/words (or a similar word list) into a
    //stream and produce an array of all words containing five distinct vowels.
    public void getVowelsWords(){
        try{
            scanner = new Scanner(file);
            Stream<String> words = scanner.tokens().filter(s -> {
                char[] chars = s.toCharArray();
                Character[] characters = new Character[chars.length];
                for(int i = 0; i < s.length(); i++){
                    characters[i] = chars[i];
                }

                Stream<Character> charsStream = Stream.of(characters).filter(c -> vowelsCheck(c)).distinct();

                long count = charsStream.count();

                if(count >= 5){
                    return true;
                }else {
                    return false;
                }
            });

            List<String> strings = words.collect(Collectors.toList());

            strings.forEach(System.out::println);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private boolean vowelsCheck(char c){
        char ch = Character.toLowerCase(c);
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' || //Eng chars
                ch == 'а' ||  ch == 'о' || ch == 'э' || ch == 'и' || ch == 'у' || ch == 'ы'|| ch == 'е' || ch == 'ё' || ch == 'ю' || ch == 'я'){ //Rus chars
            return true;
        }else {
            return false;
        }
    }

    //ex 10
    //Given a finite stream of strings, find the average string length.
    public double getAverageStringLen(){
        try {
            scanner = new Scanner(file);

            Stream<String> stringStream = scanner.tokens();

            IntSummaryStatistics s = stringStream.collect(Collectors.summarizingInt(String::length));

            double average = s.getAverage();
            System.out.println("average word length is "+average);

            return average;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1d;
    }

    //ex 11
    //11 Given a finite stream of strings, find all strings of maximum length.
    public String getMaxString(){
        try {
            scanner = new Scanner(file);

            String max = scanner.tokens().max(Comparator.comparing(String::length)).orElse("text is null");

            System.out.println("Max string is "+max);
            return max;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ex 15
    //Write a call to reduce that can be used to compute the average of a
    //Stream<Double> . Why can’t you simply compute the sum and divide by count() ?
    public double getDoubleStreamAverage(){
        double average = 0;

        Stream<Double> stream = Stream.generate(()-> Math.random()).limit(10);

        //List<Double> list = stream.collect(Collectors.toList());

        DoubleSummaryStatistics summary = stream.collect(
                Collectors.summarizingDouble(Double::doubleValue));

        //System.out.println(summary.getSum()/summary.getCount());// it work same
        System.out.println("Average of double stream is "+summary.getAverage());

        //list.forEach(System.out::println);

        return average;
    }

    //ex 16
    // Find 500 prime numbers with 50 decimal digits, using a parallel stream
    //of BigInteger and the BigInter.isProbablePrime method. Is it any faster than
    //using a serial stream?
    public void getPrime(){
        String start = "1"+ Stream.generate(()->"0").limit(49).collect(Collectors.joining());
        System.out.println("!!!!!!!!!!!!!! "+start+" "+start.length());

        BigInteger first = new BigInteger(start);

        long s,f;

        s = System.nanoTime();
        Object[] bigIntegers = Stream.iterate(first, p -> p.add(new BigInteger("1"))).parallel().filter(p -> p.isProbablePrime(1)).limit(5000).toArray();

        //for(Object o : bigIntegers){
        //    System.out.println(o);
        //}
        f = System.nanoTime();
        System.out.println("Parallel stream time "+((f-s)/1_000_000)+" ms");

        first = new BigInteger(start);

        s = System.nanoTime();
        Object[] bigIntegers2 = Stream.iterate(first, p -> p.add(new BigInteger("1"))).filter(p -> p.isProbablePrime(1)).limit(5000).toArray();

        //for(Object o : bigIntegers2){
        //System.out.println(o);
        //}
        f = System.nanoTime();
        System.out.println("Serial stream time "+((f-s)/1_000_000)+" ms");
        System.out.println("Parallel stream is faster");
    }

    //ex 17
    //Find the 500 longest strings in War and Peace with a parallel stream. Is it
    //any faster than using a serial stream?

    //what is longest string? more part of strings in book have equal lengths     -> strings are words!
    public void getLongestString(){

        try {
            scanner = new Scanner(file);

            long s1,f;

            //good

            /*Stream<String> stringStream = scanner.tokens().sorted(Comparator.comparing(String::length).reversed());
            f = System.nanoTime();

            Map<Integer,Set<String>> map = stringStream.collect(Collectors.groupingBy(s -> s.length(),Collectors.toSet()));
            map.forEach((o, o2) -> System.out.println("length = "+o+" word count = "+o2.size()+" words = "+o2));*/

            Stream<String> startStream = scanner.tokens();
            List<String> strings = startStream.map(s -> s.replaceAll("[\\p{Punct}&&[^_-]]+","")).collect(Collectors.toList());


            s1 = System.nanoTime();

            Stream<String> stringStream = strings.parallelStream().sorted(Comparator.comparing(String::length).reversed()).limit(500);

            Map<Integer,Set<String>> map = stringStream.collect(Collectors.groupingBy(s -> s.length(),Collectors.toSet()));

            f = System.nanoTime();

            System.out.println("Parallel stream time "+((f-s1)/1_000_000)+" ms");

            map.forEach((o, o2) ->{ System.out.println("length = "+o+" word count = "+o2.size()+" words = "+o2);});


            s1 = System.nanoTime();


            Stream<String> stringStream2 = strings.stream().sorted(Comparator.comparing(String::length).reversed()).limit(500);

            Map<Integer,Set<String>> map2 = stringStream2.collect(Collectors.groupingBy(s -> s.length(),Collectors.toSet()));

            f = System.nanoTime();
            System.out.println(" stream time "+((f-s1)/1_000_000)+" ms");


            map2.forEach((o, o2) ->{ System.out.println("length = "+o+" word count = "+o2.size()+" words = "+o2);});

            System.out.println("equal");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
