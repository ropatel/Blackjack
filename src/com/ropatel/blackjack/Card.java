package com.ropatel.blackjack;

public class Card {
    private Suit suit;
    private Rank rank;
    private boolean faceup;
    private String FACEDOWN_SYMBOL = "#";

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceup = true;
    }

    public void setFaceup(boolean faceup) {
        this.faceup = faceup;
    }

    public boolean isFaceup() {
        return faceup;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public String toString() {
        if (faceup) {
            return this.rank.getSymbol() + this.suit.getSymbol();
        } else {
            return FACEDOWN_SYMBOL;
        }
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.getValue();
    }
}
