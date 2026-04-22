import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;



public class ListadeCompras extends Application{

    private ArrayList<String> listaDeCompras = new ArrayList<>();
    private ListView<String> listaDeVisualizacao = new ListView<>();

    @Override
    public void start(Stage palco){

        palco.setTitle("Lista de Compras");

        TextField descricaoItem = new TextField();
        Button adicionar = new Button("Adicionar");
        Button exportar = new Button("Exportar");

        Label labelAdicionar = new Label("Digite o item que deseja adicionar: ");
        Label labelListaDeCompras = new Label ("Lista de Compras");

        //Criando objeto ObervableList

        ObservableList<String> observableListaDeCompras = FXCollections.observableArrayList(listaDeCompras);
        listaDeVisualizacao.setItems(observableListaDeCompras);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(labelAdicionar, descricaoItem, adicionar);
        vbox.getChildren().addAll(labelListaDeCompras, listaDeVisualizacao, exportar);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        adicionar.setOnAction(e -> {
            String item = descricaoItem.getText();
            if(!item.isEmpty()){
                listaDeCompras.add(item);
                listaDeVisualizacao.getItems().add(item);
                descricaoItem.clear();
            }
        });
        exportar.setOnAction(e ->{
            try {
                File arquivo = new File("listadecompras.txt");
                PrintWriter writer = new PrintWriter(arquivo);
                for (String item: listaDeCompras){
                    writer.println(item);
                }
                writer.close();
            } catch (Exception ex) {
                System.err.println("Erro ocorrido: " + ex.getMessage());
            }
        });

        Scene scene = new Scene(vbox, 400,400);
        palco.setScene(scene);
        palco.show();

    }
 
    public static void main(String[] args) {
        launch(args);
    }

}
