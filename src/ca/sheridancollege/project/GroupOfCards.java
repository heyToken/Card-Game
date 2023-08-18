/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

  //The group of cards, stored in an ArrayList
  private ArrayList < Card > cards;
  public ArrayList < Card > removedCards;

  public ArrayList < Card > getMatchingCards(ArrayList < Card > hand, String request) {
    ArrayList < Card > matchingCards = new ArrayList < > ();

    for (Card card: hand) {
      if (card.getRank().toString().equalsIgnoreCase(request)) {
        matchingCards.add(card);
      }
    }
    return matchingCards;
  }

  private int size; //the size of the grouping

  public GroupOfCards(int size) {
    this.size = size;
    initializeDeck();
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  private void initializeDeck() {
    cards = new ArrayList();
    for (Card.Suit suit: Card.Suit.values()) {
      for (Card.Rank rank: Card.Rank.values()) {
        cards.add(new Card(suit, rank));
      }
    }
    shuffle();
  }
  public ArrayList < Card > getCards(int handSize) {
    ArrayList < Card > deck = new ArrayList < > (cards); // Create a copy of the deck
    ArrayList < Card > hand = new ArrayList < > ();

    for (int i = 0; i < handSize; i++) {
      if (!cards.isEmpty()) {
        Card drawnCard = cards.remove(0); // Remove and add to the hand
        hand.add(drawnCard);
      }

    }

    return hand;
  }

  public void setHand(ArrayList < Card > hand) {
    this.cards = hand;
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }
}