

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class removeWindow {
    
    private boolean deleted;
    
    public void openWindow() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Remove My Resource");
        Insets buttonPadding = new Insets(10, 10, 10, 10);

        //VBox layout for main window
        VBox mainLayout = new VBox();

       
        HBox boxtwo = new HBox();
        Label resource= new Label("Resource Id:");
        resource.setMinWidth(80);
        TextField txt_resource = new TextField();
        AppStyles.FormatTextField_LevelTwo(txt_resource);
      
        boxtwo.getChildren().addAll(resource,txt_resource);


        HBox boxsix = new HBox();
        boxsix.setAlignment(Pos.CENTER);
        Button save = new Button("Remove");
        save.setMinWidth(130);
        save.setPadding(buttonPadding);

        boxsix.getChildren().addAll(save);

        //Creating spacing for HBoxes
        boxsix.setSpacing(5);
        boxtwo.setSpacing(5);

        mainLayout.getChildren().addAll(boxtwo, boxsix);
        mainLayout.setSpacing(5);

        save.setOnAction((ActionEvent event) -> {

            
            String str_resourceId = txt_resource.getText();
           

          

                if (!str_resourceId.isEmpty()) {
     
                       deleted = SQLDBConnect.removeResource(str_resourceId);

                        if (deleted) {
                            
                            
                            popupwindow.close();
                            
                            
                        } else {
                            popupwindow.setTitle("Could Not Delete");
                        }


                } else {

                    txt_resource.requestFocus();

                }

       

        });

        mainLayout.setPadding(new Insets(10, 10, 10, 10));
        mainLayout.setSpacing(10);

        Scene scene1 = new Scene(mainLayout, 300, 150);

        popupwindow.setScene(scene1);
        popupwindow.setResizable(false);
        popupwindow.showAndWait();
    }

    public boolean isDeleted() {
        return deleted;
    }
    
    
    
    
}
