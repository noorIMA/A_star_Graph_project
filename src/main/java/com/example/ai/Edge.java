package com.example.ai;

public class Edge {


    private City targetVertex;
    private City startVertex;
    private double weight;


    public Edge(double weight, City startVertex, City targetVertex) {
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
        this.weight = weight;

    }



    public City getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(City targetVertex) {
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public City getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(City startVertex) {
        this.startVertex = startVertex;
    }
    @Override
    public String toString() {
        return "Edge [weight=" + weight + ", startVertex=" + startVertex.getIndex() + ", targetVertex=" + targetVertex.getIndex() + "]";
    }
}