package model;

import java.io.Serializable;

public class Flight implements Serializable {
    String IATACode;
    String airlineName;
    String modelName;
    String flightNumber;
    String departureAirport;
    String originDate;
    String arrivalAirport;
    String arrivalScheduledDate;
    String arrivalTerminal;
    String arrivalGates;
    String arrivalDate;
    String departureScheduledDate;
    String departureTerminal;
    String departureGates;
    String checkInLocation;
    String checkInCounter;
    String checkInStart;
    String checkInEnd;
    String flightStatus;
    String estimatedArrival;

    public Flight(String IATACode, String airlineName, String modelName, String flightNumber, String departureAirport, String arrivalAirport, String originDate, String arrivalScheduledDate, String arrivalTerminal, String arrivalGates, String arrivalDate, String departureScheduledDate, String departureTerminal, String departureGates, String checkInLocation, String checkInCounter, String checkInStart, String checkInEnd, String flightStatus, String estimatedArrival) {
        this.IATACode = IATACode;
        this.airlineName = airlineName;
        this.modelName = modelName;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.originDate = originDate;
        this.arrivalScheduledDate = arrivalScheduledDate;
        this.arrivalTerminal = arrivalTerminal;
        this.arrivalGates = arrivalGates;
        this.arrivalDate = arrivalDate;
        this.departureScheduledDate = departureScheduledDate;
        this.departureTerminal = departureTerminal;
        this.departureGates = departureGates;
        this.checkInLocation = checkInLocation;
        this.checkInCounter = checkInCounter;
        this.checkInStart = checkInStart;
        this.checkInEnd = checkInEnd;
        this.flightStatus = flightStatus;
        this.estimatedArrival = estimatedArrival;
    }

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getOriginDate() {
        return originDate;
    }

    public void setOriginDate(String originDate) {
        this.originDate = originDate;
    }

    public String getArrivalScheduledDate() {
        return arrivalScheduledDate;
    }

    public void setArrivalScheduledDate(String arrivalScheduledDate) {
        this.arrivalScheduledDate = arrivalScheduledDate;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public String getArrivalGates() {
        return arrivalGates;
    }

    public void setArrivalGates(String arrivalGates) {
        this.arrivalGates = arrivalGates;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureScheduledDate() {
        return departureScheduledDate;
    }

    public void setDepartureScheduledDate(String departureScheduledDate) {
        this.departureScheduledDate = departureScheduledDate;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getDepartureGates() {
        return departureGates;
    }

    public void setDepartureGates(String departureGates) {
        this.departureGates = departureGates;
    }


    public String getCheckInLocation() {
        return checkInLocation;
    }

    public void setCheckInLocation(String checkInLocation) {
        this.checkInLocation = checkInLocation;
    }

    public String getCheckInCounter() {
        return checkInCounter;
    }

    public void setCheckInCounter(String checkInCounter) {
        this.checkInCounter = checkInCounter;
    }

    public String getCheckInStart() {
        return checkInStart;
    }

    public void setCheckInStart(String checkInStart) {
        this.checkInStart = checkInStart;
    }

    public String getCheckInEnd() {
        return checkInEnd;
    }

    public void setCheckInEnd(String checkInEnd) {
        this.checkInEnd = checkInEnd;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Flight(){

    }
    static public String[] flightColumnNames() {
        return new String[]{"IATA Code","Airline Name","Model name","Flight Number","Departure Airport","Origin Date","Arrival Airport","Arrival Scheduled Date","Arrival Terminal","Arrival Gates","Arrival Date","Departure Scheduled Date",
                "Departure Terminal","Departure Gates","CheckIn Location"," CheckIn Counter","CheckIn Start","CheckInEnd","Flight Status","Estimated Arrival"};
    }
    public String[] flightValues(){
        return new String[]{IATACode,airlineName,modelName,flightNumber,departureAirport,originDate,arrivalAirport,arrivalScheduledDate,
                arrivalTerminal,arrivalGates,arrivalDate,departureScheduledDate,departureTerminal,departureGates,checkInLocation,checkInCounter,checkInStart,checkInEnd,flightStatus,estimatedArrival};
    }
}

