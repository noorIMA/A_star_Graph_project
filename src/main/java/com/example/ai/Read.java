package com.example.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

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
                System.out.println("//The  registration Compeleted");
                continue;
            }
            CityOrEdge = line.split(",");/// split

            if (CityOrEdge.length == 3) {
                City vertex = new City(Integer.parseInt(CityOrEdge[0].trim()), Integer.parseInt(CityOrEdge[1].trim()),
                        Integer.parseInt(CityOrEdge[2].trim()));
                cities.add(vertex);
            } else if (CityOrEdge.length == 1) {
                cost.add(Double.parseDouble(CityOrEdge[0]));
            } else if (CityOrEdge.length != 2) {
                System.out.println("error " + counter + "\n" + CityOrEdge.length + "\n" + line);
                break;
            } else {
                counter++;
            }
        }

        scan.close();
        AddNeighbour();
        return cities;
    }

    private static void AddNeighbour() throws FileNotFoundException {
        double weight = 0;
        double weigth1 = 0;
        double weightAll = 0;
        City a;
        City b;
        File data = new File("roads.txt");
        Scanner scan = new Scanner(data);
        String line;
        String CityOrEdge[];
        int counter = 0;
        while (scan.hasNext()) {
            line = scan.next();
            counter++;

            if (line.equals("-")) {
                System.out.println("//The  registration Compeleted");
                continue;
            }
            CityOrEdge = line.split(",");/// split

            if (CityOrEdge.length == 3) {
                weigth1 = Double.parseDouble(CityOrEdge[0].trim());
                a = findCity(Integer.parseInt(CityOrEdge[1].trim()));
                b = findCity(Integer.parseInt(CityOrEdge[2].trim()));
            }

            else {
                System.out.println("error2 " + counter + "\n" + CityOrEdge.length + "\n" + line);
                break;
            }
            if (a != null && b != null) {
                weight = calculateDist(a, b);
                weightAll = weigth1 + weight;
                a.addNeighbour(new Edge(weightAll, a, b));
                b.addNeighbour(new Edge(weightAll, b, a));
            }
        }
        scan.close();
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
        double finalRes = res * 0.2645833;
//		1 PX = 2.645833E-7 km.
        return finalRes;

    }

}
