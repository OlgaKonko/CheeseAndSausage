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
import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.PvP;
import static com.olga_kondratenko.cheeseandsausage.constants.Names.FIRST_PLAYER_NAME;
import static com.olga_kondratenko.cheeseandsausage.constants.Names.SECOND_PLAYER_NAME;

public class PvPGameEndActivity extends AppCompatActivity {
    int winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvp_game_end);
        winner = getIntent().getIntExtra(WINNER, DROW);
        writeGameEndMessage();
    }

    private void writeGameEndMessage() {
        TextView firstPlayer = findViewById(R.id.first_player_message);
        TextView secondPlayer = findViewById(R.id.second_player_message);
        switch (winner) {
            case FIRST_PLAYER_WIN: {
                firstPlayer.setText(getString(R.string.player_win, FIRST_PLAYER_NAME));
                secondPlayer.setText(getString(R.string.player_lose, SECOND_PLAYER_NAME));
                break;
            }
            case SECOND_PLAYER_WIN: {
                firstPlayer.setText(getString(R.string.player_lose, FIRST_PLAYER_NAME));
                secondPlayer.setText(getString(R.string.player_win, SECOND_PLAYER_NAME));
                break;
            }
            case DROW: {
                firstPlayer.setText(getString(R.string.drow_congratulation));
                secondPlayer.setText(getString(R.string.drow_congratulation));
                break;
            }
        }
    }

    public void startOneMoreGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(GAME_MOD, PvP.name());
        startActivity(intent);
        finish();
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
