package com.ropatel.blackjack;

public abstract class BlackjackPlayer extends CardPlayer {


    public BlackjackPlayer() {
        super();
    }

    public BlackjackState getHandState() {
        int handTotal = getHandTotal();

        if (handTotal < 17) {
            return BlackjackState.UNDER_SEVENTEEN;
        } else if (handTotal < 21) {
            return BlackjackState.UNDER_TWENTYONE;
        } else if (handTotal == 21) {
            if (checkNaturalBlackjack()) {
                return BlackjackState.NATURAL_BLACKJACK;
            } else {
                return BlackjackState.BLACKJACK;
            }
        } else {
            return BlackjackState.BUST;
        }
    }

    public void showHand() {
        hand.showHand(true, false);
    }

    public void showHandWithSum() {
        hand.showHand(true, true);
    }

    public void showHiddenHand() {
        hand.showHand(false, false);
    }

    private boolean checkNaturalBlackjack() {
        return (hand.size() == 2 && containsAce() && containsFace());
    }

    private boolean containsFace() {
        return (hand.contains(Rank.KING) || hand.contains(Rank.QUEEN) || hand.contains(Rank.JACK) || hand.contains(Rank.TEN));
    }

    private boolean containsAce() {
        return hand.contains(Rank.ACE);
    }
}
