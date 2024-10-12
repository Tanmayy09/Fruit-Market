package application;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController 
{
	@FXML
	private ImageView item_image;

    @FXML
    private Label item_name;

    @FXML
    private Label item_price;

	private ActionPerformed ac;
    private Fruit fruit;
    public void setFruitData(Fruit fruit, ActionPerformed ac) 
    {
    	this.fruit = fruit;
    	this.ac =ac;
        item_name.setText(fruit.getName());
        item_price.setText(fruit.getPrice()+"Rs");
        String path = fruit.getImgSrc();
        Image image = new Image(path,200,150,false,true);
        item_image.setImage(image);
    }
    
    public void click(MouseEvent event)
    {
    	ac.Clicked(fruit);
    }
    
    
    
}
