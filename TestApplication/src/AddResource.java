

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddResource {

    private static Resource resource;
    private String resourceType;
    private boolean added = false;

    public void openWindow() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add Resources");
        Insets buttonPadding = new Insets(10, 10, 10, 10);

        //VBox layout for main window
        VBox mainLayout = new VBox();

        //HBox to hold Label and TextField
        HBox boxone = new HBox();
        Label resourceNam = new Label("Resource Name:");
       
         AppStyles.FormatTextField(resourceNam);
        TextField txt_resourceNam = new TextField();
         txt_resourceNam.setTooltip(new Tooltip("Can be a book name(Descriptive), Magazine You wish to Share, Any Learning Material"));
        AppStyles.FormatTextField_LevelThree(txt_resourceNam);
        boxone.getChildren().addAll(resourceNam, txt_resourceNam);

     
        HBox boxtwo = new HBox();
        Label source = new Label("Source:");
         AppStyles.FormatTextField(source);
        TextField txt_source = new TextField();
        txt_source.setTooltip(new Tooltip("You Ca Add a web Link, Library, A place where The Resource Can Be Found"));
        AppStyles.FormatTextField_LevelThree(txt_source);
        
        boxtwo.getChildren().addAll(source, txt_source);

        HBox boxthree = new HBox();
        Label resoucetype = new Label("Resoucetype:");
        AppStyles.FormatTextField(resoucetype);
        
        // Weekdays 
        String resource_type[] = { "Book", "Magazine", "Web Article", "News Paper" }; 
        ComboBox<String> cmb_resoucetype = new ComboBox<String>(FXCollections.observableArrayList(resource_type));
        AppStyles.FormatTextField(cmb_resoucetype);
        cmb_resoucetype.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> resourceType = t1);
        
        cmb_resoucetype.setMinWidth(150);
        boxthree.getChildren().addAll(resoucetype, cmb_resoucetype);

        HBox boxfour = new HBox();
        //Components for customer Phone details
        Label price = new Label("Price $:");
        AppStyles.FormatTextField(price);
        TextField txt_price = new TextField();
       AppStyles.FormatTextField_LevelOne(txt_price);
       System.out.println(txt_price.getMaxWidth());
        boxfour.getChildren().addAll(price, txt_price);
        
        
        VBox boxfive = new VBox();
        //Components for customer Phone details
        Label resourceDescription = new Label("Resource Description(Brief and Clear)");
         AppStyles.FormatTextField(resourceDescription );
        TextArea txt_resourceDescription = new TextArea();
        AppStyles.FormatTextField_LevelTwo( txt_resourceDescription);
      
        
        boxfive.getChildren().addAll(resourceDescription, txt_resourceDescription);

       

        HBox boxsix = new HBox();
        boxfive.setAlignment(Pos.CENTER);
        Button save = new Button("Save");
        save.setMinWidth(130);
        save.setPadding(buttonPadding);

        boxsix.getChildren().addAll(save);

        //Creating spacing for HBoxes
        boxsix.setSpacing(5);
        boxfive.setSpacing(5);
        boxfour.setSpacing(5);
        boxone.setSpacing(5);
        boxtwo.setSpacing(5);
        boxthree.setSpacing(5);

        mainLayout.getChildren().addAll(boxone, boxtwo, boxthree, boxfour, boxfive,boxsix);
        mainLayout.setSpacing(5);

        save.setOnAction((ActionEvent event) -> {

            String rsc_price = txt_price.getText();
            String rsc_description = txt_resourceDescription.getText();
            String rsc_name = txt_resourceNam.getText();
            String rsc_source = txt_source.getText();
            
            
            if (!rsc_price.isEmpty() && !rsc_description.isEmpty() && !rsc_name.isEmpty() && !rsc_source.isEmpty()) {

                if (rsc_price.matches("-?\\d+(\\.\\d+)?")) {

                    //resource(String resourceName, String source, String resoucetype, String price, String resourceDescription)
                   // users currentUser = SQLDBConnect.getCurentUser();
                    resource = new Resource(rsc_name, rsc_source, resourceType, rsc_price, rsc_description);
                   added =  SQLDBConnect.addResource(resource);
                   if(added){
                    popupwindow.close();
                   }else{
                       popupwindow.setTitle("Failed..Sorry Try Again");
                   }

                } else {

                    txt_price.requestFocus();

                }

            } else {

                popupwindow.setTitle("Please Fill All The Fields..");

            }

        });

        mainLayout.setPadding(new Insets(10, 10, 10, 10));
        mainLayout.setSpacing(10);

        Scene scene1 = new Scene(mainLayout, 430, 450);

        popupwindow.setScene(scene1);
        popupwindow.setResizable(false);
        popupwindow.showAndWait();

    }

    public boolean isAdded() {
        return added;
    }

    

}
