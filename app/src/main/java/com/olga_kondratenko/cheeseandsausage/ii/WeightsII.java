package com.olga_kondratenko.cheeseandsausage.ii;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.ii.weightsII.Predicator;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

public class WeightsII extends II {
Predicator predicator;

    public WeightsII(Field field, Sign sign) {
        super(field, sign);
        predicator = new Predicator(field, sign);
    }

    public void playerMakeMove(Coordinates playerMove){

    }

    @Override
    public Coordinates makeMove() {
        return null;
    }
}
