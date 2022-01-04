package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author Aviel  
 * @author Sahar
 * Interface of back button.
 * Implemented by all the fxml controllers.
 */
public interface ControllerInterface {

    @FXML
    void Back(ActionEvent event) throws IOException;
}
