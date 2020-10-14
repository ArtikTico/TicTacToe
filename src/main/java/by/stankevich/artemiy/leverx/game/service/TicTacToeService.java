package by.stankevich.artemiy.leverx.game.service;

import by.stankevich.artemiy.leverx.game.bean.TicTacToe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static by.stankevich.artemiy.leverx.game.bean.TicTacToe.*;

public class TicTacToeService {

    private static final char SIGN_EMPTY = ' ';
    private static final char SIGN_X = 'x';
    private static final char SIGN_O = 'o';

    private TicTacToe ticTacToe;
    private Random random;
    private Scanner scanner;


    public TicTacToeService(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        System.out.println("....Tic tac toe game....");
        System.out.println("....Initialize....");
    }

    private void initGame() {
        random = new Random();
        scanner = new Scanner(System.in);
        setField(new char[3][3]);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                getField()[i][j] = SIGN_EMPTY;
            }
        }
    }

    public void startGame() {
        chooseTypeGame();
    }

    private void chooseTypeGame() {
        System.out.println("Choose type game:");
        System.out.println("If you want to play with computer - enter '1' ");
        System.out.println("If you want to play with another player - enter '2' ");
        initGame();
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                startGameWithAi();
                break;
            case 2:
                startGameWithAnotherPlayer();
                break;
            default:
                System.out.println("You entered not correct value. Enter '1' or '2'");
        }
    }

    private void startGameWithAnotherPlayer() {
        System.out.println("Good luck players!");
        while (true) {
            turnHumanX();
            if (checkWin(SIGN_X)) {
                System.out.println("Congratulations player 1. You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
            turnHumanO();
            printFieldStream();
            if (checkWin(SIGN_O)) {
                System.out.println("Congratulations player 2. You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
        }
        System.out.println("..Game over..");
        printFieldStream();
    }

    private void startGameWithAi() {
        System.out.println("Good luck player!");
        while (true) {
            turnHumanX();
            if (checkWin(SIGN_X)) {
                System.out.println("Congratulations player. You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
            turnAI();
            printFieldStream();
            if (checkWin(SIGN_O)) {
                System.out.println("Sorry, but you loose.. AI Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
        }
        System.out.println("..Game over..");
        printFieldStream();
    }

    private void turnHumanX() {
        int x;
        int y;
        do {
            System.out.println("Enter X and Y coodrs from 1 to 3");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        getField()[y][x] = SIGN_X;
    }

    private void turnHumanO() {
        int x;
        int y;
        do {
            System.out.println("Enter X and Y coodrs from 1 to 3");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        getField()[y][x] = SIGN_O;
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3)
            return false;
        return getField()[y][x] == SIGN_EMPTY;
    }

    private void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        getField()[y][x] = SIGN_O;
    }

    private void printFieldStream() {
        Arrays.stream(getField()).forEach(System.out::println);
    }

    private boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++)
            if ((getField()[i][0] == symbol && getField()[i][1] == symbol
                    && getField()[i][2] == symbol) || (getField()[0][i] == symbol
                    && getField()[1][i] == symbol && getField()[2][i] == symbol)) {
                return true;
            }
        if ((getField()[0][0] == symbol && getField()[1][1] == symbol
                && getField()[2][2] == symbol) ||
                (getField()[2][0] == symbol && getField()[1][1] == symbol
                        && getField()[0][2] == symbol)) {
            return true;
        }
        return false;
    }

    private boolean isFieldFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (getField()[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}
