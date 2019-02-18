package com.ropatel.blackjack;

public class Casino {

    private Dealer dealer;

    private Casino() {
        int DECK_SIZE = 52;
        Deck deck = new Deck(DECK_SIZE);
        dealer = new Dealer(deck);
    }

    public static void main(String[] args) {

        Casino casino = new Casino();

        casino.dealer.showCards();
        casino.dealer.shuffleCards();
        casino.dealer.showCards();

        /*
        Card card = casino.dealer.deal(true);
        System.out.println(card);
        card = casino.dealer.deal(true);
        System.out.println(card);
        */

        casino.dealer.dealSelf(true);
        casino.dealer.dealSelf(true);
        System.out.println("Hand total = " + casino.dealer.getSum());

        casino.dealer.showCards();
    }

}
