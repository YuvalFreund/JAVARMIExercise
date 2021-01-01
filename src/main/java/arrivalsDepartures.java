import interfaces.IFlightClient;
import model.Flight;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class arrivalsDepartures {
    IFlightClient client;
    ArrayList<Flight> flights;
    JFrame frame;
    DefaultTableModel table;
    JTable jtable;
    JScrollPane scrollPane;
    int clickedVal = Integer.MAX_VALUE;
    public arrivalsDepartures(ArrayList<Flight> flights,IFlightClient client){
        this.flights = flights;
        this.client = client;
        frame =new JFrame();
        frame.addWindowListener(new WindowEventHandler());
        String [][] flightDetails = new String[flights.size()][];
        int i=0;
        for (Flight f: this.flights){
            flightDetails[i] = f.flightValues();
            i++;
        }
        String [] columnNames = Flight.flightColumnNames();
        table = new DefaultTableModel(flightDetails,columnNames);
        jtable = new JTable(table);
        scrollPane = new JScrollPane(jtable);
        jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                clickedVal = jtable.getSelectedRow();
                System.out.println(jtable.getSelectedRow());
            }
        });
        JPanel panel = new JPanel();
        JButton newButton = new JButton("new");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFlight ef = new editFlight();
                String [] args ={"New flight","ADD NEW FLIGHT"};

                ef.main(args,null,client);
            }
        });
        JButton editButton = new JButton("edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight chosen = flights.get(clickedVal);
                editFlight ef = new editFlight();
                String [] args ={"Edit flight","UPDATE FLIGHT"};
                ef.main(args,chosen,client);
            }
        });
        JButton removeButton = new JButton("remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("removeButton");
                if(clickedVal!= Integer.MAX_VALUE) {
                    Flight toBeRemoved = flights.get(clickedVal);
                    try {
                        client.flightUploadFromClient(toBeRemoved, true);
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                }
                clickedVal = Integer.MAX_VALUE;
            }
        });
        panel.add(newButton);panel.add(editButton); panel.add(removeButton);
        frame.add(BorderLayout.SOUTH, panel);
        frame.add(BorderLayout.NORTH, scrollPane);
    }
    public void main(){
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
    }
    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            try{
                client.logoutClient();
                frame.dispose();
                System.exit(1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    void changeFlight(Flight flight, boolean deleted) {
        int i = 0;
        for (Flight f : this.flights) {
            if (flight.getFlightNumber().equals(f.getFlightNumber())) break;
            i++;
        }
        if(i<this.flights.size()) {
            this.flights.remove(i);
            ((DefaultTableModel) jtable.getModel()).removeRow(i);
        }
        if(!deleted) {
            this.flights.add(flight);
            String[] data = flight.flightValues();
            ((DefaultTableModel) jtable.getModel()).addRow(data);
        }
    }
}
