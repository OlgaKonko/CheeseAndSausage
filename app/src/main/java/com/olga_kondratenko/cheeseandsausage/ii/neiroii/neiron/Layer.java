package com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron;

public class Layer {
    Neiron neirons[];
    int size;

    public Layer(int size) {
        this.size = size;
        neirons = new Neiron[size];
        for (int index =0; index<size; index++)
            neirons[index] = new Neiron();
    }

    public void countNeiron(int index, float data){
        neirons[index].data=data;
    }

    public float getNeironData(int index){
        return neirons[index].data;
    }
}
