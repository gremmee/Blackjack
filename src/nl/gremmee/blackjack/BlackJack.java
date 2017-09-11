package nl.gremmee.blackjack;

import java.util.Scanner;

import nl.gremmee.blackjack.core.Deck;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome to BlackJack!");

        // Create Deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        // Create a deck for the player
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game loop
        while (playerMoney > 0) {
            System.out.println("You have $" + playerMoney + ", how much would you like yo bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have. Please leave.");
                break;
            }

            boolean endRound = false;

            // Start Dealing
            // Player get two cards.
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            // Dealer get two cards.
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while (true) {
                System.out.println("Your hand:");
                System.out.println(playerDeck.toString());
                System.out.println("Your deck is valued at: " + playerDeck.cardsValue());

                // Dealerhand
                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + "and [Hidden]");

                System.out.println("Would you like to (1)Hit or (2)Stand?");
                int response = userInput.nextInt();

                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        break;
                    }
                }

                if (response == 2) {
                    break;
                }
            }

            System.out.println("Dealer Cards " + dealerDeck.toString());

            if (dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false) {
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;

            }
            // Dealer draw at 16, stand at 17
            while ((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }

            System.out.println("Dealer's hand values at:  " + dealerDeck.cardsValue());

            if (dealerDeck.cardsValue() > 21 && endRound == false) {
                System.out.println("Dealer Busts! You win.");
                playerMoney += playerBet;
                endRound = true;
            }

            if (playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false) {
                System.out.println("Push");
                endRound = true;

            }

            if (playerDeck.cardsValue() > dealerDeck.cardsValue() && endRound == false) {
                System.out.println("You win the hand!");
                endRound = true;
            } else if (endRound == false) {
                System.out.println("You win loose hand!");
                playerMoney -= playerBet;
                endRound = true;

            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");
        }

        System.out.println("Game over! You are out of money. :(");
    }
}
