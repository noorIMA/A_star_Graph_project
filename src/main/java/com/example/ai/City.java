package com.example.ai;





import java.util.ArrayList;
import java.util.List;

public class City implements Comparable<City> {
    private int index;
    private int X;
    private int Y;
    private List<Edge> edges;
    private boolean visited;
    private City previousVertex;
    private double minDistance = Double.MAX_VALUE;

    public City(int index, int x, int y) {
        this.visited = false;
        this.previousVertex = null;
        this.edges = new ArrayList<>();
        this.index = index;
        this.X = x;
        this.Y = y;

    }



    public void addNeighbour(Edge edge) {
        this.edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public City getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(City previousVertex) {
        this.previousVertex = previousVertex;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }
    public int getIndex() {
        return index;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int compareTo(City otherVertex) {
        return Double.compare(this.minDistance, otherVertex.minDistance);
    }
}
