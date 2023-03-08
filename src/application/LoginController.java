package application;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.Usuario;
import utilities.DataBase;

public class LoginController {


    @FXML private Pane optionsLogin, registerPane, loginPane;
    @FXML private Label bExit, bLogin, bRegister, bLoginDef;

    @FXML private Label bRegisterDef, lblshowPassReg, lblshowPassLogin, lblCompRegis;

    @FXML private ImageView imgShowPassReg, imgShowPassLogin;

    
    @FXML private TextField txtCorreoReg, txtUsuReg, txtUsuLogin;

    @FXML private PasswordField txtPassRegis, txtPassLogin;

    
    //BBDD
    DataBase db;
    
  
    public void initialize() { //FUNCION INICIAL (SE EJECUTA ANTES QUE EL CONSTRUCTOR)
    	db = new DataBase();
    	initComponents();
    }
    
    public void initComponents() { //INICIALIZAR COMPONENTES 
    	optionsLogin.setVisible(true);
    	registerPane.setVisible(false);
    	loginPane.setVisible(false);
    	lblshowPassReg.setVisible(false);
    	lblshowPassLogin.setVisible(false);
    	lblCompRegis.setVisible(false);
    }
    
    @FXML void clickBotonesInicio(MouseEvent event) {
    	if(event.getSource() == bExit) {
    		Stage stage = (Stage) this.bExit.getScene().getWindow();
            stage.close();
    	}
    	else if (event.getSource()==bRegister) {
    		registerPane.setVisible(true);
    		optionsLogin.setVisible(false);
    		loginPane.setVisible(false);
    		
    	}
    	else if (event.getSource()==bLogin) {
    		loginPane.setVisible(true);
    		optionsLogin.setVisible(false);
    		registerPane.setVisible(false);
    		
    	}
    	
    }
    
    @FXML void clickBRegisDef(MouseEvent event) {  //COMPRUEBA QUE CORREO VÁLIDO // PASSWORD Y CONFIRMPASSWORD COINCIDAN
    	String pass1 = txtPassRegis.getText();
    	String correo = txtCorreoReg.getText();
    	Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    	Matcher mather = pattern.matcher(correo);
    	
    	lblCompRegis.setVisible(true);
    	if(mather.find()) {
    		if(pass1.equals("")) {
    			lblCompRegis.setVisible(true);
    			lblCompRegis.setText("ERROR. Contraseña no válida.");
    			txtPassRegis.setText("");
    			txtUsuReg.setText("");
    			txtCorreoReg.setText("");
    		}
    		else if(txtUsuReg.getText().equals("")) { //COMPRUEBA QUE NO DEJE EL CAMPO USUARIO VACÍO
    			lblCompRegis.setVisible(true);
    			lblCompRegis.setText("ERROR. Nombre usuario no válido.");
    			txtUsuReg.setText("");
    		}
    		
    		else {
    			//DAR ALTA USUARIO EN BBDD (FUNCIONA PERFECTAMENTE)
				String nom = txtUsuReg.getText();
				Usuario u1 = new Usuario(nom, pass1, correo);
				
				try {
					if(db.registraUsuario(u1)) { 
						System.out.println("Usuario registrado correctamente");
						lblCompRegis.setVisible(true);
						lblCompRegis.setUnderline(true);


						lblCompRegis.setText("¡Cuenta creada con éxito!");
					}
					
				} catch (SQLException e) {
					System.out.println("No se pudo registrar el usuario");
					lblCompRegis.setVisible(true);
					lblCompRegis.setUnderline(false);
					
					if(e instanceof SQLIntegrityConstraintViolationException) {
						lblCompRegis.setText("El usuario ya existe");
					}
					
					
				}
    			
        		

    			 
    		}
    	}
    	else{
    		lblCompRegis.setVisible(true);
    		lblCompRegis.setText("ERROR. Formato de correo no válido.");
    		txtCorreoReg.setText("");
    		txtPassRegis.setText("");
    		txtUsuReg.setText("");
    	}
    	
    	
   	
    	
    }
    
    @FXML void clickBackInicio(MouseEvent event) {
    	if(loginPane.isVisible()) {
    		loginPane.setVisible(false);
    		registerPane.setVisible(false);
    		optionsLogin.setVisible(true);
    	}
    	else if(registerPane.isVisible()) {
    		registerPane.setVisible(false);
    		loginPane.setVisible(false);
    		optionsLogin.setVisible(true);
    	}
    }
    
    
    
    
    //MOSTRAR CONTRASEÑAS
    
    @FXML void showPassReg(MouseEvent event) {
    	lblshowPassReg.setText(txtPassRegis.getText());
    	lblshowPassReg.setVisible(true);
    	
    }

    @FXML void stopShowPassReg(MouseEvent event) {
    	lblshowPassReg.setVisible(false);
    }
    
    @FXML void showPassLogin(MouseEvent event) {
    	lblshowPassLogin.setText(txtPassLogin.getText());
    	lblshowPassLogin.setVisible(true);
    	
    }

    @FXML void stopShowPassLogin(MouseEvent event) {
    	lblshowPassLogin.setVisible(false);
    }

}

