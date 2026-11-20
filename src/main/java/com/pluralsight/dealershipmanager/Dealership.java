package com.pluralsight.dealershipmanager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership() {
        this.inventory=new ArrayList<Vehicle>();
    }

    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            BigDecimal price = vehicle.getPrice();
            if (price.compareTo(min) >= 0 && price.compareTo(max) <= 0) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public List<Vehicle>getVehiclesMakeModel(String make, String model){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            String make1 = vehicle.getMake();
            String model1 = vehicle.getModel();
            if (make1.equalsIgnoreCase(make) && model1.equalsIgnoreCase(model)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public List<Vehicle>getVehiclesByYear(int min, int max){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            int year = vehicle.getYear();
            if (year >= min && year <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public List<Vehicle>getVehiclesByColor(String color){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public List<Vehicle>getVehiclesByMileage(int min, int max){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            int mileage = vehicle.getOdometer();
            if (mileage >= min && mileage <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        // Create a new empty list to hold matching vehicles (avoids returning null if nothing matches)
        List<Vehicle> matches = new ArrayList<>();

        // Loop through every Vehicle object currently stored in inventory
        for (Vehicle vehicle : inventory) {
            // Check if this vehicle's type matches the user's input, ignoring upper/lowercase
            if (vehicle.getType().equalsIgnoreCase(vehicleType)) {
                // Add the matching vehicle to our results list
                matches.add(vehicle);
            }
        }

        // Send the list of matching vehicles back to the caller (e.g., UserInterface)
        return matches;
    }

    public void removeVehicle(Vehicle vehicle){

        this.inventory.remove(vehicle);
    }

    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory=new ArrayList<Vehicle>();
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
}
