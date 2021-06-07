package controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StudentFormController {
    public ImageView imgMinimize;
    public ImageView imgClose;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    private double xMousePos;
    private double yMousePos;

    public void initialize(){
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


}
