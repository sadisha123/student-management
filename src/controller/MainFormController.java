package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {
    public AnchorPane pneAddnewStudent;
    public JFXRippler rprAddeNewStudent;
    public ImageView imgMinimize;
    public ImageView imgClose;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    private double xMousePos;
    private double yMousePos;


    public void initialize() {
        rprAddeNewStudent.setControl(pneAddnewStudent);
        pneAddnewStudent.setFocusTraversable(true);
        initWindow();
    }



    private void initWindow(){
            lblTitle.setMouseTransparent(true);

            Platform.runLater(()->{
                lblTitle.setText(((Stage)imgClose.getScene().getWindow()).getTitle());
            });



            pneAppBar.setOnMousePressed(event -> {
                xMousePos = event.getX();
                yMousePos = event.getY();
            });

            pneAppBar.setOnMouseDragged(event -> {
                if(event.isPrimaryButtonDown()) {
                    Window mainWindow = imgMinimize.getScene().getWindow();
                    mainWindow.setX(event.getScreenX() - xMousePos);
                    mainWindow.setY(event.getScreenY() - yMousePos);
                }
            });

            imgClose.setOnMouseEntered(event -> imgClose.setImage(new Image("/view/assets/Close-hover.png")));
            imgClose.setOnMouseClicked(event -> imgClose.getScene().getWindow().hide());
            imgClose.setOnMouseExited(event -> imgClose.setImage(new Image("/view/assets/close.png")));

            imgMinimize.setOnMouseEntered(event -> imgMinimize.setImage(new Image("/view/assets/minimize-hover.png")));
            imgMinimize.setOnMouseExited(event -> imgMinimize.setImage(new Image("/view/assets/minimize.png")));
            imgMinimize.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).setIconified(true));
        }



    public void pneAddnewStudent_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE){
          rprAddeNewStudent.createManualRipple().run();
        }
    }


    public void pneAddnewStudent_OnMouseClicked(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/StudentForm.fxml"));
        Scene addNewStudentScene = new Scene(root);
        Stage primaryStage = (Stage) (rprAddeNewStudent.getScene().getWindow());
        primaryStage.setScene(addNewStudentScene);
        primaryStage.setTitle("Add new Student");
        primaryStage.centerOnScreen();
    }

    public void pneAddnewStudent_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE){
            pneAddnewStudent_OnMouseClicked(null);
        }
    }
}
