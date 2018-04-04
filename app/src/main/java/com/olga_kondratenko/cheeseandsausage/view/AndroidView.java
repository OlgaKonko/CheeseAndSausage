package com.olga_kondratenko.cheeseandsausage.view;


import com.olga_kondratenko.cheeseandsausage.MainActivity;
import com.olga_kondratenko.cheeseandsausage.constants.Sign;

public class AndroidView implements View{
    private MainActivity mainActivity;
    public AndroidView(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void showMove(int x, int y, Sign sign){
        mainActivity.showMove(x,y,sign);
    }

    @Override
    public void showGameEnd(int winner) {
        mainActivity.endGame(winner);
    }
}
