package aims.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import media.Book;
import media.CompactDisc;
import media.DigitalVideoDisc;
import media.Media;
import media.Track;
import order.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller implements Initializable {
//public class Controller {
	private static Order order = new Order();
	private static Order exOrder = createExampleOrder();
	private static List<String> exOrderTitleList = exOrder.getTitleList();
	private static boolean check = false;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	String currentItem;
	
	@FXML
	private AnchorPane scenePane;
	@FXML
	private ListView<String> deleteListView = new ListView<String>();
	@FXML
	private Label deleteLabel;
	@FXML
	private ListView<String> myListView = new ListView<String>();
	@FXML
	private Label myLabel;
	@FXML
	TableView<Media> table = new TableView<Media>();
	@FXML
	private TableColumn<Media, String> titleColumn = new TableColumn<Media, String>();
	@FXML
	private TableColumn<Media, String> categoryColumn = new TableColumn<Media, String>();
	@FXML
	private TableColumn<Media, Float> costColumn = new TableColumn<Media, Float>();
	@FXML
	private TableColumn<Media, String> typeColumn = new TableColumn<Media, String>();
	@FXML
	private TextField filterField = new TextField();
	@FXML
	private ChoiceBox<String> displaySceneBox = new ChoiceBox<String>();
	
	public void switchTo(ActionEvent event, String name) throws IOException {
		if (check) {
			root = FXMLLoader.load(getClass().getResource(name));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setTitle("Warning alert");
			alert.setHeaderText("Not so fast!");
			alert.setContentText("You have to create your order first!");

			alert.showAndWait();
		}
	}

	public void backToMenu(ActionEvent event) throws IOException {
		switchTo(event, "Menu.fxml");
	}

	public void switchToAddScene(ActionEvent event) throws IOException {
		switchTo(event, "AddScene.fxml");
	}

	public void switchToDeleteScene(ActionEvent event) throws IOException {
		switchTo(event, "DeleteScene.fxml");
	}

	public void switchToDisplayScene(ActionEvent event) throws IOException {
		switchTo(event, "DisplayScene.fxml");
	}

	public void createOrderAlert(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle("Just an information alert!");
		alert.setHeaderText("Good job champion!");
		alert.setContentText("You've sucessfully created new order!");

		alert.showAndWait();
		System.out.println("You've successfully created new order");
		check = true;
	}

	public void exit(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Farewell");
		alert.setHeaderText("Thanks for using my application");
		alert.setContentText("Do you really want to quit?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) scenePane.getScene().getWindow();
			System.out.println("You've successfully logged out!");
			stage.close();
		}
	}

	public static Order createExampleOrder() {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Python King", "Animation", 19.95f, 87, "Roger Allers");
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("The Java - an unexpected journey", "Codeventure", 99.96f, 90,
				"Mad Demon");
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("A beautiful hello World", "Science Fiction", 60.9f, 89,
				"Peter Scripter");

		Book book1 = new Book("The trial", "Classic", 100.0f, "Franz Kafka");
		Book book2 = new Book("Doraemon", "Fiction", 20.05f, "Fujiko F. Fujio");

		Track track1 = new Track("Heart-shaped box", 12);
		Track track2 = new Track("Polly", 10);
		Track track3 = new Track("The man who sold the world", 8);
		Track track4 = new Track("Lithium", 21);
		Track track5 = new Track("Smells like teen spirit", 15);

		CompactDisc album1 = new CompactDisc("Depression", "Rock", 245.55f, "Nirvana", track1, track2, track3, track4,
				track5);
		Order order = new Order();
		order.addMedia(dvd1, dvd2, dvd3, book1, book2, album1);
		return order;
	}

	public void addToOrder(ActionEvent event) {

		Media item = exOrder.searchMedia(currentItem);
		if (item != null) {
			order.addMedia(item);
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Just an information alert!");
			alert.setHeaderText("Nicely done!");
			alert.setContentText("Sucessfully added " + currentItem + " to your order!");

			alert.showAndWait();
			System.out.println("Added " + currentItem + " to your order!");
		} else {
			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error alert");
			alert.setHeaderText("Uh oh");
			alert.setContentText("Can not add item!");

			alert.showAndWait();
		}
	}
	
	public void deleteFromOrder(ActionEvent event) throws IOException {

		Media item = order.searchMedia(currentItem);
		if (item != null) {
			order.removeMedia(item);
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Just an information alert!");
			alert.setHeaderText("Nicely done!");
			alert.setContentText("Sucessfully deleted " + currentItem + " from your order!");

			alert.showAndWait();
			System.out.println("Deleted " + currentItem + " from your order!");
			switchToDeleteScene(event);
		} else {
			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error alert");
			alert.setHeaderText("Uh oh");
			alert.setContentText("Can not add item!");

			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		///////////////////////////// Display
		titleColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
		costColumn.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("type"));
		ObservableList<Media> list = FXCollections.observableArrayList(order.getItemsOrderedList());
		table.setItems(list);
		
		displaySceneBox.getItems().addAll("Title", "Category", "Type");
		displaySceneBox.setValue("Title");
		
		FilteredList<Media> filteredData = new FilteredList<Media>(list, b -> true);
		filterField.textProperty().addListener((obs, oldValue, newValue) -> {
			switch(displaySceneBox.getValue()) {
				case "Title":
					filteredData.setPredicate(item -> {
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						String filter = newValue.toLowerCase();
						
						if (item.getTitle().toLowerCase().indexOf(filter) != -1) {
							return true;
						}
						return false;
					});
					break;
				case "Category":
					filteredData.setPredicate(item -> {
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						String filter = newValue.toLowerCase();
						
						if (item.getCategory().toLowerCase().indexOf(filter) != -1) {
							return true;
						}
						return false;
					});
					break;
				case "Type":
					filteredData.setPredicate(item -> {
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						String filter = newValue.toLowerCase();
						
						if (item.getType().toLowerCase().indexOf(filter) != -1) {
							return true;
						}
						return false;
					});
					break;
			}
		});
		
		SortedList<Media> sortedData = new SortedList<Media>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
		///////////////////////////// Delete
		deleteListView.getItems().addAll(order.getTitleList());
		
		deleteListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				currentItem = deleteListView.getSelectionModel().getSelectedItem();
				Media item = exOrder.searchMedia(currentItem);
				if (item != null) {
					if (item instanceof DigitalVideoDisc) {
						DigitalVideoDisc temp = (DigitalVideoDisc) item;
						deleteLabel.setText("Title: " + temp.getTitle() + "\nType: " + "DVD" + "\nCategory: "
								+ temp.getCategory() + "\nCost: " + temp.getCost() + "\nDirector: " + temp.getDirector()
								+ "\nLength: " + temp.getLength());
					}
					if (item instanceof Book) {
						Book temp = (Book) item;
						deleteLabel.setText("Title: " + temp.getTitle() + "\nType: " + "CD" + "\nCategory: "
								+ temp.getCategory() + "\nCost: " + temp.getCost() + "\nAuthors: " + temp.getAuthors());
					}
					if (item instanceof CompactDisc) {
						CompactDisc temp = (CompactDisc) item;
						deleteLabel.setText("Title: " + temp.getTitle() + "\nType: " + "CD" + "\nCategory: "
								+ temp.getCategory() + "\nCost: " + temp.getCost() + "\nArtist: " + temp.getArtist()
								+ "\nLength: " + temp.getLength());
					}
				}
			}
		});
		///////////////////////////// Add
		myListView.getItems().addAll(exOrderTitleList);
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				currentItem = myListView.getSelectionModel().getSelectedItem();
				Media item = exOrder.searchMedia(currentItem);
				if (item != null) {
					if (item instanceof DigitalVideoDisc) {
						DigitalVideoDisc temp = (DigitalVideoDisc) item;
						myLabel.setText("Title: " + temp.getTitle() + "\nType: " + "DVD" + "\nCategory: "
								+ temp.getCategory() + "\nCost: " + temp.getCost() + "\nDirector: " + temp.getDirector()
								+ "\nLength: " + temp.getLength());
					}
					if (item instanceof Book) {
						Book temp = (Book) item;
						myLabel.setText("Title: " + temp.getTitle() + "\nType: " + "CD" + "\nCategory: "
								+ temp.getCategory() + "\nCost: " + temp.getCost() + "\nAuthors: " + temp.getAuthors());
					}
					if (item instanceof CompactDisc) {
						CompactDisc temp = (CompactDisc) item;
						myLabel.setText("Title: " + temp.getTitle() + "\nType: " + "CD" + "\nCategory: "
								+ temp.getCategory() + "\nCost: " + temp.getCost() + "\nArtist: " + temp.getArtist()
								+ "\nLength: " + temp.getLength());
					}
				}
			}
		});
	}
}
