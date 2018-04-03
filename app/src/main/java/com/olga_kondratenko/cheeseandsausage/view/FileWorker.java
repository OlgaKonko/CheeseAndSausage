package com.olga_kondratenko.cheeseandsausage.view;

import com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.Link;

import java.io.IOException;

public interface FileWorker {

    Link[][] readFromFile (String fileName) throws IOException, ClassNotFoundException;
    void saveLinks(Link layersLinks[][], String fileName);
    void createFile(String fileName);
}