package com.ropatel.blackjack;

import java.util.Scanner;

public class Casino {

    private final Dealer dealer;
    private final double MINIMUM_BET = 5.0;
    private final Scanner reader;
    private Player player;
    private boolean endState = false;

    public Casino() {
        int DECK_SIZE = 52;
        Deck deck = new Deck(DECK_SIZE);
        dealer = new Dealer(deck);
        reader = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Casino casino = new Casino();
        casino.addPlayer();
        casino.openingRound();
        casino.playerLoop();
        casino.dealerLoop();
    }

    public void addPlayer() {
        System.out.println("Enter player name?");
        String name = reader.next();
        player = new Player(name);
        showPlayerFunds();
    }

    public void openingRound() {

        playerHit();
        dealerHit(false, false);
        playerHit();
        dealerHit(true, false);

        if (checkDealerAce()) {
            checkInsurance();
            if (checkBlackjack(dealer.getHand())) {
                showDealerWin(EndStates.NATURAL_BLACKJACK);
            }
        }

        if (checkBlackjack(player.getHand())) {
            showPlayerWin(EndStates.NATURAL_BLACKJACK);
        }
    }

    public void playerLoop() {

        String playerChoice = promptForCard();
        while (!playerChoice.equals("S") && !endState) {
            playerHit();
            if (checkBlackjack(player.getHand())) {
                showPlayerWin(EndStates.BLACKJACK);
                break;
            }
            if (checkBust(player.getHand())) {
                showDealerWin(EndStates.PLAYER_BUST);
                break;
            }
            playerChoice = promptForCard();
        }
        System.out.println();
    }

    public void dealerLoop() {
        while (dealer.getSum() < 18 && !endState) {
            dealerHit(true, true);
        }
        checkWinConditions();
    }

    private void showPlayerFunds() {
        player.totalFunds();
    }

    private void playerHit() {
        System.out.println("====== Player =======");
        dealer.deal(true, player.getHand());
        player.showHand();
    }

    private void dealerHit(final boolean faceup, final boolean openHand) {
        System.out.println("====== Dealer =======");
        dealer.dealSelf(faceup);
        if (openHand) {
            dealer.showHand();
        } else {
            dealer.showHiddenHand();
        }
    }


    private boolean checkDealerAce() {
        return dealer.getHand().checkUpAce();
    }

    private void checkInsurance() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Dealer Ace - Insurance? (Y/N)");
        String choice = reader.next();
        if (choice.equals("Y")) {
            System.out.println("Insurance purchased");
        } else {
            System.out.println("No Insurance");
        }
    }

    private String promptForCard() {
        System.out.println();
        System.out.println("(H)it (S)tay");
        return reader.next();
    }


    private void checkWinConditions() {
        int dealerHand = dealer.getSum();
        int playerHand = player.getSum();

        if (dealerHand >= 18) {
            if (dealerHand == playerHand) {
                showPlayerWin(EndStates.PUSH);
            } else if (dealerHand < playerHand) {
                showPlayerWin(EndStates.PLAYER_HIGHER);
            } else if (dealerHand > 21) {
                showPlayerWin(EndStates.DEALER_BUST);
            } else {
                showDealerWin(EndStates.DEALER_HIGHER);
            }
        }
    }

    private void showFinalState() {
        System.out.println();
        System.out.println("*********** FINAL OUTCOME *************");
        System.out.println("====== Dealer =======");
        System.out.println("Hand total = " + dealer.getSum());
        dealer.showHand();

        System.out.println("====== Player =======");
        System.out.println("Hand total = " + player.getSum());
        player.showHand();
    }

    //TODO - Refactor to remove duplicate code. Player and Dealer should implement an interface.
    private void showPlayerWin(final EndStates state) {
        switch (state) {
            case NATURAL_BLACKJACK:
                System.out.println("Player Wins - Natural Blackjack!");
                break;
            case BLACKJACK:
                System.out.println("Player Wins - Blackjack!");
                break;
            case DEALER_BUST:
                System.out.println("Player Wins - Dealer busts!");
                break;
            case PLAYER_HIGHER:
                System.out.println("Player Wins");
                break;
        }
        endState = true;
        showFinalState();
    }

    private void showDealerWin(final EndStates state) {
        switch (state) {
            case NATURAL_BLACKJACK:
                System.out.println("Dealer Wins - Natural Blackjack!");
                break;
            case BLACKJACK:
                System.out.println("Dealer Wins - Blackjack!");
                break;
            case PLAYER_BUST:
                System.out.println("Dealer Wins - Player busts!");
                break;
            case DEALER_HIGHER:
                System.out.println("Dealer Wins");
                break;
        }
        endState = true;
        showFinalState();
    }

    private boolean checkBlackjack(final Hand hand) {
        return (hand.getSum() == 21);
    }

    private boolean checkBust(final Hand hand) {
        return (hand.getSum() > 21);
    }

    private enum EndStates {
        NATURAL_BLACKJACK,
        BLACKJACK,
        DEALER_BUST,
        PLAYER_BUST,
        PUSH,
        PLAYER_HIGHER,
        DEALER_HIGHER
    }
}
