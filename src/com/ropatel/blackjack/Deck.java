package com.ropatel.blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Deck {
    private int size;
    private ArrayList<Card> cardstore; // Types should be the interface not the implementation
    private Stack<Card> deck;

    public Deck(int size) { // Final
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

    public void showCards() { // toString()
        for (Card card : deck) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public void shuffleCards() {
        int first;
        int second;
        int NUMBER_OF_SWAPS = 3000; // Static final
        Random rand = new Random();

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
