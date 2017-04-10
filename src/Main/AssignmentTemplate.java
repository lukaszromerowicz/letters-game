package Main;
import Screens.HowToScreen;
import Screens.MainScreen;
import Screens.MenuScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * Main application class
 */
public class AssignmentTemplate extends Application {
	 public static final MenuScreen MENU_SCREEN = new MenuScreen();
	 public static final HowToScreen HOW_TO_SCREEN = new HowToScreen();
	 public MainScreen mainScreen;
			
	public static void main(String[] args) {
			launch(args);
	}

	Scene scene;
	TabPane root;
	Tab tab1, tab2;
	

	public void start(Stage stage) throws Exception {
	  	stage.setTitle("Software Architectures – Lukasz Romerowicz");
		mainScreen = new MainScreen(MENU_SCREEN);
		String style = getClass().getResource("styles.css").toExternalForm();
		
	  	root = new TabPane();
	    scene = new Scene(root, 800, 600);

		scene.getStylesheets().clear();
		scene.getStylesheets().add(style);

	  	tab1 = new Tab();
	  	tab1.setText("First Tab");
	  	tab1.setClosable(false);
	  
	  	root.getTabs().add(tab1);

	  	tab2 = new Tab();
	  	tab2.setText("Second Tab");
	  	tab2.setClosable(false);
	  	root.getTabs().add(tab2);
	  	
	  	tab1.setContent(mainScreen);	
	  	
	  	// Turns off ability to resize window
	  	stage.setResizable(false);
	  	// Turns off window controllers
	  	stage.initStyle(StageStyle.UNDECORATED);
	  	stage.setScene(scene);
	  	stage.show();
	}
}
