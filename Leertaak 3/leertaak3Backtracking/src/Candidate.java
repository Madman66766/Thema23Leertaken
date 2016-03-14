/**
 * Created by Christopher on 02/03/2016.
 */
public class Candidate {
    private char cardChar;
    private int available;

    public Candidate(char cardChar, int available) {
        this.cardChar = cardChar;
        this.available = available;
    }

    public Candidate(char cardName) {
        this(cardName, 1);
    }

    public char getCardChar() {
        return cardChar;
    }

    public int getAvailable() {
        return available;
    }

    public void addOne() {
        available++;
    }

    public void takeOne() {
        available--;
    }

    @Override
    public String toString() {
        return ""+cardChar+"("+available+") ";
    }

    @Override
    public boolean equals(Object obj) {
        Candidate candidate = (Candidate) obj;
        return (cardChar == candidate.getCardChar());
    }
}
