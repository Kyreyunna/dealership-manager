package com.pluralsight.dealershipmanager.data;

import com.pluralsight.dealershipmanager.model.Dealership;
import com.pluralsight.dealershipmanager.model.Vehicle;

import java.io.*;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class DealershipFileManager {
    public Dealership getDealership() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"));

            boolean isHeader = true;
            String line;
            Dealership dealership = new Dealership();
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitValues = line.split(Pattern.quote("|"));

                if (isHeader) {
//                   Does this go here? dealership = new Dealership(splitValues[0], splitValues[1], splitValues[2]);
                    dealership.setName(splitValues[0]);
                    dealership.setAddress(splitValues[1]);
                    dealership.setPhone(splitValues[2]);
                    isHeader = false;
                } else {
                    //deal with each property of vehicle
                    int vin = Integer.parseInt(splitValues[0]);
                    int year = Integer.parseInt(splitValues[1]);
                    String make = splitValues[2];
                    String model = splitValues[3];
                    String type = splitValues[4];
                    String color = splitValues[5];
                    int odometer = Integer.parseInt(splitValues[6]);
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(splitValues[7]));

                    dealership.addVehicle(new Vehicle(vin, year, odometer, make, model, color, type, price));
                }

            }
            return dealership;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDealership(Dealership dealership) {
//Save rewrites the whole file: the dealership line, then one | line per vehicle.
// Without save, a new vehicle is gone when the app restarts.
        //this is going to rewrite entire file
        //overwrite update file later

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv"));

            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone()); // Writes the dealership header line separated by pipes
            bufferedWriter.newLine(); // Moves to the next line after the dealership header

            for (Vehicle vehicle : dealership.getAllVehicles()) { // Loops through every vehicle currently stored in the dealership
                bufferedWriter.write( // Writes the next vehicle's pipe-delimited line to the file
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice()
                );
                bufferedWriter.newLine(); // Moves to a new line after each vehicle entry

        }
            bufferedWriter.close(); // Flushes and closes the writer so changes are saved to disk cleanly
        }
            catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

