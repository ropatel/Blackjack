package com.ropatel.blackjack;

public abstract class CardPlayer {
    protected double wagerPool;
    protected BlackjackHand hand;

    public CardPlayer() {
        this.wagerPool = 0;
        this.hand = new BlackjackHand();
    }

    public void putCard(Card card) {
        hand.addCardToHand(card);
    }

    public int getHandTotal() {
        return hand.getSum();
    }

    public void clearHand() {
        hand = new BlackjackHand();
    }

    public abstract void showHand();
}
