package com.olga_kondratenko.cheeseandsausage.game;

import com.olga_kondratenko.cheeseandsausage.ii.II;
import com.olga_kondratenko.cheeseandsausage.ii.NeiroII;
import com.olga_kondratenko.cheeseandsausage.ii.RandomII;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;

import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.COMPUTER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.DROW;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.FIRST_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;

public class PvCGame extends Game {
    private II ii;

    public PvCGame() {
        super();
        this.ii = new NeiroII(field, anotherSign, "FirstPopulation0");
    }

    @Override
    public void makeMove(int x, int y) {

        if (field.makeMove(x, y, playerSign)) {
            view.showMove(x, y, playerSign);
            if (field.checkGameEnding()) {
                winner = (field.getWinnerSign() != FREE) ? FIRST_PLAYER_WIN : DROW;
                if (winner == FIRST_PLAYER_WIN){
                    wins++;
                }
                all++;
                view.showGameEnd(winner);
            }
        }

        Coordinates iiMove = ii.makeMove();

        view.showMove(iiMove.x,iiMove.y, ii.iiSign);
        if (field.checkGameEnding()) {
            winner = (field.getWinnerSign() != FREE) ? COMPUTER_WIN : DROW;
            if (winner == COMPUTER_WIN){
                loses++;
            }
            all++;
            view.showGameEnd(winner);
        }
    }
}
