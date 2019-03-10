/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymaxheapdemo;

/**
 *
 * @author Hallur
 */
public class Passenger {

    private String name;
    private int priority;

    public Passenger(String name) throws Exception {
        if(!name.equals("monkey") && !name.equals("family") && !name.equals("businessClass") && !name.equals("disabled") && !name.equals("lateForFlight")){
            throw new Exception("passenger; " + name + " does not exist");
        }
        if (name.equals("monkey")) {
            this.name = name;
            this.priority = 1;
        }
        if (name.equals("family")) {
            this.name = name;
            this.priority = 2;
        }
        if (name.equals("businessClass")) {
            this.name = name;
            this.priority = 3;
        }
        if (name.equals("disabled")) {
            this.name = name;
            this.priority = 4;
        }
        if (name.equals("lateForFlight")) {
            this.name = name;
            this.priority = 5;
        }
    }

    public String getName() {
        return name;
    }
    
    public int getPriority() {
        return priority;
    }
}
