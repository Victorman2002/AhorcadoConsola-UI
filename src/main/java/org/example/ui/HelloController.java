package org.example.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.example.domain.Juego;
import org.example.service.GestionJuego;

import java.net.URL;
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
    public Button btnA;
    @FXML
    public Button btnB;
    @FXML
    public Button btnC;
    @FXML
    public Button btnD;
    @FXML
    public Button btnE;
    @FXML
    public Button btnF;
    @FXML
    public Button btnG;
    @FXML
    public Button btnH;
    @FXML
    public Button btnI;
    @FXML
    public Button btnJ;
    @FXML
    public Button btnK;
    @FXML
    public Button btnL;
    @FXML
    public Button btnM;
    @FXML
    public Button btnN;
    @FXML
    public Button btnO;
    @FXML
    public Button btnP;
    @FXML
    public Button btnQ;
    @FXML
    public Button btnR;
    @FXML
    public Button btnS;
    @FXML
    public Button btnT;
    @FXML
    public Button btnU;
    @FXML
    public Button btnV;
    @FXML
    public Button btnW;
    @FXML
    public Button btnX;
    @FXML
    public Button btnY;
    @FXML
    public Button btnZ;
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public ImageView imagen;

    GestionJuego gestionJuego = new GestionJuego();

    Juego juego = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Recuperar partida anterior
        juego = gestionJuego.leerJuegoFichero();
        //Añadimos la incognita que ha generado la clase Juego al Label que verá el usuario
        incognita.setText(juego.getLetrasDescubiertas());

        //Reinicio el marcador de intentos

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

    public void comprobarLetra(ActionEvent actionEvent) {

        //Cojo el boton que ha llamado al metodo
        Button boton = (Button) actionEvent.getSource();

        //Y cojo su contenido
        char letra = boton.getText().charAt(0);
        System.out.println("El botón " + letra + " ha sido presionado.");

        //Cálculo si pulsado está en la palabra para que cambie en la clase Juego
        juego.calcularLetrasDescubiertas(letra);

        //Actualizo el Label de la Incognita tras hacer la comprobacion
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

    public void reiniciarBotones() {
        btnA.setDisable(false);
        btnA.setStyle("");
        btnB.setDisable(false);
        btnB.setStyle("");
        btnC.setDisable(false);
        btnC.setStyle("");
        btnD.setDisable(false);
        btnD.setStyle("");
        btnE.setDisable(false);
        btnE.setStyle("");
        btnF.setDisable(false);
        btnF.setStyle("");
        btnG.setDisable(false);
        btnG.setStyle("");
        btnH.setDisable(false);
        btnH.setStyle("");
        btnI.setDisable(false);
        btnI.setStyle("");
        btnJ.setDisable(false);
        btnJ.setStyle("");
        btnK.setDisable(false);
        btnK.setStyle("");
        btnL.setDisable(false);
        btnL.setStyle("");
        btnM.setDisable(false);
        btnM.setStyle("");
        btnN.setDisable(false);
        btnN.setStyle("");
        btnO.setDisable(false);
        btnO.setStyle("");
        btnP.setDisable(false);
        btnP.setStyle("");
        btnQ.setDisable(false);
        btnQ.setStyle("");
        btnR.setDisable(false);
        btnR.setStyle("");
        btnS.setDisable(false);
        btnS.setStyle("");
        btnT.setDisable(false);
        btnT.setStyle("");
        btnU.setDisable(false);
        btnU.setStyle("");
        btnV.setDisable(false);
        btnV.setStyle("");
        btnW.setDisable(false);
        btnW.setStyle("");
        btnX.setDisable(false);
        btnX.setStyle("");
        btnY.setDisable(false);
        btnY.setStyle("");
        btnZ.setDisable(false);
        btnZ.setStyle("");
    }

}