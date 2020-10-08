package by.stankevich.artemiy.leverx.game;

import by.stankevich.artemiy.leverx.game.bean.TicTacToe;
import by.stankevich.artemiy.leverx.game.service.TicTacToeService;

public class App {
    public static void main(String[] args) {

        TicTacToe ticTacToe = new TicTacToe();
        TicTacToeService ticTacToeService = new TicTacToeService(ticTacToe);
        ticTacToeService.startGame();
    }
}
