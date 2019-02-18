package com.ropatel.blackjack;

public class Card {
    private Suit suit;
    private Rank rank;
    private boolean faceup;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceup = true;
    }

    public void setFaceup(boolean faceup) {
        this.faceup = faceup;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public String toString() {
        if (faceup) {
            return this.rank.getSymbol() + this.suit.getSymbol();
        } else {
            return "X";
        }
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.getValue();
    }
}
