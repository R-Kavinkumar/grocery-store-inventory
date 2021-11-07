package com.example.ksr_glocery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {






    @FXML
    private ComboBox d;
    @FXML
    private ComboBox f;
    @FXML
    private ComboBox r;
    @FXML
    private ComboBox s;
    @FXML
    private ComboBox t;
    @FXML
    private Label Total ;
    @FXML
    private TextField RICE;
    @FXML
    private CheckBox rice;
    @FXML
    private TextField DHALL;
    @FXML
    private CheckBox dhall;
    @FXML
    private TextField SUGAR;
    @FXML
    private CheckBox tea;
    @FXML
    private TextField TEA;
    @FXML
    private CheckBox flour;
    @FXML
    private TextField FLOUR;
    @FXML
    private CheckBox sugar;
    @FXML
    private TableView <data> table;
    @FXML
    private TableColumn <data,String> total;
    @FXML
    private TableColumn <data,String> rate;
    @FXML
    private TableColumn <data,String> qty;
    @FXML
    private TableColumn <data,String> items;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        d.getItems().add("kg");
        d.getItems().add("grams");
        f.getItems().add("kg");
        f.getItems().add("grams");
        s.getItems().add("kg");
        s.getItems().add("grams");
        r.getItems().add("kg");
        r.getItems().add("grams");
        t.getItems().add("kg");
        t.getItems().add("grams");
        s.setValue("kg");
        d.setValue("kg");
        f.setValue("kg");
        t.setValue("kg");
        r.setValue("kg");
        total.setCellValueFactory(new PropertyValueFactory<data,String>("total"));
        rate.setCellValueFactory(new PropertyValueFactory<data,String>("rate"));
        qty.setCellValueFactory(new PropertyValueFactory<data,String>("qty"));
        items.setCellValueFactory(new PropertyValueFactory<data,String>("items"));
        SUGAR.setPromptText("43/kg");
        DHALL.setPromptText("43/kg");
        TEA.setPromptText("498/kg");
        FLOUR.setPromptText("40/kg");
        RICE.setPromptText("50/kg");
    }

    @FXML
    public void ok(ActionEvent actionEvent) {





        double grandtotal = 0;


        String val_tea = null;
        String qty_tea_str = null;
        if (tea.isSelected()) {
            double qty_tea, rate_tea = 498;
            qty_tea = Double.parseDouble(TEA.getText());
            qty_tea_str = String.valueOf(qty_tea) + " g";
            if (t.getSelectionModel().getSelectedItem() == "kg") {
                qty_tea = qty_tea;
                qty_tea_str = String.valueOf(qty_tea) + "kg";
            } else {
                qty_tea = qty_tea / 1000;
            }
            val_tea = String.valueOf(rate_tea * qty_tea);
            grandtotal = grandtotal + rate_tea * qty_tea;
        }
        String val_sugar = null;
        String qty_sugar_str = null;
        if (sugar.isSelected()) {
            double qty_sugar, rate_sugar = 43;
            qty_sugar = Double.parseDouble(SUGAR.getText());
            qty_sugar_str = String.valueOf(qty_sugar) + " g";
            if (s.getSelectionModel().getSelectedItem() == "kg") {
                qty_sugar=qty_sugar;
                qty_sugar_str = String.valueOf(qty_sugar) + "kg";
            } else {
                qty_sugar = qty_sugar / 1000;

            }
            val_sugar = String.valueOf(rate_sugar * qty_sugar);
            grandtotal = grandtotal + rate_sugar * qty_sugar;
        }
        String val_rice = null;
        String qty_rice_str = null;
        if (rice.isSelected()) {
            double qty_rice, rate_rice = 50;
            qty_rice = Double.parseDouble(RICE.getText());
            qty_rice_str = String.valueOf(qty_rice) + " g";
            if (r.getSelectionModel().getSelectedItem() == "kg") {
                qty_rice_str = String.valueOf(qty_rice) + "kg";
            } else {
                qty_rice = qty_rice / 1000;
            }
            val_rice = String.valueOf(rate_rice * qty_rice);
            grandtotal = grandtotal + rate_rice * qty_rice;

        }
        String val_dhall = null;
        String qty_dhall_str = null;
        if (dhall.isSelected()) {
            double qty_dhall, rate_dhall = 43;
            qty_dhall = Double.parseDouble(DHALL.getText());
            qty_dhall_str = String.valueOf(qty_dhall) + " g";
            if (d.getSelectionModel().getSelectedItem() == "kg") {
                qty_dhall = qty_dhall;
                qty_dhall_str = String.valueOf(qty_dhall) + "kg";
            } else {
                qty_dhall = qty_dhall / 1000;
            }
            val_dhall = String.valueOf(rate_dhall * qty_dhall);
            grandtotal = grandtotal + rate_dhall * qty_dhall;

        }
        String val_flour = null;
        String qty_flour_str = null;
        if (flour.isSelected()) {
            double rate_flour = 40;
            double qty_flour = Double.parseDouble(FLOUR.getText());
            qty_flour_str = String.valueOf(qty_flour) + " g";
            if (f.getSelectionModel().getSelectedItem() == "kg") {
                qty_flour = qty_flour;
                qty_flour_str = String.valueOf(qty_flour) + "kg";
            } else {
                qty_flour = qty_flour / 1000;
            }
            val_flour = String.valueOf(rate_flour * qty_flour);
            grandtotal = grandtotal + rate_flour * qty_flour;

        }


        ObservableList<data> list = FXCollections.observableArrayList(
                new data("SUGAR", "43.00", qty_sugar_str, val_sugar),
                new data("TEA", "498.00", qty_tea_str, val_tea),
                new data("FLOUR", "40.00", qty_flour_str, val_flour),
                new data("RICE", "50,00", qty_rice_str, val_rice),
                new data("DHALL", "43.00", qty_dhall_str, val_dhall)
        );
        table.setItems(list);
        Total.setText(String.valueOf(grandtotal));

        String query="insert into ksr_glocery.data(sugar,tea,rice,dhall,flour,total) values("+"'"+qty_sugar_str+"'"+","+"'"+qty_tea_str+"'"+","+"'"+qty_flour_str+"'"+","+"'"+qty_rice_str+"'"+","+"'"+qty_dhall_str+"'"+","+String.valueOf(grandtotal)+")";
        System.out.println(query);
        try{
            Statement s;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksr_glocery", "root", ".....Kkr822");
            s=connection.createStatement();
            s.executeUpdate(query);
        }
        catch(Exception e){

            e.printStackTrace();

        }
    }


}





