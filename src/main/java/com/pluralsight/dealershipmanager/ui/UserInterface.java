package com.pluralsight.dealershipmanager.ui;

import com.pluralsight.dealershipmanager.data.DealershipFileManager;
import com.pluralsight.dealershipmanager.model.Dealership;
import com.pluralsight.dealershipmanager.model.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private Dealership dealership;

    private void displayVehicles(List<Vehicle> vehicleList) {
        //I think I will need to read all lines on file to display all vehicles to the console
        //or just print vehicle list
        //print vehicleList
        if (vehicleList.isEmpty()) {
            System.out.println("List is empty");
            return;//return means stop sometimes
        }
        for (Vehicle vehicle : vehicleList) {
            System.out.printf(vehicle.toString());
        }

    }

    private void init() {
        // loads the dealership with the file manager
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
//        dealershipFileManager.getDealership();
        this.dealership = dealershipFileManager.getDealership();
    }

    public UserInterface() {

    }

    public void display() {
//        Scanner scanner = new Scanner(System.in);


        init();
        System.out.println("This is the " + dealership.getName() + " inventory management system");
        while (true) {
            System.out.println("Select a number from following options:");
            System.out.println("\t1 - Find vehicles by price");
            System.out.println("\t2 - Find vehicles by make/model");
            System.out.println("\t3 - Find vehicles by year");
            System.out.println("\t4 - Find vehicles by color");
            System.out.println("\t5 - Find vehicles by mileage range");
            System.out.println("\t6 - Find vehicles by type");
            System.out.println("\t7 - View all vehicles");
            System.out.println("\t8 - Add a vehicle");
            System.out.println("\t9 - Remove a vehicle");
            System.out.println("\t0 - Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> {
                    System.out.println("goodbye");
                    System.exit(0);
                }
            }
        }
    }

    public void processGetByPriceRequest() {
//  search for a vehicle in file on price. if price in file matches user input print entire vehicle line.
        //no bc file is read in init and turns lines into objects inside dealership object
//        processGetByPriceRequest() doesn't check prices or print lines itself:
//        In OOP, each method has a clear job
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter price min");
        BigDecimal min = scanner.nextBigDecimal();

        System.out.println("Enter price max");
        BigDecimal max = scanner.nextBigDecimal();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);

//        displayVehicles() helper method
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter make");
        String make = scanner.nextLine();

        System.out.println("Enter model");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.println("Enter min");
        int min = scanner.nextInt();

        System.out.println("Enter max");
        int max = scanner.nextInt();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }


    public void processGetByColorRequest() {
        System.out.println("Enter color");
        String color = scanner.nextLine().trim();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }


    public void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter min mileage");
        int min = scanner.nextInt();

        System.out.println("Enter max mileage");
        int max = scanner.nextInt();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);

    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter type");
        String type = scanner.nextLine().trim();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);

    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
        //hand allVehicles to displayVehicles
    }

    public void processRemoveVehicleRequest() {
        System.out.println("Enter vin");
        int vin = scanner.nextInt();
        //if vehicle or line in file matches the vin it gets removed
        Vehicle vehicleToRemove = null; // Holds the matching Vehicle once found, or stays null if not found
        for (Vehicle vehicle : dealership.getAllVehicles()) { // Loops through all vehicles currently in the dealership
            if (vehicle.getVin() == vin) { // Checks if this vehicle's VIN matches the entered VIN
                vehicleToRemove = vehicle; // Saves a reference to the matching vehicle
                break; // Stops searching once a match is found
            }
        }

        if (vehicleToRemove != null) { // Ensures a matching vehicle was found before removing and saving
            dealership.removeVehicle(vehicleToRemove); // Removes the vehicle from the dealership's in-memory inventory
            System.out.println("Vehicle removed successfully."); // Confirms the removal to the user

            DealershipFileManager fileManager = new DealershipFileManager(); // Creates a file manager to persist the change
            fileManager.saveDealership(dealership); // Rewrites inventory.csv without the removed vehicle
        } else { // Runs when no vehicle in inventory matches the entered VIN
            System.out.println("No vehicle found with VIN " + vin); // Informs the user that nothing was removed
        }
    }

    public void processAddVehicleRequest() {
    //i want to add a vehicle to inventory through the dealership
    //I can prompt user then somehow show the added vehicle by calling the method that formats the vehicle with pipes

        System.out.println("Enter vin");
        int vin = scanner.nextInt();
        System.out.println("Enter year");
        int year = scanner.nextInt();
        System.out.println("Enter odometer");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter make");
        String make = scanner.nextLine();
        System.out.println("Enter model");
        String model = scanner.nextLine();
        System.out.println("Enter color");
        String color = scanner.nextLine();
        System.out.println("Enter type");
        String type = scanner.nextLine();
        System.out.println("Enter price");
        BigDecimal price = scanner.nextBigDecimal();
        //im missing something to initialize vehicle but i think i also need to somehow
//   add details of how many characters to type for each section?
        Vehicle vehicle = new Vehicle(vin,year,odometer,make,model,color,type,price);
        dealership.addVehicle(vehicle);

        List<Vehicle>newVehicle= new ArrayList<>();
        newVehicle.add(vehicle); // Puts the newly created vehicle into that list

//        displayVehicles(List<Vehicle> vehicle);
        displayVehicles(newVehicle);// Passes the list to displayVehicles so it formats and prints the vehicle
//        this.dealership.addVehicle(vehicle);
        DealershipFileManager fileManager = new DealershipFileManager(); // Creates a DealershipFileManager instance to handle file writing
        fileManager.saveDealership(dealership); // Writes the updated dealership and inventory back out to inventory.csv
    }

    public void appendtoCSV(String record) {
        try {


            FileWriter fileWriter = new FileWriter("inventory.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(record);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
