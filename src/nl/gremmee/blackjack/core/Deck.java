package nl.gremmee.blackjack.core;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // Generate cards
        for (Suit cardSuit : Suit.values()) {
            for (Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> tmpDeck = new ArrayList<Card>();

        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();

        for (int i = 0; i < originalSize; i++) {
            randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
            tmpDeck.add(this.cards.get(randomCardIndex));

            this.cards.remove(randomCardIndex);
        }
        this.cards = tmpDeck;
    }

    @Override
    public String toString() {
        String cardListOutput = "";
        int i = 0;
        for (Card card : this.cards) {
            cardListOutput += "\n " + card.toString();
            i++;
        }
        return cardListOutput;
    }

    public void removeCard(int index) {
        this.cards.remove(index);
    }

    public Card getCard(int index) {
        return this.cards.get(index);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    public void draw(Deck aCommingFrom) {
        this.cards.add(aCommingFrom.getCard(0));
        aCommingFrom.removeCard(0);
    }

    public int deckSize() {
        return this.cards.size();
    }

    public void moveAllToDeck(Deck aMoveTo) {
        int thisDeckSize = this.cards.size();

        for (int i = 0; i < thisDeckSize; i++) {
            aMoveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }

    }

    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card card : this.cards) {
            switch (card.getValue()) {
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;
                case ACE:
                    aces += 1;
                    break;
            }
        }

        for (int i = 0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}
