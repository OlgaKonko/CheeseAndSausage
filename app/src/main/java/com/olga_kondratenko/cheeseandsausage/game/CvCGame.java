package com.olga_kondratenko.cheeseandsausage.game;

import android.os.CountDownTimer;

import com.olga_kondratenko.cheeseandsausage.ii.II;
import com.olga_kondratenko.cheeseandsausage.ii.NeiroII;
import com.olga_kondratenko.cheeseandsausage.ii.RandomII;
import com.olga_kondratenko.cheeseandsausage.ii.WeightsII;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;

import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.DROW;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.FIRST_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.SECOND_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Moves.currentMoves;

public class CvCGame extends Game{
    private WeightsII firstIi;
    private WeightsII secondIi;
    Coordinates move;
    boolean gameContinue = true;
    boolean firstPlayerTurn = true;

    public CvCGame() {
        super();
        this.firstIi = new WeightsII(field, playerSign);
        this.secondIi = new WeightsII(field, anotherSign);
    }

    @Override
    public void play(){
        new CountDownTimer(10, 1) {
            public void onTick(long millisUntilFinished) {
                if (firstPlayerTurn){
                    makeWeightIIMove(firstIi, secondIi,FIRST_PLAYER_WIN);
                }
                else {
                    makeWeightIIMove(secondIi, firstIi, SECOND_PLAYER_WIN);
                }
                if (!gameContinue){
                   // System.out.println("game end");
                    cancel();
                    endGame();}
            }

            @Override
            public void onFinish() {
                if (gameContinue){
                play();}

            }
        }.start();
    }

    public void makeWeightIIMove( WeightsII ii,  WeightsII anotherII, int expectedWinner){
        currentMoves++;
        move = ii.makeMove();
        anotherII.setPlayerMove(move);
        view.showMove(move.x, move.y, ii.iiSign);
        if (field.checkGameEnding()) {
            winner = (field.getWinnerSign() != FREE) ? expectedWinner : DROW;
            gameContinue =false;
        }
        firstPlayerTurn = !firstPlayerTurn;

    }

    public void makeIIMove(II ii, int expectedWinner){
        currentMoves++;
        move = ii.makeMove();
        view.showMove(move.x, move.y, ii.iiSign);
        if (field.checkGameEnding()) {
            winner = (field.getWinnerSign() != FREE) ? expectedWinner : DROW;
            gameContinue =false;
            if (winner != DROW){
                ii.mutate();
            }
        }
        firstPlayerTurn = !firstPlayerTurn;

    }
    private void endGame(){
        if (winner == FIRST_PLAYER_WIN){
            wins++;
        }
        if (winner == SECOND_PLAYER_WIN){
            loses++;
        }
        if (winner == DROW){
            firstIi.mutate();
            secondIi.mutate();
        }

        all++;
        view.showGameEnd(winner);
    }

    @Override
    public void makeMove(int x, int y) {

    }
}
