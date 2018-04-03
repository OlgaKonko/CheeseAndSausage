package com.olga_kondratenko.cheeseandsausage.ii;


import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

import java.util.Random;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;

public class RandomII extends II{

    Random random;
    public RandomII(Field field, Sign sign){
        super(field, sign);
        random = new Random();
    }

    public Coordinates makeMove(){
        moveX = random.nextInt(FIELD_SIZE);
        moveY = random.nextInt(FIELD_SIZE);
        while (field.field[moveX][moveY]!=FREE){
            moveX = random.nextInt(FIELD_SIZE);
            moveY = random.nextInt(FIELD_SIZE);
        }
        field.makeMove(moveX, moveY,iiSign);
        return new Coordinates(moveX, moveY);
    }
}
