package com.company.analyzer.datamodel;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class State {
    private String nameState;
    private List<Area> areas;
    private Polygon polygons;

    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Polygon getPolygons() {
        return polygons;
    }

    public void setPolygons(Polygon polygons) {
        this.polygons = polygons;
    }

    public State(String nameState)
    {
        this.nameState = nameState;
        areas = new ArrayList<>();
        //polygons = new Polygon();
    }

    public String toString(){
        return "\"" + nameState + "\" : " + areasToString();
    }
    public String areasToString(){
        String string = "[";
        for(Area area : areas){
            string += area.toString();
            if(area != areas.get(areas.size() - 1))
                string += ", ";
            else
                string += "]";
        }
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State states = (State) o;

        if (nameState != null ? !nameState.equals(states.nameState) : states.nameState != null) return false;
        if (areas != null ? !areas.equals(states.areas) : states.areas != null) return false;
        return polygons != null ? polygons.equals(states.polygons) : states.polygons == null;
    }

    @Override
    public int hashCode() {
        int result = nameState != null ? nameState.hashCode() : 0;
        result = 31 * result + (areas != null ? areas.hashCode() : 0);
        result = 31 * result + (polygons != null ? polygons.hashCode() : 0);
        return result;
    }
}

