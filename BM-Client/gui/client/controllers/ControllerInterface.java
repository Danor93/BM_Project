package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface ControllerInterface {

    @FXML
    void Back(ActionEvent event) throws IOException;
}
