package com.olga_kondratenko.cheeseandsausage.model;


import com.olga_kondratenko.cheeseandsausage.constants.Sign;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_LAST;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.WIN_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;


public class Field {
    public Sign[][] field;
    private boolean isGameEnd;
    private Sign gameResult;
    public int filled;
    private int lastMoveX;
    private int lastMoveY;

    public Field(){
        field = new Sign[FIELD_SIZE][FIELD_SIZE];
        cleanField();
    }

    public boolean makeMove(int x, int y, Sign sign){
        if (field[x][y]==FREE){
            field[x][y]=sign;
            filled++;
            lastMoveX =x;
            lastMoveY =y;
            return true;
        }
        return false;
    }
    public Sign getWinnerSign(){
        return gameResult;
    }

    public boolean removeLastMove(){
        if ((lastMoveY>=0)&&(lastMoveX>=0)){
       field[lastMoveX][lastMoveY]=FREE;
       filled--;
       lastMoveY=-1;
       lastMoveX=-1;
        }
        return false;
    }

    public int getFieldSize(){
        return FIELD_SIZE;
    }

    public boolean checkGameEnding (){
        isGameEnd = false;
        if ((lastMoveY>=0)&&(lastMoveX>=0)){
        if (isFieldFilled()) {
            isGameEnd = true;
            gameResult = FREE;
        }
        else {
            checkVertical(lastMoveY);
            checkHorizontal(lastMoveX);
            if (lastMoveY > lastMoveX) {
                checkDiagonalDownRight(lastMoveY - lastMoveX);
            } else {
                checkDiagonalDownLeft(lastMoveX - lastMoveY);
            }

            if ((lastMoveY + lastMoveX) > FIELD_LAST) {
                checkDiagonalUpRight((lastMoveY + lastMoveX) - FIELD_LAST);
            } else {
                checkDiagonalUpLeft(lastMoveY + lastMoveX);
            }
        }}
        return isGameEnd;
    }
    public boolean isFieldFilled(){
       return filled==(FIELD_SIZE*FIELD_SIZE);
    }

    private boolean checkHorizontal(int x){
        //System.out.println("!!!!!! on "+x+" x");

        int currentHorizontalSignsNumber =0;
        Sign lastHorizontalSign = FREE;
            for (int y = 0; y<FIELD_SIZE; y++){
                //System.out.println("!!!! on "+y+" y is"+field[x][y]+" last one is "+lastHorizontalSign+" progression is "+currentHorizontalSignsNumber);
                if (field[x][y]==lastHorizontalSign){
                    if (field[x][y]!=FREE){
                        currentHorizontalSignsNumber++;
                     //   System.out.println("!! one to progression ");
                    }
                }
                else {
                    currentHorizontalSignsNumber =1;
                   // System.out.println("!! skip progression ");
                }
                lastHorizontalSign = field[x][y];
                if (currentHorizontalSignsNumber==WIN_SIZE){
                   // System.out.println("!! progression has win size ");
                    gameResult = lastHorizontalSign;
                    isGameEnd = true;
                    return true;
                }
            }
            return false;
    }

