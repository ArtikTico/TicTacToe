package by.stankevich.artemiy.leverx.game.service;

import by.stankevich.artemiy.leverx.game.bean.TicTacToe;

import java.util.Random;
import java.util.Scanner;

import static by.stankevich.artemiy.leverx.game.bean.TicTacToe.*;

public class TicTacToeService {

    private TicTacToe ticTacToe;
    private Random random;
    private Scanner scanner;


    public Scanner getScanner() {
        return scanner;
    }

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
                getField()[i][j] = getSignEmpty();
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
        greetingsPlayers();
        while (true) {
            turnHumanX();
            printField();
            if (checkWin(getSignX())) {
                System.out.println("Congratulations player: " + ticTacToe.getNamePlayerOne() + ". You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
            turnHumanO();
            printField();
            if (checkWin(getSignO())) {
                System.out.println("Congratulations player: " + ticTacToe.getNamePlayerTwo() + ". You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
        }
        System.out.println("..Game over..");
        printField();
    }

    private void startGameWithAi() {
        System.out.println("Good luck player!");
        greetingsPlayer();
        while (true) {
            turnHumanX();
            if (checkWin(getSignX())) {
                System.out.println("Congratulations player: " + ticTacToe.getNamePlayerOne() + ". You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
            turnAI();
            printField();
            if (checkWin(getSignO())) {
                System.out.println("Sorry, but you loose.. AI Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry DRAW!");
                break;
            }
        }
        System.out.println("..Game over..");
        printField();
    }


    private void greetingsPlayers() {
        boolean isFlag = false;
        do {
            System.out.println("First player enter your name: ");
            ticTacToe.setNamePlayerOne(scanner.nextLine());
            System.out.println("Second player enter your name: ");
            ticTacToe.setNamePlayerTwo(scanner.nextLine());
            if (ticTacToe.getNamePlayerOne().equals("") || ticTacToe.getNamePlayerTwo().equals("")) {
                System.out.println("You entered an empty name, please try again.. ");
            } else isFlag = true;
        } while (!isFlag);

        System.out.println("Greeting player <" + ticTacToe.getNamePlayerOne() + ">, " +
                "and player <" + ticTacToe.getNamePlayerTwo() + ">.");
        System.out.println("Let's the battle begin...!!!");
    }

    public void greetingsPlayer() {
        boolean isFlag = false;
        do {
            System.out.println("Player enter your name: ");
            ticTacToe.setNamePlayerOne(scanner.nextLine());
            if (ticTacToe.getNamePlayerOne().equals("")) {
                System.out.println("You entered an empty name, please try again.. ");
            } else isFlag = true;
        } while (!isFlag);

        System.out.println("Greeting player <" + ticTacToe.getNamePlayerOne() + ">.");
        System.out.println("Let's the battle begin...!!!");
    }

    private void turnHumanX() {
        int x;
        int y;
        do {
            System.out.println("Enter X and Y coodrs from 1 to 3");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        getField()[y][x] = getSignX();
    }

    private void turnHumanO() {
        int x;
        int y;
        do {
            System.out.println("Enter X and Y coodrs from 1 to 3");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        getField()[y][x] = getSignO();
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3)
            return false;
        return getField()[y][x] == getSignEmpty();
    }

    private void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        getField()[y][x] = getSignO();
    }

    private void printField() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(getField()[row][col] + " ");
            }
            System.out.println();
        }
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
                if (getField()[row][col] == getSignEmpty())
                    return false;
        return true;
    }
}
