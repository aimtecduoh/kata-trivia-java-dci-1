package trivia;

import java.util.LinkedList;

public class QuestionDeck {
    private final String deckName;
    private LinkedList<String> questions;

    public QuestionDeck(String deckName) {
        this.deckName = deckName;
        questions = new LinkedList();
    }

    public String getDeckName() {
        return this.deckName;
    }

    public void addQuestion(String question) {
        this.questions.addLast(question);
    }

    public String drawQuestion() {
        return this.questions.removeFirst();
    }
}
