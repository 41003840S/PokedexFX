package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;


public class Controller {

    public ListView listView;
    public Label nombre, hp, ataque, defensa , peso;
    public Circle image;
    public ImageView imagen;
    public Slider slider;
    public static ObservableList items =
            FXCollections.observableArrayList();


    public void initialize() {

        System.out.println("Descargando Pokemons....");
        Llamada.downloadInfoListView();
        listView.setItems(items);
        System.out.println("Descarga completada");

        //Listener del listview para que cuando hagamos click en un posicion haga algo
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                //Sacamos el numero del �ndice que tenemos seleccionado
                int itemSeleccionado = listView.getSelectionModel().getSelectedIndex();
                //Le sumo uno porque empieza en 0 y las ids de los pokemon empiezan por 1
                itemSeleccionado = itemSeleccionado + 1;
                //System.out.println("http://pokeapi.co/media/img/"+ itemSeleccionado +".png");

                //Muestra la imagen del pokemon cogiendo su posicion que es igual a su id
                imagen.setImage(new Image("http://pokeapi.co/media/img/"+ itemSeleccionado +".png"));

                Pokemon poke1 = Llamada.downloadInfoDetails(itemSeleccionado);

                nombre.setText(poke1.nombre);
                hp.setText(String.valueOf(poke1.hp)+ "");
                ataque.setText(String.valueOf(poke1.ataque));
                defensa.setText(String.valueOf(poke1.defensa));
                peso.setText(poke1.peso);
            }
        });

        // Listen for Slider value changes
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {


                imagen.resize(slider.getValue(),slider.getValue());
                System.out.println(slider.getValue());


            }
        });
    }
    //Salir de aplicacion
    public void salirAplicacion(ActionEvent actionEvent) {
        Platform.exit();
    }

    //Borra un pokemon de la lista
    public void borrarPoke(ActionEvent actionEvent) {
        int itemSeleccionado = listView.getSelectionModel().getSelectedIndex();
        items.remove(itemSeleccionado);
    }

    //Mensaje About
    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Pokedex");
        alert.setContentText("Lista de pokemons con sus atributos");
        alert.showAndWait();
    }


}
