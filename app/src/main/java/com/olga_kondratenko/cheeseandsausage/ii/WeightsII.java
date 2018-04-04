package com.olga_kondratenko.cheeseandsausage.ii;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.ii.weightsII.Predicator;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

public class WeightsII extends II {
Predicator predicator;
    Coordinates playerMove;
    public WeightsII(Field field, Sign sign) {
        super(field, sign);
        predicator = new Predicator(field, sign);
        playerMove = new Coordinates(-1, -1);
    }

    public void setPlayerMove(Coordinates playerMove){
        this.playerMove = playerMove;
    }

    @Override
    public Coordinates makeMove() {
        Coordinates move = predicator.makeMove(playerMove);
        field.makeMove(move.x, move.y,iiSign);
       return move;
    }
}
