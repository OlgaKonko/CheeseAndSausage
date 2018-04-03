package com.olga_kondratenko.cheeseandsausage.game;

import android.os.CountDownTimer;

import com.olga_kondratenko.cheeseandsausage.ii.education.GeneticNeiroII;

public class CvCGeneticEducation extends Game{
    private GeneticNeiroII geneticNeiroII;
    private boolean educationContinue = true;


    public CvCGeneticEducation() {
        super();
        System.out.println("start generic education");
        this.geneticNeiroII = new GeneticNeiroII(field);
    }

    public void restart(){
       play();
    }

    @Override
    public void play(){
        new CountDownTimer(3, 1) {
            public void onTick(long millisUntilFinished) {
                geneticNeiroII.check();
                System.out.println("generetion end");
                view.showGameEnd();
            }

            @Override
            public void onFinish() {
                if (educationContinue){
                play();}
            }
        }.start();
    }

    public void stopGame(){
        educationContinue = false;
        geneticNeiroII.saveWinners();
    }

    @Override
    public void makeMove(int x, int y) {

    }
}
