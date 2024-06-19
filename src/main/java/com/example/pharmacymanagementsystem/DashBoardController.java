package com.example.pharmacymanagementsystem;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class DashBoardController {

    @FXML
    private TextField AddPurchase_amount;

    @FXML
    private Button AddPurchase_buy;

    @FXML
    private TextField AddPurchase_buyerContact;

    @FXML
    private TextField AddPurchase_buyerName;

    @FXML
    private Button AddPurchase_cancel;

    @FXML
    private TextField AddPurchase_drugName;

    @FXML
    private AnchorPane AddPurchase_form;

    @FXML
    private TextField AddPurchase_price;

    @FXML
    private TextField AddPurchase_quantity;

    @FXML
    private TextField AddPurchase_total;

    @FXML
    private Button Delete_cancel;

    @FXML
    private Button Delete_deleteBtn;

    @FXML
    private TextField Delete_drugName;

    @FXML
    private AnchorPane Delete_form;

    @FXML
    private Button Drugs_add;

    @FXML
    private Button Drugs_delete;

    @FXML
    private AnchorPane Drugs_form;

    @FXML
    private TextField Drugs_search;

    @FXML
    private Button Drugs_tab;

    @FXML
    private TableView<DrugsData> Drugs_tableView;

    @FXML
    private TableColumn<DrugsData, String> Drugs_tableView_col_drugName;

    @FXML

    private TableColumn<DrugsData, Double> Drugs_tableView_col_price;

    @FXML
    private TableColumn<DrugsData, Integer> Drugs_tableView_col_quantity;

    @FXML
    private TableColumn<DrugsData, Integer> Drugs_tableView_col_supplierID;

    @FXML
    private Button Drugs_update;

    @FXML
    private Button New_add;

    @FXML
    private Button New_cancel;

    @FXML
    private TextField New_drugName;

    @FXML
    private AnchorPane New_form;

    @FXML
    private TextField New_price;

   /* @FXML
    private Spinner<?> New_spinner;*/

    @FXML
    private Spinner<Integer> New_spinner;

    @FXML
    private TextField New_supplierName;

    @FXML
    private Button Purchase_tab;

    @FXML
    private Button Purchases_buy;

    @FXML
    private AnchorPane Purchases_form;

    @FXML
    private Button Purchases_receipt;

    @FXML
    private TableView<?> Purchases_tableView;

    @FXML
    private Button Sign_out;

    @FXML
    private TableView<SuppliersData> Suppler_tableView;

    @FXML
    private TableColumn<SuppliersData, String> Suppler_tableView_col_email;

    @FXML
    private TableColumn<SuppliersData, String> Suppler_tableView_col_location;

    @FXML
    private TableColumn<SuppliersData, String> Suppler_tableView_col_supplierContact;

    @FXML
    private TableColumn<SuppliersData, Integer> Suppler_tableView_col_supplierID;

    @FXML
    private TableColumn<SuppliersData, String> Suppler_tableView_col_supplierName;

    @FXML
    private AnchorPane Supplier_form;

    @FXML
    private TextField Supplier_search;

    @FXML
    private Button Supplier_tab;

    @FXML
    private Button supplier_add;

    @FXML
    private Button supplier_cancel;

    @FXML
    private AnchorPane supplier_addForm;

    @FXML
    private Button Update_cancel;

    @FXML
    private TextField Update_drugName;

    @FXML
    private AnchorPane Update_form;

    @FXML
    private TextField Update_price;

    @FXML
    private TextField Update_supplierID;

    @FXML
    private Button Update_updateBtn;

    @FXML
    private Button closeBtn;

    public TextField AddSupplierName;
    public TextField AddSupplierEmail;
    public TextField AddSupplierContact;
    public TextField AddSupplierLocation;
    public Button addSupplier_btn;

    //Database credentials
    String url = "jdbc:mysql://localhost:3306/pharmacy";
    String username = "root";
    String password = "ezioauditore@77";
    Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;


    @FXML
    private void initialize() {
        // Set up the Spinner value factory from 0 - 100
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        New_spinner.setValueFactory(valueFactory);
    }

    @FXML
    private HashMap<Integer, Drug> drugHashTable;

    @FXML
    private HashMap<Integer, Supplier> supplierHashMap;

    public DashBoardController() throws SQLException {
        drugHashTable = new HashMap<>();
        // Spinner<?> newSpinner = new Spinner<>();
        supplierHashMap = new HashMap<>();

    }


    @FXML
    private void handleAddDrugButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource() == New_add) {
            // Retrieve values from text fields and spinner
            String supplierName = New_supplierName.getText();
            double price = Double.parseDouble(New_price.getText());
            String drugName = New_drugName.getText();
            int quantity = New_spinner.getValue(); // Not sure about spinner
            int supplierID = findSupplierKeyByName(supplierName);


            // Create a new drug object
            Drug newDrug = new Drug(drugName, supplierName, price, quantity);

            // Add drug object to the hash table
            drugHashTable.put(newDrug.getId(), newDrug);

            // Clear text fields and spinner value
            New_supplierName.clear();
            New_price.clear();
            New_drugName.clear();
            New_spinner.getValueFactory().setValue(null);

            connectToDatabase(url, username, password);

            // inserting into drugs table
            String insertQuery = "INSERT INTO drugs (drug_id, drug_name, supplier_id, unit_price, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = null;
            try{
                // Prepare the statement
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, newDrug.getId());
                preparedStatement.setString(2, drugName);
                preparedStatement.setInt(3, supplierID); //  About to implement suppliers  hash table
                preparedStatement.setDouble(4, price);
                preparedStatement.setInt(5, quantity);
                // Execute the statement
                preparedStatement.executeUpdate();
                System.out.println("Data inserted successfully!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Drugs Added");
                alert.setHeaderText(null);
                alert.setContentText("Drugs added successfully!");
                alert.showAndWait();

            }
            catch (SQLException e) {
                System.err.println("Error occurred while inserting data into the database: " + e.getMessage());
            }
            finally {
                // Close the prepared statement
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        System.err.println("Error occurred while closing prepared statement: " + e.getMessage());
                    }
                }

                // Close the database connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.err.println("Error occurred while closing database connection: " + e.getMessage());
                    }
                }
            }
        }

    }

    public int findSupplierKeyByName(String supplierName) {
        for (int key : supplierHashMap.keySet()) {
            Supplier supplier = supplierHashMap.get(key);
            if (supplier.getSupplierName().equals(supplierName)) {
                return key;
            }
        }
        return -1; // Return -1 if no matching supplier is found
    }


    //Method to connect to Database
    public void connectToDatabase(String url, String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully");
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    public void handleAddSupplierButtonAction(ActionEvent event) {
        if (event.getSource() == addSupplier_btn) {
            String supplierName = AddSupplierName.getText();
            String supplierEmail = AddSupplierEmail.getText();
            String supplierContact = AddSupplierContact.getText();
            String supplierLocation = AddSupplierLocation.getText();

            Supplier newSupplier = new Supplier(supplierName, supplierContact, supplierLocation, supplierEmail);
            int supplierID = newSupplier.getId();

            // Add supplier to the supplier hashmap
            supplierHashMap.put(supplierID, newSupplier);

            // Clear text fields and spinner value
            AddSupplierName.clear();
            AddSupplierEmail.clear();
            AddSupplierContact.clear();
            AddSupplierLocation.clear();

            try {
                connectToDatabase(url, username, password);
                if (connection != null) {
                    String insertQuery = "INSERT INTO suppliers (supplier_id, supplier_name, contact_number, email, location) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(insertQuery);
                    stmt.setInt(1, supplierID);
                    stmt.setString(2, supplierName);
                    stmt.setString(3, supplierContact);
                    stmt.setString(4, supplierEmail);
                    stmt.setString(5, supplierLocation);
                    stmt.executeUpdate();
                    stmt.close();
                }
            } catch (SQLException e) {
                // Handle SQLException here (e.g., display error message or log the exception)
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Added");
            alert.setHeaderText(null);
            alert.setContentText("Supplier added successfully!");
            alert.showAndWait();
        }
    }



    public ObservableList<DrugsData> addDrugsListData() throws SQLException {

        ObservableList<DrugsData> DrugsList = FXCollections.observableArrayList();

        String sql = "SELECT * from drugs";
        connectToDatabase(url, username, password);

        try{
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            DrugsData drug;

            while(result.next()){
                drug = new DrugsData(
                        result.getInt("drug_id"),
                        result.getString("drug_name"),
                        result.getInt("supplier_id"),
                        result.getDouble("unit_price"),
                        result.getInt("quantity")
                );

                DrugsList.add(drug);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return DrugsList;
    }

    private ObservableList<DrugsData> addDrugsList;
    public void addDrugsShowListData() throws SQLException {
        addDrugsList = addDrugsListData();

        Drugs_tableView_col_supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        Drugs_tableView_col_drugName.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        Drugs_tableView_col_price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        Drugs_tableView_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        Drugs_tableView.setItems(addDrugsList);
    }

    public ObservableList<SuppliersData> addSuppliersListData() throws SQLException {

        ObservableList<SuppliersData> SuppliersList = FXCollections.observableArrayList();

        String sql = "SELECT * from suppliers";
        connectToDatabase(url, username, password);

        try{
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            SuppliersData supplier;

            while(result.next()){
                supplier = new SuppliersData(result.getInt("supplier_id")
                        , result.getString("supplier_name")
                        , result.getString("contact_number")
                        , result.getString("email")
                        , result.getString("location"));

                SuppliersList.add(supplier);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return SuppliersList;
    }

    private ObservableList<SuppliersData> addSuppliersList;
    public void addSuppliersShowListData() throws SQLException {
        addSuppliersList = addSuppliersListData();

        Suppler_tableView_col_supplierID.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        Suppler_tableView_col_supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        Suppler_tableView_col_supplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        Suppler_tableView_col_email.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        Suppler_tableView_col_location.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));
        Suppler_tableView.setItems(addSuppliersList);
    }


    //switching between screens
    public void switchForms(ActionEvent event) throws SQLException {
        if(event.getSource() == Drugs_tab) {
            Drugs_form.setVisible(true);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);

            Drugs_tab.setStyle("-fx-background-color: linear-gradient(to bottom right, #19b999,#09948d);");
            Purchase_tab.setStyle("-fx-background: transparent");
            Supplier_tab.setStyle("-fx-background: transparent");

            addDrugsShowListData();

        } else if (event.getSource()== Purchase_tab) {
            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(true);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);

            Purchase_tab.setStyle("-fx-background-color: linear-gradient(to bottom right, #19b999,#09948d);");
            Drugs_tab.setStyle("-fx-background: transparent");
            Supplier_tab.setStyle("-fx-background: transparent");

        } else if (event.getSource()== Supplier_tab) {

            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(true);

            Supplier_tab.setStyle("-fx-background-color: linear-gradient(to bottom right, #19b999,#09948d);");
            Drugs_tab.setStyle("-fx-background: transparent");
            Purchase_tab.setStyle("-fx-background: transparent");

            addSuppliersShowListData();
        }
    }


    public void Drugs_add(ActionEvent event){
        if(event.getSource() == Drugs_add){
            Drugs_form.setVisible(true);
            New_form.setVisible(true);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);
        } else if (event.getSource() == Drugs_update) {
            Drugs_form.setVisible(true);
            New_form.setVisible(false);
            Update_form.setVisible(true);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);
        } else if (event.getSource() == Drugs_delete) {
            Drugs_form.setVisible(true);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(true);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);
        }
    }

    public void drugsCancel(ActionEvent event){
        Drugs_form.setVisible(true);
        New_form.setVisible(false);
        Update_form.setVisible(false);
        Delete_form.setVisible(false);
        Purchases_form.setVisible(false);
        AddPurchase_form.setVisible(false);
        Supplier_form.setVisible(false);
    }

    public void PurchasesBuy(ActionEvent event){
        if(event.getSource() == Purchases_buy){
            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(true);
            AddPurchase_form.setVisible(true);
            Supplier_form.setVisible(false);
        }
    }

    public void addPurchaseCancel(ActionEvent event){
        Drugs_form.setVisible(false);
        New_form.setVisible(false);
        Update_form.setVisible(false);
        Delete_form.setVisible(false);
        Purchases_form.setVisible(true);
        AddPurchase_form.setVisible(false);
        Supplier_form.setVisible(false);
    }

    public void addSupplier(ActionEvent event){
        if(event.getSource() == supplier_add){
            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(true);
            supplier_addForm.setVisible(true);
        }
    }

    public void cancelSupplier(ActionEvent event){
        if(event.getSource() == supplier_cancel){
            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(true);
            supplier_addForm.setVisible(false);
        }
    }
    public void closeBtn(){
        System.exit(0);
    }

    public void Sign_out() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();

        Sign_out.getScene().getWindow().hide();
    }

    // method to add new drug to the database


}
