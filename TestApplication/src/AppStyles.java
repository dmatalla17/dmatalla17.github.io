

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AppStyles {
    
    private static String buttonStyles = "";
    private static String mainScreenStyles = "";
    //private static String topBarStyle = "";
    private static String leftBarStyle = "";

    public static void setButtonStyles(Button btn) {
        
        buttonStyles = "-fx-padding: 10px;"
                + "-fx-text-fill: black;"
                + "-fx-font-size: 14px;"
                + "-fx-max-width:160px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-background-color:seagreen;"
                + "-fx-border-radius:0px";
               
        btn.setStyle(buttonStyles);
        btn.setCursor(Cursor.HAND);
        
    }
    
    
    public static void FormatSearchField(TextField txt) {
        
        buttonStyles = "-fx-padding: 10px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-min-width:550px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    
    public static void FormatTextField(TextField txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-min-width:150px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    public static void FormatTextField(Label txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 13px;"
                + "-fx-min-width:100px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    public static void FormatTextField_Values(Label txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: Black;"
                + "-fx-font-size: 13px;"
                + "-fx-min-width:150px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
      public static void FormatTextField_LevelOne(TextField txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-max-width:50px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
      
    public static void FormatTextField(ComboBox<String> txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-min-width:150px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    public static void FormatTextField_LevelTwo(TextField txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-min-width:200px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    
     public static void FormatTextField_LevelThree(TextField txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-min-width:280px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    public static void FormatTextField_LevelTwo(TextArea txt) {
        
        buttonStyles = "-fx-padding: 5px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-min-width:250px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-border-radius:0px";
               
        txt.setStyle(buttonStyles);
        txt.setCursor(Cursor.TEXT);
        
    }
    
    public static void FormatSearchButton(Button btn) {
        
        buttonStyles = "-fx-padding: 10px;"
                + "-fx-text-fill: blue;"
                + "-fx-font-size: 15px;"
                + "-fx-max-width:150px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-background-color:gray;"
                + "-fx-border-radius:0px";
               
        btn.setStyle(buttonStyles);
        
        
    }

    public static String getMainScreenStyles() {
        return mainScreenStyles;
    }
    
    
      public static void FormatSearchBar(HBox btn){
    
         String btnStle = "-fx-padding: 5px;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 12px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-background-color:#c0c0c0;"
                + "-fx-border-radius:0px";
               btn.setStyle(btnStle);
                
    
    }
    
    public static void styleTopBarButtons(Button btn){
    
         String btnStle = "-fx-padding: 5px;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 12px;"
                + "-fx-max-width:100px;"
                + "-fx-border-insets: 1px;"
                +" -fx-background-insets: 1px;"
                + "-fx-background-color:blue;"
                + "-fx-border-radius:0px";
               btn.setStyle(btnStle);
                
    
    }
    
    
    

    public static String getLeftBarStyle() {
       
        leftBarStyle  = "-fx-padding: 15px;"
                + "-fx-font-size: 14px;"
                + "-fx-max-width:170px;"
                + "-fx-spacing:10px;"
                + "-fx-border-width: 2;"
                + "-fx-border-color: seagreen;;";
        
        return leftBarStyle;
        
    }

    public static void setLeftBarStyle(String leftBarStyle) {
        AppStyles.leftBarStyle = leftBarStyle;
    }
    
    
   
    
    
}
