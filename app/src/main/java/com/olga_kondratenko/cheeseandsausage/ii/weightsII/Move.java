package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import com.olga_kondratenko.cheeseandsausage.model.Coordinates;

import java.util.Random;

import static com.olga_kondratenko.cheeseandsausage.ii.weightsII.Constants.ATACK_RATE;
import static com.olga_kondratenko.cheeseandsausage.ii.weightsII.Constants.EFFECT_RATE;

public class Move {
    Coordinates coordinates;
    public int defenseWeight;
    public int attackWeight;
    public int effect=0;

    public Move(Coordinates coordinates) {
        this.coordinates = coordinates;
        defenseWeight=0;
        attackWeight=0;
    }
    public Move(int x, int y) {
        this.coordinates = new Coordinates(x,y);
        defenseWeight=0;
        attackWeight=0;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int countEffect() {
            effect = defenseWeight+Math.round(attackWeight*ATACK_RATE)+ new Random().nextInt(EFFECT_RATE);
        return effect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move moves = (Move) o;

        return coordinates != null ? coordinates.equals(moves.coordinates) : moves.coordinates == null;
    }

    @Override
    public int hashCode() {
        return coordinates != null ? coordinates.hashCode() : 0;
    }
}
