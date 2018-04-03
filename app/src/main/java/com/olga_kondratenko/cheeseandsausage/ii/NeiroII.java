package com.olga_kondratenko.cheeseandsausage.ii;


import android.support.annotation.NonNull;

import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.ii.neiroii.Counter;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

public class NeiroII  extends II implements Comparable<NeiroII>{
    Counter counter;
    String name;
    public int effect=0;

    public NeiroII(Field field, Sign sign, String name) {
        super(field, sign);
        System.out.println("create neiro ii "+name);
        this.name = name;
        counter = new Counter(sign, this.field, name);
    }

    public NeiroII(Field field, Sign sign, String name, boolean createNew) {
        super(field, sign);
        this.name = name;
        counter = new Counter(sign, this.field, name, createNew);
    }

    public NeiroII(NeiroII firstParent, NeiroII secondParent) {
        super(firstParent.field, firstParent.iiSign);
        this.name = firstParent.name;
        counter = new Counter(firstParent.counter, secondParent.counter);
    }

    @Override
    public Coordinates makeMove() {
        Coordinates move = counter.makeDessigion();
        //System.out.println (name+ "moved to x "+ move.x+ " y "+ move.y );
        moveX = move.x;
        moveY = move.y;
        field.makeMove(move.x, move.y,iiSign);
       return move;
    }

    public void mutate(){
        counter.changeLinks();
    }

    public void save(){
       counter.saveConfiguration();
    }

    @Override
    public int compareTo(@NonNull NeiroII neiroII) {
        return -Integer.compare(this.effect, neiroII.effect);
    }
}
