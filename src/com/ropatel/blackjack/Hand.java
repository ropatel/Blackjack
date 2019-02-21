package com.ropatel.blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

    protected List<Card> hand;
    protected int size;
    protected int sum;

    public Hand() {
        hand = new ArrayList<>();
        size = 0;
        sum = 0;
    }



    public void addCardToHand(Card card) {
        this.hand.add(card);
        size++;

    }

    public boolean contains(Rank rank) {
        for (Card card : hand) {
            if (card.getRank() == rank) return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public abstract int getSum();

    public void showHand(boolean faceup, boolean showSum) {
        for (Card card : hand) {
            if (faceup) {
                card.setFaceup(true);
            }
            flipCard(card);
        }
        if (showSum) {
            System.out.println("     Sum = " + sum);
        } else {
            System.out.println();
        }
    }

    private void flipCard(Card card) {
        int DELAY = 100;
        System.out.print(card + " ");
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
