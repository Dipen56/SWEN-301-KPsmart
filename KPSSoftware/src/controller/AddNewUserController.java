package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
 * Created by Dipen on 25/05/2017.
 */
public class AddNewUserController implements Initializable {
    private static KPSmartModel kpSmartModel;
    @FXML
    private Label userLable;
    @FXML
    private Label errorLabel;
    @FXML
    private ImageView avatar;
    @FXML
    private TextField firstNameTextfield;
    @FXML
    private TextField lastNameTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField phoneTextfield;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private CheckBox managerCheckBox;
    @FXML
    private CheckBox clerkCheckBox;

    public AddNewUserController() {
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
            Parent changePasswordScreen = FXMLLoader.load(AddNewUserController.class.getResource("/fxml/ChangePassword.fxml"));
            Scene changePasswordScene = new Scene(changePasswordScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(changePasswordScene);
            tempStage.show();
        } else if (event.toString().contains("ManageUser")) {
            Parent manageUserScreen = FXMLLoader.load(AddNewUserController.class.getResource("/fxml/manage user.fxml"));
            Scene manageUserScene = new Scene(manageUserScreen);
            Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tempStage.setScene(manageUserScene);
            tempStage.show();

        } else if (event.toString().contains("logout")) {
            //TODO; POP up dialog box to ask the user if they are sure want to logout
            DialogBox.LogoutyMsg("Logout", "Are you sure to logout?");
            Parent loginScreen = FXMLLoader.load(AddNewUserController.class.getResource("/fxml/login screen.fxml"));
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
        if (event.toString().contains("reset")) {
            clearContent(event);
        } else if (event.toString().contains("discard")) {
            returnUserManagement(event);
        } else if (event.toString().contains("accept")) {
            //TODO need a dialog box to show defaul password.
            if (firstNameTextfield.getText().equals("") || lastNameTextfield.getText().equals("") || emailTextfield.getText().equals("") ||
                    phoneTextfield.getText().equals("") || usernameTextfield.getText().equals("") || (!managerCheckBox.isSelected() && !clerkCheckBox.isSelected())) {
                errorLabel.setText("Error :( Please make sure all the information is provided Correctly");
            } else {
                if (managerCheckBox.isSelected()) {
                    boolean userCreated = kpSmartModel.addNewUser(firstNameTextfield.getText(), lastNameTextfield.getText(), emailTextfield.getText(), phoneTextfield.getText(), usernameTextfield.getText(), true);
                    if (!userCreated) {
                        errorLabel.setText("Error :( Please try again with a different username");
                    } else {
                        clearContent(event);
                    }
                } else {
                    boolean userCreated = kpSmartModel.addNewUser(firstNameTextfield.getText(), lastNameTextfield.getText(), emailTextfield.getText(), phoneTextfield.getText(), usernameTextfield.getText(), false);
                    if (!userCreated) {
                        errorLabel.setText("Error :( Please try again with a different username");
                    } else {
                        clearContent(event);
                    }
                }
            }
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
        avatar.setImage(new Image(AddNewUserController.class.getResourceAsStream("/img/" + staff.id + ".png")));

    }

    private void clearContent(ActionEvent event) {
        Parent changePasswordScreen = null;
        try {
            changePasswordScreen = FXMLLoader.load(AddNewUserController.class.getResource("/fxml/AddNewUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene changePasswordScene = new Scene(changePasswordScreen);
        Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tempStage.setScene(changePasswordScene);
        tempStage.show();
    }


    private void returnUserManagement(ActionEvent event) {
        Parent userManagementscreen = null;
        try {
            userManagementscreen = FXMLLoader.load(AddNewUserController.class.getResource("/fxml/user settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene userManagementScene = new Scene(userManagementscreen);
        Stage tempStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tempStage.setScene(userManagementScene);
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
