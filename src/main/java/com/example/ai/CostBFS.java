package com.example.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CostBFS {
    private static ArrayList<City> cities = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        readDataToList();

    }

    static ArrayList<City> readDataToList() throws FileNotFoundException {
        File data = new File("C:\\Users\\Admin\\Desktop\\AIPROJ1\\AirDistanc.txt");
        Scanner scan = new Scanner(data);

        int counter = 0;
        while (scan.hasNext()) {
            String line = scan.nextLine().trim();
            counter++;

            if (line.equals("-")) {
                System.out.println(" registration is  completed");
                continue;
            }

            String[] cityData = line.split(",");

            if (cityData.length == 3) {
                int index = Integer.parseInt(cityData[0].trim());
                int x = Integer.parseInt(cityData[1].trim());
                int y = Integer.parseInt(cityData[2].trim());
                City city = new City(index, x, y);
                cities.add(city);
            } else if (cityData.length == 2) {
                City a = findCity(Integer.parseInt(cityData[0].trim()));
                City b = findCity(Integer.parseInt(cityData[1].trim()));
                if (a != null && b != null) {
                    double weight = calculateDist(a, b);
                    a.addNeighbour(new Edge(weight, a, b));
                    b.addNeighbour(new Edge(weight, b, a));
                }
            } else {
                System.out.println("line error !! " + counter + ": " + line);
                break;
            }
        }

        scan.close();
        return cities;
    }

    private static City findCity(int index) {
        for (City city : cities) {
            if (city.getIndex() == index)
                return city;
        }
        return null;
    }

    private static double calculateDist(City a, City b) {
        double x1 = a.getX();
        double y1 = a.getY();
        double x2 = b.getX();
        double y2 = b.getY();
        double res = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        return res;
    }
}