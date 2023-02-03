package gov.iti.link.presentation.controllers;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.cj.jdbc.Blob;

import gov.iti.link.business.DTOs.ContactDto;
import gov.iti.link.business.DTOs.InvitationDTO;
import gov.iti.link.business.DTOs.UserDTO;
import gov.iti.link.business.services.ServiceManager;
import gov.iti.link.business.services.StageManager;
import gov.iti.link.business.services.StateManager;
import gov.iti.link.business.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

import javafx.scene.text.Text;

public class ChatController implements Initializable {

   
    @FXML
    private AnchorPane MAIN_FRM;

    @FXML
    private ScrollPane SCROLL_BAR;

    @FXML
    private ScrollPane SCROLL_BAR_CONTACTS;

    @FXML
    private TextField SEARCH_TXT;

    @FXML
    private HBox TITLE_FINAL_CONTAINER;

    @FXML
    private Button btnSend;

    @FXML
    private ListView<Parent> lstFriend;

    @FXML
    private VBox messageContainer;

    @FXML
    private TextArea txtMessage;

    @FXML
    private ImageView img;

    @FXML
    private Label lblUserName;

    @FXML
    Circle circleUserImage;

    private ServiceManager serviceManager;
    private UserService userService;
    private StateManager stateManager;
    private StageManager stageManager;

    ObservableList<Parent> friendsList = FXCollections.observableArrayList();
    //ObservableList<UserDTO> users = FXCollections.observableArrayList();

    public ChatController() {
        serviceManager = ServiceManager.getInstance();
        userService = serviceManager.getUserService();
        stateManager = StateManager.getInstance();
        List<InvitationDTO> invitations;
        try {
            invitations = userService.getInvitations(stateManager.getUser().getPhone());
            stateManager.getUser().setInvitations(invitations) ;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    
        stageManager = StageManager.getInstance();
    }

    @FXML
    void sendMessage(ActionEvent event) {
    }

    @FXML
    void onProfile() {
        stageManager.switchToProfile();
    }
    @FXML
    void onLogOut() {
        stageManager.switchToLogin();
    }

    @FXML
    void showNewDialog() {
        System.out.println("Add contact");
      
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/components/add-contact.fxml"));
            DialogPane addDialogPane = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addDialogPane);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblUserName.setText(stateManager.getUser().getName());
        //circleUserImage.setFill(new ImagePattern(new Image(stateManager.getUser().getPicture())));
        try {
        
            Vector<ContactDto> allContacts = userService.getAllContacts(stateManager.getUser().getPhone());
            for (ContactDto contactDto : allContacts) {
                addCardinListView(contactDto.getImageUrl(), contactDto.getName(),contactDto.getPhoneNumber(),true);
            }
           
            

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    void addCardinListView(String imageUrl, String name, String phone, Boolean isActive) {
        String pageName = "lblcontact";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(String.format("/views/%s.fxml", pageName)));
        Parent label;
        try {
            label = fxmlLoader.load();
            LabelContactController labelContactController = fxmlLoader.getController();
            labelContactController.setName(name);
            labelContactController.setImage(imageUrl);
            labelContactController.setPhone(phone);
            labelContactController.setStatus(isActive);
            friendsList.add(label);
            lstFriend.setItems(friendsList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    void changeOnFriendState() {
        
        
    lstFriend.refresh();
    }


}
