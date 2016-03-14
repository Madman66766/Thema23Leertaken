package cards;

import java.util.Stack;

/**
 * the solution is a sequence of cards placed on the board according to the card positions
 * example without border
 */
public class Solution extends Stack<Candidate> {
    // The board is an 2D array.
    // 0123
    // 0..-.
    // 1---.
    // 2.---
    // 3..-.
    private Candidate[][] board = new Candidate[4][4];

    // card positions on the board
    // the first card position on the board are
    // {0,2}, {1,0}. {1,1}
    private int[] row = {0, 1, 1, 1, 2, 2, 2, 3};
    private int[] column = {2, 0, 1, 2, 1, 2, 3, 2};
    //  indices of adjacent cards in the solution.
    //                 0   1  2   3   4    5     6    7
    int[][] check = {{}, {}, {1}, {0}, {2}, {3, 4}, {5, 6}, {7}};

    int[][] checkClosed = {{}, {}, {1}, {0}, {2}, {3, 4}, {6}, {5, 7}};
    int[][] adjacent = {{3}, {2}, {1, 3, 4}, {0, 2, 5}, {2, 5}, {3, 4, 6, 7}, {5}, {5}};


    public Solution() {
    }


    // Checks whether a candidate with card CardChar is in
    // an adjacent position of the board position (row, column)
    // @param row, column, candidate
    // @return Boolean indicating if cardChar is found.
    // can be used in the methods fits and isCorrect
    private boolean bordersCard(int row, int column, char cardChar) {
        int index = getIndex(row, column);

        if (index >= 0) {
            int[] toCheck = adjacent[index];
            for (int i = 0; i < toCheck.length; index++) {
                if (toCheck[i] < this.size()) {
                    Candidate candidate = get(toCheck[i]);
                    if (candidate.getCardChar() == cardChar) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int getIndex(int row, int column) {
        for (int i = 0; i < this.row.length; i++) {
            if (this.row[i] == row && this.column[i] == column) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException("The given row and column do not appear to be in the field");
    }


    /**
     * Checks whether candidate card of same kind.
     * Checks whether by placing candidate the solution sofar still complies with the rules
     *
     * @param candidate
     * @return boolean indicating whether this candidate can be put in the
     * next free position.
     */
    public boolean fits(Candidate candidate) {
        return isCorrect(candidate);
    }

    public void record(Candidate candidate) {
        int i = this.size(); // i= index in this stack of next for the next candidate
        board[row[i]][column[i]] = candidate; //x=row, y=column
        this.push(candidate);

    }

    public boolean complete() {
        return this.size() == 8;
    }

    public void show() {
        System.out.println(this);
    }

    public Candidate eraseRecording() {
        int i = this.size() - 1;           // i= index of the candidate that is removed from this Stack;
        board[row[i]][column[i]] = null; // remove candidate from board
        return this.pop();
    }

    // can be used in method isCorrect
    private char mustBeAdjacentTo(char card) {
        if (card == 'A') return 'K';
        if (card == 'K') return 'Q';
        if (card == 'Q') return 'J';

        throw new IllegalArgumentException("Expected input is either 'A', 'K' or 'Q'. Actual input was'" + card + "'.");
    }

    /**
     * Checks whether the rules below are fulfilled
     * For the positions that can be checked for solution sofar.
     * Rules:
     * Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king).
     * Elke heer grenst aan een vrouw (queen).
     * Elke vrouw grenst aan een boer (jack).
     *
     * @return true if all checks are correct.
     */
    // uses methods borderCard and mustBeAdjacent to
    private boolean isCorrect(Candidate candidate) {
        int i = this.size();
        push(candidate);
        if (bordersCard(row[i], column[i], candidate.getCardChar())) {
            pop();
            return false;
        }

        int[] indices = check[i];
        for (int index : indices) {
            if (get(index).getCardChar() != 'J' && !bordersCard(row[index], column[index], mustBeAdjacentTo(get(getIndex(row[index], column[index])).getCardChar()))) {
                pop();
                return false;
            }
        }
        pop();
        return true;
    }


    /**
     * @return a representation of the solution on the board
     */
    public String toString() {
        String string = "01234\n";
        int index = 0;
        for (int i = 0; i < 4; i++) {
            string += i;
            for (int j = 0; j < 4; j++) {
                if (index < size() && row[index] == i && column[index] == j) {
                    string += get(index).getCardChar();
                    index++;
                } else {
                    string += " . ";
                }
            }
            string += "\n";
        }
        return string;
    }

}
