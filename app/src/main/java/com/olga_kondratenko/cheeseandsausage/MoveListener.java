package com.olga_kondratenko.cheeseandsausage;

import android.view.View;

import com.olga_kondratenko.cheeseandsausage.game.Game;

public class MoveListener implements View.OnClickListener {
    private int x = 0;
    private int y = 0;
    private Game game;

    public MoveListener(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public void onClick(View view) {
        game.makeMove(x, y);
    }
}
