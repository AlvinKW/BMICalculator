package application;

/*
 * Name: Alvin Kwan
 * CS422 Midterm Program
 * 
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMICalculatorController {
	
	//formatter for BMI to 1 decimal place
	private static final NumberFormat bmi = new DecimalFormat("#0.0"); 

	// GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private ChoiceBox<String> weightChoiceBox = new ChoiceBox<>();
    
    @FXML
    private ChoiceBox<String> heightChoiceBox = new ChoiceBox<>();

    @FXML
    private Label bmiValueLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label bmiRangeLabel;
    
    @FXML
    private Label errorLabel;

    /**
     * Calculate Button Listener: listens for if calculate button is pressed.
     * Calculates BMI given weight and height inputs based on metric unit. 
     * Displays to user BMI Result: Value, Status, Range.
     * 
     * @param event
     */
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
    	errorLabel.setText(""); //clear error label
    	try {
    		Double weight = new Double(weightTextField.getText()); //get value of user weight
    		if(weightChoiceBox.getValue() == "kg") { //if weight is in KG
        		weight = convertKgToLbs(weight);	 //convert to LBS
        	}
    		
    		if(weight == 0) { //check if weight is 0
    			errorLabel.setText("Enter valid number"); //error label
    		}
    		
    		Double height = new Double(heightTextField.getText()); //get value of user height
    		if(heightChoiceBox.getValue() == "cm") { //if height is in CM
        		height = convertCmToIn(height); 	 //convert to inch
        	}
    		
    		if(height == 0) { //check if height is 0
    			errorLabel.setText("Height cannot be zero"); //error label
    		}
    		
    		Double bmi = calculateBMI(weight, height); //calculate user BMI
    		resultBMI(bmi); //display result to user
    	}
    	catch(NumberFormatException ex) {
    		errorLabel.setText("Enter valid number"); //error label
    		heightTextField.setText(""); //clear height text field
    		heightTextField.selectAll();
    		heightTextField.requestFocus();
      		weightTextField.setText(""); //clear weight text field
    		weightTextField.selectAll();
    		weightTextField.requestFocus(); //refocus at weight text field
    	}
    }

    /**
     * Rest Button Listener: Listens if reset button is pressed.
     * Clears and reset application.
     * 
     * @param event
     */
    @FXML
    private void resetButtonPressed(ActionEvent event) {
    	errorLabel.setText(""); //clear error label
    	bmiValueLabel.setText(""); // clear BMI value
		statusLabel.setText(""); //clear status
		bmiRangeLabel.setText(""); //clear BMI range
		weightTextField.setText(""); //clear weight text field
		heightTextField.setText(""); //clear height text field
		weightTextField.selectAll();
		weightTextField.requestFocus(); //refocus at weight text field
    }
    
    /**
     * Called by FXMLLoader to initialize controller
     */
    public void initialize() {
    	weightChoiceBox.getItems().addAll("lbs", "kg"); //add LBS and KG option for drop down 
    	weightChoiceBox.setValue("lbs"); //initialize value to LBS
    	heightChoiceBox.getItems().addAll("in", "cm"); //add IN and CM option for drop down
    	heightChoiceBox.setValue("in");	//initialize value to inch
    }
    
    /**
     * Converts kilogram to pounds 
     * 
     * @param kg
     * @return converted value from KG to LBS
     */
    public Double convertKgToLbs(Double kg) {
    	double val = 2.20462262;
    	
    	return kg * val; //1 KG = 2.20462262 LBS
    }
    
    /**
     * Converts centimeter to inch
     * @param cm
     * @return converted value from cm to inch
     */
    public Double convertCmToIn(Double cm) {
    	double val = 0.393701;
    	
    	return cm * val; //1 CM = 0.393701 IN
    }
    
    /**
     * Calculates BMI given weight and height of user input.
     * 
     * @param weight
     * @param height
     * @return BMI value
     */
    public Double calculateBMI(Double weight, Double height) {
    	Double value = new Double(703);
    	Double heightSquared = Math.pow(height, 2);
    	
    	return (value * weight) / heightSquared; 
    }
    
    /**
     * Display result of BMI after calculation.
     * Puts user in one of 4 categories:  underweight ( BMI < 18.5),
     * normal weight (BMI 18.5-24.9), overweight (BMI 25.0 - 29.9) 
     * or overweight (BMI > 30).
     *   
     * @param bmi
     */
    public void resultBMI(Double bmi) {
    	String bmiValue = BMICalculatorController.bmi.format(bmi); //format bmi to 1 decimal place
    	bmiValueLabel.setText(bmiValue); //set BMI value
    	
    	if(bmi < 18.5) {
    		statusLabel.setText("Underweight");
    		bmiRangeLabel.setText("( BMI < 18.5)");
    	}else if(bmi >= 18.5 && bmi<= 24.9) {
    		statusLabel.setText("Normal weight");
    		bmiRangeLabel.setText("(BMI 18.5 - 24.9)");
    	}else if(bmi >= 25 && bmi <= 29.9) {
    		statusLabel.setText("Overweight");
    		bmiRangeLabel.setText("(BMI 25.0 - 29.9)");
    	}else if(bmi > 29.9) {
    		statusLabel.setText("Overweight");
    		bmiRangeLabel.setText("(BMI > 30)");
    	}else {
    		//if does not fall in category then set value to empty string
    		bmiValueLabel.setText("");
    		statusLabel.setText("");
    		bmiRangeLabel.setText("");
    	}
    }

}
