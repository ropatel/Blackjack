package com.ropatel.blackjack;

public enum Suit {
    CLUBS(0x2663),
    SPADES(0x2660),
    HEARTS(0x2665),
    DIAMONDS(0x2666);

    private String symbol;

    Suit(int c) {
        this.symbol = Character.toString((char) c);
    }

    public String getSymbol() {
        return this.symbol;
    }
}
