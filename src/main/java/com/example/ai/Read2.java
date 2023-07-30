package com.example.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read2 {

    public static ArrayList<City> cities = new ArrayList<City>();
    public static ArrayList<Double> cost = new ArrayList<Double>();

    static ArrayList<City> readDataToList() throws FileNotFoundException {
        File data = new File("AirDistanc.txt");

        Scanner scan = new Scanner(data);
        String line;
        String CityOrEdge[];
        int counter = 0;
        while (scan.hasNext()) {
            line = scan.next();
            counter++;

            if (line.equals("-")) {
                System.out.println("//The cities registration Compeleted");
                continue;
            }
            CityOrEdge = line.split(",");/// split

            if (CityOrEdge.length == 3) {
                City vertex = new City(Integer.parseInt(CityOrEdge[0].trim()), Integer.parseInt(CityOrEdge[1].trim()),
                        Integer.parseInt(CityOrEdge[2].trim()));
                cities.add(vertex);
            } else if (CityOrEdge.length == 2) {
                City a = findCity(Integer.parseInt(CityOrEdge[0].trim()));
                City b = findCity(Integer.parseInt(CityOrEdge[1].trim()));
                if (a != null && b != null) {
                    double weight = calculateDist(a, b);
                    a.addNeighbour(new Edge(weight, a, b));
                    b.addNeighbour(new Edge(weight, b, a));
                }

            } else if (CityOrEdge.length != 2) {
                System.out.println("error " + counter + "\n" + CityOrEdge.length + "\n" + line);
                break;
            } else {
                counter++;
            }
        }

        scan.close();

        return cities;
    }

    private static City findCity(Integer index) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getIndex() == index)
                return cities.get(i);

        }
        return null;
    }

    public static int findIndex(City city) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).equals(city))
                return cities.get(i).getIndex();

        }
        return (Integer) null;
    }

    // calculate euclidean distance between two cities.
    private static double calculateDist(City a, City b) {
        double x1 = a.getX();
        double y1 = a.getY();
        double x2 = b.getX();
        double y2 = b.getY();
        double res = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        double finalRes = res;
        // 1 PX = 2.645833E-7 km.
        // in BFS we wont change from pixel to km because the number is very low

        return finalRes;

    }

}
