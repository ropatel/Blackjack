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

        boolean finished = false;

        Casino casino = new Casino();
        casino.addPlayer();

        while (!finished) {
            casino.openingRound();
            casino.playerLoop();
            casino.dealerLoop();
            finished = casino.done();
            if (!finished) {
                casino.newGame();
            }
        }
    }

    public void newGame() {
        player.clearHand();
        dealer.clearHand();
        endState = false;
    }

    public void addPlayer() {
        System.out.print("Enter player name? ");
        String name = reader.next();
        player = new Player(name);
        showPlayerFunds();
    }

    public void openingRound() {

        playerHit();
        dealerHit(false, false);
        paintState(false);
        pauseDealer();
        playerHit();
        dealerHit(true, false);
        paintState(false);
        pauseDealer();

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
        if (!endState) {
            while (!playerChoice.equals("S")) {
                playerHit();
                paintState(false);
                pauseDealer();
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
        }
    }

    public void dealerLoop() {
        while (dealer.getSum() < 18 && !endState) {
            dealerHit(true, true);
            paintState(true);
            pauseDealer();
            System.out.println();
        }
        checkWinConditions();
    }

    public boolean done() {
        String response;
        System.out.println();
        System.out.println("Done? (Y/N)");
        response = reader.next();
        return response.equals("Y");
    }

    private void pauseDealer() {
        int DELAY = 1000;
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showPlayerFunds() {
        player.totalFunds();
    }

    private void playerHit() {
        dealer.deal(true, player.getHand());
    }

    private void dealerHit(final boolean faceup, final boolean openHand) {
        dealer.dealSelf(faceup);
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

    private void paintState(boolean openHand) {
        System.out.println("----------------------------------------------------------");
        System.out.println(".......... Dealer ..........");
        if (openHand) {
            dealer.showHandWithSum();
        } else {
            dealer.showHiddenHand();
        }
        System.out.println(".......... Player ..........");
        player.showHandWithSum();
        System.out.println("----------------------------------------------------------");
    }

    private void showFinalState(String status) {
        pauseDealer();
        System.out.println(".......... Dealer ..........");
        System.out.println("Hand total = " + dealer.getSum());
        dealer.showHand();
        System.out.println(".......... Player ..........");
        System.out.println("Hand total = " + player.getSum());
        player.showHand();
        System.out.println("************ " + status.toUpperCase() + " *************");
    }

    //TODO - Refactor to remove duplicate code. Player and Dealer should implement an interface.
    private void showPlayerWin(final EndStates state) {
        String status = "";
        switch (state) {
            case NATURAL_BLACKJACK:
                status = "Player Wins - Natural Blackjack!";
                break;
            case BLACKJACK:
                status = "Player Wins - Blackjack!";
                break;
            case DEALER_BUST:
                status = "Player Wins - Dealer busts!";
                break;
            case PLAYER_HIGHER:
                status = "Player Wins";
                break;
        }
        endState = true;
        showFinalState(status);
    }

    private void showDealerWin(final EndStates state) {
        String status = "";
        switch (state) {
            case NATURAL_BLACKJACK:
                status = "Dealer Wins - Natural Blackjack!";
                break;
            case BLACKJACK:
                status = "Dealer Wins - Blackjack!";
                break;
            case PLAYER_BUST:
                status = "Dealer Wins - Player busts!";
                break;
            case DEALER_HIGHER:
                status = "Dealer Wins";
                break;
        }
        endState = true;
        showFinalState(status);
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
