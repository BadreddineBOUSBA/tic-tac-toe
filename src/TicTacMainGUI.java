import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.stage.Stage;
import java.util.ArrayList;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
public class TicTacMainGUI extends Application { 
    static  boolean isCircle= true;  
    static int click_count= 0;
    static int[]res={12, 2, 3, 4, 5, 6, 7, 8, 9};
    boolean stop_event_handler=false;
    AnchorPane r1=new AnchorPane(),r2=new AnchorPane(),r3=new AnchorPane(),r21=new AnchorPane(), r22=new AnchorPane(),
            r23=new AnchorPane(), r31=new AnchorPane(), r32=new AnchorPane(), r33=new AnchorPane();
    ArrayList<AnchorPane> anchors = new ArrayList<>();
    int o_gamer_gains=0, x_gamer_gains=0;
   
@Override
public void start(Stage primaryStage) throws Exception{
    Line l1 = new Line(10, 10, 500, 500);
    Label title = new Label("Tic-Tac-Toe");
    BorderPane root = new BorderPane();
    BorderPane top_bar = new BorderPane();
     anchors.add(r1);anchors.add(r2);anchors.add(r3);anchors.add(r21);anchors.add(r22);anchors.add(r23);
    anchors.add(r31);anchors.add(r32);anchors.add(r33);
    for(AnchorPane el:anchors){
    el.setMaxWidth(205);
    el.setMaxHeight(185);
    el.setMinWidth(145);
    el.setMinHeight(125);   
    el.getStyleClass().add("rect");
    }
    Label x_gamer_count=new Label("O gamer gains : 0");
     Label o_gamer_count = new Label("X gamer gains : 0");
    chartData ch=new chartData();
    for(AnchorPane elem : anchors){
        elem.addEventHandler(MouseEvent.MOUSE_CLICKED, ev->{
            if(!stop_event_handler){
            Circle c1 = new Circle(72, 62, 60);
            c1.setFill(Color.TRANSPARENT);
            c1.setStroke(Color.BLACK);
            c1.setStrokeType(StrokeType.INSIDE);
            c1.setStrokeWidth(8);
            if(elem.getChildren().isEmpty() && isCircle){
                res[anchors.indexOf(elem)] = 0;
                click_count++;
                elem.getChildren().addAll(c1); 
                isCircle = false;
            }else if(elem.getChildren().isEmpty() && !isCircle){
               res[anchors.indexOf(elem)] = 1;
               click_count++;
               Line l = new Line(15,15, 130, 105);
               Line l2 = new Line(15,110, 125, 15);
               l.setStrokeWidth(8);l2.setStrokeWidth(8);
               l.setStrokeLineCap(StrokeLineCap.ROUND);
               l2.setStrokeLineCap(StrokeLineCap.ROUND);
               elem.getChildren().addAll(l, l2);
               isCircle = true;
            }
            ArrayList<Integer> p = isline(res);
            if(p.get(3)==1) {
             int s = ((int)p.get(4));
             char ss;
             if(s==0){ss='O';o_gamer_gains++;
             ch.update_chart(x_gamer_gains, o_gamer_gains);x_gamer_count.setText("O gamer gains : "+o_gamer_gains);}
             else {ss = 'X';
             x_gamer_gains++;ch.update_chart(x_gamer_gains, o_gamer_gains);
             o_gamer_count.setText("X gamer gains : "+x_gamer_gains);}
                anchors.get(p.get(0)).getStyleClass().add("success");
                anchors.get(p.get(1)).getStyleClass().add("success");
                anchors.get(p.get(2)).getStyleClass().add("success");
                dialogBox(primaryStage, ss, " Gained this tour !  LOOOOL");
                stop_event_handler=true;
               }else{
                if( click_count==9)
                     dialogBox(primaryStage, 'n', "No gainer No loser");
               }
                
            }
    });
    }
    top_bar.setCenter(title);
    root.setTop(top_bar);
    VBox table=new VBox();
    table.setAlignment(Pos.CENTER);
    table.setSpacing(30);
    HBox row1 = new HBox();
    row1.setSpacing(50);
    Button refresh_table = new Button("Reset Table");
    row1.getChildren().addAll(r1, r2, r3,refresh_table);
    refresh_table.addEventHandler(MouseEvent.MOUSE_CLICKED, ev->{
        reset_table();
    });
    HBox row2 = new HBox();
    row2.setSpacing(50);
    row2.getChildren().addAll(r21, r22, r23, x_gamer_count);
    HBox row3 = new HBox();
    row3.setSpacing(50);
    row3.getChildren().addAll(r31, r32, r33, o_gamer_count);
    table.getChildren().addAll(row1, row2, row3);
    root.setCenter(table);
    PieChart chart1 = new PieChart();
    chart1.setTitle("Tic-Tac-Toe game stats");
    ObservableList<PieChart.Data> chartata = ch.getChartData();
    chart1.setData(chartata);
    root.setRight(chart1);
    BorderPane.setMargin(table, new Insets(20, 0, 0, 0));
    Button play_solo = new Button("Play Solo");
    Button with_friend = new Button("Play with a friend (local)");
    Button online = new Button("Play with a friend (Online)");
    play_solo.setMaxWidth(Double.MAX_VALUE);with_friend.setMaxWidth(Double.MAX_VALUE);online.setMaxWidth(Double.MAX_VALUE);
    VBox choice = new VBox(20,play_solo, with_friend, online);
    with_friend.addEventHandler(MouseEvent.MOUSE_CLICKED, ev->{
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(650);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.centerOnScreen();
    });
    Scene scene = new Scene(choice); 
    o_gamer_count.getStyleClass().add("txt");
    x_gamer_count.getStyleClass().add("txt");
    top_bar.getStyleClass().add("title-app");
    table.getStyleClass().add("anchor-table");
    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.setTitle("Tic-Tac-Toe");
    primaryStage.getIcons().addAll(new Image("logo.png"));
    primaryStage.setWidth(500);
    primaryStage.setHeight(300);
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.show();
}
public static void main(String[] args) { 
    launch(args);
}
public void reset_table(){
    click_count=0;
    isCircle=true;
    stop_event_handler=false;
    res = new int[]{12, 2, 3, 4, 5, 6, 7, 8, 9};
    for(AnchorPane elem:anchors){
        elem.getChildren().clear();
        elem.getStyleClass().remove("success");
    }
    
}
public ArrayList<Integer> isline(int[] arr){
    ArrayList<Integer> a= new ArrayList<>();
   if(arr[0]==arr[1]&& arr[1]==arr[2]){
        a.add(0);a.add(1);a.add(2);a.add(1) ;a.add(arr[0]);
   }else if(arr[3]==arr[4]&& arr[4]==arr[5]){
        a.add(3);a.add(4);a.add(5);a.add(1);a.add(arr[3]); 
   }else if(arr[6]==arr[7]&& arr[7]==arr[8]){
        a.add(6);a.add(7);a.add(8);a.add(1);a.add(arr[6]);
   }else if(arr[0]==arr[3]&& arr[3]==arr[6]){
        a.add(0);a.add(3);a.add(6);a.add(1);a.add(arr[0]);
   }else if(arr[1]==arr[4]&& arr[4]==arr[7]){
        a.add(1);a.add(4);a.add(7);a.add(1);a.add(arr[1]);
   }else if(arr[2]==arr[5]&& arr[5]==arr[8]){
        a.add(2);a.add(5);a.add(8);a.add(1);a.add(arr[2]);
   }else if(arr[0]==arr[4]&& arr[4]==arr[8]){
        a.add(0);a.add(4);a.add(8);a.add(1);a.add(arr[0]);
   }else if( arr[2]==arr[4]&& arr[4]==arr[6]){
        a.add(2);a.add(4);a.add(6);a.add(1);a.add(arr[2]);
   }else  a.add(0);a.add(1);a.add(2);a.add(0);
     return a;   
}
private void dialogBox(Stage s, char xo, String msg)  {
            Stage stage = new Stage();
            stage.initOwner(s);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            Button close = new Button("Close");
            close.setOnAction(e ->{ stage.close();
            });
            Label text;
            if(xo=='n')
               text =new Label(msg);
            else
             text=new Label(xo+msg);
            text.setStyle("-fx-font:17 Lucida, sans-serif;-fx-text-fill:white;" +"-fx-font-size:20px;"+"-fx-font-weight:bold");
            close.setStyle("-fx-background-color:rgb(59, 190, 7);\n" +"-fx-padding:10px;\n" +
                    "-fx-text-fill:white;\n" +"-fx-font-size:18px;"+"\n" +
                    "    -fx-border-width: 1px;\n" +
                    "    -fx-border-color: white;\n" +
                    "    -fx-border-style: solid;  ");
            HBox buttons = new HBox(20,close);
            VBox root=new VBox(20,text,buttons);
            root.setStyle("-fx-border-width:1.5;"+
                          "-fx-background-color:  rgb(46, 255, 5);"+"\n" +
                        "    -fx-border-width: 2px;\n" +
                        "    -fx-border-color: white;\n" +
                        "    -fx-border-style: solid;  ");
            root.setPadding(new Insets(35));
            Scene scene = new Scene(root);
            stage.setMinWidth(400);
            stage.setMinHeight(250);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.showAndWait();
}
}