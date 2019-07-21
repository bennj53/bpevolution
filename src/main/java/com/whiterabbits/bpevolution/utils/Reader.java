package com.whiterabbits.bpevolution.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static String readFile(String filePath){

        File file = new File(filePath);
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }

        }catch (IOException exception){
            exception.getMessage();
        }

        return null;
    }
}
