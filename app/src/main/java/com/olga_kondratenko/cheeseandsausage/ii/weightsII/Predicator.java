package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

import java.util.HashSet;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.ii.weightsII.Constants.SCATTER;

public class Predicator {
    Field field;
    Sign sign;
    HashSet<Move> potentialMoves;
    public Predicator(Field field, Sign sign) {
        this.field = field;
        this.sign = sign;
        potentialMoves = new HashSet<>();
    }

    public void addPotentialMoves(Coordinates playerMove){
        Move potentialMove;
        for (int xIndex = playerMove.x-SCATTER; xIndex < playerMove.x+SCATTER; xIndex++)
            for (int yIndex = playerMove.y-SCATTER; yIndex < playerMove.y+SCATTER; yIndex++){
            if ((xIndex>=0&&xIndex<FIELD_SIZE)&&(yIndex>=0&&yIndex<FIELD_SIZE)&&(xIndex!= playerMove.x && yIndex!=playerMove.y)){
               if (field.field[xIndex][yIndex] == Sign.FREE){
                potentialMove = new Move(xIndex, yIndex);
                potentialMoves.add(potentialMove);
            }}
        }
        potentialMove = new Move(playerMove);
        potentialMoves.remove(potentialMove);
    }

    private void checkMoveWeight(Move move, Sign sign){

    }
}
