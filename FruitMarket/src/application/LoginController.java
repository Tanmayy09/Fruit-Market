package application;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable
{
	@FXML
    private AnchorPane SetPassword_form;

    @FXML
    private Hyperlink already_account;

    @FXML
    private Button change_password;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private TextField forget_username;

    @FXML
    private Button forget_back;

    @FXML
    private AnchorPane forget_form;

    @FXML
    private Button forget_nextbtn;

    @FXML
    private Hyperlink login_create;

    @FXML
    private Hyperlink login_forget;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;

    @FXML
    private Button logint_button;

    @FXML
    private PasswordField newpassword;

    @FXML
    private Button set_back;

    @FXML
    private RadioButton sign_female;

    @FXML
    private TextField sign_location;

    @FXML
    private RadioButton sign_male;

    @FXML
    private PasswordField sign_password;

    @FXML
    private TextField sign_username;

    @FXML
    private Button signbtn;

    @FXML
    private AnchorPane signup_form;

    
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    
    
    
    
    
    // making login button
    public void loginbtn()
    {
    	if(login_username.getText().isEmpty() || login_password.getText().isEmpty())
    	{
    		alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setTitle("Login Error");
    		alert.setContentText("Username & password is empty!");
    		alert.showAndWait();
    	}
    	else
    	{
    		String takingData = "SELECT username,password FROM login_details WHERE username=? and password=?";
    		connect = Database.connectdb();
    		
    		try
    		{
    			prepare = connect.prepareStatement(takingData);
    			prepare.setString(1, login_username.getText());
    			prepare.setString(2, login_password.getText());
    			result = prepare.executeQuery();
    			
    			if(result.next())
    			{
    				alert = new Alert(AlertType.INFORMATION);
    	    		alert.setHeaderText(null);
    	    		alert.setTitle("Login Success");
    	    		alert.setContentText("Login Successful");
    	    		alert.showAndWait();
    	    		
    	    		Parent root =FXMLLoader.load(getClass().getResource("FruitMain.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene((Parent) root);
					stage.setTitle("Fruit Market");
					stage.setMinHeight(600);
					stage.setMinWidth(850);
					stage.setScene(scene);
					stage.show();
					
					login_form.getScene().getWindow().hide();
    			}
    			else
    			{
    				alert = new Alert(AlertType.ERROR);
    	    		alert.setHeaderText(null);
    	    		alert.setTitle("Login Error");
    	    		alert.setContentText("Invalid username or password");
    	    		alert.showAndWait();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		
    	}
    }
    
    
    
    //making signing up button
    public void signupbtn()
    {
    	if(sign_username.getText().isEmpty() || sign_password.getText().isEmpty() || ((sign_male.isSelected() && sign_female.isSelected())||(!sign_male.isSelected() && !sign_female.isSelected())) 
    			|| sign_location.getText().isEmpty())
    	{
    		alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setTitle("Signing Error");
    		alert.setContentText("Please fill all fields");
    		alert.showAndWait();
    	}
    	else
    	{
    		String registerData = "INSERT INTO login_details (username,password,gender,location)"+"VALUES(?,?,?,?)";
    		connect = Database.connectdb();
    		
    		try
    		{
    			String checkuser = "SELECT username FROM login_details WHERE username ='"+sign_username.getText()+"'";
    			prepare = connect.prepareStatement(checkuser);
    			result = prepare.executeQuery();
    			
    			if(result.next())
    			{
    				alert = new Alert(AlertType.WARNING);
    	    		alert.setHeaderText(null);
    	    		alert.setTitle("user error");
    	    		alert.setContentText("Username is already taken!!!");
    	    		alert.showAndWait();
    			}
    			else if(sign_password.getText().length()<5)
    			{
    				alert = new Alert(AlertType.ERROR);
    	    		alert.setHeaderText(null);
    	    		alert.setTitle("Password Error");
    	    		alert.setContentText("Password length should be 5");
    	    		alert.showAndWait();
    			}
    			else
    			{
    				prepare = connect.prepareStatement(registerData);
    				prepare.setString(1, sign_username.getText());
    				prepare.setString(2, sign_password.getText());
    				prepare.setString(3, gender());
    				prepare.setString(4, sign_location.getText());
    				
    				prepare.executeUpdate();
    				
    				alert = new Alert(AlertType.INFORMATION);
    	    		alert.setHeaderText(null);
    	    		alert.setTitle("Success");
    	    		alert.setContentText("Account created successfully");
    	    		alert.showAndWait();
    	    		
    	    		sign_username.setText("");
    	    		sign_password.setText("");
    	    		sign_location.setText("");
    	    		
    	    		signup_form.setVisible(false);
    	    		login_form.setVisible(true);
    			}
    				
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }
    
    
    
    
    // selecting gender
    private String gen="";
    public String gender()
    {
    	
    	if(sign_male.isSelected())
    	{
    		gen = "Male";
    	}
    	else if(sign_female.isSelected())
    	{
    		gen = "Female";
    	}
    	return gen;
    }
    
    
    
    
    
    //switching form
    public void switchform(ActionEvent event)
    {
    	if(event.getSource()==login_create)
    	{
    		login_form.setVisible(false);
    		signup_form.setVisible(true);
    		forget_form.setVisible(false);
    		SetPassword_form.setVisible(false);
    		
    		login_username.setText("");
    		login_password.setText("");
    	}
    	else if(event.getSource()==login_forget)
    	{
    		login_form.setVisible(false);
    		signup_form.setVisible(false);
    		forget_form.setVisible(true);
    		SetPassword_form.setVisible(false);
    	}
    	else if(event.getSource()==already_account)
    	{
    		login_form.setVisible(true);
    		signup_form.setVisible(false);
    		forget_form.setVisible(false);
    		SetPassword_form.setVisible(false);
    		sign_username.setText("");
    		sign_password.setText("");
    		sign_location.setText("");
    	}
    }
    
    
    // switching back
    public void switchback(ActionEvent event)
    {
    	if(event.getSource()==set_back)
    	{
    		login_form.setVisible(false);
    		signup_form.setVisible(false);
    		forget_form.setVisible(true);
    		SetPassword_form.setVisible(false);
    	}
    	else if(event.getSource()==forget_back)
    	{
    		login_form.setVisible(true);
    		signup_form.setVisible(false);
    		forget_form.setVisible(false);
    		SetPassword_form.setVisible(false);
    	}
    }
    
    
    
    
    // forget option
    public void forgetbtn()
    {
    	if(forget_username.getText().isEmpty())
    	{
    		alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText(null);
    		alert.setTitle("Warning");
    		alert.setContentText("Enter username fisrt");
    		alert.showAndWait();
    	}
    	else
    	{
    		String checkuser = "SELECT username FROM login_details WHERE username =?";
    		connect = Database.connectdb();
    		
    		try
    		{
    			prepare = connect.prepareStatement(checkuser);
    			prepare.setString(1, forget_username.getText());
    			result = prepare.executeQuery();
    			
    			if(result.next())
    			{
    				forget_form.setVisible(false);
    	    		SetPassword_form.setVisible(true);
    			}
    			else
    			{
    				alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error");
    				alert.setHeaderText(null);
    				alert.setContentText("Incorrect username");
    				alert.showAndWait();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }
    
    
    
    // changed password
    public void changedbtn()
    {
    	if(newpassword.getText().isEmpty() || confirmpassword.getText().isEmpty())
    	{
    		alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Fill password first");
			alert.showAndWait();
    	}
    	else
    	{
    		if(newpassword.getText().equals(confirmpassword.getText()))
    		{
    			String updatePass = "UPDATE login_details SET password ='"+newpassword.getText()+"'";
    			connect = Database.connectdb();
    			
    			try
    			{
    				prepare = connect.prepareStatement(updatePass);
    				prepare.executeUpdate();
    				
    				alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setHeaderText(null);
					alert.setContentText("Succesfully Changed Password");
					alert.showAndWait();
					
					login_form.setVisible(true);
		    		signup_form.setVisible(false);
		    		forget_form.setVisible(false);
		    		SetPassword_form.setVisible(false);
		    		
		    		newpassword.setText("");
		    		confirmpassword.setText("");
		    		forget_username.setText("");
    			}
    			catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		else
    		{
    			alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("Password doesn't match");
				alert.showAndWait();
    		}
    	}
    }
    
    
    
    
    
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
