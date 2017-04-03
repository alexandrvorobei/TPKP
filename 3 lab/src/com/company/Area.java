package com.company;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Area extends java.awt.geom.Area {
    private List<Point2D.Double> points;

    public List<Point2D.Double> getPoints() {
        return points;
    }

    public void setPoints(List<Point2D.Double> points) {
        this.points = points;
    }

    public Area(){
        points = new ArrayList<>();
    }

    public void addPoint(Point2D.Double point){
        points.add(point);
    }

    public String toString(){
        String string = "[";
        for (Point2D.Double point : points){
            string += "[" + point.x + ", " + point.y + "]";
            if(point != points.get(points.size() - 1))
                string += ", ";
            else string += "]";
        }
        return string;
    }
}

