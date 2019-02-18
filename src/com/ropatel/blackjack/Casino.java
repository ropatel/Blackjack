package com.ropatel.blackjack;

public class Casino {

    private Dealer dealer;
    private Player player;

    private Casino() {
        //TODO - Update this to allow the use of a "Shoe" with options on the number of decks.
        int DECK_SIZE = 52;
        Deck deck = new Deck(DECK_SIZE);
        dealer = new Dealer(deck);
        player = new Player("Ro");
    }

    public void hit() {

    }

    public static void main(String[] args) {

        Casino casino = new Casino();

        casino.dealer.shuffleCards();

        /*
        Card card = casino.dealer.deal(true);
        System.out.println(card);
        card = casino.dealer.deal(true);
        System.out.println(card);
        */

        System.out.println("====== Player =======");
        casino.dealer.deal(true, casino.player.getHand());
        casino.player.showHand();

        System.out.println("====== Dealer =======");
        casino.dealer.dealSelf(false);
        casino.dealer.showHiddenHand();

        System.out.println("====== Player =======");
        casino.dealer.deal(true, casino.player.getHand());
        casino.player.showHand();

        System.out.println("====== Dealer =======");
        casino.dealer.dealSelf(true);
        casino.dealer.showHiddenHand();

        System.out.println("====== Dealer =======");
        System.out.println("Hand total = " + casino.dealer.getSum());
        casino.dealer.showHand();

        System.out.println("====== Player =======");
        System.out.println("Hand total = " + casino.player.getSum());
    }

}
