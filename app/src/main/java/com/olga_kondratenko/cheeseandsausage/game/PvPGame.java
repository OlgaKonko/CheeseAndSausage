package com.olga_kondratenko.cheeseandsausage.game;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;

import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.*;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;

public class PvPGame extends Game {
    private int currentPlayer;

    public PvPGame() {
        super();
        currentPlayer = 0;

    }

    @Override
    public void makeMove(int x, int y) {
        Sign sign = currentPlayer == 0 ? playerSign : anotherSign;
        if (field.makeMove(x, y, sign)) {
            view.showMove(x,y,sign);
            if (field.checkGameEnding()) {
                winner = (field.getWinnerSign() == playerSign)? FIRST_PLAYER_WIN: ((field.getWinnerSign() == anotherSign)? SECOND_PLAYER_WIN:DROW);
                if (winner == FIRST_PLAYER_WIN){
                    wins++;
                }
                if (winner == SECOND_PLAYER_WIN){
                    loses++;
                }
                all++;
                view.showGameEnd(winner);
            }

            currentPlayer = (currentPlayer + 1) % 2;
        }
    }
}
