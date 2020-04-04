package com.company.algorithms;

import java.util.*;

public class Dijkstra {

    private List<City> country;

    public Dijkstra() {
        country = new ArrayList<>();

        City piter = new City("S.Piterburg");
        City moskow = new City("Moscow");
        City nizniy = new City("N.Novgorod");
        City semenov = new City("Semenov");
        City voronez = new City("Voronez");
        City omsk = new City("Omsk");
        City sochi = new City("Sochi");
        City sevastorol = new City("Sevastopol");

        piter.addNeighbors(moskow,500);

        moskow.addNeighbors(piter,500);
        moskow.addNeighbors(sevastorol,1000);
        moskow.addNeighbors(nizniy,400);
        moskow.addNeighbors(voronez,200);

        nizniy.addNeighbors(moskow,400);
        nizniy.addNeighbors(voronez,500);
        nizniy.addNeighbors(omsk,1000);
        nizniy.addNeighbors(semenov,50);

        semenov.addNeighbors(nizniy,50);

        voronez.addNeighbors(moskow,200);
        voronez.addNeighbors(nizniy,500);
        voronez.addNeighbors(sochi,100);

        sevastorol.addNeighbors(moskow,1000);
        sevastorol.addNeighbors(sochi,100);

        sochi.addNeighbors(sevastorol,100);
        sochi.addNeighbors(voronez,100);
        sochi.addNeighbors(omsk,1000);

        country.add(piter);
        country.add(moskow);
        country.add(nizniy);
        country.add(semenov);
        country.add(voronez);
        country.add(sevastorol);
        country.add(sochi);
        country.add(omsk);

        getShortestWay(omsk,sevastorol);

    }

    public void getShortestWay(City city1, City city2){
        City current = city1;
        current.weight = 0d;

        //find min way fo city1
        while (current != null) {
            //System.out.println("current city = " + current.name);
            for (Neighbor neighbor : current.neighbors) {

                if(!neighbor.city.isVisited){

                    if (current.weight + neighbor.distance < neighbor.city.weight) {
                        neighbor.city.weight = current.weight + neighbor.distance;
                    }
                }
            }

            current.isVisited = true;


            City temp = null;

            for(City city : country){
                if(!city.isVisited) {
                    if (temp == null) {
                        temp = city;
                    } else {
                        if (city.weight < temp.weight) {
                            temp = city;
                        }
                    }
                }
            }

            current = temp;
        }

        //print way
        City temp = city2;

        System.out.println("-> "+temp);

        while (temp != null) {
            boolean found = false;
            for (Neighbor neighbor : temp.neighbors) {

                if (temp.weight == neighbor.city.weight + neighbor.distance
                        && (temp.weight < Double.POSITIVE_INFINITY && neighbor.city.weight < Double.POSITIVE_INFINITY)) {
                    temp = neighbor.city;
                    System.out.println(temp);
                    found = true;

                    if(temp.name.equals(city1.name)){
                        return;
                    }
                }
            }

            if(!found){
                System.out.println("way not found");
                return;
            }


        }
    }


    private class Neighbor{
        private int distance;
        private City city;

        public Neighbor(City city, int distance) {
            this.distance = distance;
            this.city = city;
        }

        @Override
        public String toString() {
            return city +" | "+distance;
        }
    }

    private class City{
        private Double weight = Double.POSITIVE_INFINITY;//min distance to this city
        private boolean isVisited;
        private String name;
        private List<Neighbor> neighbors;

        private City(String name) {
            this.name = name;
            neighbors = new ArrayList();
        }

        private void addNeighbors(City city,int dist){
            neighbors.add(new Neighbor(city,dist));
        }

        @Override
        public String toString() {
            return "City{" +
                    "w=" + weight +
                    ", v=" + isVisited +
                    ", n='" + name + '\'';
        }

    }

}
