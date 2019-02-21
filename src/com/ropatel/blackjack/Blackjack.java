package com.ropatel.blackjack;

import java.util.Scanner;

public class Blackjack {

    private final VirtualBlackjackPlayer virtualDealer;
    private Player player;
    private VirtualBlackjackPlayer virtualPlayer;
    private static final double MINIMUM_BET = 5.0;
    private final Scanner reader;
    private boolean endState = false;
    private boolean finished = false;
    private Deck deck;
    private static final int DECK_SIZE = 52;

    private enum EndStates {
        NATURAL_BLACKJACK,
        BLACKJACK,
        DEALER_BUST,
        PLAYER_BUST,
        PUSH,
        PLAYER_CLOSER,
        DEALER_CLOSER
    }

    public Blackjack() {
        deck = new Deck(DECK_SIZE);
        deck.shuffleCards();
        virtualDealer = new VirtualBlackjackPlayer();
        reader = new Scanner(System.in);
    }

    public static void main(String[] args) {

        Blackjack casino = new Blackjack();
        casino.addPlayer();

        while (!casino.done()) {
            casino.openingRound();
            casino.playerLoop();
            casino.dealerLoop();
            casino.playAgain();
            if (!casino.done()) {
                casino.newGame();
            }
        }
    }

    public void newGame() {
        virtualPlayer.clearHand();
        virtualDealer.clearHand();
        endState = false;
    }

    public void addPlayer() {
        System.out.print("Enter player name? ");
        String name = reader.next();
        player = new Player(name);
        showPlayerFunds();
        virtualPlayer = new VirtualBlackjackPlayer();
    }

    public void openingRound() {

        playerHit();
        dealerHit();
        paintState(false);
        pauseDealer();
        playerHit();
        dealerHit();
        paintState(false);
        pauseDealer();

        /*if (checkDealerAce()) {
            checkInsurance();
            if (checkBlackjack(virtualDealer)) {
                showDealerWin(EndStates.NATURAL_BLACKJACK);
            }
        }*/

        if (virtualPlayer.getHandState() == BlackjackState.BLACKJACK) {
            showPlayerWin(EndStates.NATURAL_BLACKJACK);
        }
    }

    public void playerLoop() {

        if (!endState) {
            String playerChoice = promptForCard();
            while (!playerChoice.toUpperCase().equals("S")) {
                playerHit();
                paintState(false);
                pauseDealer();
                if (checkBlackjack(virtualPlayer)) {
                    showPlayerWin(EndStates.BLACKJACK);
                    break;
                }
                if (checkBust(virtualPlayer)) {
                    showDealerWin(EndStates.PLAYER_BUST);
                    break;
                }
                playerChoice = promptForCard();
            }
        }
    }

    public void dealerLoop() {
        if (!endState) {
            while (virtualDealer.getHandState() == BlackjackState.UNDER_SEVENTEEN && !endState) {
                dealerHit();
                paintState(true);
                pauseDealer();
                System.out.println();
            }
            checkWinConditions();
        }
    }

    public void playAgain() {
        String response;
        System.out.println();
        System.out.println("Play again? (Y/N)");
        response = reader.next();
        finished = !response.toUpperCase().equals("Y");
    }

    public boolean done() {
        return finished;
    }

    private void pauseDealer() {
        int DELAY = 10;
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Card draw() {
        Card card = deck.draw();
        if (card == null) {
            deck = new Deck(DECK_SIZE);
            deck.shuffleCards();
            card = deck.draw();
        }
        return card;
    }

    public Card deal(VirtualBlackjackPlayer player) {
        Card card = draw();
        player.putCard(card);
        return card;
    }

    private void showPlayerFunds() {
        player.showBank();
    }

    private void playerHit() {
        deal(virtualPlayer);
    }

    private void dealerHit() {
        deal(virtualDealer);
    }

    /*private boolean checkDealerAce() {
        return virtualDealer.getHand().checkUpAce();
    }*/

    private void checkInsurance() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Dealer Ace - Insurance? (Y/N)");
        String choice = reader.next();
        if (choice.toUpperCase().equals("Y")) {
            System.out.println("Insurance purchased");
        } else {
            System.out.println("No Insurance");
        }
    }

    private String promptForCard() {
        System.out.println("............................");
        System.out.println("(H)it (S)tay");
        return reader.next();
    }

    private void checkWinConditions() {
        int dealerHand = virtualDealer.getHandTotal();
        int playerHand = virtualPlayer.getHandTotal();

        if (dealerHand >= 17) {
            if (dealerHand == playerHand) {
                showPlayerWin(EndStates.PUSH);
            } else if (dealerHand < playerHand) {
                showPlayerWin(EndStates.PLAYER_CLOSER);
            } else if (dealerHand > 21) {
                showPlayerWin(EndStates.DEALER_BUST);
            } else {
                showDealerWin(EndStates.DEALER_CLOSER);
            }
        }
    }

    private void paintState(final boolean openHand) {
        //System.out.println("----------------------------------------------------------");
        System.out.println(".......... Dealer ..........");
        if (openHand) {
            virtualDealer.showHandWithSum();
        } else {
            virtualDealer.showHiddenHand();
        }
        //System.out.println("............................");
        System.out.println(".......... " + player.getName() + " ..........");
        virtualPlayer.showHandWithSum();
        //System.out.println("............................");
        //System.out.println("----------------------------------------------------------");
    }

    private void showFinalState(final String status) {
        pauseDealer();
        System.out.println(".......... Dealer ..........");
        System.out.println("Hand total = " + virtualDealer.getHandTotal());
        virtualDealer.showHand();
        System.out.println(".......... virtualPlayer ..........");
        System.out.println("Hand total = " + virtualPlayer.getHandTotal());
        virtualPlayer.showHand();
        System.out.println("************ " + status.toUpperCase() + " *************");
    }

    //TODO - Refactor to remove duplicate code. virtualPlayer and Dealer should implement an interface.
    private void showPlayerWin(final EndStates state) {
        String status = "";
        switch (state) {
            case NATURAL_BLACKJACK:
                status = "virtualPlayer Wins - Natural Blackjack!";
                break;
            case BLACKJACK:
                status = "virtualPlayer Wins - Blackjack!";
                break;
            case DEALER_BUST:
                status = "virtualPlayer Wins - Dealer busts!";
                break;
            case PLAYER_CLOSER:
                status = "virtualPlayer Wins";
                break;
            case PUSH:
                status = "Push";
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
                status = "Dealer Wins - " + player.getName() + " busts!";
                break;
            case DEALER_CLOSER:
                status = "Dealer Wins";
                break;
        }
        endState = true;
        showFinalState(status);
    }

    private boolean checkBlackjack(VirtualBlackjackPlayer player) {
        return (player.getHandState() == BlackjackState.BLACKJACK);
    }

    private boolean checkBust(final VirtualBlackjackPlayer player) {
        return (player.getHandState() == BlackjackState.BUST);
    }
}
