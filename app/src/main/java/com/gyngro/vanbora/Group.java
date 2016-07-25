package com.gyngro.vanbora;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandre Lara on 01/07/2016.
 */

public class Group implements Serializable {

    private String name, shift, van;
    Map<String, Object> members = new HashMap<>();
    Map<String, Object> neighbourhoods = new HashMap<>();

    public Group(){

    }

    public Group( String name, String shift, String van, Map<String, Object> members, Map<String, Object> neighbourhoods ){
        this.name = name;
        this.shift = shift;
        this.van = van;
        this.members = members;
        this.neighbourhoods = neighbourhoods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getVan() {
        return van;
    }

    public void setVan(String van) {
        this.van = van;
    }

    public Map<String, Object> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Object>members) {
        this.members = members;
    }

    public Map<String, Object> getNeighbourhoods() {
        return neighbourhoods;
    }

    public void setNeighbourhoods(Map<String, Object> neighbourhoods) {
        this.neighbourhoods = neighbourhoods;
    }
}
