package com.allinonefx.controllers;

import com.allinonefx.MainDemo;
import com.allinonefx.dao.StaffMapper;
import com.allinonefx.model.Staff;
import com.allinonefx.mybatis.MyBatisConnectionFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.annotation.PostConstruct;
import org.apache.ibatis.session.SqlSession;

/**
 * genarated by APX file generation template File name: LoginController.java
 */
@ViewController(value = "/fxml/Login.fxml", title = "Login")
public class LoginController {

    SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @FXML
    private Label lblMessage;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView imgProgress;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        handleValidation();
        lblMessage.setVisible(false);
        imgProgress.setVisible(false);
    }

    private void handleValidation() {
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Input required");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtUsername.getValidators().add(fieldValidator);
        txtUsername.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtUsername.validate();
            }
        });
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Input required");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtPassword.getValidators().add(fieldValidator2);
        txtPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                txtPassword.validate();
            }
        });
    }

    @FXML
    private void login(ActionEvent event) {
        lblMessage.setVisible(false);
        imgProgress.setVisible(true);
        //transition
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            // check user exists
            StaffMapper staffMapper = sqlSession.getMapper(StaffMapper.class);
            Staff staff = staffMapper.selectByUsernameAndPassword(txtUsername.getText(), txtPassword.getText());
            if (staff != null) {
                try {
                    completeLogin(staff);
                } catch (FlowException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                imgProgress.setVisible(false);
                lblMessage.setVisible(true);
            }
        });
        pauseTransition.play();

    }

    private void completeLogin(Staff staff) throws FlowException {
        btnLogin.getScene().getWindow().hide();
        imgProgress.setVisible(false);
        Stage stage = new Stage();
        Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flowContext.register("User", staff);
        flow.createHandler(flowContext).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);

        double width = 800;
        double height = 600;
        try {
            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
            width = bounds.getWidth() / 1.5;
            height = bounds.getHeight() / 1.15;
        } catch (Exception e) {
        }

        Scene scene = new Scene(decorator, width, height);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                "bootstrapfx.css",
                MainDemo.class.getResource("/fxml/ui/bootstrapfx/sampler.css").toExternalForm(),
                MainDemo.class.getResource("/fxml/ui/bootstrapfx/xml-highlighting.css").toExternalForm(),
                MainDemo.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                MainDemo.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                MainDemo.class.getResource("/css/jfoenix-main-demo.css").toExternalForm(),
                MainDemo.class.getResource("/css/theme-blue.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
