package application;

import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FruitMainController implements Initializable {

	
	@FXML
    private Button Add_cart;

    @FXML
    private VBox choosen_product;

    @FXML
    private ImageView fruit_image;

    @FXML
    private Label fruit_name;

    @FXML
    private Label fruit_price;

    @FXML
    private TextField search_box;

    @FXML
    private Button search_btn;

    @FXML
    private Button signout_btn;
    
    @FXML
    private GridPane grid_fruit;
    
    @FXML
    private ScrollPane scroll_fruit;
	
	
	private PreparedStatement prepare;
	private Connection connect;
	private ResultSet result;
	private Alert alert;
	private ActionPerformed ac;
	
	
	// signing out
	public void signout()
	{
		try
		{
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Logout");
			alert.setContentText("Are you Sure?");
			Optional<ButtonType> option = alert.showAndWait();
			
			if(option.get().equals(ButtonType.OK))
			{
				signout_btn.getScene().getWindow().hide();
				Parent root =FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene((Parent) root);
				stage.setTitle("Fruit Market Registration");
				stage.setMinHeight(450);
				stage.setMinWidth(700);
				stage.setScene(scene);
				stage.show();
			}
			else
			{
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Logout");
				alert.setContentText("Operation Cancelled");
				alert.showAndWait();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	 // display to Front
    public void setChosenFruit(Fruit fruit) {
        fruit_name.setText(fruit.getName());
        fruit_price.setText(fruit.getPrice()+"Rs");
        String path = fruit.getImgSrc();
        Image image = new Image(path,216,152,false,true);
        fruit_image.setImage(image);
        choosen_product.setStyle("-fx-background-color : linear-gradient(to bottom right,"+fruit.getColor()+");\n" +
                "    -fx-background-radius: 30;");
    }
    
		
	
	
	
	// list for fruit
	private List<Fruit> ff;
    private Image image;
	private List<Fruit> getData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;
        
        fruit = new Fruit();
        fruit.setName("Apple");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/apple.png");
        fruit.setColor("#FF0000,#800000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Apricot");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/apricot.png");
        fruit.setColor("#FFDAB9,#FFB6C1");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Banana");
        fruit.setPrice(70.0);
        fruit.setImgSrc("file:src/img/banana.png");
        fruit.setColor("#FFEB3B,#FF9800");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("BlackBerry");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/blackberry.png");
        fruit.setColor("#4B0082,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Cherry");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/img/cherry.png");
        fruit.setColor("#FF0033,#800000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Coconut");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/img/coconut.png");
        fruit.setColor("#8B4513,#FFFFFF");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Gooseberry");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/gooseberry.png");
        fruit.setColor(" #A2C93E, #427F08");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Grapes");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/img/grapes.png");
        fruit.setColor("#6930c3,#5e60ce");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Green Apple");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/green_apple.png");
        fruit.setColor(" #b3ffab, #00b300");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Green Avocado");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/green_avocado.png");
        fruit.setColor("#C4DF7A, #4B6B28");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Green Pear");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/green_pear.png");
        fruit.setColor("#A8E6CF, #63B27C");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Guava");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/guava.png");
        fruit.setColor("#FF6B6B, #A8D18D");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Kiwi");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/img/kiwi.png");
        fruit.setColor("#B0E57C, #4E9A43");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lychee");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/lychee.png");
        fruit.setColor("#FFC0CB, #E75480");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Mango");
        fruit.setPrice(100.0);
        fruit.setImgSrc("file:src/img/mango.png");
        fruit.setColor("#FFD700, #FFA500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Orange");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/orange.png");
        fruit.setColor("#FFA500, #FF4500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Papaya");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/papaya.png");
        fruit.setColor("#FFD700, #FFA500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Peach");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/peach.png");
        fruit.setColor("#FFDAB9, #FFA07A");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pineapple");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/pineapple.png");
        fruit.setColor("#FFD700, #008000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pomegranate");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/pomegranate.png");
        fruit.setColor("#C0392B, #E74C3C");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Red Dragon Fruit");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/red_dragon_fruit.png");
        fruit.setColor("#FF0000, #FF3399, #FF66CC");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Red Raspberry");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/red_raspberry.png");
        fruit.setColor("#FF0000, #E30B5D");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Strawberry");
        fruit.setPrice(140.0);
        fruit.setImgSrc("file:src/img/strawberry.png");
        fruit.setColor("#FFB6C1, #FF1493");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Sweet Lemon");
        fruit.setPrice(110.0);
        fruit.setImgSrc("file:src/img/sweet_lemon.png");
        fruit.setColor(" #B2FF69, #76FF00");
        fruits.add(fruit);
      
        fruit = new Fruit();
        fruit.setName("Watermelon");
        fruit.setPrice(150.0);
        fruit.setImgSrc("file:src/img/watermelon.png");
        fruit.setColor("#00FF00,#FFA07A,#00FF00");
        fruits.add(fruit);
     
        return fruits;
    }
    // showing to fruit grid
    public void showfruitdata()
    {
    	ff = new ArrayList(getData());
    	
    	if (ff.size() > 0) {
            setChosenFruit(ff.get(0));
            ac = new ActionPerformed() {
                @Override
                public void Clicked(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
    	
    	
        int column = 0;
        int row = 1;
        grid_fruit.getChildren().clear();
        grid_fruit.getRowConstraints().clear();
        grid_fruit.getColumnConstraints().clear();
        try 
        {
        	for(int i=0; i<ff.size(); i++)
            {
            	FXMLLoader Loader = new FXMLLoader();
        		Loader.setLocation(getClass().getResource("Items.fxml"));
        		AnchorPane box = Loader.load();
            	ItemController itemController = Loader.getController();
               
            	itemController.setFruitData(ff.get(i),ac);
            	if (column == 3) 
            	{
            		column = 0;
            		row++;
            	}

            	grid_fruit.add(box ,column++, row); //(child,column,row)
                //set grid width
            	grid_fruit.setMinWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
            	grid_fruit.setMinHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(box, new Insets(10));
            }
        	
        	
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
    
   
    
    // list for vegetables
    private List<Fruit> getvegData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("Beetroot");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/beetroot.png");
        fruit.setColor("#8b0000, #660033");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Bell pepper");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/bell_pepper.png");
        fruit.setColor("#8CC63F, #377E3F");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Cabbage");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/cabbage.png");
        fruit.setColor("#90FF00, #00A74A,#000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Carrot");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/carrot.png");
        fruit.setColor("#98FB98, #5F9F5F, #228B22, #008000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Cauliflower");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/cauliflower.png");
        fruit.setColor("#8AFF5A, #008000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Corn");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/corn.png");
        fruit.setColor("#FFFF99, #FFD700");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Cucumber");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/cucumber.png");
        fruit.setColor("#8CC152, #5CB85C");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Daikon");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/daikon.png");
        fruit.setColor("#ffffff, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Garlic");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/garlic.png");
        fruit.setColor("#A7745B,#FFF5E1");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Ginger");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/ginger.png");
        fruit.setColor("#FFA500, #FF4500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Green beans");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/green_beans.png");
        fruit.setColor("#FFFF99, #8BC34A");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Mushroom");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/mushroom.png");
        fruit.setColor("#808080, #8b5a2b");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Okra");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/okra.png");
        fruit.setColor("#2ecc71, #f1c40f");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Onion");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/onion.png");
        fruit.setColor("#B22222,#800080");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Peas");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/peas.png");
        fruit.setColor("#71BF44, #4B892A");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Potato");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/potato.png");
        fruit.setColor("#F4EBC3, #D4B483");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pumpkin");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/pumpkins.png");
        fruit.setColor("#FFFF99, #FFD700");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Sweet Pepper");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/sweet-pepper.png");
        fruit.setColor("#00FF00, #FF0000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Tomato");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgveg/tomato.png");
        fruit.setColor("#FF0000, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Turnip");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgveg/turnip.png");
        fruit.setColor("#7b68ee, #ffffff");
        fruits.add(fruit);
        
        return fruits;
    }
    // showing to fruit grid
    public void showVegdata()
    {
    	ff = new ArrayList(getvegData());
    	
    	if (ff.size() > 0) {
            setChosenFruit(ff.get(0));
            ac = new ActionPerformed() {
                @Override
                public void Clicked(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
    	
    	
        int column = 0;
        int row = 1;
        grid_fruit.getChildren().clear();
        grid_fruit.getRowConstraints().clear();
        grid_fruit.getColumnConstraints().clear();
        try 
        {
        	for(int i=0; i<ff.size(); i++)
            {
            	FXMLLoader Loader = new FXMLLoader();
        		Loader.setLocation(getClass().getResource("Items.fxml"));
        		AnchorPane box = Loader.load();
            	ItemController itemController = Loader.getController();
               
            	itemController.setFruitData(ff.get(i),ac);
            	if (column == 3) 
            	{
            		column = 0;
            		row++;
            	}

            	grid_fruit.add(box ,column++, row); //(child,column,row)
                //set grid width
            	grid_fruit.setMinWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
            	grid_fruit.setMinHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(box, new Insets(10));
            }
        	
        	
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    //list for beverage
    private List<Fruit> getBeverageData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("Boost");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/boost.png");
        fruit.setColor("#FF0000, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Coco Cola");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/coco_cola.png");
        fruit.setColor("#FF0000, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Cola Can");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/coco_cola_can.png");
        fruit.setColor("#FF0033, #CC0011");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Complan");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/complan.png");
        fruit.setColor("#996515, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Corona Extra");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/corona_extra.png");
        fruit.setColor("#A7745B,#FFF5E1");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Mountain Dew");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/dew.png");
        fruit.setColor("#90FF00, #00A74A");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Dew Can");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/dew_can.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Fanta");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/fanta.png");
        fruit.setColor("#FF8C00, #FFA500, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Fanta Can");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/fanta_can.png");
        fruit.setColor("#000000,#FF8C00, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Green Tea");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/green_tea.png");
        fruit.setColor("#000000,#4caf50, #388e3c");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Heineken");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/heineken.png");
        fruit.setColor("#4caf50, #388e3c, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Horlicks");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/horlicks.png");
        fruit.setColor("#000000, #E8D3A2");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Jack Daniels");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/jack_daniels.png");
        fruit.setColor("#000000, #ffffff");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Lanson");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/lason.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Monster");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/monster.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Pack of 5");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/pack_of_5.png");
        fruit.setColor("#FF0000,#0033A0,#FFA500,#1EB53A,#00A859,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pepsi");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/pepsi.png");
        fruit.setColor("#000000,#0033A0");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Pepsi Can");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/pepsi_can.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Red Bull");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbeverage/red_bull.png");
        fruit.setColor("#FF0000,#0033A0,#000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Red Label");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/red_label.png");
        fruit.setColor("#8B0000, #FF4500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Sprite");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/sprite.png");
        fruit.setColor("#00A859,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Sprite Can");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/sprite_can.png");
        fruit.setColor("#000000, #00A859");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Tang");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/tang.png");
        fruit.setColor("#FFA500,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Tata Tea");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/tata_tea.png");
        fruit.setColor("#A52A2A, #228B22");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Thumbs up");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbeverage/thumbs_up.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        return fruits;
    }
    
    
    // showing to fruit grid
    public void showBeveragedata()
    {
    	ff = new ArrayList(getBeverageData());
    	
    	if (ff.size() > 0) {
            setChosenFruit(ff.get(0));
            ac = new ActionPerformed() {
                @Override
                public void Clicked(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
    	
    	
        int column = 0;
        int row = 1;
        grid_fruit.getChildren().clear();
        grid_fruit.getRowConstraints().clear();
        grid_fruit.getColumnConstraints().clear();
        try 
        {
        	for(int i=0; i<ff.size(); i++)
            {
            	FXMLLoader Loader = new FXMLLoader();
        		Loader.setLocation(getClass().getResource("Items.fxml"));
        		AnchorPane box = Loader.load();
            	ItemController itemController = Loader.getController();
               
            	itemController.setFruitData(ff.get(i),ac);
            	if (column == 3) 
            	{
            		column = 0;
            		row++;
            	}

            	grid_fruit.add(box ,column++, row); //(child,column,row)
                //set grid width
            	grid_fruit.setMinWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
            	grid_fruit.setMinHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(box, new Insets(10));
            }
        	
        	
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    //list for beverage
    private List<Fruit> getsnackData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("20-20");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/20-20.png");
        fruit.setColor("#FF0000, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("50-50 ");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/50-50.png");
        fruit.setColor("#FF0000, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Bingo Mad Angle");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/bingo_mad.png");
        fruit.setColor("#FF0033, #CC0011");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Black Bourbon");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/black_bourbon.png");
        fruit.setColor("#996515, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Bombay Mix");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/bombay_mix.png");
        fruit.setColor("#A7745B,#FFF5E1");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Cheetos");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/cheetos.png");
        fruit.setColor("#90FF00, #00A74A");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Diamond Puff");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/diamond_puff.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Doritos BBQ");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/doritos_bbq.png");
        fruit.setColor("#FF8C00, #FFA500, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Doritos Collision");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/doritos_collision.png");
        fruit.setColor("#000000,#FF8C00, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Doritos Nacho");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/doritos_nacho.png");
        fruit.setColor("#000000,#4caf50, #388e3c");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Doritos Salted");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/doritps_salted.png");
        fruit.setColor("#4caf50, #388e3c, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Good Day");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/good_day.png");
        fruit.setColor("#000000, #E8D3A2");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Haldiram Bhujia");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/haldiram_bhujia.png");
        fruit.setColor("#000000, #ffffff");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Happy Happy");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/happy_happy.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Happy Happy Cake");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/happy_happy_cake.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Hide & Seek");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/hide&seek.png");
        fruit.setColor("#FF0000,#0033A0,#FFA500,#1EB53A,#00A859,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Kuch Kuch");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/kuch_kuch.png");
        fruit.setColor("#000000,#0033A0");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Lays BLT");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/lays_blt.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lays Chheddar");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgsnack/lays_chheddar.png");
        fruit.setColor("#FF0000,#0033A0,#000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Lays Classic");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/lays_classic.png");
        fruit.setColor("#8B0000, #FF4500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lays Gusto");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/lays_gusto.png");
        fruit.setColor("#00A859,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lays Salt");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/lays_salt.png");
        fruit.setColor("#000000, #00A859");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lays Tomato");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/lays_tomato.png");
        fruit.setColor("#FFA500,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lays Wavy");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/lays_wavy.png");
        fruit.setColor("#A52A2A, #228B22");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Magix");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/magix.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Moms Magic");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/moms_magic.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Monaco");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/monaco.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Moong Dal");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/moong_dal.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Murrukku");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/murrukku.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Oreo");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/oreo.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pack of 4");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/pack_of_4.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Parle G Bheem");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/parkeG_bheem.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Parle G");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/parleG.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pringle Paprika");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/pringle_paprika.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Soan Papdi");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/soan_papdi.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Soya Sticks");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/soya_sticks.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Tedhe Medhe");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgsnack/tedhe_medhe.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        
        return fruits;
    }
    
    
    // showing to fruit grid
    public void showsnackdata()
    {
    	ff = new ArrayList(getsnackData());
    	
    	if (ff.size() > 0) {
            setChosenFruit(ff.get(0));
            ac = new ActionPerformed() {
                @Override
                public void Clicked(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
    	
    	
        int column = 0;
        int row = 1;
        grid_fruit.getChildren().clear();
        grid_fruit.getRowConstraints().clear();
        grid_fruit.getColumnConstraints().clear();
        try 
        {
        	for(int i=0; i<ff.size(); i++)
            {
            	FXMLLoader Loader = new FXMLLoader();
        		Loader.setLocation(getClass().getResource("Items.fxml"));
        		AnchorPane box = Loader.load();
            	ItemController itemController = Loader.getController();
               
            	itemController.setFruitData(ff.get(i),ac);
            	if (column == 3) 
            	{
            		column = 0;
            		row++;
            	}

            	grid_fruit.add(box ,column++, row); //(child,column,row)
                //set grid width
            	grid_fruit.setMinWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
            	grid_fruit.setMinHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(box, new Insets(10));
            }
        	
        	
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
  //list for beverage
    private List<Fruit> getdairyData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("Bis");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/bis.png");
        fruit.setColor("#0000FF, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Bournville");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/bournville.png");
        fruit.setColor("#35281e, #8b735b");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Bueno");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/bueno.png");
        fruit.setColor("#35281e, #8b735b ,#000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Dairy Milk");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/dairy_milk.png");
        fruit.setColor("#35281E, #0000FF,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Dairy Milk Silk");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/dairy_silk_silk.png");
        fruit.setColor("#8B4513, #0033A0, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Ferrero Rocher");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/ferrero_rocher.png");
        fruit.setColor("#A17E46, #C2A659,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Fuse");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/fuse.png");
        fruit.setColor("#A17E46, #C2A659, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Galak");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/galak.png");
        fruit.setColor("#FF8C00, #FFA500, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("KitKat");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/kitkat.png");
        fruit.setColor("#FF0000, #FFFFFF ,#000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("KitKat Dark");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/KitKat_dark.png");
        fruit.setColor("#000000,#FF0000, #3D2200");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nescafe");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/nescafe.png");
        fruit.setColor("#8B4513, #A0522D, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Nestle Classic");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/nestle_classic.png");
        fruit.setColor("#000000, #E8D3A2");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nutella");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/nutella.png");
        fruit.setColor("#000000, #ffffff");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Oreo");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/oreo.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Snicker");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgdairy/snicker.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Twix");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgdairy/twix.png");
        fruit.setColor("#FF0000,#0033A0,#FFA500,#1EB53A,#00A859,#000000");
        fruits.add(fruit);
        
                
        return fruits;
    }
    
    
    // showing to fruit grid
    public void showdairydata()
    {
    	ff = new ArrayList(getdairyData());
    	
    	if (ff.size() > 0) {
            setChosenFruit(ff.get(0));
            ac = new ActionPerformed() {
                @Override
                public void Clicked(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
    	
    	
        int column = 0;
        int row = 1;
        grid_fruit.getChildren().clear();
        grid_fruit.getRowConstraints().clear();
        grid_fruit.getColumnConstraints().clear();
        try 
        {
        	for(int i=0; i<ff.size(); i++)
            {
            	FXMLLoader Loader = new FXMLLoader();
        		Loader.setLocation(getClass().getResource("Items.fxml"));
        		AnchorPane box = Loader.load();
            	ItemController itemController = Loader.getController();
               
            	itemController.setFruitData(ff.get(i),ac);
            	if (column == 3) 
            	{
            		column = 0;
            		row++;
            	}

            	grid_fruit.add(box ,column++, row); //(child,column,row)
                //set grid width
            	grid_fruit.setMinWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
            	grid_fruit.setMinHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(box, new Insets(10));
            }
        	
        	
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
  //list for beverage
    private List<Fruit> getbodyData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("Axe Twist");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/axe_twist.png");
        fruit.setColor("#FF0000, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Bela Herbs");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/bela_herbs.png");
        fruit.setColor("#FF0000, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Boss");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/boss.png");
        fruit.setColor("#FF0033, #CC0011");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Dettol BodyWash");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/detol_bodywash.png");
        fruit.setColor("#996515, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Dettol Handwash");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/detol_handwash.png");
        fruit.setColor("#A7745B,#FFF5E1");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Dettol Soap");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/detol_soap.png");
        fruit.setColor("#90FF00, #00A74A");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Dove Cremebad");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/dove_cremebad.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Dove Whitening");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/dove_whitening.png");
        fruit.setColor("#FF8C00, #FFA500, #000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Gillete Fusion");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/gillete_fusion.png");
        fruit.setColor("#000000,#FF8C00, #FFA500");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Lifebuoy HandWash");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/lifebuoy_handwash.png");
        fruit.setColor("#000000,#4caf50, #388e3c");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lifebuoy Soap");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/lifebuoy_soap.png");
        fruit.setColor("#4caf50, #388e3c, #000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Lux fresh");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/lux_fresh.png");
        fruit.setColor("#000000, #E8D3A2");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Lux Strawberry");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/lux_strawberry.png");
        fruit.setColor("#000000, #ffffff");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Nivea Cream");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/nivea_cream.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nivea Fresh");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/nivea_fresh.png");
        fruit.setColor("#000000, #00FF00");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Nivea Invisible");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/nivea_invisible.png");
        fruit.setColor("#FF0000,#0033A0,#FFA500,#1EB53A,#00A859,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nivea Power");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/nivea_power.png");
        fruit.setColor("#000000,#0033A0");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Nivea PowerFruit");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/nivea_powerfruit.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nivea Pure Impact");
        fruit.setPrice(80.0);
        fruit.setImgSrc("file:src/imgbody/nivea_pure_impact.png");
        fruit.setColor("#FF0000,#0033A0,#000000");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Nivea Shampoo");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/nivea_shampoo.png");
        fruit.setColor("#8B0000, #FF4500");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nivea Shaving Gel");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/nivea_shaving_gel.png");
        fruit.setColor("#00A859,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Nivea Wash Gel");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/nivea_wash_gel.png");
        fruit.setColor("#000000, #00A859");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Pro ease");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/pro-ease_pad.png");
        fruit.setColor("#FFA500,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Vaseline");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/vaseline.png");
        fruit.setColor("#A52A2A, #228B22");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Veet");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/veet.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        fruit = new Fruit();
        fruit.setName("Veet Cream");
        fruit.setPrice(120.0);
        fruit.setImgSrc("file:src/imgbody/veet_cream.png");
        fruit.setColor("#0033A0,#000000");
        fruits.add(fruit);
        
        return fruits;
    }
    
    
    // showing to fruit grid
    public void showBodydata()
    {
    	ff = new ArrayList(getbodyData());
    	
    	if (ff.size() > 0) {
            setChosenFruit(ff.get(0));
            ac = new ActionPerformed() {
                @Override
                public void Clicked(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
    	
    	
        int column = 0;
        int row = 1;
        grid_fruit.getChildren().clear();
        grid_fruit.getRowConstraints().clear();
        grid_fruit.getColumnConstraints().clear();
        try 
        {
        	for(int i=0; i<ff.size(); i++)
            {
            	FXMLLoader Loader = new FXMLLoader();
        		Loader.setLocation(getClass().getResource("Items.fxml"));
        		AnchorPane box = Loader.load();
            	ItemController itemController = Loader.getController();
               
            	itemController.setFruitData(ff.get(i),ac);
            	if (column == 3) 
            	{
            		column = 0;
            		row++;
            	}

            	grid_fruit.add(box ,column++, row); //(child,column,row)
                //set grid width
            	grid_fruit.setMinWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefWidth(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxWidth(Region.USE_PREF_SIZE);
                
                //set grid height
            	grid_fruit.setMinHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setPrefHeight(Region.USE_COMPUTED_SIZE);
            	grid_fruit.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(box, new Insets(10));
            }
        	
        	
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    public void ClickFruit(MouseEvent event)
    {
    	showfruitdata();
    }
    
    public void ClickVeg(MouseEvent event)
    
    {
    	showVegdata();
    }
    
    public void ClickBeverage(MouseEvent event)
    {
    	showBeveragedata();
    }
    public void ClickSnack(MouseEvent event)
    {
    	showsnackdata();
    }
    public void ClickDairy(MouseEvent event)
    {
    	showdairydata();
    }
    public void ClickBody(MouseEvent event)
    {
    	showBodydata();
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// TODO Auto-generated method stub
		showfruitdata();
		showVegdata();
		showBeveragedata();
	}
}
