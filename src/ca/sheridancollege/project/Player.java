/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */

import java.util.*;

public abstract class Player {

  private String name; // the unique name for this player

  private static Set < String > usedNames = new HashSet < > (); // to track used player names

  public Player() {
    setNameWithInput();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    checkAndSetName(name);
  }

  private void checkAndSetName(String newName) {
    if (usedNames.contains(newName)) {
      throw new IllegalArgumentException("Player name already taken: " + newName);
    }

    if (name != null) {
      usedNames.remove(name);
    }

    name = newName;
    usedNames.add(name);
  }

  private void setNameWithInput() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Please enter name for Player");
      String newName = scanner.nextLine();

      try {
        checkAndSetName(newName);
        break; // Break out of the loop if name is valid
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}

/**
 * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
 * with logic to play your game.
 */