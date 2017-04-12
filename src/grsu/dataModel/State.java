package grsu.dataModel;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.*;

public class State {
    private String nameState;
    public List<Area> areas;
    public List<Polygon> polygons;

    public State(String nameState) {
        this.nameState = nameState;
        this.areas = new ArrayList();
        this.polygons = new ArrayList<>();
    }

    public String getNameState(){
        return nameState;
    }
    public String toString() {
        return "\"" + this.nameState + "\" : " + this.areasToString();
    }

    public String areasToString() {
        String string = "[";
        Iterator iter = this.areas.iterator();
        while(iter.hasNext()) {
            Area area = (Area)iter.next();
            string = string + area.toString();
            if(area != this.areas.get(this.areas.size() - 1)) {
                string = string + ", ";
            } else {
                string = string + "]";
            }
        }
        return string;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        State temp = (State) obj;
        return temp.nameState == nameState && temp.areas == areas && temp.polygons == polygons;
    }

    @Override
    public int hashCode(){
        int result = nameState.hashCode();
        result = 31*result + areas.hashCode();
        result = 31*result + polygons.hashCode();
        return result;
    }
}
