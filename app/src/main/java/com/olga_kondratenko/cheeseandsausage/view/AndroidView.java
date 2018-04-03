package com.olga_kondratenko.cheeseandsausage.view;


import com.olga_kondratenko.cheeseandsausage.GameActivity;
import com.olga_kondratenko.cheeseandsausage.constants.Sign;

public class AndroidView implements View{
    GameActivity mainActivity;
    AndroidFileWorker fileWorker;
    public AndroidView(GameActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public AndroidFileWorker getFileWorker(){
        return fileWorker;
    }

    @Override
    public void showMove(int x, int y, Sign sign){
        mainActivity.showMove(x,y,sign);
    }

    @Override
    public void showGameEnd(int winner) {
        mainActivity.endGame(winner);
    }
    @Override
    public void showGameEnd() {
        mainActivity.endGame();
    }
}
