package com.olga_kondratenko.cheeseandsausage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.olga_kondratenko.cheeseandsausage.constants.ActivityDataKey.GAME_MOD;
import static com.olga_kondratenko.cheeseandsausage.constants.ActivityDataKey.WINNER;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.DROW;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.FIRST_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.SECOND_PLAYER_WIN;
import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.PvC;
import static com.olga_kondratenko.cheeseandsausage.constants.Names.PLAYER;

public class PvCGameEndActivity extends AppCompatActivity {
    int winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvc_game_end);
        winner = getIntent().getIntExtra(WINNER, DROW);
        writeGameEndMessage();
    }

    private void writeGameEndMessage() {
        TextView mainMessage = findViewById(R.id.main_message);
        mainMessage.setVisibility(View.VISIBLE);
        switch (winner) {
            case FIRST_PLAYER_WIN: {
                mainMessage.setText(getString(R.string.player_win, PLAYER));
                break;
            }
            case SECOND_PLAYER_WIN: {
                mainMessage.setText(getString(R.string.player_lose, PLAYER));
                break;
            }
            case DROW: {
                mainMessage.setText(getString(R.string.drow_congratulation));
                break;
            }
        }
    }
    public void startOneMoreGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(GAME_MOD, PvC.name());
        startActivity(intent);
        finish();
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
