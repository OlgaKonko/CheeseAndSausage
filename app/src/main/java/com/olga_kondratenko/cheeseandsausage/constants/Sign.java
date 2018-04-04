package com.olga_kondratenko.cheeseandsausage.constants;

public enum Sign {
    KROSS('K'),
    CIRCLE('C'),
    FREE('F'),
    TEST('T'),;

    private char simbol;

    Sign(char simbol) {
        this.simbol = simbol;
    }

    public char getSimbol() {
        return simbol;
    }
}
