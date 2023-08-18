/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

/**
 *
 * @author Sean
 */
public class PlayGoFish extends Game {
  public PlayGoFish(String name) {
    super(name);
  }

  public class currentPlayer extends Player {
    public currentPlayer() {
      super();
    }
  }
  GroupOfCards deck = new GroupOfCards(52);
  currentPlayer player = new currentPlayer();
  Scanner scan = new Scanner(System.in);
  boolean isPlayerTurn = true;
  int playerSetsCompleted = 0;
  int computerSetsCompleted = 0;

  private static Card.Rank generateRandomRank() {
    Card.Rank[] ranks = Card.Rank.values();
    return ranks[new Random().nextInt(ranks.length)];
  }

  private void removeAndPrintSets(ArrayList < Card > hand) {
    for (Card.Rank rank: Card.Rank.values()) {
      int count = 0;
      for (Card card: hand) {
        if (card.getRank() == rank) {
          count++;
        }
      }
      if (count == 4) {
        if (isPlayerTurn) {
          System.out.println("You have a set of " + rank + "s!");
          deck.removedCards.addAll(hand.stream()
            .filter(card -> card.getRank() == rank)
            .collect(Collectors.toList()));

          hand.removeIf(card -> card.getRank() == rank);
          playerSetsCompleted++;
        } else {
          System.out.println("Computer has a set of " + rank + "s!");
          deck.removedCards.addAll(hand.stream()
            .filter(card -> card.getRank() == rank)
            .collect(Collectors.toList()));

          hand.removeIf(card -> card.getRank() == rank);
          computerSetsCompleted++;
        }
      }
    }
  }

  @Override
  public void play() {
    deck.removedCards = new ArrayList < > ();

    int handSize = 5;

    //deal hands out
    ArrayList < Card > playerHand = deck.getCards(handSize);
    ArrayList < Card > computerHand = deck.getCards(handSize);

    System.out.println("Welcome to Go Fish " + player.getName() + "! \n" +
      "Here is your hand:\n");
    for (Card card: playerHand) {
      System.out.println(card);
    }
    System.out.println("\nPlease request cards in the proper format:\nExample:\n" +
      "If you want to request a card of rank Ten\nSimply type \"ten\"\nCase" +
      "does not matter. \nIf you want to see your hand, " +
      "simply type \"card\"");

    while (playerSetsCompleted < 3 && computerSetsCompleted < 3) {
      if (isPlayerTurn) {
        removeAndPrintSets(playerHand);
        System.out.println("Player sets completed: " + playerSetsCompleted);
        System.out.println("Please ask the computer for a card\n");
        String playerRequest = scan.nextLine();

        if (playerRequest.equalsIgnoreCase("Card")) {
          System.out.println("Your Current cards:\n");
          System.out.println(playerSetsCompleted);
          for (Card card: playerHand) {
            System.out.println(card);

          }
          continue;
        }

        ArrayList < Card > matchingCards = deck.getMatchingCards(computerHand, playerRequest);

        if (!matchingCards.isEmpty()) {
          System.out.println("You received cards: \n");
          playerHand.addAll(matchingCards);
          computerHand.removeAll(matchingCards);

          for (Card card: matchingCards) {
            System.out.println(card);
          }
        } else {
          if (!deck.isEmpty()) {
            System.out.println("Go Fish! You draw a card.\n");
            Card drawnCard = deck.getCards(1).get(0);
            playerHand.add(drawnCard);
          } else {
            System.out.println("Go Fish! The Deck is empty.\n");

          }

        }
        isPlayerTurn = false;
      } else {

        removeAndPrintSets(computerHand);
        System.out.println("Computer Sets Completed: " + computerSetsCompleted);
        Card.Rank computerRequestRank = generateRandomRank();
        String computerRequest = computerRequestRank.toString();
        System.out.println("Computer requests: Any " + computerRequest + "'s\n");

        ArrayList < Card > computerMatchingCards = deck.getMatchingCards(playerHand, computerRequest);

        if (!computerMatchingCards.isEmpty()) {
          Card requestedCard = computerMatchingCards.get(new Random().nextInt(computerMatchingCards.size()));
          playerHand.remove(requestedCard);
          computerHand.add(requestedCard);
        }
        isPlayerTurn = true; // Switch to the player's turn
      }

    }
    declareWinner();
  }

  @Override
  public void declareWinner() {
    if (playerSetsCompleted >= 3) {
      System.out.println("Congratulations, you got 3 sets and won!");
    } else if (computerSetsCompleted >= 3) {
      System.out.println("Computer Wins! Better luck next time!");
    } else {
      System.out.println("Game Ended for Unknown reason.");
    }
  }

  public static void main(String[] args) {
    PlayGoFish game = new PlayGoFish("Go Fish");
    game.play();
  }
}