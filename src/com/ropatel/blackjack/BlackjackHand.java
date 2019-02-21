package com.ropatel.blackjack;

public class BlackjackHand extends Hand {


    public BlackjackHand() {
        super();
    }

    @Override
    public int getSum() {
        boolean aceFound = false;

        for (Card card : hand) {
            sum += card.getValue();
            if (!aceFound) {
                if (card.getRank() == Rank.ACE) aceFound = true;
            }
        }

        if (sum > 21 && aceFound) {
            sum -= 10;
        }

        return sum;
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
}
