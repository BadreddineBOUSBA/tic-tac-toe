import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
public class chartData{
ObservableList<PieChart.Data> data = FXCollections. observableArrayList();
public ObservableList<PieChart.Data> getChartData() {
        data.add(new PieChart.Data("X gamer", 0));
        data.add(new PieChart.Data("O gamer", 0));
    return data;
}
public  void update_chart(int x_gamer_gains, int o_gamer_gains){
    data.clear();
    data.add(new PieChart.Data("X gamer", x_gamer_gains));
    data.add(new PieChart.Data("O gamer", o_gamer_gains));  
}
}