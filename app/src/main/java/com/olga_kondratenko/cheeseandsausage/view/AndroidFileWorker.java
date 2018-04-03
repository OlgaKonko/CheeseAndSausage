package com.olga_kondratenko.cheeseandsausage.view;

import android.content.Context;
import android.os.Environment;

import com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.Link;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AndroidFileWorker implements FileWorker{
    public static Context context;

    @Override
    public Link[][] readFromFile (String fileName) throws IOException, ClassNotFoundException {
        Link[][] links;
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            links = (Link[][]) is.readObject();
            is.close();

            return links;
        }

    @Override
    public void saveLinks(Link layersLinks[][], String fileName){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(layersLinks);
            os.close();
        } catch (IOException w) {
            w.printStackTrace();
        }
    }

    @Override
    public void createFile(String fileName) {

    }
}
