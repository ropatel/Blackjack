package com.ropatel.blackjack;

import java.util.*;

public class Deck {
    private int size;
    private ArrayList<Card> cardstore;
    private Stack<Card> deck;

    public Deck(int size) {
        this.size = size;
        cardstore = new ArrayList<>();
        buildDeck();
        deck = new Stack<>();
        deck.addAll(cardstore);
    }

    private void buildDeck() {
        int i = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                cardstore.add(i, card);
            }
        }
    }

    public void showCards() {
        for (Card card : deck) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public void shuffleCards() {
        int first;
        int second;
        int NUMBER_OF_SWAPS = 3000;
        Random rand = new Random();

        /*
        HashSet<Integer> audit = new HashSet<>();

        while (audit.size()!=51) {
            first = rand.nextInt(52);
            audit.add(first);
            second = rand.nextInt(52);
            audit.add(second);
            swapCards(first,second);
            //System.out.println(audit.size());
        }
        */

        for (int i = 0; i < NUMBER_OF_SWAPS; i++) {
            first = rand.nextInt(52);
            second = rand.nextInt(52);
            swapCards(first, second);
        }

        deck.clear();
        deck.addAll(cardstore);
    }

    private void swapCards(int first, int second) {
        Card temp = cardstore.get(first);
        cardstore.set(first, cardstore.get(second));
        cardstore.set(second, temp);
    }

    public Card draw() {
        if (!this.deck.isEmpty()) {
            return this.deck.pop();
        }
        return null;
    }
}