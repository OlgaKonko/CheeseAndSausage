package com.olga_kondratenko.cheeseandsausage.model;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        return x*FIELD_SIZE+y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode()==this.hashCode();
    }
}
