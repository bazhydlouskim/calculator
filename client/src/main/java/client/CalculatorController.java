package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class CalculatorController {

	@FXML
	private Label label;
	private Client client;

	@FXML
	private void handleButtonAction(ActionEvent event) {

		if (event.getTarget().getClass() != Button.class) return;
		Response result = client.sendEvent(((Button)event.getTarget()).getText());
		processResult(result);
	}

	private void processResult(Response result) {
		System.out.println(result);
		if (result != null) {
			System.out.println("Is Successful: " + result.isSuccessful);
			System.out.println(result.result);
			if (result.isSuccessful) {
				if (result.result.length() > 2 && result.result.charAt(result.result.length() - 1) == '0')
					label.setText(result.result.substring(0, result.result.length()-2));
				else
					label.setText(result.result);
			} else {
				showError(result.result);
			}
		}
	}

	private void showError(String prompt) {
		Alert alert = new Alert(Alert.AlertType.ERROR );
		alert.setTitle("Invalid Action");
		alert.setHeaderText(null);
		alert.setContentText(prompt);

		ButtonType DismissButton = new ButtonType("Dismiss");
		ButtonType ResetButton = new ButtonType("Reset");

		alert.getButtonTypes().setAll(DismissButton, ResetButton);

		Optional<ButtonType> result = alert.showAndWait();
		Response response;
		if (result.isPresent() && result.get() == DismissButton){
			response = client.sendEvent("discard");
		} else {
			// User clicked "Reset" button or closed the dialog
			response = client.sendEvent("reset");
		}
		processResult(response);
	}

	public void initialize() {
		// TODO
		client = new Client();
	}
}
