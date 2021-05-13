module Aims {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens aims.gui to javafx.graphics, javafx.fxml;
	opens media to javafx.graphics, javafx.fxml, javafx.base;
}