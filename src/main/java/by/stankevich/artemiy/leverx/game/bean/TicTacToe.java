package by.stankevich.artemiy.leverx.game.bean;

public class TicTacToe {

    private static char[][] field;

    public static char[][] getField() {
        return field;
    }

    public static void setField(char[][] field) {
        TicTacToe.field = field;
    }
}
