

import javafx.application.Application;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application{

    private VBox centreBox;
    private users currentUser;

    public void start(Stage primaryStage){

        //Create a temporary user, will be change after log in
        currentUser = new users();
       
        //connect to db on start
        SQLDBConnect.ConnectDB();
        /*Creating a top bar of the main screen*/
        VBox topBar = new VBox();

        /*Top Bar Button*/
        Button register = new Button("Register");
        Button logIn = new Button("Log In");
        //Styling topBar buttons
        AppStyles.styleTopBarButtons(register);
        AppStyles.styleTopBarButtons(logIn);

        //HBox to hold user Details on log in
        HBox userDetails = new HBox();
        userDetails.setPadding(new Insets(2, 2, 2, 2));
        userDetails.setSpacing(4);
        Label uname = new Label("Log in to Add,Delete,View and Update Your Materials ");
        Label uemail = new Label();
        userDetails.getChildren().addAll(uname, uemail);
        topBar.getChildren().add(userDetails);

        logIn.setOnAction((ActionEvent event) -> {
            //Test connection

            loginWindow window = new loginWindow();
            window.openWindow();
            boolean isMember = window.IsMember();

            if (isMember) {

                currentUser = SQLDBConnect.getCurentUser();
                uname.setText("User Name:" + currentUser.getUsernme());
                uemail.setText("Your Email:" + currentUser.getUserEmail());
                displayDialog("Welcome To Our Resource Finder " + currentUser.getUserEmail());
              
            }

        });

        register.setOnAction((ActionEvent event) -> {
            //Test connection
            registerWindow window = new registerWindow();
            window.openWindow();
            boolean isregistered = window.isRegistered();

            if (isregistered) {

                displayDialog("Welcome To Our Resource Finder,"
                        + "Now You Can Share Resource with others, such as books, articles, journals, and much more!");
            }

        });

        //Add buttons to Top bar
        HBox topbox = new HBox();
        topbox.setAlignment(Pos.CENTER_RIGHT);
        topbox.setPadding(new Insets(2, 2, 2, 2));
        topbox.getChildren().addAll(register, logIn);
        topBar.getChildren().add(topbox);

        /*Creating a side bar of the main screen*/
        VBox leftBar = new VBox();
        leftBar.setStyle(AppStyles.getLeftBarStyle());

        /*Menu Buttons*/
        Button addBooks = new Button("Add Resource");
        Button delBooks = new Button("Remove Resource");
        Button updateBooks = new Button("Update/Edit Resources");
        Button refreshchBooks = new Button("Refresh");

        /*Styling buttons*/
        AppStyles.setButtonStyles(addBooks);
        AppStyles.setButtonStyles(delBooks);
        AppStyles.setButtonStyles(updateBooks);
        AppStyles.setButtonStyles(refreshchBooks);

        /**
         * **************************************
         */
        //VBox to hold Resource Details inform oh Hbox
        VBox mainresourceHolder = new VBox();
        mainresourceHolder.setSpacing(10);
        //Get all the resources
        ArrayList<Resource> resources = SQLDBConnect.getResources();
        setResourceDetails(mainresourceHolder, resources);
        ScrollPane spane = new ScrollPane();
        spane.setFitToWidth(true);
        spane.setPannable(true);
        spane.setContent(mainresourceHolder);

        /**
         * *************End Of Resource Box*************
         *
         * /*Add Actions to Button
         */
        updateBooks.setOnAction((ActionEvent event) -> {
           
              if (currentUser.isIsRegistered()) {

                updateResource rsc = new updateResource();
                rsc.openWindow();

                if (rsc.isUpdated()) {

                    displayDialog("Resource Has Been Update..");
                    ArrayList<Resource> ress = SQLDBConnect.getResources();
                    setResourceDetails(mainresourceHolder, ress);
                }

            } else {

                displayDialog("Only Member ar Allowed To Add Resources");

            }

        });

        /*Add Actions to Button*/
        addBooks.setOnAction((ActionEvent event) -> {
            //we first check if user has logged in

            if (currentUser.isIsRegistered()) {

                AddResource rsc = new AddResource();
                rsc.openWindow();

                if (rsc.isAdded()) {

                    displayDialog("New Resource Has Been Added..");
                    ArrayList<Resource> ress = SQLDBConnect.getResources();
                    setResourceDetails(mainresourceHolder, ress);
                }

            } else {

                displayDialog("Only Member ar Allowed To Add Resources");

            }

        });

        /*Add Actions to Button*/
        delBooks.setOnAction((ActionEvent event) -> {
            //Test connection

            if (currentUser.isIsRegistered()) {

                removeWindow win = new removeWindow();
                win.openWindow();

                if (win.isDeleted()) {

                    displayDialog("Resource Has Been Deleted..");
                    ArrayList<Resource> ress = SQLDBConnect.getResources();
                    setResourceDetails(mainresourceHolder, ress);
                } else {

                    displayDialog("Could Not Remove Resource, Maybe Its Not Yours");
                }

            } else {

                displayDialog("Only Member ar Allowed To Delete Resources");

            }

        });

        /*Add Actions to Button*/
        refreshchBooks.setOnAction((ActionEvent event) -> {
            //Test connection

            ArrayList<Resource> ress = SQLDBConnect.getResources();
            setResourceDetails(mainresourceHolder, ress);

        });

       
        /*Add buttons to left pane*/
        leftBar.getChildren().addAll(addBooks, delBooks, updateBooks, refreshchBooks);

        //Center Bar to hold resources details
        centreBox = new VBox();
        centreBox.setPadding(new Insets(2, 2, 2, 2));
        centreBox.setSpacing(4);
        centreBox.setStyle("-fx-background-color: white");

        /**
         * ************************************SearchBar************************
         * This bar is responsible for holding the search fields and button at
         * the centre Allow user to enter text and click search for searching
         */
        //Add searchng box hbox
        HBox searchBar = new HBox();
        searchBar.setPadding(new Insets(2, 2, 2, 2));
        searchBar.setSpacing(1);
        searchBar.setAlignment(Pos.CENTER_RIGHT);

        //Add serachField and searchButton to the searchbox
        TextField searchField = new TextField();
        //Style textField
        AppStyles.FormatSearchField(searchField);

        //Add search Button 
        Button searchButton = new Button("Find Resource");
        AppStyles.FormatSearchButton(searchButton);
        searchBar.getChildren().addAll(searchField, searchButton);
        AppStyles.FormatSearchBar(searchBar);

        searchButton.setOnAction((ActionEvent event) -> {
            //Test connection
            ArrayList<Resource> ress = SQLDBConnect.getResources(searchField.getText());
            setResourceDetails(mainresourceHolder, ress);

        });
        //Add the searchBar to the Main vbox at the center
        centreBox.getChildren().add(searchBar);

        centreBox.getChildren().add(spane);

        /*Main Layout For main Screen*/
        BorderPane root = new BorderPane();
        root.setLeft(leftBar);
        root.setTop(topBar);
        root.setCenter(centreBox);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Record Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Application.launch(args);
    }


	//Creates and display a message dialog
    public static void displayDialog(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resource Finder");
        alert.setHeaderText("");
        alert.setContentText(message);

        alert.showAndWait();

    }

    /*This methods will be use to collect all the resources
     using methods in sqlConnection
     each Detail will be Added in HBox Layout*/
    public void setResourceDetails(VBox box, ArrayList<Resource> resources) {

        box.getChildren().clear();

        VBox resourceholder = new VBox();
        resourceholder.setPadding(new Insets(4, 4, 4, 4));
        resourceholder.setSpacing(4);
        //Collect All the Resources

        Label rsc_title;
        Label rsc_value;

        TextArea area = new TextArea();
        AppStyles.FormatTextField_LevelTwo(area);
        area.setEditable(false);

      

        for (int x = 0; x < resources.size(); x++) {
            VBox holder = new VBox();
            HBox box1 = new HBox();

            //Field One
            rsc_title = new Label();
            rsc_value = new Label();
            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField(rsc_value);
            rsc_title.setText("Resource ID:");
            rsc_value.setText(resources.get(x).getResource_id());
            box1.getChildren().addAll(rsc_title, rsc_value);
            //Field Two
            rsc_title = new Label();
            rsc_value = new Label();
            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField(rsc_value);
            rsc_title.setText("Resource Type:");
            rsc_value.setText(resources.get(x).getResoucetype());
            box1.getChildren().addAll(rsc_title, rsc_value);
            //Field Three
            rsc_title = new Label();
            rsc_value = new Label();
            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField(rsc_value);
            rsc_title.setText("Added On:");
            rsc_value.setText(resources.get(x).getCreationDate());
            box1.getChildren().addAll(rsc_title, rsc_value);
            holder.getChildren().add(box1);

            //Field One
            box1 = new HBox();
            rsc_title = new Label();
            rsc_value = new Label();
            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField(rsc_value);
            rsc_title.setText("Resource Name");
            rsc_value.setText(resources.get(x).getResourceName());
            box1.getChildren().addAll(rsc_title, rsc_value);
            holder.getChildren().add(box1);

            //Field Two
            box1 = new HBox();
            rsc_title = new Label();
            rsc_value = new Label();
            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField(rsc_value);
            rsc_title.setText("Mejor Source");
            rsc_value.setText(resources.get(x).getSource());
            box1.getChildren().addAll(rsc_title, rsc_value);
            holder.getChildren().add(box1);

            box1 = new HBox();
            rsc_title = new Label();
            rsc_value = new Label();
            //Style labels
            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField(rsc_value);
            //Set Text to labels
            rsc_title.setText("Added By.");
            rsc_value.setText(resources.get(x).getProvider());
            box1.getChildren().addAll(rsc_title, rsc_value);
            holder.getChildren().add(box1);

            VBox description = new VBox();
            rsc_title = new Label();
            rsc_value = new Label();

            AppStyles.FormatTextField(rsc_title);
            AppStyles.FormatTextField_Values(rsc_value);
            rsc_title.setText("More Description");
            rsc_value.setText(resources.get(x).getResourceDescription());
            description.getChildren().addAll(rsc_title, rsc_value);
            holder.getChildren().add(description);

            holder.setStyle("-fx-border:1px; -fx-border-color:green");
            resourceholder.getChildren().add(holder);

        }

        box.getChildren().add(resourceholder);

        box.setPadding(new Insets(4, 4, 4, 4));

    }

}
