package com.ropatel.blackjack;

public class Dealer {

    private Deck deck;
    private Hand hand;

    Dealer(Deck deck) {
        this.deck = deck;
        deck.shuffleCards();
        hand = new Hand();
    }

    void shuffleCards() {
        deck.shuffleCards();
    }

    void dealSelf(boolean faceup) {
        Card card = deck.draw();
        card.setFaceup(faceup);
        hand.addCardToHand(card);
    }

    public Card deal(boolean faceup, Hand hand) {
        Card card = deck.draw();
        card.setFaceup(faceup);
        hand.addCardToHand(card);
        return card;
    }

    public void clearHand() {
        hand = new Hand();
    }

    public Hand getHand() {
        return hand;
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

    void showCards() {
        deck.showCards();
    }

    int getSum() {
        return hand.getSum();
    }

}
