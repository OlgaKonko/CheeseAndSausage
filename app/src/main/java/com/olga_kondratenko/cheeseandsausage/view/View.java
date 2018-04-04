package com.olga_kondratenko.cheeseandsausage.view;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;

public interface  View {
    void showMove(int x, int y, Sign sign);
    void showGameEnd(int winner);
}
