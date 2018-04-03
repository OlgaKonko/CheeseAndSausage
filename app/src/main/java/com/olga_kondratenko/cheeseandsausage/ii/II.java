package com.olga_kondratenko.cheeseandsausage.ii;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

public abstract class II {
    public int moveX;
    public int moveY;
    Field field;
    public Sign iiSign;

    public II(Field field, Sign sign) {
        this.field = field;
        iiSign=sign;
    }

    public abstract Coordinates makeMove();
    public void mutate(){

    }
}
