package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea chatText;
    @FXML
    TextField chatMessage;

    public void addText() {
        if (!chatMessage.getText().isEmpty()) {
            chatText.appendText(new Date().toString() + ": " + chatMessage.getText());
            chatText.appendText("\n");
            chatMessage.setText("");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatMessage.setText("");
        chatText.appendText(new Date().toString() + ": " + "New chat started" + "\n");
    }

     public void addTextOnEnter (KeyEvent event)  {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
//                if (event.isShiftDown()) {
//                    chatMessage.appendText(System.getProperty("line.separator"));
//                } else {
                    addText();
//                }
            }
        };
}
