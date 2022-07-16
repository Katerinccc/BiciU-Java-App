package com.sofka.Bicycle;

import com.sofka.entities.Bicycle;
import com.sofka.entities.BicycleType;
import com.sofka.util.Utility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateBicycle {

    private Utility utility = new Utility();


    public ArrayList<Bicycle> createBicycles(String externalFile){

        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<Bicycle> bicycles = new ArrayList<>();

        try {

            fileReader = new FileReader(externalFile);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null){
                addNewBicycle(bicycles, line);
            }

            bufferedReader.close();
            fileReader.close();

        }catch (Exception exception){
            utility.displayData("File upload failed. " +  exception.getMessage());
        }

        return bicycles;
    }

    private void addNewBicycle(ArrayList<Bicycle> bicycles, String line) {
        List<String> newBicycle;
        newBicycle = Arrays.stream(line.split(";")).toList();
        bicycles.add(new Bicycle(newBicycle.get(0),
                assignBicycleType(newBicycle.get(1)) ,
                newBicycle.get(2) ,
                Boolean.parseBoolean(newBicycle.get(3))));
    }

    private BicycleType assignBicycleType(String readData){
        if (readData.equalsIgnoreCase("Road")){
            return BicycleType.ROAD;
        }
        return BicycleType.MOUNTAIN;
    }


}
