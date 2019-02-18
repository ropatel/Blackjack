package com.ropatel.blackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;
    private int size;
    private int sum;

    public Hand() {
        hand = new ArrayList<>();
        size = 0;
        sum = 0;
    }

    public boolean checkUpAce() {
        if (size == 2) {
            for (Card card : hand) {
                if (card.isFaceup()) {
                    if (card.getRank() == Rank.ACE) return true;
                }
            }
        }
        return false;
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
        size++;
        if (card.getRank() == Rank.ACE) {
            if (sum < 11) {
                sum += 11;
            } else {
                sum++;
            }
        } else {
            sum += card.getValue();
        }
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

    public int getSum() {
        return sum;
    }

    public void showHand(boolean faceup, boolean showSum) {
        for (Card card : hand) {
            if (faceup) {
                card.setFaceup(true);
            }
            System.out.print(card + " ");
        }
        if (showSum) {
            System.out.println("     Sum = " + sum);
        } else {
            System.out.println();
        }
    }
}
