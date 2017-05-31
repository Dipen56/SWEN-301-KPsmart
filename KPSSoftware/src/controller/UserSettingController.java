package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.KPSmartModel;
import model.staff.Staff;
import view.DialogBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dipen on 25/04/2017. this class represents the user setting controller.
 */
public class UserSettingController implements Initializable {
    private static KPSmartModel kpSmartModel;
    @FXML
    private Label userLable;
    @FXML
    private ImageView avatar;
    @FXML
    private Button manageUser;
    @FXML
    private Button addNewUser;

    /**
     * this constructor is used to set the reference for the model.
     */
    public UserSettingController() {
        KPSmartModel.setLoginScreenController(this);
    }

    /**
     * this method is used by the buttons on the left side menu to change change the scene.
     *
     * @param event
     * @throws IOException
     */
    public void changeScenes(ActionEvent event) throws IOException {

        if (event.toString().contains("ChangePassword")) {
            Parent changePasswordScreen = FXMLLoader.load(UserSettingController.class.getResource("/fxml/ChangePassword.fxml"));
            Scene changePasswordScene = new Scene(changePasswordScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(changePasswordScene);
            tempStage.show();
        } else if (event.toString().contains("ManageUser")) {
            Parent manageUserScreen = FXMLLoader.load(UserSettingController.class.getResource("/fxml/ManageUser.fxml"));
            Scene manageUserScene = new Scene(manageUserScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(manageUserScene);
            tempStage.show();

        } else if (event.toString().contains("AddNewUser")) {
            Parent addNewUserScreen = FXMLLoader.load(UserSettingController.class.getResource("/fxml/AddNewUser.fxml"));
            Scene addNewUserScene = new Scene(addNewUserScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(addNewUserScene);
            tempStage.show();
        } else if (event.toString().contains("Exit")) {
            Parent returnHomeScreen = FXMLLoader.load(UserSettingController.class.getResource("/fxml/home screen.fxml"));
            Scene returnHomeScene = new Scene(returnHomeScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(returnHomeScene);
            tempStage.show();
        } else if (event.toString().contains("logout")) {
            //TODO; POP up dialog box to ask the user if they are sure want to logout
            DialogBox.LogoutyMsg("Logout", "Are you sure to logout?");
            Parent loginScreen = FXMLLoader.load(UserSettingController.class.getResource("/fxml/login screen.fxml"));
            Scene loginScene = new Scene(loginScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(loginScene);
            tempStage.show();
        }
    }

    /**
     * This method is used to handel local screen button actions. i.e accept, reset, discard and exit
     *
     * @param event
     */
    public void handleButtons(ActionEvent event) {
        if (event.toString().contains("Exit")) {
            returnHome(event);
        }
    }

    /**
     * Everything that should occur before the home is displayed should go in here.
     *
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        Staff staff = kpSmartModel.getCurrentUser();
        userLable.setText(staff.getFirstName());
        avatar.setImage(new Image(SendMailScreenController.class.getResourceAsStream("/img/" + staff.getUID() + ".png")));
        if (!staff.isManager()) {
            manageUser.setVisible(false);
            manageUser.setDisable(false);
            addNewUser.setVisible(false);
            addNewUser.setVisible(false);
        }

    }

    /**
     * this method is used to allow the user to return back home.
     *
     * @param event
     */
    private void returnHome(ActionEvent event) {
        Parent homeScreen = null;
        try {
            homeScreen = FXMLLoader.load(UserSettingController.class.getResource("/fxml/user settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene homeScene = new Scene(homeScreen);
        Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tempStage.setScene(homeScene);
        tempStage.show();
    }

    /**
     * to set the KPSmodels class reference.
     *
     * @param kpsModel
     */
    public static void setKpSmartModel(KPSmartModel kpsModel) {
        kpSmartModel = kpsModel;
    }
}
