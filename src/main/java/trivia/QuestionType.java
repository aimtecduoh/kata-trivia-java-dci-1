package trivia;

import java.util.Locale;

public enum QuestionType {
    POP,
    SCIENCE,
    SPORTS,
    ROCK;

    @Override
    public String toString() {
        return this.name().charAt(0) +
                this.name().substring(1).toLowerCase(Locale.ROOT);
    }
}
