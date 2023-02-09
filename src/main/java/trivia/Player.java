package trivia;

public class Player {
    private int coins;
    private final String name;
    private boolean isInPenaltyBox;

    private boolean isGettingOutOfPenaltyBox;

    private int position;

    public Player(String name) {
        this.coins = 0;
        this.name = name;
        this.isInPenaltyBox = false;
        this.position = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins() {
        this.coins++;

        System.out.println(getName()
                + " now has "
                + getCoins()
                + " Gold Coins.");
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }
    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;

        isGettingOutOfPenaltyBox = false;

        System.out.println(getName() + " was sent to the penalty box");
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position > 11 ? position - 12 : position;
    }

    public void addRollToPosition(int roll) {
        setPosition(position + roll);

        System.out.println(getName()
                + "'s new location is "
                + getPosition());
    }

    public boolean hasWon() {
        return this.coins != 6;
    }

    public void setIsGettingOutOfPenaltyBox(int roll) {
        if (!isInPenaltyBox) {
            return;
        }

        if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(getName() + " is getting out of the penalty box");
        } else {
            isGettingOutOfPenaltyBox = false;
            System.out.println(getName() + " is not getting out of the penalty box");
        }
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}
