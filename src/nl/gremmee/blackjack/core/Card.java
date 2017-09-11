package nl.gremmee.blackjack.core;

public class Card {

    private Suit suit;
    private Value value;

    public Card(Suit aSuit, Value aValue) {
        this.value = aValue;
        this.suit = aSuit;
    }

    @Override
    public String toString() {
        return this.suit.toString() + "-" + this.value.toString();
    }

    public Value getValue() {
        return this.value;
    }
}
