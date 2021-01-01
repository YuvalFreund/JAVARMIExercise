import interfaces.IFlightClient;
import model.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class editFlight {
    IFlightClient client;
    public HashMap<String, JTextField> values;

    public void main(String[] args, Flight flight, IFlightClient client) {
        this.client = client;
        values = new HashMap<>();
        JFrame frame = new JFrame(args[1]);
        frame.setSize(600,600);
        JPanel mainLeftPanel = new JPanel();
        JPanel mainRightPanel = new JPanel();
        mainLeftPanel.setLayout(new BoxLayout(mainLeftPanel, BoxLayout.Y_AXIS));
        mainRightPanel.setLayout(new BoxLayout(mainRightPanel, BoxLayout.Y_AXIS));
        JButton add = new JButton(args[1]);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight res = getFlightFromInput();
                try {
                    client.flightUploadFromClient(res, false);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
        doAllLeftEntries(mainLeftPanel);
        doAllRightEntries(mainRightPanel);
        if(flight!= null){
            doFillwithFlightVals(flight);
        }
        JPanel main = new JPanel();
        main.add(BorderLayout.SOUTH, add);
        main.add(BorderLayout.WEST, mainLeftPanel);
        main.add(BorderLayout.EAST, mainRightPanel);
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void doAllLeftEntries(JPanel panel){
        JPanel IATACode = textfieldAndLabel("IATA code:");
        JPanel airlineName = textfieldAndLabel("Operating Airline");
        JPanel modelName = textfieldAndLabel("Aircraft Model Name:");
        JPanel flightNumber = textfieldAndLabel("Tracking number");
        JPanel departureAirport = textfieldAndLabel("Departure Airport");
        JPanel originDate = textfieldAndLabel("Origin Date");
        JPanel departureScheduledDate = textfieldAndLabel("Scheduled Departure");
        JPanel departureTerminal = textfieldAndLabel("Departure terminal");
        JPanel departureGates = textfieldAndLabel("Departure gates");
        JPanel departureDate = textfieldAndLabel("Estimated Departure");
        JPanel checkInLocation = textfieldAndLabel("Check-in Location");
        JPanel checkInCounter = textfieldAndLabel("Check-in counter");
        JPanel checkInStart = textfieldAndLabel("Check-in start");
        JPanel checkInEnd = textfieldAndLabel("Check-in end");
        panel.add(IATACode);
        panel.add(airlineName);
        panel.add(modelName);
        panel.add(flightNumber);
        panel.add(departureAirport);
        panel.add(originDate);
        panel.add(departureScheduledDate);
        panel.add(departureTerminal);
        panel.add(departureGates);
        panel.add(departureDate);
        panel.add(checkInLocation);
        panel.add(checkInCounter);
        panel.add(checkInStart);
        panel.add(checkInEnd);
    }
    public void doAllRightEntries(JPanel panel){
        JPanel arrivalAirport = textfieldAndLabel("Arrival Airport");
        JPanel arrivalTerminal = textfieldAndLabel("Arrival Terminal");
        JPanel arrivalScheduledDate = textfieldAndLabel("Scheduled Arrival");
        JPanel arrivalGates = textfieldAndLabel("Arrival Gates");
        JPanel estimatedArrival = textfieldAndLabel("Estimated arrival");
        JPanel flightStatus = textfieldAndLabel("flight status");
        panel.add(arrivalAirport);
        panel.add(arrivalTerminal);
        panel.add(arrivalScheduledDate);
        panel.add(arrivalGates);
        panel.add(estimatedArrival);
        panel.add(flightStatus);
    }
    public JPanel textfieldAndLabel(String header){
        JPanel panel = new JPanel();
        JLabel lab0el = new JLabel(header);
        JTextField tf = new JTextField(20);
        values.put(header,tf);
        panel.add(label);
        panel.add(tf);
        return panel;
    }
    Flight getFlightFromInput(){
        String IATACode = values.get("IATA code:").getText();
        String airlineName = values.get("Operating Airline").getText();
        String modelName = values.get("Aircraft Model Name:").getText();
        String flightNumber = values.get("Tracking number").getText();
        String departureAirport= values.get("Departure Airport").getText();
        String originDate= values.get("Origin Date").getText();
        String departureScheduledDate= values.get("Scheduled Departure").getText();
        String departureTerminal= values.get("Departure terminal").getText();
        String departureGates= values.get("Departure gates").getText();
        String checkInLocation= values.get("Check-in Location").getText();
        String checkInCounter= values.get("Check-in counter").getText();
        String checkInStart= values.get("Check-in start").getText();
        String checkInEnd= values.get("Check-in end").getText();
        String arrivalAirport = values.get("Arrival Airport").getText();
        String arrivalTerminal = values.get("Arrival Terminal").getText();
        String arrivalScheduledDate = values.get("Scheduled Arrival").getText();
        String arrivalGates = values.get("Arrival Gates").getText();
        String estimatedArrival = values.get("Estimated arrival").getText();
        String flightStatus = values.get("flight status").getText();
        return new Flight(IATACode,airlineName,modelName,flightNumber,departureAirport,arrivalAirport,originDate,arrivalScheduledDate,arrivalTerminal,arrivalGates,arrivalScheduledDate,departureScheduledDate,departureTerminal,departureGates,checkInLocation,checkInCounter,checkInStart,checkInEnd,flightStatus,estimatedArrival);
    }
    void doFillwithFlightVals(Flight flight) {
        values.get("IATA code:").setText(flight.getIATACode());
        values.get("Operating Airline").setText(flight.getAirlineName());
        values.get("Aircraft Model Name:").setText(flight.getModelName());
        values.get("Tracking number").setText(flight.getFlightNumber());
        values.get("Departure Airport").setText(flight.getDepartureAirport());
        values.get("Origin Date").setText(flight.getOriginDate());
        values.get("Scheduled Departure").setText(flight.getDepartureScheduledDate());
        values.get("Departure terminal").setText(flight.getDepartureTerminal());
        values.get("Departure gates").setText(flight.getDepartureGates());
        values.get("Check-in Location").setText(flight.getCheckInLocation());
        values.get("Check-in counter").setText(flight.getCheckInCounter());
        values.get("Check-in start").setText(flight.getCheckInStart());
        values.get("Check-in end").setText(flight.getCheckInEnd());
        values.get("Arrival Airport").setText(flight.getArrivalAirport());
        values.get("Arrival Terminal").setText(flight.getArrivalTerminal());
        values.get("Scheduled Arrival").setText(flight.getArrivalDate());
        values.get("Arrival Gates").setText(flight.getArrivalGates());
        values.get("Estimated arrival").setText(flight.getArrivalScheduledDate());
        values.get("flight status").setText(flight.getFlightStatus());
    }
}
