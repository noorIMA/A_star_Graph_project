package com.example.ai;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    private List<City> Cityp;
    private long counter;
    private long space;
    private PriorityQueue<City> CitiesQueue;
    private double weight;
    private double dist;

    public AStar() {
        setCityp(new ArrayList<>());
        CitiesQueue = new PriorityQueue<>();
        counter = 0;
        space = 0;
        weight = 0.0;
        dist = 0.0;
    }

    public void computeCityp(City StartCity) {
        StartCity.setMinDistance(0);
        CitiesQueue.add(StartCity);

        while (!CitiesQueue.isEmpty()) {
            City nextCity = CitiesQueue.poll();

            for (Edge edge : nextCity.getEdges()) {
                City X = edge.getTargetVertex();
                weight = edge.getWeight();
                dist = nextCity.getMinDistance() + weight;

                if (dist < X.getMinDistance()) {
                    CitiesQueue.remove(nextCity);
                    X.setPreviousVertex(nextCity);
                    X.setMinDistance(dist);
                    CitiesQueue.add(X);
                }
            }

            space += CitiesQueue.size();
            counter++;
        }
    }

    public List<City> getShortestCitypTo(City targetCity) {
        setCityp(new ArrayList<>());

        for (City vertex = targetCity; vertex != null; vertex = vertex.getPreviousVertex()) {
            getCityp().add(0, vertex);
        }

        return getCityp();
    }

    public long getCounter() {
        return counter;
    }

    public long getSpace() {
        return space;
    }

    public List<City> getCityp() {
        return Cityp;
    }

    public void setCityp(List<City> cityp) {
        Cityp = cityp;
    }
}
