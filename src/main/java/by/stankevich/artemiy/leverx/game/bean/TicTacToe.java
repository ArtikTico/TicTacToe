package by.stankevich.artemiy.leverx.game.bean;

public class TicTacToe {

    private static final char SIGN_EMPTY = ' ';
    private static final char SIGN_X = 'x';
    private static final char SIGN_O = 'o';
    private static char[][] field;
    private String namePlayerOne;
    private String namePlayerTwo;

    public String getNamePlayerOne() {
        return namePlayerOne;
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.namePlayerOne = namePlayerOne;
    }

    public String getNamePlayerTwo() {
        return namePlayerTwo;
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.namePlayerTwo = namePlayerTwo;
    }

    public static char getSignEmpty() {
        return SIGN_EMPTY;
    }

    public static char getSignX() {
        return SIGN_X;
    }

    public static char getSignO() {
        return SIGN_O;
    }

    public static char[][] getField() {
        return field;
    }

    public static void setField(char[][] field) {
        TicTacToe.field = field;
    }
}
