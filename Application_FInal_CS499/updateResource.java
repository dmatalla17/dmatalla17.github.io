

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class updateResource {

    private boolean updated = false;
    public void openWindow() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Update My Resource");
        Insets buttonPadding = new Insets(10, 10, 10, 10);

        //VBox layout for main window
        VBox mainLayout = new VBox();

        HBox boxtwo = new HBox();
        Label resource = new Label("Resource Id:");
        resource.setMinWidth(80);
        TextField txt_resource = new TextField();
        AppStyles.FormatTextField_LevelTwo(txt_resource);

        boxtwo.getChildren().addAll(resource, txt_resource);

        HBox boxthree = new HBox();
        Label resoucetype = new Label("Which Detail ?");
        AppStyles.FormatTextField(resoucetype);
        ComboBox<String> cmb_resoucetype = new ComboBox<String>(FXCollections.observableArrayList("Resource Name", "Source", "Description", "Price"));
        AppStyles.FormatTextField(cmb_resoucetype);
        cmb_resoucetype.setMinWidth(150);
        boxthree.getChildren().addAll(resoucetype, cmb_resoucetype);

        HBox boxtfour = new HBox();
        Label newValue = new Label("Replace With:");
        newValue.setMinWidth(80);
        TextArea txt_newValue = new TextArea();
        AppStyles.FormatTextField_LevelTwo(txt_newValue);
        boxtfour.getChildren().addAll(newValue, txt_newValue);

        HBox boxsix = new HBox();
        boxsix.setAlignment(Pos.CENTER);
        Button save = new Button("Remove");
        save.setMinWidth(130);
        save.setPadding(buttonPadding);

        boxsix.getChildren().addAll(save);

        //Creating spacing for HBoxes
        boxsix.setSpacing(5);
        boxtwo.setSpacing(5);

        mainLayout.getChildren().addAll(boxtwo, boxthree, boxtfour, boxsix);
        mainLayout.setSpacing(5);

        save.setOnAction((ActionEvent event) -> {

            String str_resourceId = txt_resource.getText();
            int column = cmb_resoucetype.getSelectionModel().getSelectedIndex();
            String str_newValue = txt_newValue.getText();
            String str_column = "";

            if (!str_resourceId.isEmpty() &&  !str_newValue.isEmpty()) {

                switch (column) {
                    case 0:
                        str_column = "resourceName";
                        break;
                    case 1:
                        str_column = "source";
                        break;
                    case 2:
                        str_column = "briefDescription";
                        break;
                    case 3:
                        str_column = "briefDescription";
                        break;
                    default:
                        break;
                }
                
                
                updated = SQLDBConnect.updateResource(str_column, str_resourceId, str_newValue);

                if (updated) {

                    popupwindow.close();

                } else {
                    popupwindow.setTitle("Could Not Update");
                }

            } else {

                txt_resource.requestFocus();

            }

        });

        mainLayout.setPadding(new Insets(10, 10, 10, 10));
        mainLayout.setSpacing(10);

        Scene scene1 = new Scene(mainLayout, 300, 250);

        popupwindow.setScene(scene1);
        popupwindow.setResizable(false);
        popupwindow.showAndWait();
    }

    public boolean isUpdated() {
        return updated;
    }
    
    

}
