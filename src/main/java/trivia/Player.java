package trivia;

public class Player {
    private int coins;
    private final String name;
    private boolean inPenaltyBox;
    private int position;

    public Player(String name) {
        this.name = name;
        this.coins = 0;
        this.inPenaltyBox = false;
        this.position = 0;
    }

    public int getCoins() {
        return coins;
    }

    public void increaseCoin() {
        this.coins++;
    }

    public String getName() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public int getPosition() {
        return position;
    }

    public void increasePositionByRoll(int roll) {
        this.position += roll;
        if (this.position > 11) {
            this.position -= 12;
        }
    }
}
