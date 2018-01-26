package com.test.gameoflife.game;

import java.util.concurrent.Callable;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class Human implements Callable<Boolean> {

    private State state;
    private Universe universe;
    private Location location;

    public Human(Universe universe, Location location) {
        this(State.DEAD, universe, location);
    }

    public Human(State state, Universe universe, Location location) {
        this.state = state;
        this.universe = universe;
        this.location = location;
    }

    @Override
    public Boolean call() throws Exception {
        return startLife();
    }



    private Boolean startLife() {

        universe.updateHumanState(this);
        return true;

    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Human{" +
                "location=" + location +
                ", state=" + state +
                '}';
    }
}
