package com.ropatel.blackjack;

public abstract class Gambler extends Human {

    protected double bank;

    public Gambler() {
        super();
    }

    public Gambler(String name) {
        super(name);
        double INITIAL_BANK = 200.;
        this.bank = INITIAL_BANK;
    }

    public double bet(double bet) {
        if (bank < bet) {
            return 0;
        } else {
            bank -= bet;
            return bet;
        }
    }

    public String getName() {
        return name;
    }

    public void showBank() {
        System.out.printf(name + " has $%.2f\n", bank);
        System.out.println();
    }
}
