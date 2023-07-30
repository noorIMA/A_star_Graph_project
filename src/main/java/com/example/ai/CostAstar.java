package com.example.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CostAstar {
    public static ArrayList<City> cities = new ArrayList<>();
    public static ArrayList<Double> cost = new ArrayList<>();

    static ArrayList<City> readDataToList() throws FileNotFoundException {
        File data = new File("C:\\Users\\Admin\\Desktop\\AIPROJ1\\AirDistanc.txt");
        Scanner scan = new Scanner(data);

        int counter = 0;
        while (scan.hasNext()) {
            String line = scan.next();
            counter++;

            if (line.equals("-")) {
                System.out.println(" registration  is Completed");
                continue;
            }

            String[] cityOrEdge = line.split(",");

            if (cityOrEdge.length == 3) {
                City vertex = new City(
                        Integer.parseInt(cityOrEdge[0].trim()),
                        Integer.parseInt(cityOrEdge[1].trim()),
                        Integer.parseInt(cityOrEdge[2].trim())
                );
                cities.add(vertex);
            } else if (cityOrEdge.length == 1) {
                cost.add(Double.parseDouble(cityOrEdge[0]));
            } else if (cityOrEdge.length != 2) {
                System.out.println("MISTAKE :(!! " + counter + "\n" + cityOrEdge.length + "\n" + line);
                break;
            }
        }

        scan.close();
        addNeighbour();
        return cities;
    }

    private static void addNeighbour() throws FileNotFoundException {
        double weight;
        double weight1;
        double weightAll;
        City a;
        City b;
        File data = new File("C:\\Users\\Admin\\Desktop\\AIPROJ1\\roads.txt");
        Scanner scan = new Scanner(data);

        int counter = 0;
        while (scan.hasNext()) {
            String line = scan.next();
            counter++;

            if (line.equals("-")) {
                System.out.println(" registration is Completed");
                continue;
            }

            String[] cityOrEdge = line.split(",");

            if (cityOrEdge.length == 3) {
                weight1 = Double.parseDouble(cityOrEdge[0].trim());
                a = findCity(Integer.parseInt(cityOrEdge[1].trim()));
                b = findCity(Integer.parseInt(cityOrEdge[2].trim()));

                if (a != null && b != null) {
                    weight = calculateDist(a, b);
                    weightAll = weight1 + weight;
                    a.addNeighbour(new Edge(weightAll, a, b));
                    b.addNeighbour(new Edge(weightAll, b, a));
                }
            } else {
                System.out.println("MISTAKE :( " + counter + "\n" + cityOrEdge.length + "\n" + line);
                break;
            }
        }

        scan.close();
    }

    private static City findCity(Integer index) {
        for (City city : cities) {
            if (city.getIndex() == index) {
                return city;
            }
        }
        return null;
    }

    public static int findIndex(City city) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).equals(city)) {
                return cities.get(i).getIndex();
            }
        }
        return -1;
    }
    private static double calculateDist(City a, City b) {
        double x1 = a.getX();
        double y1 = a.getY();
        double x2 = b.getX();
        double y2 = b.getY();

        double distanceSquared = (y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1);
        double distance = Math.sqrt(distanceSquared);
        double finalDistance = distance * 0.2645833; // 1 PX is 2.645833E-7 km.

        return finalDistance;
    }

}
