package com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron;

public class changeDataFunction {

    public static float activate(float input){
       // System.out.print((float)Math.pow(Math.E, input));
        return (1/(1+(float)Math.pow(Math.E, input)));
       // return (1/input);
    }
}
