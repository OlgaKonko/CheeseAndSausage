package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.model.Field;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_LAST;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.WIN_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;

public class MoveWeightChecker {
    Field field; Move move; Sign sign;

    public MoveWeightChecker(Field field, Move move, Sign sign) {
        this.field = field;
        this.move = move;
        this.sign = sign;
    }
    String[] symbols = new String[4];
    public void checkGameEnding (){
        int x = move.coordinates.x;
        int y = move.coordinates.y;
        if (field.field[x][y] == FREE){

        }
        else {
            move.attackWeight = 0;
            move.defenseWeight = 0;
        }
        //check move is free
        //set comp move and count attack
        //set player move and count attack
        //set FREE

    }

    private int countWeight(Sign sign, int x, int y){
        int weight = 0;
        field.field[x][y] = sign;
        weight += checkString(sign,checkHorizontal(x),x);
        weight += checkString(sign,checkVertical(y), y);
        weight += checkString(sign,checkDiagonalDown(x,y));
        weight += checkString(sign,checkDiagonalUp(x,y));
        field.field[x][y] = FREE;
        return weight;
    }

    private int checkString(Sign sign, String s, int index){
        int points =0;

    }

    private String checkHorizontal(int x){

        char[] combo = new char[FIELD_SIZE];
        for (int y = 0; y<FIELD_SIZE; y++){
           combo[y] = field.field[x][y].getSimbol();
        }
        new String(combo);
        return new String(combo);
    }

    private String checkVertical(int y){
        char[] combo = new char[FIELD_SIZE];


        for (int x = 0; x<FIELD_SIZE; x++){
            combo[x] = field.field[x][y].getSimbol();

            }
        return new String(combo);
    }
    private String checkDiagonalDown(int x, int y){
        String diagonalCheck;
        if (y > x) {
            diagonalCheck = checkDiagonalDownRight(y - x);
        } else {
           diagonalCheck = checkDiagonalDownLeft(x - y);
        }
        return diagonalCheck;
    }

    private String checkDiagonalDownLeft(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int x  = 0; x<FIELD_SIZE; x++){

            if (x+number<FIELD_SIZE){
                combo[x] = field.field[x+number][x].getSimbol();
            }
        }
        return new String(combo);
    }

    private String checkDiagonalDownRight(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int x  = 0; x<FIELD_SIZE; x++){
            if (x+number<FIELD_SIZE){
                combo[x] = field.field[x][x+number].getSimbol();
        }
        }
            return new String(combo);
    }

    private String checkDiagonalUp(int x, int y){
        String diagonalCheck;
        if ((x+y) > FIELD_LAST) {
            diagonalCheck = checkDiagonalUpRight((x + y) - FIELD_LAST);
        } else {
            diagonalCheck = checkDiagonalUpLeft(x + y);
        }
        return diagonalCheck;
    }


    private  String checkDiagonalUpRight(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int x  = 0; x<=FIELD_SIZE; x++){
            if (number+x<FIELD_SIZE){
                combo[x] = field.field[FIELD_LAST - x][x+number].getSimbol();
                //  System.out.println("!!!! field "+field[FIELD_LAST - x][x+number]+" on+" +(FIELD_LAST - x)+" x "+(x+number)+" y");
                //  System.out.println("!!!! last one is "+lastDiagonalSign+" progression is "+currentDiagonalSignsNumber);


            }
        }
        return new String(combo);
    }

    private  String checkDiagonalUpLeft(int number){
        char[] combo = new char[FIELD_SIZE];
        for (int x  = 0; x<=number; x++){
            if (number-x>=0){
                combo[x] = field.field[x][number-x].getSimbol();
            }
        }
        return new String(combo);
    }


}
