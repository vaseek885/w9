package com.vsaf.common;

import java.io.Serializable;

public class Point implements Serializable{

    private long id;


    private float x;


    private float y;


    private boolean included;


    private float radius;


    public Point(){}

    public Point(float x, float y, float r){
        this.x = x;
        this.y = y;
        this.radius = r;
        included = false;
        id = hashCode();
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj){
        Point p = null;
        try {
            p = (Point)obj;
        }catch (ClassCastException cce){return false;}

        if( p != null ){
            if( this.x == p.x && this.y == p.y ){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        return (int)(x*1000000+y*1000 + radius);
    }
}
