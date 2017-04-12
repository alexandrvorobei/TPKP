package grsu.dataModel;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Area {
    private List<Double> points = new ArrayList<>();

    public Area() {
    }

    public void addPoint(Double point) {
        this.points.add(point);
    }

    public String toString() {
        String string = "[";
        Iterator iter = this.points.iterator();
        while(iter.hasNext()) {
            Double point = (Double)iter.next();
            string = string + "[" + point.x + ", " + point.y + "]";
            if(point != this.points.get(this.points.size() - 1)) {
                string = string + ", ";
            } else {
                string = string + "]";
            }
        }
        return string;
    }
}

