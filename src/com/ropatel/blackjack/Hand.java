package com.ropatel.blackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public int getHandSum() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getValue();
        }
        return sum;
    }

    public void showHand() {
        for (Card card : hand) {
            System.out.print(card + " ");
            System.out.println();
        }
    }
}