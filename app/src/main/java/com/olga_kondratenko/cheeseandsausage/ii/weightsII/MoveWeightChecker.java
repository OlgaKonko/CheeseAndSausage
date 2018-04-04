package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Field;

import java.util.ArrayList;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_LAST;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.WIN_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.CIRCLE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.KROSS;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.TEST;
import static com.olga_kondratenko.cheeseandsausage.ii.weightsII.SignsPatterns.countString;

public class MoveWeightChecker {
    Field field;
    Move move;
    Sign sign;
    Sign enemySign;
    int playerX;
    int playerY;

    public MoveWeightChecker(Field field, Move move, Sign sign) {
        this.field = field;
        this.move = move;
        this.sign = sign;
        enemySign = sign==KROSS? CIRCLE:KROSS;
        playerX = move.coordinates.x;
        playerY = move.coordinates.y;
    }
    ArrayList<String> symbols = new ArrayList<>(4);

    public void checkWeights(){
      //  System.out.println("Check Weights for "+playerX+" "+playerY);
        if (field.field[playerX][playerY] != FREE){

        }
        else {
            field.field[playerX][playerY] = TEST;
            symbols.add(checkHorizontal());
            symbols.add(checkVertical());
            symbols.add(checkDiagonalDown());
            symbols.add(checkDiagonalUp());
            field.field[playerX][playerY] = FREE;

            move.attackWeight = countWeight(sign);
            move.defenseWeight = countWeight(enemySign);
            move.countEffect();
        }
    }

    private int countWeight(Sign sign){
        int weight = 0;
        for (String s: symbols) {
            weight += checkString(sign,s);
        }
        return weight;
    }

    private int checkString(Sign sign, String s){
        if (s.length()<WIN_SIZE)
            return 0;
        else
       return countString(s.replace(sign.getSimbol(), 'P'));

    }

    private String checkHorizontal(){

        char[] combo = new char[FIELD_SIZE];
        for (int y = 0; y<FIELD_SIZE; y++){
           combo[y] = field.field[playerX][y].getSimbol();
        }
        new String(combo);
        return new String(combo);
    }

    private String checkVertical(){
        char[] combo = new char[FIELD_SIZE];


        for (int x = 0; x<FIELD_SIZE; x++){
            combo[x] = field.field[x][playerY].getSimbol();

            }
        return new String(combo);
    }
    private String checkDiagonalDown(){
        String diagonalCheck;
        if (playerY > playerX) {
            diagonalCheck = checkDiagonalDownRight(playerY - playerX);
        } else {
           diagonalCheck = checkDiagonalDownLeft(playerX - playerY);
        }
        return diagonalCheck;
    }

    private String checkDiagonalDownLeft(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int index  = 0; index<FIELD_SIZE; index++){

            if (index+number<FIELD_SIZE){
                combo[index] = field.field[index+number][index].getSimbol();
            }
        }
        return new String(combo);
    }

    private String checkDiagonalDownRight(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int index  = 0; index<FIELD_SIZE; index++){
            if (index+number<FIELD_SIZE){
                combo[index] = field.field[index][index+number].getSimbol();
        }
        }
            return new String(combo);
    }

    private String checkDiagonalUp(){
        String diagonalCheck;
        if ((playerX + playerY) > FIELD_LAST) {
            diagonalCheck = checkDiagonalUpRight((playerX + playerY) - FIELD_LAST);
        } else {
            diagonalCheck = checkDiagonalUpLeft(playerX + playerY);
        }
        return diagonalCheck;
    }


    private  String checkDiagonalUpRight(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int index  = 0; index<=FIELD_SIZE; index++){
            if (number+index<FIELD_SIZE){
                combo[index] = field.field[FIELD_LAST - index][index+number].getSimbol();
            }
        }
        return new String(combo);
    }

    private  String checkDiagonalUpLeft(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int index  = 0; index<=number; index++){
            if (number-index>=0){
                combo[index] = field.field[index][number-index].getSimbol();
            }
        }
        return new String(combo);
    }


}
