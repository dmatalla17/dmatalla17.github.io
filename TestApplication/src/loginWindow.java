

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class loginWindow {
    
    private  boolean Member = false;
    
     public void openWindow() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Log In");
        Insets buttonPadding = new Insets(10, 10, 10, 10);

        //VBox layout for main window
        VBox mainLayout = new VBox();

       
        HBox boxtwo = new HBox();
        Label email = new Label("Email:");
        email.setMinWidth(80);
        TextField txt_email = new TextField();
        AppStyles.FormatTextField_LevelTwo(txt_email);
      
        boxtwo.getChildren().addAll(email, txt_email);

        HBox boxfour = new HBox();
        //Components for customer Phone details
        Label pass = new Label("Password:");
        pass.setMinWidth(80);
        PasswordField txt_pass = new PasswordField();
        AppStyles.FormatTextField_LevelTwo(txt_pass);
        
        boxfour.getChildren().addAll(pass, txt_pass);

        HBox boxsix = new HBox();
        boxsix.setAlignment(Pos.CENTER);
        Button save = new Button("Log in");
        save.setMinWidth(130);
        save.setPadding(buttonPadding);

        boxsix.getChildren().addAll(save);

        //Creating spacing for HBoxes
        boxsix.setSpacing(5);
        boxfour.setSpacing(5);
        boxtwo.setSpacing(5);

        mainLayout.getChildren().addAll(boxtwo, boxfour, boxsix);
        mainLayout.setSpacing(5);

        save.setOnAction((ActionEvent event) -> {

            
            String str_email = txt_email.getText();
            String str_pass = txt_pass.getText();
          

            if ( !str_email.isEmpty() && !str_pass.isEmpty() ) {

                if (str_email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {

                   

                        
                        Member = SQLDBConnect.logIn(str_email, str_pass);

                        if (Member) {
                            
                            
                            popupwindow.close();
                            
                            
                        } else {
                            popupwindow.setTitle("Failed..Sorry Try Again");
                        }


                } else {

                    txt_email.requestFocus();

                }

            } else {

                popupwindow.setTitle("Please Fill All The Fields..");

            }

        });

        mainLayout.setPadding(new Insets(10, 10, 10, 10));
        mainLayout.setSpacing(10);

        Scene scene1 = new Scene(mainLayout, 300, 150);

        popupwindow.setScene(scene1);
        popupwindow.setResizable(false);
        popupwindow.showAndWait();

    }

    public  boolean IsMember() {
        
        return Member;
    }
     
     
     

    
}
