package com.ropatel.blackjack;

public class Player {

    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void showHand() {
        hand.showHand(true);
    }

    public Hand getHand() {
        return hand;
    }

    public int getSum() {
        return hand.getHandSum();
    }

}
