package com.ropatel.blackjack;

public abstract class Human {

    protected String name;

    public Human() {
    }

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
