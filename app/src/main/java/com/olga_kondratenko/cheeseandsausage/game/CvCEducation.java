package com.olga_kondratenko.cheeseandsausage.game;

import android.os.CountDownTimer;

import com.olga_kondratenko.cheeseandsausage.ii.II;
import com.olga_kondratenko.cheeseandsausage.ii.NeiroII;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;

import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.DROW;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.FIRST_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.SECOND_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Moves.currentMoves;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Moves.gameMovesAverage;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;

public class CvCEducation extends Game{
    private NeiroII firstIi;
    private NeiroII secondIi;
    private boolean gameContinue = true;
    private boolean educationContinue = true;
    private boolean firstPlayerTurn = true;

    public CvCEducation() {
        super();
        System.out.println("start education");
        this.firstIi = new NeiroII(field, playerSign, "FirstII");
        this.secondIi = new NeiroII(field, anotherSign, "SecondII");
        currentMoves = 0;
    }
    public void restart(){
       gameContinue = true;
       field.cleanField();
        currentMoves = 0;
        firstPlayerTurn= !firstPlayerTurn;
       play();
    }

    @Override
    public void play(){
        new CountDownTimer(10, 1) {
            public void onTick(long millisUntilFinished) {
                if (firstPlayerTurn){
                    makeIIMove(firstIi, FIRST_PLAYER_WIN);
                }
                else {
                    makeIIMove(secondIi, SECOND_PLAYER_WIN);
                }
                if (!gameContinue){
                    System.out.println("game end");
                    this.cancel();
                    endGame();}
            }

            @Override
            public void onFinish() {
                if (educationContinue){
                play();}
            }
        }.start();
    }

    public void makeIIMove(II ii, int expectedWinner){
        System.out.println("make move!");
        currentMoves++;
        Coordinates move = ii.makeMove();
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
        System.out.println("game end");
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

        if (all==1){
            gameMovesAverage = currentMoves;
        }
        else {
            gameMovesAverage = (gameMovesAverage*(all-1)+currentMoves)/all;
        }

        view.showGameEnd(winner);
    }

    public void stopGame(){
        gameContinue = false;
    }

    @Override
    public void makeMove(int x, int y) {

    }
}
