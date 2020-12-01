import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.stage.Stage;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.web.WebEngine;
public class Networking extends Application { 
@Override
public void start(Stage primaryStage) throws Exception{
Button btn2 = new Button(); Button search = new Button(); 
btn2.setText("top bar 1");search.setText("search");
 GlyphsDude.setIcon(search, FontAwesomeIcon.SEARCH);
WebView web_view = new WebView();
web_view.setContextMenuEnabled(false);
TextField url = new TextField();
        WebView webView = new WebView();
        webView.setFontScale(0.90);
        final WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.google.com");
search.setOnMouseClicked(e->webEngine.load(url.getText()));
BorderPane root = new BorderPane(); 
HBox top_bar = new HBox(url, search);
root.setTop(top_bar);
root.setCenter(webView);
Scene scene = new Scene(root); 
scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
primaryStage.setTitle("Hello World!");
primaryStage.setMaximized(true);
primaryStage.setScene(scene);
primaryStage.show();
}
public static void main(String[] args) { 
    launch(args);
}
}