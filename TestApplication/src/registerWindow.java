

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

public class registerWindow {
    
   private static boolean  registered = false;

    public void openWindow() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Register");
        Insets buttonPadding = new Insets(10, 10, 10, 10);

        //VBox layout for main window
        VBox mainLayout = new VBox();

        //HBox to hold Label and TextField
        HBox boxone = new HBox();
        Label uname = new Label("User Name:");
        uname.setMinWidth(120);
        TextField txt_uname = new TextField();
        txt_uname.setMinWidth(150);
        AppStyles.FormatTextField(txt_uname);
        boxone.getChildren().addAll(uname, txt_uname);

        HBox boxtwo = new HBox();
        Label email = new Label("Email:");
        email.setMinWidth(120);
        TextField txt_email = new TextField();
        txt_email.setMinWidth(150);
        AppStyles.FormatTextField_LevelTwo(txt_email);
        boxtwo.getChildren().addAll(email, txt_email);

        HBox boxfour = new HBox();
        //Components for customer Phone details
        Label pass = new Label("Password:");
        pass.setMinWidth(120);
        PasswordField txt_pass = new PasswordField();
        txt_pass.setMinWidth(150);
        AppStyles.FormatTextField(txt_pass);
        boxfour.getChildren().addAll(pass, txt_pass);

        HBox boxfive = new HBox();
        //Components for customer Phone details
        Label repass = new Label("Re Enter Password:");
        repass.setMinWidth(120);
        PasswordField txt_repass = new PasswordField();
        txt_repass.setMinWidth(150);
        AppStyles.FormatTextField(txt_repass);
        boxfive.getChildren().addAll(repass, txt_repass);

        HBox boxsix = new HBox();
        boxsix.setAlignment(Pos.CENTER);
        Button save = new Button("Register");
        save.setMinWidth(130);
        save.setPadding(buttonPadding);

        boxsix.getChildren().addAll(save);

        //Creating spacing for HBoxes
        boxsix.setSpacing(5);
        boxfive.setSpacing(5);
        boxfour.setSpacing(5);
        boxone.setSpacing(5);
        boxtwo.setSpacing(5);

        mainLayout.getChildren().addAll(boxone, boxtwo, boxfour, boxfive, boxsix);
        mainLayout.setSpacing(5);

        save.setOnAction((ActionEvent event) -> {

            String str_uname = txt_uname.getText();
            String str_email = txt_email.getText();
            String str_pass = txt_pass.getText();
            String str_repass = txt_repass.getText();

            if (!str_uname.isEmpty() && !str_email.isEmpty() && !str_pass.isEmpty() && !str_repass.isEmpty()) {

                if (str_email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {

                    if (str_pass.equals(str_repass)) {

                        users user = new users(str_uname, str_pass, str_email);
                        registered = SQLDBConnect.registerClient(user);

                        if (registered) {
                            
                            popupwindow.close();
                            
                            
                        } else {
                            popupwindow.setTitle("Failed..Sorry Try Again");
                        }

                    }else{
                    
                    popupwindow.setTitle("Password Do Not Match!!");
                    
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

        Scene scene1 = new Scene(mainLayout, 330, 220);

        popupwindow.setScene(scene1);
        popupwindow.setResizable(false);
        popupwindow.showAndWait();

    }

    public boolean isRegistered() {
        return registered;
    }
    
    
    

}
