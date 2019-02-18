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
        System.out.println(card);
        hand.addCardToHand(card);
    }

    public void showHand() {
        hand.showHand();
    }

    public Card deal(boolean faceup) {
        Card card = deck.draw();
        card.setFaceup(faceup);
        return card;
    }

    void showCards() {
        deck.showCards();
    }

    int getSum() {
        return hand.getHandSum();
    }

}
