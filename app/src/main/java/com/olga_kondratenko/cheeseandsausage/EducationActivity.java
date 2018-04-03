package com.olga_kondratenko.cheeseandsausage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.game.CvCGeneticEducation;
import com.olga_kondratenko.cheeseandsausage.view.AndroidView;

import static com.olga_kondratenko.cheeseandsausage.game.game_data.Moves.educationWinnerEffect;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Moves.gameMovesAverage;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins_per;

public class EducationActivity extends Activity implements GameActivity{
    CvCGeneticEducation game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        game = new CvCGeneticEducation();
        game.setView(new AndroidView(this));
    }
    public void onResume() {
        super.onResume();
        game.play();
    }

    @Override
    public void showMove(int x, int y, Sign sign) {
    }

    @Override
    public void endGame(int winner) {
        TextView results = findViewById(R.id.results);
        results.setText(getString(R.string.results, all, (all-(loses+wins)), gameMovesAverage));
        game.restart();
    }

    public void endGame() {
        TextView results = findViewById(R.id.results);
        results.setText(getString(R.string.generic_results, educationWinnerEffect, wins_per));
        game.restart();
    }

    public void stopEducation(View view) {
        game.stopGame();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return getBaseContext();
    }

}
