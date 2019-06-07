/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

/**
 *
 * @author Rajat.kumar
 */
public class Images {

    String xs;
    String s;
    String m;
    String l;

    public Images(String imageUrl) {
        imageUrl = imageUrl.substring(0, imageUrl.length() - 9);
        this.xs = imageUrl + "125_0.jpg";
        this.s = imageUrl + "126_0.jpg";
        this.m = imageUrl + "127_0.jpg";
        this.l = imageUrl + "128_0.jpg";
    }
    
    public Images(String imageUrl,String format) {
        
        imageUrl = imageUrl.substring(0, imageUrl.length() - 9);
        this.xs = imageUrl + "125_0."+format;
        this.s = imageUrl + "126_0."+format;
        this.m = imageUrl + "127_0."+format;
        this.l = imageUrl + "128_0."+format;
    }

    public Images(String xs, String s, String m, String l) {
        this.xs = xs;
        this.s = s;
        this.m = m;
        this.l = l;
    }

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

}
