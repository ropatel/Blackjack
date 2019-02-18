package com.ropatel.blackjack;

public class Dealer {

    private Deck deck;
    private Hand hand;

    Dealer(Deck deck) {
        this.deck = deck;
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

    public void showHand() {
        hand.showHand(true);
    }

    public void showHiddenHand() {
        hand.showHand(false);
    }

    void showCards() {
        deck.showCards();
    }

    int getSum() {
        return hand.getHandSum();
    }

}