    private boolean checkVertical(int y){
       // System.out.println("!!!!!! on "+y+" y");
        int currentVerticalSignsNumber =0;
        Sign lastVerticalSign = FREE;
        for (int x = 0; x<FIELD_SIZE; x++){
           // System.out.println("!!!! on "+y+" y is"+field[x][y]+" last one is "+lastVerticalSign+" progression is "+currentVerticalSignsNumber);
            if (field[x][y]==lastVerticalSign){
                if (field[x][y]!=FREE){
                    currentVerticalSignsNumber++;
                }
            }
            else {
                currentVerticalSignsNumber =1;
            }
            lastVerticalSign = field[x][y];
            if (currentVerticalSignsNumber==WIN_SIZE){
                gameResult = lastVerticalSign;
                isGameEnd = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalDownLeft(int number){
        //System.out.println("!!!!!! on "+number+" right diagonal ");
        int currentDiagonalSignsNumber =0;
        Sign lastDiagonalSign = FREE;

        for (int x  = 0; x<FIELD_SIZE; x++){
          //  System.out.println("!!!! on "+x+" x and "+(x+number)+" y ");
            if (x+number<FIELD_SIZE){

                if (field[x+number][x]==lastDiagonalSign){
                    if (field[x+number][x]!=FREE){
                        currentDiagonalSignsNumber++;
                    }
                }
                else {
                    currentDiagonalSignsNumber =1;
                }
                lastDiagonalSign = field[x+number][x];
                if (currentDiagonalSignsNumber==WIN_SIZE){
                    gameResult = lastDiagonalSign;
                    isGameEnd = true;
                    return true;
                }

            }
            else {
                break;
            }
        }
        return false;
    }

    private boolean checkDiagonalDownRight(int number){
        int currentDiagonalSignsNumber =0;
        Sign lastDiagonalSign = FREE;
        for (int x  = 0; x<FIELD_SIZE; x++){
            if (x+number<FIELD_SIZE){
              //  System.out.println("!!!! field "+field[x][x+number]+" last one is "+lastDiagonalSign+" progression is "+currentDiagonalSignsNumber);

                if (field[x][x+number]==lastDiagonalSign){
                    if (field[x][x+number]!=FREE){
                        currentDiagonalSignsNumber++;
                        //System.out.println("!!!! add progresion ");
                    }
                }
                else {
                    currentDiagonalSignsNumber =1;
                    //System.out.println("!!!! skip progresion ");
                }
                lastDiagonalSign = field[x][x+number];
                if (currentDiagonalSignsNumber==WIN_SIZE){
                    gameResult = lastDiagonalSign;
                    isGameEnd = true;
                    return true;
                }

            }
            else {
                break;
            }
        }
        return false;
    }
    private boolean checkDiagonalUpRight(int number){
       // System.out.println("!!!!!! on "+number+" right diagonal ");
        int currentDiagonalSignsNumber =0;
        Sign lastDiagonalSign = FREE;
        for (int x  = 0; x<=FIELD_SIZE; x++){
            if (number+x<FIELD_SIZE){
              //  System.out.println("!!!! field "+field[FIELD_LAST - x][x+number]+" on+" +(FIELD_LAST - x)+" x "+(x+number)+" y");
              //  System.out.println("!!!! last one is "+lastDiagonalSign+" progression is "+currentDiagonalSignsNumber);

                if (field[FIELD_LAST - x][x+number]==lastDiagonalSign){
                    if (field[FIELD_LAST- x][x+number]!=FREE){
                        currentDiagonalSignsNumber++;
                  //      System.out.println("!!!! add progresion ");
                    }
                }
                else {
                    currentDiagonalSignsNumber =1;
                 //   System.out.println("!!!! skip progresion ");
                }
                lastDiagonalSign = field[FIELD_LAST - x][x+number];
                if (currentDiagonalSignsNumber==WIN_SIZE){
                    gameResult = lastDiagonalSign;
                    isGameEnd = true;
                    return true;
                }

            }
        }
        return false;
    }

    private boolean checkDiagonalUpLeft(int number){
        int currentDiagonalSignsNumber =0;
        Sign lastDiagonalSign = FREE;
        for (int x  = 0; x<=number; x++){
            if (number-x>=0){

                if (field[x][number-x]==lastDiagonalSign){
                    if (field[x][number-x]!=FREE){
                        currentDiagonalSignsNumber++;
                    }
                }
                else {
                    currentDiagonalSignsNumber =1;
                }
                lastDiagonalSign = field[x][number-x];
                if (currentDiagonalSignsNumber==WIN_SIZE){
                    gameResult = lastDiagonalSign;
                    isGameEnd = true;
                    return true;
                }

            }
        }
        return false;
    }

    public void cleanField(){
        for (int x = 0; x<FIELD_SIZE; x++)
            for (int y = 0; y<FIELD_SIZE; y++){
                field[x][y] = FREE;
            }
        filled = 0;
        isGameEnd=false;
        gameResult = FREE;
        lastMoveX =-1;
        lastMoveY=-1;

    }
}
