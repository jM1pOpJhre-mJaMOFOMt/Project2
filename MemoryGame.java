import java.util.Random;

/**
 * Project 2 (PLTW 3.6.5)
 *
 * Author: Ryan Trust (Period 4)
 * 
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 * 
 */
public class MemoryGame {
  public static void main(String[] args) {

    // Create the possible "memory strings" - an array of strings to 
    // show in the buttons, one element at a time
    String[] memoryStrings = {"a", "c", "b", "e", "n", "m"};


    // Create the game and gameboard. Configure a randomized board with 3 buttons.
    MemoryGameGUI game = new MemoryGameGUI();
    game.createBoard(3, true);
    int score = 0;
    int round = 1;

    // Play the game until user wants to quit.
    while (true) {
      Random random = new Random();
      // random length of memory strings between 1 and 5
      int length = (int) (Math.random() * 5) + 1;
      if (memoryStrings.length == 0) length = 0;

      String[] strings = new String[length];
      for (int i = 0; i < length; i++) {
          int randomIndex = random.nextInt(memoryStrings.length);
          // sets each element of strings to a random possible memory string
          strings[i] = memoryStrings[randomIndex];
      }


      // randomizes the speed between 0.3 and 0.8
      double speed = Math.random() * 0.5 + 0.3;
      String guess = game.playSequence(strings, speed);

      if (guess == null) break; // ends game if user presses cancel

      // Determine if player's guess matches all elements of the random sequence.
      // Cleanup the guess - remove commas and spaces
      if (guess.replace(" ","").replace(",","").equals(String.join("",strings))) {
        // increase score and signal a match
        game.matched();
        score++;
      } else game.tryAgain();
      game.showScore(score, round); // display score after each round

      // Ask if user wants to play another round of the game 
      // and track the number of games played.
      if (game.playAgain()) round++;
      else break;
    }
    
    // When done playing, show score and end the game.
    System.out.println("Score: " + score);
    game.quit();
  }
}