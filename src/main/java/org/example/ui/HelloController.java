package org.example.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.example.domain.Juego;
import org.example.service.GestionJuego;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    public Label incognita;

    @FXML
    public ChoiceBox categoriaChoiceMenu;
    @FXML
    public ChoiceBox nivelChoiceMenu;
    @FXML
    public Button adivinarPalabraButton;
    @FXML
    public Button nuevaPalabraButton;

    @FXML
    public TextField intentoDeAdivinarTextField;

    @FXML
    public AnchorPane anchorPane;
    @FXML
    public ImageView imagen;

    @FXML
    public HBox botones1;
    @FXML
    public HBox botones2;
    @FXML
    public HBox botones3;
    @FXML
    public HBox botones4;

    GestionJuego gestionJuego = new GestionJuego();
    private final List<Button> listaBotones = FXCollections.observableArrayList();
    private ArrayList<Boolean> botonesPulsados = new ArrayList<>();
    Juego juego = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Guardar todos los botones del FXML en un Array.
        for (Node nodo : botones1.getChildren()) {
            if (nodo instanceof Button) {
                listaBotones.add((Button) nodo);
            }
        }
        for (Node nodo : botones2.getChildren()) {
            if (nodo instanceof Button) {
                listaBotones.add((Button) nodo);
            }
        }
        for (Node nodo : botones3.getChildren()) {
            if (nodo instanceof Button) {
                listaBotones.add((Button) nodo);
            }
        }
        for (Node nodo : botones4.getChildren()) {
            if (nodo instanceof Button) {
                listaBotones.add((Button) nodo);
            }
        }

        //Inicializo el array de booleanos
        for (int i = 0; i < 26; i++) {
            botonesPulsados.add(false);
        }

        //Recuperar partida anterior
        juego = gestionJuego.leerJuegoFichero();
        //Añadimos la incognita que ha generado la clase Juego al Label que verá el usuario
        incognita.setText(juego.getLetrasDescubiertas());
        //Coger la imagen de la partida anterior en el caso de que la haya
        imagen.setImage(new Image("File:src/main/resources/org/example/ahorcado" + juego.getIntentos() + ".png"));
        //Recupero el estado anterior de los botones
        retomarEstadoBotones();
    }

    public void nuevaPalabra() {

        //Cogemos de los choicebox los elementos seleccionados por el usuario
        String categoriaSeleccionada;
        int nivelSeleccionado;

        //Comprobar si se ha seleccionado una categoria en los ChoiceMenu sino darle una predeterminada
        if (categoriaChoiceMenu.getValue() == null || nivelChoiceMenu.getValue() == null) {
            categoriaSeleccionada = "country";
            nivelSeleccionado = 1;
        } else {
            categoriaSeleccionada = (String) categoriaChoiceMenu.getSelectionModel().getSelectedItem();
            nivelSeleccionado = Integer.parseInt((String) nivelChoiceMenu.getSelectionModel().getSelectedItem());
        }

        //Los introducimos en la clase juego
        juego = new Juego(nivelSeleccionado, categoriaSeleccionada);

        //Añadimos la incognita que ha generado la clase Juego al Label que verá el usuario
        incognita.setText(juego.getLetrasDescubiertas());

        //Reinicio el marcador de intentos
        imagen.setImage(null);
        //Reinicio los botones llamando a la funcion reiniciar botones
        reiniciarBotones();

        System.out.println("La palabra a adivinar es: " + juego.getElementoAdivinar().getIncognita());

    }

    public void onLetraPulsada(ActionEvent actionEvent) {

        //Cojo el boton que ha llamado al método
        Button boton = (Button) actionEvent.getSource();

        //Y cojo su contenido
        char letra = boton.getText().charAt(0);
        System.out.println("El botón " + letra + " ha sido presionado.");

        //Cálculo si pulsado está en la palabra para que cambie en la clase Juego
        juego.calcularLetrasDescubiertas(letra);

        //Actualizo el Label de la Incognita tras hacer la comprobación
        incognita.setText(juego.getLetrasDescubiertas());

        //Comprobar si la letra esta, si lo está el boton se vuelve verde y sino rojo
        if (juego.comprobrarSiLaLetraEsta(letra)) {
            boton.setStyle("-fx-background-color: green;");
            boton.setDisable(true);
            if (juego.comprobarPalabraAcertada(incognita.getText())) {
                palabraAcertada();
            }
        } else {
            boton.setStyle("-fx-background-color: red;");
            boton.setDisable(true);
            juego.setIntentos(juego.getIntentos() - 1);
            imagen.setImage(new Image("File:src/main/resources/org/example/ahorcado" + juego.getIntentos() + ".png"));
        }
        if (juego.getIntentos() < 1) {
            finIntentos();
        }

        //Cojo la posicion del boton en el abecedario y lo pongo en dicha posicion como true (pulsado)
        //en el array booleanos de botones pulsados.
        botonesPulsados = (ArrayList<Boolean>) juego.getBotones();
        botonesPulsados.set(boton.getText().charAt(0) - 65, true);
        juego.setBotones(botonesPulsados);

        //Guardo el estado del juego tras que se haya pulsado el boton
        gestionJuego.escribirJuegoFichero(juego);

    }

    public void adivinarPalabraEntera() {
        if (juego.comprobarPalabraAcertada(intentoDeAdivinarTextField.getText())) {
            palabraAcertada();
        } else {
            palabraFallada();
        }
        intentoDeAdivinarTextField.clear();
    }

    public void finIntentos() {
        Alert alertaFinJuego = new Alert(Alert.AlertType.INFORMATION);
        alertaFinJuego.setTitle(" ");
        alertaFinJuego.setHeaderText("SE HAN ACABADO LOS INTENTOS");
        alertaFinJuego.setContentText("La palabra era: " + juego.getElementoAdivinar().getIncognita());
        alertaFinJuego.showAndWait();
        nuevaPalabra();
    }

    public void palabraFallada() {
        Alert alertaFinJuego = new Alert(Alert.AlertType.ERROR);
        alertaFinJuego.setTitle(" ");
        alertaFinJuego.setHeaderText("FALLASTE LA PALABRA");
        alertaFinJuego.setContentText("La palabra era: " + juego.getElementoAdivinar().getIncognita());
        alertaFinJuego.showAndWait();
        nuevaPalabra();
    }

    public void palabraAcertada() {
        Alert alertaFinJuego = new Alert(Alert.AlertType.INFORMATION);
        alertaFinJuego.setTitle(" ");
        alertaFinJuego.setHeaderText("ACERTASTE LA PALABRA");
        alertaFinJuego.setContentText("La palabra era: " + juego.getElementoAdivinar().getIncognita());
        alertaFinJuego.showAndWait();
        nuevaPalabra();
    }

    public void retomarEstadoBotones() {
        List<Boolean> botonesPulsados = juego.getBotones();
        for (int i = 0; i < botonesPulsados.size(); i++) {
            if (botonesPulsados.get(i)) {
                listaBotones.get(i).setDisable(true);
            }
        }
    }

    public void reiniciarBotones() {
        for (Button boton : listaBotones) {
            boton.setDisable(false);
            boton.setStyle("");
        }
    }

}