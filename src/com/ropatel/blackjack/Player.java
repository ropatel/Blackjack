package com.ropatel.blackjack;

public class Player {

    private String name;
    private double bank;
    private Hand hand;

    public Player(String name) {
        double INITIAL_BANK = 200.0;

        this.name = name;
        bank = INITIAL_BANK;
        hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public double bet(double wager) {
        if (bank < wager) return 0.0;
        bank -= wager;
        return wager;
    }

    public void clearHand() {
        hand = new Hand();
    }

    public void addFunds(double funds) {
        bank += funds;
    }

    public void totalFunds() {
        System.out.printf(name + " has $%.2f\n", bank);
        System.out.println();
    }

    public void showHand() {
        hand.showHand(true, false);
    }

    public void showHandWithSum() {
        hand.showHand(true, true);
    }

    public Hand getHand() {
        return hand;
    }

    public int getSum() {
        return hand.getSum();
    }

}
