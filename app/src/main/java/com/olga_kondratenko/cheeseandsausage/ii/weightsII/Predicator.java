package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

import java.util.HashSet;
import java.util.Random;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_LAST;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.ii.weightsII.Constants.SCATTER;

public class Predicator {
    Field field;
    Sign sign;
    HashSet<Move> potentialMoves;

    private float[][] test;

    public Predicator(Field field, Sign sign) {
        this.field = field;
        this.sign = sign;
        potentialMoves = new HashSet<>();
        test = new float[8][8];
    }

    private void creanTest(){
        for (int x = 0; x<8; x++)
            for (int y = 0; y<8; y++)
                test[x][y]=0;
    }

    public Coordinates makeMove(Coordinates playerMove){
        creanTest();
        if (playerMove.x>0&&playerMove.y>0)
            addPotentialMoves(playerMove);
        Coordinates coordinates = checkMoveWeights().coordinates;
        addPotentialMoves(coordinates);
       // potentialMoves.remove(new Move(coordinates));
       // drowTest();
        return coordinates;
    }

    private void addPotentialMoves(Coordinates playerMove){
        Move potentialMove;
     //   System.out.print("Add moves ");
        for (int xIndex = playerMove.x-SCATTER; xIndex < playerMove.x+SCATTER; xIndex++)
            for (int yIndex = playerMove.y-SCATTER; yIndex < playerMove.y+SCATTER; yIndex++){
            if ((xIndex>=0&&xIndex<FIELD_SIZE)&&(yIndex>=0&&yIndex<FIELD_SIZE)){
               if (field.field[xIndex][yIndex] == Sign.FREE){
                potentialMove = new Move(xIndex, yIndex);
                potentialMoves.add(potentialMove);
               // System.out.print(xIndex+":"+yIndex+" ");
            }}
        }
        System.out.println();
        potentialMove = new Move(playerMove);
        potentialMoves.remove(potentialMove);
      //  System.out.println("remove"+playerMove.x+":"+playerMove.y);
    }

    private Move checkMoveWeights(){
        int effect = 0 ;
        Move bestMove = new Move(new Random().nextInt(FIELD_LAST), new Random().nextInt(FIELD_LAST));
        for (Move move: potentialMoves
             ) {
            new MoveWeightChecker(field, move, sign).checkWeights();
            test[move.coordinates.x][move.coordinates.y] = move.effect/100;

            if (move.effect>effect){
                effect = move.effect;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private void drowTest(){
        for (int x = 0; x<8; x++) {
            for (int y = 0; y < 8; y++)
                System.out.print(String.format("%.5f ",test[x][y]));
            System.out.println();
        }
    }
}
