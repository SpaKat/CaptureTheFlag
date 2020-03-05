package Gui;

import java.io.IOException;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import CaptureTheFlagGame.Statistics;
import Client.ClientAPI;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameClientGUI extends BorderPane {

	ClientAPI api;

	public GameClientGUI() {

		setupConnection();
	}

	private void setupConnection() {
		VBox Background = new VBox();
		TextField ip = makeLabelandField("Enter ip", Background);
		TextField port = makeLabelandField("Enter port", Background);
		Button enter = new Button("Connect");
		Text error = new Text("Failed to connect");
		error.setFill(Color.RED);
		error.setVisible(false);

		enter.setOnAction(e->{
			try {
				int p = Integer.parseInt(port.getText());
				api = new ClientAPI(ip.getText(), p);
				setupPlayer();
			} catch (Exception e1) {
				error.setVisible(true);
				e1.printStackTrace();
			}

		});

		Background.getChildren().addAll(enter,error);
		setCenter(Background);



	}

	private void setupPlayer() {
		VBox Background = new VBox();
		TextField name = makeLabelandField("Enter Name", Background);
		Statistics s = new Statistics(0, 0, 0, 0);
		TextField team = makeLabelandField("enter Team",Background);
		team.setStyle("-fx-background-color: red");
		team.textProperty().addListener((obs,oldv,newv) ->{
			try {
				int x = Integer.parseInt(newv);
				if (x<4 && x>=0) {
					team.setStyle("-fx-background-color: green");

				}else {
					team.setStyle("-fx-background-color: red");

				}
			}catch (Exception e) {
				team.setStyle("-fx-background-color: red");
			}
		});

		Slider attack = makeSlider("enter attack",Background,0,s.getMAXRATING());
		attack.valueProperty().addListener((observable, oldValue, newValue)->{
			s.setAttack(newValue.doubleValue());
			if (!s.getRateing()) {
				s.setAttack(oldValue.doubleValue());
				attack.setValue(oldValue.doubleValue());
			}
		});
		Slider defense = makeSlider("enter defense",Background,0,s.getMAXRATING());
		defense.valueProperty().addListener((observable, oldValue, newValue)->{
			s.setDefense(newValue.doubleValue());
			if (!s.getRateing()) {
				s.setDefense(oldValue.doubleValue());
				defense.setValue(oldValue.doubleValue());
			}
		});
		Slider health = makeSlider("enter health",Background,0,s.getMAXRATING());
		health.valueProperty().addListener((observable, oldValue, newValue)->{
			s.setHealth(newValue.doubleValue());
			if (!s.getRateing()) {
				s.setHealth(oldValue.doubleValue());
				health.setValue(oldValue.doubleValue());
			}
		});
		Slider movement = makeSlider("enter movement",Background,0,s.getMAXRATING());
		movement.valueProperty().addListener((observable, oldValue, newValue)->{
			s.setMovespeed(newValue.doubleValue());
			if (!s.getRateing()) {
				s.setMovespeed(oldValue.doubleValue());
				movement.setValue(oldValue.doubleValue());
			}
		});
		Button enter = new Button("submit");

		enter.setOnAction(e->{
			try {
				api.setupPlayer(s, Integer.parseInt(team.getText()), name.getText());
				setupGame();
			} catch (Exception e1) {

			}

		});

		Background.getChildren().addAll(enter);
		setCenter(Background);


	}

	private void setupGame() {
		
		setOnKeyPressed(e->{
			switch (e.getCode()) {
			case UP:
				try {
					api.sendAction(Math.PI*3/2,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case DOWN:
				try {
					api.sendAction(Math.PI/2,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case LEFT:
				try {
					api.sendAction(Math.PI,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case RIGHT:
				try {
					api.sendAction(0,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case SPACE:
				try {
					api.sendFire();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			default:
				break;
			}
		});
		setOnMouseClicked(e->{
			requestFocus();
		});
	}

	private Slider makeSlider(String string, VBox background,double min, double max) {
		Slider s = new Slider(min, max, min);
		Label l = new Label(string);
		VBox v = new VBox(l,s);
		background.getChildren().add(v);
		return s;
	}

	private TextField makeLabelandField(String string, VBox background) {
		Label l = new Label(string);
		TextField tf = new TextField();
		HBox hbox = new HBox(l,tf);
		background.getChildren().add(hbox);
		return tf;
	}

}
