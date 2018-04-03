package com.olga_kondratenko.cheeseandsausage;

import android.content.Context;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;

public interface GameActivity {
    void showMove(int x, int y, Sign sign);
    void endGame(int winner);
    void endGame();
    Context getContext();
}
