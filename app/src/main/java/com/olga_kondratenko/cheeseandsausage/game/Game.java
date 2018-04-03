package com.olga_kondratenko.cheeseandsausage.game;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Field;
import com.olga_kondratenko.cheeseandsausage.view.View;

import java.util.Random;

import static com.olga_kondratenko.cheeseandsausage.constants.Sign.*;

public abstract class Game {
    Field field;
    Sign playerSign;
    Sign anotherSign;
    View view;
    int winner;

    public Game() {
        field = new Field();
        playerSign = new Random().nextBoolean() ? KROSS : CIRCLE;
        anotherSign = (playerSign == CIRCLE) ? KROSS : CIRCLE;
    }
    public void play(){}
    public void setView(View view) {
        this.view = view;
    }

    public int getFieldSize() {
        return field.getFieldSize();
    }

    public abstract void makeMove(int x, int y);

}
