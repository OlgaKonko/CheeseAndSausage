package com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron;

import com.olga_kondratenko.cheeseandsausage.constants.Environments;
import com.olga_kondratenko.cheeseandsausage.env.Environment;
import com.olga_kondratenko.cheeseandsausage.view.AndroidFileWorker;
import com.olga_kondratenko.cheeseandsausage.view.FileWorker;

import java.util.Random;

public class Links {
    int enterLayerSize;
    int endLayerSize;
    public Link layersLinks[][];
    String fileName;
    Random random;
    AndroidFileWorker fileWorker;

    public Links(int enterLayerSize, int endLayerSize, String iiName, String linksName){
        this.enterLayerSize=enterLayerSize;
        this.endLayerSize = endLayerSize;
        layersLinks = new Link[enterLayerSize][endLayerSize];
        random = new Random();
        this.fileName = iiName+linksName;
        if (Environment.env == Environments.ANDROID)
            fileWorker = new AndroidFileWorker();
        if (!readFromFile()){
            createNewLinks();
        }

    }

    public Links(int enterLayerSize, int endLayerSize, String iiName, String linksName, boolean createNew){
        this.enterLayerSize=enterLayerSize;
        this.endLayerSize = endLayerSize;
        layersLinks = new Link[enterLayerSize][endLayerSize];
        random = new Random();
        this.fileName = iiName+linksName;
        if (Environment.env == Environments.ANDROID)
            fileWorker = new AndroidFileWorker();
        if (!createNew){
            if (!readFromFile()){
                createNewLinks();
            }}
            else
            createNewLinks();

    }

    public Links(Links momLinks, Links dadLinks) {
        enterLayerSize = momLinks.enterLayerSize;
        endLayerSize = momLinks.endLayerSize;
        layersLinks = new Link[enterLayerSize][endLayerSize];
        random = new Random();
        fileName = momLinks.fileName;
        fileWorker = momLinks.fileWorker;
        createNewLinks();

        for (int fIndex = 0; fIndex < enterLayerSize; fIndex++)
            for (int sIndex = 0; sIndex < endLayerSize; sIndex++) {
            if (random.nextBoolean())
                layersLinks[fIndex][sIndex] = momLinks.layersLinks[fIndex][sIndex];
            else
                layersLinks[fIndex][sIndex] = dadLinks.layersLinks[fIndex][sIndex];
            }
    }

    public float getWight(int enterLayerIndex, int endLayerIndex){
        return layersLinks[enterLayerIndex][endLayerIndex].weight;
    }
    public void changeLinks(){
        float correction;
        for (int fIndex=0; fIndex<enterLayerSize; fIndex++)
            for (int sIndex=0; sIndex<endLayerSize; sIndex++){
            correction = 0.1f*random.nextFloat();
            if (random.nextBoolean()){
                correction=-correction;
            }
            if ((layersLinks[fIndex][sIndex].weight +correction>=1)||(layersLinks[fIndex][sIndex].weight +correction<=0))
                layersLinks[fIndex][sIndex].weight -= correction;
            else
                layersLinks[fIndex][sIndex].weight += correction;
            }
    }

    public void crossLinks(Links pareLinks){
        float correction;
        for (int fIndex=0; fIndex<(enterLayerSize*endLayerSize/2); fIndex++)
            for (int sIndex=0; sIndex<endLayerSize; sIndex++){

            }
    }

    public void createNewLinks(){
        //System.out.println(iiName+" "+linksName+":");
        for (int fIndex=0; fIndex<enterLayerSize; fIndex++)
            for (int sIndex=0; sIndex<endLayerSize; sIndex++){
                layersLinks[fIndex][sIndex] = new Link(random.nextFloat());
                //System.out.print(layersLinks[fIndex][sIndex].weight+" ");

        }
       // System.out.println();
        //System.out.println();
      //  createFile();
      //  saveLinks();
    }

    public void saveLinks(){
        try {
            System.out.println("try save links file");
            fileWorker.saveLinks(layersLinks, fileName);
            System.out.println("save to file "+fileName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean readFromFile(){
        boolean canDoIt=false;
        try {
            System.out.println("try read from file");
            layersLinks = fileWorker.readFromFile(fileName);
            canDoIt = true;
System.out.println("read from file "+fileName);
        } catch (Exception e) {
         e.printStackTrace();
        }
        return canDoIt;
    }
}
