package com.vsaf.common;

import java.util.Iterator;
import java.util.ArrayList;


public class AreaChecker {
    // private ArrayList<Point> points;
    
    // private void checkAllPoints(ArrayList<Point> points, float r) {
    //     Iterator<Point> itr = points.iterator();
    //     while(itr.hasNext()) checkPoint(itr.next(), r);
    // }
    public static void checkPoint(Point p){
        Float r = p.getRadius();
        if(p.getY()>=0 && p.getX()<=0 && (p.getX() * p.getX() + p.getY() * p.getY()) <= r*r ){
            p.setIncluded(true);
            return;
        }
        if(p.getX()>=0&&p.getX()<=r/2&&p.getY()<=0&&p.getY()>=(p.getX()*2 - r)){
            p.setIncluded(true);
            return;
        }
        if(p.getX()>=0&&p.getX()<=r/2&&p.getY()>=0&&p.getY()<=r){
            p.setIncluded(true);
            return;
        }
        p.setIncluded(false);
    }
}
