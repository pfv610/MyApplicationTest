package edu.utsa.cs3443.ebookreadertraining.model;

import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import edu.utsa.cs3443.ebookreadertraining.BookReader;

public class TxtBook extends EBook {


    public TxtBook(String title, String fileName){
        super(title, fileName);
    }

    @Override
    public String readBook(BookReader bookReader) {

        AssetManager manager = bookReader.getAssets();
        String page = "";
        Scanner scan;
        try {
            InputStream in = manager.open(getPath());
            scan = new Scanner(in);
            String line;
            while( scan.hasNextLine() ) {
                line = scan.nextLine();
                page = page + line;
            }
            return page;
        } catch( FileNotFoundException e ) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch( IOException e ) {
            System.out.println("IO Exception!");
            e.printStackTrace();
        }
        return null;
    }
}

