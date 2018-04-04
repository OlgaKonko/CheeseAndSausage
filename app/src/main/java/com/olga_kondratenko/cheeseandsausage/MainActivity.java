package com.olga_kondratenko.cheeseandsausage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.game.CvCGame;
import com.olga_kondratenko.cheeseandsausage.game.Game;
import com.olga_kondratenko.cheeseandsausage.game.PvCGame;
import com.olga_kondratenko.cheeseandsausage.game.PvPGame;
import com.olga_kondratenko.cheeseandsausage.view.AndroidView;

import static com.olga_kondratenko.cheeseandsausage.constants.ActivityDataKey.GAME_MOD;
import static com.olga_kondratenko.cheeseandsausage.constants.ActivityDataKey.WINNER;

import static com.olga_kondratenko.cheeseandsausage.env.Environment.gameMode;
import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.CvC;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.CIRCLE;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;


public class MainActivity extends Activity{

    Game game;
    public ImageView[][] buttons;
    private int fieldSize;
    private TableLayout tableLayout;
    boolean gameStoped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.gameField);
        switch (gameMode){
            case PvP:{
                game = new PvPGame();
                showPvPField();
                break;
            }
            case PvC:{
                game = new PvCGame();
                break;
            }
            case CvC:{
                showPvCField();
                game = new CvCGame();
                break;
            }
        }
        TextView statistic = findViewById(R.id.statistic);
        statistic.setText(getString(R.string.statistic, wins, loses, all));
        game.setView(new AndroidView(this));
        fieldSize = game.getFieldSize();
        buttons = new ImageView[fieldSize][fieldSize];
        drawField();
        gameStoped = false;
    }

    public void onResume() {
        super.onResume();
        game.play();
    }

    public void onClick(View view) {

        if (view.getId() == R.id.addition_menu_button || view.getId() == R.id.player_menu_button){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            gameStoped = true;
            finish();
        }

        if (view.getId() == R.id.addition_repeat_button || view.getId() == R.id.player_repeat_button){
            all++;
            recreate();
        }

    }

    public void showPvPField(){
        Button additionMenuButton = findViewById(R.id.addition_menu_button);
        additionMenuButton.setVisibility(View.VISIBLE);
        Button additionRepeatButton = findViewById(R.id.addition_repeat_button);
        additionRepeatButton.setVisibility(View.VISIBLE);
        TextView additionStatistic = findViewById(R.id.addition_statistic);
        additionStatistic.setVisibility(View.VISIBLE);
        additionStatistic.setText(getString(R.string.statistic, loses, wins, all));
    }
    public void showPvCField(){
        TextView additionStatistic = findViewById(R.id.addition_statistic);
        additionStatistic.setVisibility(View.VISIBLE);
        additionStatistic.setText(getString(R.string.statistic, loses, wins, all));
    }

    public void showMove(int x, int y, Sign sign) {
        int picture = (sign == CIRCLE) ? R.drawable.circle_cell : R.drawable.kross_cell;
        buttons[x][y].setImageResource(picture);
    }

    public void endGame(int winner) {
        if (!gameStoped){
        Intent intent;
        switch (gameMode){
            case PvP:{
                intent = new Intent(this, PvPGameEndActivity.class);
                intent.putExtra(WINNER, winner);
                break;
            }
            case PvC:{
                intent = new Intent(this, PvCGameEndActivity.class);
                intent.putExtra(WINNER, winner);
                break;
            }
            default:{
                intent = new Intent(this, MainActivity.class);
               // intent.putExtra(GAME_MOD, CvC.name());
                break;
            }

        }
        startActivity(intent);
        finish();}
    }

    public Context getContext() {
        return getBaseContext();
    }

    private void drawField() {
        int weight = getTableSize() / fieldSize;
        for (int x = 0; x < fieldSize; x++) {
            TableRow row = new TableRow(this);

            for (int y = 0; y < fieldSize; y++) {
                createButton(x, y, weight, row);

            }
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private void createButton(int x, int y, int weight, TableRow row) {
        ImageView button = new ImageView(this);
        buttons[x][y] = button;
        button.setImageResource(R.drawable.empty_cell);
        button.setOnClickListener(new MoveListener(x, y, game));
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = weight;
        button.getLayoutParams().height = weight;

        button.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private int getTableSize() {
        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);
        return size.x;
    }
}
