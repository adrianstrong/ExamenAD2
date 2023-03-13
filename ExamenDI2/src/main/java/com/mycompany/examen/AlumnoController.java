package com.mycompany.examen;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Alumno;
import net.sf.jasperreports.engine.JRException;

public class AlumnoController implements Initializable {
    @FXML
    public TableColumn colNombre;
    @FXML
    public TableColumn colApellidos;
    @FXML
    public TableColumn colAD;
    @FXML
    public TableColumn colSGE;
    @FXML
    public TableColumn colDI;
    @FXML
    public TableColumn colPMDM;
    @FXML
    public TableColumn colPSP;
    @FXML
    public TableColumn colEIE;
    @FXML
    public TableColumn colHLC;
    @FXML
    private TableView tblPrincipal;
    @FXML
    private Button anhadir;

    @FXML
    private Button salir;

    @FXML
    private Button imprimirpdf;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputApellido;

    @FXML
    private Spinner inputAD;

    @FXML
    private Spinner inputSGE;

    @FXML
    private Spinner inputDI;

    @FXML
    private Spinner inputPMDM;

    @FXML
    private Spinner inputPSP;

    @FXML
    private Spinner inputEIE;

    @FXML
    private Spinner inputHLC;

    private Long id;
    ObservableList<Alumno> alumList;

    private final AlumnoDAOJDBC alumnoDAO = new AlumnoDAOJDBC();

    @FXML
    private void verInforme() {
        try {
            Informe.pdfReport();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void rellenarCampos() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText("Debes de rellenar los campos");
        alert.showAndWait();
    }

    @FXML
    private void intervaloNotas() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText("Las notas deben de estar entre 0 y 10");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alumList = FXCollections.observableArrayList();

        alumList.addAll(getAlumnos());
        fillSpinners();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colAD.setCellValueFactory(new PropertyValueFactory<>("AD"));
        colSGE.setCellValueFactory(new PropertyValueFactory<>("SGE"));
        colDI.setCellValueFactory(new PropertyValueFactory<>("DI"));
        colPMDM.setCellValueFactory(new PropertyValueFactory<>("PMDM"));
        colPSP.setCellValueFactory(new PropertyValueFactory<>("PSP"));
        colEIE.setCellValueFactory(new PropertyValueFactory<>("EIE"));
        colHLC.setCellValueFactory(new PropertyValueFactory<>("HLC"));

        tblPrincipal.setItems(alumList);

        id = 4L;
        anhadir.onMouseClickedProperty().set(event -> {
            crearAlumno();
            fillTable(crearAlumno());
        });

        imprimirpdf.onMouseClickedProperty().set(event -> {
            verInforme();
        });

        salir.onMouseClickedProperty().set(event -> {
            System.exit(0);
        });

    }

   private void fillSpinners() {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
       inputAD.setValueFactory(svf);

        SpinnerValueFactory svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        inputSGE.setValueFactory(svf2);

        SpinnerValueFactory svf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        inputDI.setValueFactory(svf3);

        SpinnerValueFactory svf4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        inputPMDM.setValueFactory(svf4);

        SpinnerValueFactory svf5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        inputPSP.setValueFactory(svf5);

        SpinnerValueFactory svf6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        inputEIE.setValueFactory(svf6);

        SpinnerValueFactory svf7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        inputHLC.setValueFactory(svf7);
    }

    public void fillTable(Alumno alumno) {
        tblPrincipal.getItems().add(alumno);
    }

    public Alumno crearAlumno() {
        String nombre = inputNombre.getText();
        String apellido = inputApellido.getText();
        Integer ad = (Integer) inputAD.getValue();
        Integer sge = (Integer) inputSGE.getValue();
        Integer di = (Integer) inputDI.getValue();
        Integer pmdm = (Integer) inputPMDM.getValue();
        Integer psp = (Integer) inputPSP.getValue();
        Integer eie = (Integer) inputEIE.getValue();
        Integer hlc = (Integer) inputHLC.getValue();

        if (ad < 0.0 || ad > 10.0) {
            intervaloNotas();
            return null;
        }

        if (nombre.isEmpty() || apellido.isEmpty() || ad.toString().isEmpty() ||
                sge.toString().isEmpty() || di.toString().isEmpty() || pmdm.toString().isEmpty() ||
                psp.toString().isEmpty()|| eie.toString().isEmpty() || hlc.toString().isEmpty()) {
            rellenarCampos();
            return null;
        }

        try {
            return new Alumno(id, inputNombre.getText(), inputApellido.getText(), ad, sge, di, pmdm, psp, eie, hlc);
        } catch (Exception e) {
            rellenarCampos();
            return null;
        }
    }

    private double calcularMedia(Alumno alumno) {
        double sum = alumno.getAD() + alumno.getSGE() + alumno.getDI() + alumno.getPMDM() +
                alumno.getPSP() + alumno.getEIE() + alumno.getHLC();
        return sum / 7.0;
    }

    private ArrayList<Alumno> getAlumnos() {
        return alumnoDAO.getAlumnos();
    }

    private int contarFallos(Alumno alumno) {
        int count = 0;
        if (alumno.getAD() < 5) count++;
        if (alumno.getSGE() < 5) count++;
        if (alumno.getDI() < 5) count++;
        if (alumno.getPMDM() < 5) count++;
        if (alumno.getPSP() < 5) count++;
        if (alumno.getEIE() < 5) count++;
        if (alumno.getHLC() < 5) count++;
        return count;
    }
    private void modalAlerta(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clckModal(javafx.scene.input.MouseEvent mouseEvent) {
        Alumno alumno = (Alumno) tblPrincipal.getSelectionModel().getSelectedItem();
        int numFailures = contarFallos(alumno);
        String message;
        if (numFailures == 0) {
            message = "La nota media del alumno " + alumno.getNombre() + " " + alumno.getApellidos() +
                    " es " + calcularMedia(alumno);
            modalAlerta("Notas medias", message);
        } else {
            message = "El alumno " + alumno.getNombre() + " " + alumno.getApellidos() + " ha suspendido " +
                    numFailures + " módulos.";
            modalAlerta("Asignaturas suspensas", message);
        }
    }
}
