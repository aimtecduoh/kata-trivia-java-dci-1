package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {
   List<Player> players = new ArrayList<>();
   List<QuestionDeck> questionDecks = new ArrayList<>();

   private int currentPlayerIndex = 0;

   public GameBetter() {
      questionDecks.add(createNewQuestionDeck("Pop"));
      questionDecks.add(createNewQuestionDeck("Science"));
      questionDecks.add(createNewQuestionDeck("Sports"));
      questionDecks.add(createNewQuestionDeck("Rock"));
   }

   private QuestionDeck createNewQuestionDeck(String deckName) {
      QuestionDeck deck = new QuestionDeck(deckName);

      for (int i = 0; i < 50; i++) {
         deck.addQuestion(deckName + " Question " + i);
      }

      return deck;
   }


   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      Player newPlayer = new Player(playerName);
      players.add(newPlayer);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      Player currentPlayer = getCurrentPlayer();
      
      System.out.println(currentPlayer.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      currentPlayer.setIsGettingOutOfPenaltyBox(roll);

      if (currentPlayer.isInPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
         return;
      }

      currentPlayer.addRollToPosition(roll);
      askQuestion();
   }

   private void askQuestion() {
      QuestionDeck deck = getCurrentQuestionDeck();
      System.out.println("The category is " + deck.getDeckName());
      System.out.println(deck.drawQuestion());

   }

   private QuestionDeck getCurrentQuestionDeck() {
      int currentPosition = getCurrentPlayer().getPosition();
      return questionDecks.get(currentPosition % questionDecks.size());
   }

   public boolean wasCorrectlyAnswered() {
      Player currentPlayer = getCurrentPlayer();

      if (currentPlayer.isInPenaltyBox()) {
         if (currentPlayer.isGettingOutOfPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.addCoins();

            boolean winner = didPlayerWin();
            switchToNextPlayer();

            return winner;
         } else {
            switchToNextPlayer();
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         currentPlayer.addCoins();


         boolean winner = didPlayerWin();
         switchToNextPlayer();

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      getCurrentPlayer().setInPenaltyBox(true);

      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return getCurrentPlayer().hasWon();
   }

   private Player getCurrentPlayer() {
      return players.get(currentPlayerIndex);
   }

   private void switchToNextPlayer() {
      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
   }
}
