package com.olga_kondratenko.cheeseandsausage.ii.neiroii;


import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.Layer;
import com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.Links;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;
import com.olga_kondratenko.cheeseandsausage.view.AndroidFileWorker;

import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.changeDataFunction.activate;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants.NeironConstants.EMPTY_SIGN;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants.NeironConstants.ENEMY_SIGN;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants.NeironConstants.FIRST_LAYER_NEIRONS_COUNT;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants.NeironConstants.LAST_LAYER_NEIRONS_COUNT;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants.NeironConstants.MY_SIGN;
import static com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants.NeironConstants.SECOND_LAYER_NEIRONS_COUNT;

public class Counter {
    Links linksFS;
    Links linksSE;
   Layer firstLayer;
    Layer secondLayer;
    Layer lastLayer;
    Sign mySign;
    Field field;
    String iiName;

    public Counter(Sign mySign, Field field, String iiName) {
        firstLayer = new Layer(FIRST_LAYER_NEIRONS_COUNT);
        secondLayer = new Layer(SECOND_LAYER_NEIRONS_COUNT);
        lastLayer = new Layer(LAST_LAYER_NEIRONS_COUNT);
        linksFS = new Links(FIRST_LAYER_NEIRONS_COUNT, SECOND_LAYER_NEIRONS_COUNT, iiName, "FSlinks", false);
        linksSE = new Links(SECOND_LAYER_NEIRONS_COUNT, LAST_LAYER_NEIRONS_COUNT, iiName, "SElinks", false);
        this.field=field;
        this.mySign = mySign;
        this.iiName = iiName;
    }

    public Counter(Sign mySign, Field field, String iiName, boolean needNew){
        firstLayer = new Layer(FIRST_LAYER_NEIRONS_COUNT);
        secondLayer = new Layer(SECOND_LAYER_NEIRONS_COUNT);
        lastLayer = new Layer(LAST_LAYER_NEIRONS_COUNT);
        linksFS = new Links(FIRST_LAYER_NEIRONS_COUNT, SECOND_LAYER_NEIRONS_COUNT, iiName, "FSlinks", needNew);
        linksSE = new Links(SECOND_LAYER_NEIRONS_COUNT, LAST_LAYER_NEIRONS_COUNT, iiName, "SElinks", needNew);
        this.field=field;
        this.mySign = mySign;
        this.iiName = iiName;
    }

    public Counter(Counter firstParentCounter, Counter secondParentCounter){
        firstLayer = new Layer(FIRST_LAYER_NEIRONS_COUNT);
        secondLayer = new Layer(SECOND_LAYER_NEIRONS_COUNT);
        lastLayer = new Layer(LAST_LAYER_NEIRONS_COUNT);
        linksFS = new Links(firstParentCounter.linksFS, secondParentCounter.linksFS);
        linksSE = new Links(firstParentCounter.linksSE, secondParentCounter.linksSE);
        this.field=firstParentCounter.field;
        this.mySign = firstParentCounter.mySign;
        this.iiName = firstParentCounter.iiName;
    }

    public void saveConfiguration(){
        linksFS.saveLinks();
        linksSE.saveLinks();
    }

    public Coordinates makeDessigion(){
      //  System.out.println(iiName+" make dessigion ");
        countFirstLayer();
        countSecondLayer();
        countLastLayer();

        return checkMaxLastNeiron();
    }

    public void changeLinks(){
        linksFS.changeLinks();
        linksSE.changeLinks();
    }

    public void crossLinks(Counter pareCounter){
        linksFS.changeLinks();
        linksSE.changeLinks();
    }

    private void countFirstLayer(){
       // System.out.println("count first layer");
        float data;
        for (int x = 0; x<FIELD_SIZE; x++)
            for (int y = 0; y<FIELD_SIZE; y++){
            data = (field.field[x][y] == mySign)? MY_SIGN:((field.field[x][y] == FREE)?EMPTY_SIGN:ENEMY_SIGN);
            firstLayer.countNeiron(x*FIELD_SIZE+y, data);
                //System.out.println("on x "+x+" y "+y+" data "+data);
            }
    }

    private void countSecondLayer() {
        float data;
        //System.out.println("count second layer");
        for (int index = 0; index < SECOND_LAYER_NEIRONS_COUNT; index++) {
            data = 0;

            for (int firstLayerIndex = 0; firstLayerIndex < FIRST_LAYER_NEIRONS_COUNT; firstLayerIndex++) {
                data += firstLayer.getNeironData(firstLayerIndex)*linksFS.getWight(firstLayerIndex, index);
            }
            secondLayer.countNeiron(index, activate(data/FIRST_LAYER_NEIRONS_COUNT));
           // System.out.println(" on index "+index+" data "+data+" activate data "+activate(data));
        }
    }

    private void countLastLayer() {
        float data;
       // System.out.println("count last layer");
        for (int index = 0; index < LAST_LAYER_NEIRONS_COUNT; index++) {
            data = 0;

            for (int secondLayerIndex = 0; secondLayerIndex < SECOND_LAYER_NEIRONS_COUNT; secondLayerIndex++) {
                data += secondLayer.getNeironData(secondLayerIndex)*linksSE.getWight(secondLayerIndex, index);

            }

            lastLayer.countNeiron(index, activate(data/SECOND_LAYER_NEIRONS_COUNT));
          //  System.out.println(" on index "+index+" data "+data+" activate data "+activate(data));
        }
    }

    private Coordinates checkMaxLastNeiron(){
       // System.out.println("start check dessigion");
        float maxData =0;
        int maxIndex = 0;
        int index;
        for (index = 0; index < LAST_LAYER_NEIRONS_COUNT; index++) {
            if ((lastLayer.getNeironData(index)>maxData)&&(field.field[index/FIELD_SIZE][index%FIELD_SIZE]==FREE)){
                //System.out.println("on x "+index/FIELD_SIZE+" y "+index%FIELD_SIZE+" field is "+field.field[maxIndex/FIELD_SIZE][maxIndex%FIELD_SIZE]);

                maxData = lastLayer.getNeironData(index);
                maxIndex = index;
            }
        }
        //System.out.println("returned coordinates is x "+maxIndex/FIELD_SIZE+" y "+maxIndex%FIELD_SIZE);
        return new Coordinates(maxIndex/FIELD_SIZE, maxIndex%FIELD_SIZE);
    }
}
