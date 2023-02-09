package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// REFACTOR ME
public class GameBetter implements IGame {
   private static final int MAX_PLAYERS = 4;
   private final List<Player> players = new ArrayList<>(MAX_PLAYERS);

   private final Queue<String> popQuestions = new LinkedList<>();
   private final Queue<String> scienceQuestions = new LinkedList<>();
   private final Queue<String> sportsQuestions = new LinkedList<>();
   private final Queue<String> rockQuestions = new LinkedList<>();

   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.add("Pop Question " + i);
         scienceQuestions.add(("Science Question " + i));
         sportsQuestions.add(("Sports Question " + i));
         rockQuestions.add(createRockQuestion(i));
      }
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      Player player = new Player(playerName);
      players.add(player);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      Player currentPlayer = players.get(currentPlayerIndex);
      String currentPlayerName = currentPlayer.getName();
      System.out.println(currentPlayerName + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(currentPlayerName + " is getting out of the penalty box");
            currentPlayer.increasePositionByRoll(roll);

            System.out.println(currentPlayerName
                               + "'s new location is "
                               + currentPlayer.getPosition());
            System.out.println("The category is " + currentCategory());
            askQuestion();
         } else {
            System.out.println(currentPlayerName + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         currentPlayer.increasePositionByRoll(roll);

         System.out.println(currentPlayerName
                            + "'s new location is "
                            + currentPlayer.getPosition());
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      if (currentCategory() == "Pop")
         System.out.println(popQuestions.remove());
      if (currentCategory() == "Science")
         System.out.println(scienceQuestions.remove());
      if (currentCategory() == "Sports")
         System.out.println(sportsQuestions.remove());
      if (currentCategory() == "Rock")
         System.out.println(rockQuestions.remove());
   }


   private String currentCategory() {
      int currentPlayerPosition = players.get(currentPlayerIndex).getPosition();
      if (currentPlayerPosition == 0) return "Pop";
      if (currentPlayerPosition == 4) return "Pop";
      if (currentPlayerPosition == 8) return "Pop";
      if (currentPlayerPosition == 1) return "Science";
      if (currentPlayerPosition == 5) return "Science";
      if (currentPlayerPosition == 9) return "Science";
      if (currentPlayerPosition == 2) return "Sports";
      if (currentPlayerPosition == 6) return "Sports";
      if (currentPlayerPosition == 10) return "Sports";
      return "Rock";
   }

   public boolean wasCorrectlyAnswered() {
      var player = players.get(currentPlayerIndex);
      if (player.isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            player.increaseCoin();
            System.out.println(player.getName()
                               + " now has "
                               + player.getCoins()
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;

            return winner;
         } else {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         player.increaseCoin();
         System.out.println(player.getName()
                            + " now has "
                            + player.getCoins()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayerIndex++;
         if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      var player = players.get(currentPlayerIndex);
      System.out.println("Question was incorrectly answered");
      System.out.println(player.getName() + " was sent to the penalty box");
      player.setInPenaltyBox(true);

      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
      return true;
   }


   private boolean didPlayerWin() {
      var player = players.get(currentPlayerIndex);
      return !(player.getCoins() == 6);
   }
}
