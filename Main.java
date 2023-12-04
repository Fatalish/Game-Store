package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		inituser();
		initpro();
		
		loginPage(arg0);

		
		arg0.show();
	}
	
	Scene loginPage, registerPage, adminPage, userWelcome, userPage;
	ArrayList<user> registeredUser = new ArrayList<>();
	ArrayList<product> productlist = new ArrayList<>();
	
	user curr = new user("default", "pass", 0, 0, 0, 0); 
	

	//membuat txt file
	void insertuser(String data) {
		try {
			FileWriter fw = new FileWriter("save/account.txt", true);
			fw.write(data);
			fw.close();
		}catch (Exception e){
			
		}
	}
	
	void insertproduct(String data) {
		try {
			FileWriter fw = new FileWriter("save/product.txt", true);
			fw.write(data);
			fw.close();
		}catch (Exception e){
			
		}
	}
	
	void rewriteproduct(String data) {
		try {
			FileWriter fw = new FileWriter("save/product.txt");
			fw.write(data);
			fw.close();
		}catch (Exception e){
			
		}
	}
	
	void rewriteuser(String data) {
		try {
			FileWriter fw = new FileWriter("save/account.txt");
			fw.write(data);
			fw.close();
		}catch (Exception e){
			
		}
	}
	

	
	String gabunguser(String email, String password, Integer k1buy, Integer k2buy, Integer k3buy, Integer k4buy) {
		return email + "#" + password + "#" + k1buy + "#" + k2buy + "#" + k3buy + "#" + k4buy + "\n";
	}
	
	String gabungproduct(String proimg, String pronm, Integer propr, Integer prost, String prodesc) {
		return proimg + "#" + pronm + "#" + propr + "#" + prost + "#" + prodesc + "\n";
	}
	
	String[] pisah(String data) {
		return data.split("#");
	}
	
	//musik
	Media lofiuser = new Media(new File("assets/lofi.mp3").toURI().toString());
	MediaPlayer musicuser = new MediaPlayer(lofiuser);
	
	//menyimpan data user
	public void inituser() throws FileNotFoundException{
			
		if(registeredUser.isEmpty()) {
			File filesave = new File("save/account.txt");
				
			try {
				Scanner scfile = new Scanner(filesave);
				
				while(scfile.hasNextLine()) {
						
					String result = scfile.nextLine();
					String[] hasil = pisah(result);
					
					registeredUser.add(new user(hasil[0], hasil[1], Integer.valueOf(hasil[2]), Integer.valueOf(hasil[3]), Integer.valueOf(hasil[4]), Integer.valueOf(hasil[5])));
				}

				scfile.close();
			} catch(Exception e) {
				
			}
		}
	}
	
	//Ini untuk masukkan produknya ke list
	public void initpro() throws FileNotFoundException{
		
		if(productlist.isEmpty()) {
			File fileprod = new File("save/product.txt");
			
			try {
				Scanner scfile = new Scanner(fileprod);
				
				while(scfile.hasNextLine()) {
					
					String result = scfile.nextLine();
					String[] hasil = pisah(result);
					
					productlist.add(new product(hasil[0], hasil[1], Integer.valueOf(hasil[2]), Integer.valueOf(hasil[3]), hasil[4]));
					
				}
				
				scfile.close();
			} catch(Exception e) {
				
			}
		}
	}
	
	//Login page
	public void loginPage(Stage arg0) throws FileNotFoundException {
		Label title = new Label("Jee Keyboard Store");
		Label emaillbl = new Label("Email");
		Label passlbl = new Label("Password");
		TextField email = new TextField();
		PasswordField pass = new PasswordField();
		Button regisBtn = new Button("Register");
		Button loginBtn = new Button("Login");
		Image image = new Image(new File("assets/logo.png").toURI().toString());
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		HBox hbBtn = new HBox();

		arg0.getIcons().add(image);
		arg0.setTitle("Login");
		
		bp.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setMinHeight(100);
		title.setStyle("-fx-font-size:30px; -fx-font-weight:bold");
		
		gp.add(emaillbl, 0, 0);
		emaillbl.setStyle("-fx-font-size:15px");
		gp.add(email, 1, 0);
		email.setMinWidth(280);
		email.setMinHeight(35);
		gp.add(passlbl, 0, 1);
		passlbl.setStyle("-fx-font-size:15px");
		passlbl.setPrefWidth(80);
		gp.add(pass, 1, 1);
		pass.setMinWidth(280);
		pass.setMinHeight(35);
		hbBtn.getChildren().addAll(regisBtn,loginBtn);
		regisBtn.setMinWidth(135);
		regisBtn.setMinHeight(35);
		regisBtn.setStyle("-fx-color:#3c4043; -fx-text-fill:white; -fx-font-size:12px;");
		loginBtn.setMinWidth(135);
		loginBtn.setMinHeight(35);
		loginBtn.setStyle("-fx-color:#3c4043; -fx-text-fill:white; -fx-font-size:12px");
		hbBtn.setSpacing(10);
		gp.add(hbBtn, 1, 2);
		
		bp.setCenter(gp);
		gp.setAlignment(Pos.BASELINE_CENTER);
		gp.setVgap(10);
		
		
		regisBtn.setOnAction(e->{
			try {
				registerPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		loginBtn.setOnAction(e->{
			int check=1;
			
			for(user User : registeredUser) {
				if(User.getEmail().equals(email.getText()) && User.getPassword().equals(pass.getText())) {
					check = 0;
					curr = User;
					break;
				}
			}
			if(email.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login failed");
				alert.setHeaderText(null);
				alert.setContentText("Please fill Email field");
				alert.showAndWait();
			}
			else if(pass.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login failed");
				alert.setHeaderText(null);
				alert.setContentText("Please fill Password field");
				alert.showAndWait();
			}
			else if(email.getText().equals("admin") && pass.getText().equals("admin")) {
				try {
					adminPage(arg0);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(check==1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login failed");
				alert.setHeaderText(null);
				alert.setContentText("Please make sure that your email and password are correct");
				alert.showAndWait();
			}
			else {
				try {
					userWelcome(arg0);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		loginPage = new Scene(bp,500,300);
		arg0.setScene(loginPage);
	}
	
	//Register
	public void registerPage(Stage arg0) throws FileNotFoundException {
		Label title = new Label("Register");
		Label emaillbl = new Label("Email");
		Label passlbl = new Label("Password");
		Label conPasslbl = new Label("Confirm Password");
		TextField email = new TextField();
		PasswordField pass = new PasswordField();
		PasswordField conPass = new PasswordField();
		Button regisBtn = new Button("Register");
		Button loginBtn = new Button("Login");
		Button resetBtn = new Button("Reset");
		Image image = new Image(new File("assets/logo.png").toURI().toString());
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		HBox hbBtn = new HBox();
		
		arg0.getIcons().add(image);
		arg0.setTitle("Register");
		
		bp.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setMinHeight(100);
		title.setStyle("-fx-font-size:30px; -fx-font-weight:bold");
		
		gp.add(emaillbl, 0, 0);
		emaillbl.setStyle("-fx-font-size:15px");
		gp.add(email, 1, 0);
		email.setMinWidth(280);
		email.setMinHeight(35);
		gp.add(passlbl, 0, 1);
		passlbl.setStyle("-fx-font-size:15px");
		passlbl.setPrefWidth(80);
		gp.add(pass, 1, 1);
		pass.setMinWidth(280);
		pass.setMinHeight(35);
		gp.add(conPasslbl, 0, 2);
		conPasslbl.setPrefWidth(130);
		conPasslbl.setStyle("-fx-font-size:15px");
		gp.add(conPass, 1, 2);
		conPass.setMinWidth(280);
		conPass.setMinHeight(35);
		hbBtn.getChildren().addAll(loginBtn,regisBtn,resetBtn);
		regisBtn.setMinWidth(90);
		regisBtn.setMinHeight(35);
		regisBtn.setStyle("-fx-color:#3c4043; -fx-text-fill:white; -fx-font-size:12px;");
		loginBtn.setMinWidth(90);
		loginBtn.setMinHeight(35);
		loginBtn.setStyle("-fx-color:#3c4043; -fx-text-fill:white; -fx-font-size:12px");
		resetBtn.setMinWidth(90);
		resetBtn.setMinHeight(35);
		resetBtn.setStyle("-fx-color:#3c4043; -fx-text-fill:white; -fx-font-size:12px");
		hbBtn.setSpacing(10);
		gp.add(hbBtn, 1, 3);
		
		bp.setCenter(gp);
		gp.setAlignment(Pos.BASELINE_CENTER);
		gp.setVgap(10);
		
		loginBtn.setOnAction(e->{
			try {
				loginPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		resetBtn.setOnAction(e->{
			email.setText("");
			pass.setText("");
			conPass.setText("");
		});
		
		regisBtn.setOnAction(e->{
			int count=0;
			int check=0;
			for(int i=0;i<email.getText().length();i++) {
				if(email.getText().charAt(i)=='@') {
					count++;
				}
			}
			for(user User : registeredUser) {
				if(User.getEmail().equals(email.getText())) {
					check=1;
					break;
				}
			}
			if(email.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill Email field");
				alert.showAndWait();
				}
			else if(!email.getText().endsWith("@email.com")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Email must ends with '@email.com'");
				alert.showAndWait();
				}
			else if(email.getText().startsWith("@")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Email must not start with '@'");
				alert.showAndWait();
				}
			else if(count>1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Email must only have one '@'");
				alert.showAndWait();
				}
			else if(email.getText().contains(" ")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Email must not have space");
				alert.showAndWait();
				}
			else if(conPass.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill Confirm password field");
				alert.showAndWait();
				}
			else if(pass.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill Password field");
				alert.showAndWait();
				}
			else if(check==1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Email has been registered before");
				alert.showAndWait();
				}


			else if(!conPass.getText().equals(pass.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Register error");
				alert.setHeaderText(null);
				alert.setContentText("Confirm password must be the same as Password");
				alert.showAndWait();
				}
			else {
				curr = new user(email.getText(), pass.getText(), 0, 0, 0, 0);
				registeredUser.add(curr);
				insertuser(gabunguser(email.getText(), pass.getText(), 0, 0, 0, 0));
				
				try {
					loginPage(arg0);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		);
		
		registerPage = new Scene(bp,500,350);
		arg0.setScene(registerPage);
	}
	
	public void viewImage(product product) throws FileNotFoundException{
		
		Stage viewsta = new Stage();
		viewsta.setTitle("View Image");
		Image logo = new Image(new File("assets/logo.png").toURI().toString());
		viewsta.getIcons().add(logo);
		
		BorderPane vbp = new BorderPane();
		
		ImageView proimg = new ImageView(new Image(new File(product.getProimg()).toURI().toString()));
		
		Label prolb = new Label(product.getProdesc());
		prolb.setMinWidth(230);
		prolb.setWrapText(true);
		proimg.setFitHeight(200);
		proimg.setFitWidth(200);
		
		GridPane btnlayout = new GridPane();
		Button rtright = new Button("Rotate Right");
		Button rtleft = new Button("Rotate Left");
		Button zmin = new Button("Zoom In");
		Button zmout = new Button("Zoom Out");
		
		rtright.setMinWidth(100);
		rtleft.setMinWidth(100);
		zmin.setMinWidth(100);
		zmout.setMinWidth(100);
		btnlayout.add(rtright, 0, 0);
		btnlayout.add(rtleft, 0, 1);
		btnlayout.add(zmin, 1, 0);
		btnlayout.add(zmout, 1, 1);
		btnlayout.setVgap(10);
		btnlayout.setHgap(10);
		
		VBox imgbox = new VBox(proimg);
		imgbox.setPadding(new Insets(0, 0, 30, 0)); 
		VBox probox = new VBox(imgbox, prolb, btnlayout);
		probox.setSpacing(10);
		
		vbp.getChildren().add(probox);
		

		probox.setPadding(new Insets(50, 140, 50, 140));
		
		rtright.setOnAction(e->{
			proimg.setRotate(proimg.getRotate() + 90);
		});
		
		rtleft.setOnAction(e->{
			proimg.setRotate(proimg.getRotate() - 90);
		});
		
		zmin.setOnAction(e->{
			if(proimg.getScaleX() * 1.2 <= 2.5) {
				proimg.setScaleX(proimg.getScaleX() * 1.2);
				proimg.setScaleY(proimg.getScaleY() * 1.2);
				
			}
		});
		
		zmout.setOnAction(e->{
			if(proimg.getScaleX() * 0.8 >= 0.4) {
				proimg.setScaleX(proimg.getScaleX() * 0.8);
				proimg.setScaleY(proimg.getScaleY() * 0.8);
				
			}
		});
		
		Scene imgsce = new Scene(vbp, 500, 400);
		viewsta.setScene(imgsce);
		
		viewsta.show();
	}
	
	//Barang yang ada di admin
	public void addtoAdmin(TilePane tp, product product) throws FileNotFoundException{
		GridPane probox = new GridPane();
		
		ImageView prov = new ImageView(new Image(new File(product.getProimg()).toURI().toString()));;
		Label pronm = new Label("Name: ");
		TextField pronmed = new TextField(product.getPronm());
		pronmed.setDisable(true);
		Label propr = new Label("Price: ");
		TextField propred = new TextField(String.valueOf(product.getPropr()));
		Label prost = new Label("Stock: ");
		TextField prosted = new TextField(String.valueOf(product.getProst()));
		Label prodesc = new Label("Description: ");
		TextField prodesced = new TextField(product.getProdesc());
		Button proupd = new Button("Update");
		
		prov.setFitHeight(150); 
		prov.setFitWidth(200);
		probox.setAlignment(Pos.CENTER);
		probox.add(prov, 0, 0);
		probox.add(pronm, 1, 0);
		probox.add(pronmed, 2, 0);
		probox.add(propr, 3, 0);
		probox.add(propred, 4, 0);
		probox.add(prost, 5, 0);
		probox.add(prosted, 6, 0);
		probox.add(prodesc, 1, 1);
		probox.add(prodesced, 2, 1);
		GridPane.setColumnSpan(prodesced, 4);
		probox.add(proupd, 2, 2);
		
		tp.getChildren().add(probox);
		
		prov.setOnMouseClicked(e->{
			try {
				viewImage(product);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//untuk update button
		proupd.setOnMouseClicked(e->{
			try {
				if(prosted.getText().equals("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The stock must not be empty");
					alert.showAndWait();
				}
				else if(propred.getText().equals("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The price must not be empty");
					alert.showAndWait();
				}
				else if(prodesced.getText().equals("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The description must not be empty");
					alert.showAndWait();
				}
				else if(!(prosted.getText().matches("^-[1-9]\\d*|0$") || prosted.getText().matches("^[0-9]+$"))) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The stock must be numeric");
					alert.showAndWait();
				}
				else if(!(propred.getText().matches("^-[1-9]\\d*|0$") || propred.getText().matches("^[0-9]+$"))) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The price must be numeric");
					alert.showAndWait();
				}
				else if(Integer.valueOf(prosted.getText()) < 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The stock must be more than or equal to 0");
					alert.showAndWait();
				}
				else if(Integer.valueOf(propred.getText()) <= 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("The price must be more than 0");
					alert.showAndWait();
				}
				else {
					product.setPropr(Integer.valueOf(propred.getText()));
					product.setProst(Integer.valueOf(prosted.getText()));
					product.setProdesc(prodesced.getText());					
					
					rewriteproduct(gabungproduct(productlist.get(0).getProimg(), productlist.get(0).getPronm(), productlist.get(0).getPropr(), productlist.get(0).getProst(), productlist.get(0).getProdesc()));
					
					for(int i = 1; i < productlist.size(); i++) {
						insertproduct(gabungproduct(productlist.get(i).getProimg(), productlist.get(i).getPronm(), productlist.get(i).getPropr(), productlist.get(i).getProst(), productlist.get(i).getProdesc()));
					}
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Update product");
					alert.setHeaderText(null);
					alert.setContentText("Updated successfully");
					alert.showAndWait();
				}
				
			} catch (Exception e1){
				e1.printStackTrace();
			}
		});
		
	}
	
	//Admin
	public void adminPage(Stage arg0) throws FileNotFoundException {
		Image image = new Image(new File("assets/logo.png").toURI().toString());
		
		BorderPane bp = new BorderPane();
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem logout = new MenuItem("Logout");
		
		menu.getItems().add(logout);
		mb.getMenus().add(menu);
		bp.setTop(mb);
		
		ScrollPane sp = new ScrollPane();
		TilePane tp = new TilePane();
		
		addtoAdmin(tp, productlist.get(0));
		addtoAdmin(tp, productlist.get(1));
		addtoAdmin(tp, productlist.get(2));
		addtoAdmin(tp, productlist.get(3));
		
		tp.setPadding(new Insets(10));
		tp.setVgap(70);
		sp.setContent(tp);
		tp.setAlignment(Pos.CENTER);
		sp.setPadding(new Insets(30, 70, 0, 70));
		sp.setFitToWidth(true);
		bp.setCenter(sp);
		
		arg0.getIcons().add(image);
		arg0.setTitle("ADMIN");
		
		logout.setOnAction(e->{
			try {
				loginPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		adminPage = new Scene(bp, 960, 700);
		arg0.setScene(adminPage);
	}
	
	//Welcome 
	public void userWelcome(Stage arg0) throws FileNotFoundException {
		Image image = new Image(new File("assets/logo.png").toURI().toString());
		BorderPane bp = new BorderPane();
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem viewKeyboard = new MenuItem("View Keyboard");
		MenuItem logout = new MenuItem("Logout");
		VBox vb = new VBox();
		Text title = new Text("NEW KEYBOARD ARRIVED!");
		Button contBtn = new Button("CONTINUE >>");
		Media vid = new Media(new File("assets/keyboard.mp4").toURI().toString());
		MediaPlayer mp = new MediaPlayer(vid);
		MediaView viewVid = new MediaView(mp);
		mp.setAutoPlay(true);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		viewVid.setFitWidth(400);
		viewVid.setFitHeight(400);
		
		menu.getItems().addAll(viewKeyboard,logout);
		mb.getMenus().add(menu);
		bp.setTop(mb); 
		vb.getChildren().addAll(title,viewVid,contBtn);
		title.setStyle("-fx-font-size:30px; -fx-font-weight:bold");
		contBtn.setStyle("-fx-font-size:12px; -fx-color:#3c4043; -fx-text-fill:white");
		contBtn.setMinWidth(200);
		contBtn.setMinHeight(35);
		bp.setCenter(vb);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);
		
		arg0.getIcons().add(image);
		arg0.setTitle("USER");
		
		logout.setOnAction(e->{
			try {
				mp.stop();
				loginPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		viewKeyboard.setOnAction(e->{
			try {
				mp.stop();
				musicuser.setAutoPlay(true);
				musicuser.setCycleCount(MediaPlayer.INDEFINITE);
				userPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		contBtn.setOnAction(e->{
			try {
				mp.stop();
				musicuser.setAutoPlay(true);
				musicuser.setCycleCount(MediaPlayer.INDEFINITE);
				userPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		userWelcome = new Scene(bp,900,500);
		arg0.setScene(userWelcome);
	}
	
	//Untuk tambah ke catalogue di User Page
	public void addCatalogue(TilePane tp, product product) throws FileNotFoundException{
		VBox probox = new VBox();
		
		ImageView prov = new ImageView(new Image(new File(product.getProimg()).toURI().toString()));
		Label pronm = new Label("Name: " + product.getPronm());
		Label propr = new Label("Price: " + String.valueOf(product.getPropr()));
		Label prost = new Label("Stock: " + String.valueOf(product.getProst()));
		Label prodesc = new Label("Description: " + product.getProdesc());
		
		pronm.setMaxSize(200, 200);
		pronm.setWrapText(true);
		prodesc.setMaxSize(200, 200);
		prodesc.setWrapText(true);
		prov.setFitHeight(200);
		prov.setFitWidth(300);
		probox.getChildren().addAll(prov, pronm, propr, prost, prodesc);
		probox.setId(product.getPronm());
		
		tp.getChildren().add(probox);
		
		prov.setOnMouseClicked(e->{
			try {
				viewImage(product);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		probox.setOnDragDetected((e)->{
			
			Dragboard db = probox.startDragAndDrop(TransferMode.ANY);
			ClipboardContent cont = new ClipboardContent();
		
			cont.putString(probox.getId());
			db.setContent(cont);
			e.setDragDetect(true);
			e.consume();
		});
	}
	
	
	//bagian cart
	public void addtoCart(TilePane tp, product product) throws FileNotFoundException{
		VBox probox = new VBox();
		
		ImageView prov = new ImageView(new Image(new File(product.getProimg()).toURI().toString()));
		Label pronm = new Label(product.getPronm());
		
		pronm.setMaxSize(200, 200);
		pronm.setWrapText(true);
		prov.setFitHeight(200);
		prov.setFitWidth(200);
		
		Label probuy = new Label("Total: ");
		
		if(product.getPronm().equals("Igoltech Keebs XO200")) {
			probuy.setText("Total: " + curr.getk1buy());
			
		} else if(product.getPronm().equals("Dark Black RGB")){

			probuy.setText("Total: " + curr.getk2buy());
			
		} else if(product.getPronm().equals("Watermelon Keebs Z200")){
			
			probuy.setText("Total: " + curr.getk3buy());
			
		} else if(product.getPronm().equals("Igoltech Classic Keebs")){
			
			probuy.setText("Total: " + curr.getk4buy());
			
		}
		
		prov.setFitHeight(200);
		prov.setFitWidth(300);
		
		probox.getChildren().addAll(prov, pronm, probuy);
		
		tp.getChildren().add(probox);
	}
	
	//User 
	public void userPage(Stage arg0) throws FileNotFoundException {
		
		Image image = new Image(new File("assets/logo.png").toURI().toString());
		
		BorderPane bp = new BorderPane();
		VBox container = new VBox();
		HBox usepage = new HBox();
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Menu");
		Label header = new Label("Keyboard Catalogue");
		MenuItem logout = new MenuItem("Logout");
		
		container.getChildren().addAll(mb,header,bp);
		
		ScrollPane sp = new ScrollPane();
		TilePane tp = new TilePane();
		
		addCatalogue(tp, productlist.get(0));
		addCatalogue(tp, productlist.get(1));
		addCatalogue(tp, productlist.get(2));
		addCatalogue(tp, productlist.get(3));
		
		
		sp.setContent(tp);
		sp.setFitToWidth(true);
		sp.setPrefSize(450, 450);
		
		tp.setPadding(new Insets(10));
		tp.setHgap(10);
		tp.setVgap(10);
		
		VBox cartlyt = new VBox();
		ScrollPane cartsp = new ScrollPane();
		TilePane carttp = new TilePane();
		
		Label cartlbl = new Label("Your Cart");
		Button buy = new Button("Buy");
		Button clrcart = new Button("Clear Cart");
		
		buy.setMinWidth(90);
		clrcart.setMinWidth(90);
		HBox cartbtn = new HBox(buy, clrcart);
		cartbtn.setSpacing(5);
		
		cartsp.setContent(carttp);
		cartsp.setFitToWidth(true);
		cartsp.setPrefSize(450, 450);
		
		carttp.setPadding(new Insets(10));
		carttp.setHgap(10);
		carttp.setVgap(10);
		
		cartlyt.getChildren().addAll(cartlbl, cartsp, cartbtn);
		cartlyt.setAlignment(Pos.CENTER);
		
		cartbtn.setAlignment(Pos.BOTTOM_RIGHT);
		
		usepage.getChildren().addAll(sp, cartlyt);
		bp.setCenter(usepage);
		
		container.setAlignment(Pos.TOP_CENTER);
		
		cartlbl.setStyle("-fx-font-size:20px; -fx-font-weight:bold");
		BorderPane.setAlignment(usepage, Pos.TOP_CENTER);
		arg0.getIcons().add(image);
		arg0.setTitle("USER");
		
		menu.getItems().addAll(logout);
		mb.getMenus().add(menu);
		
		//Kalau akun lain sudah beli dan stok sekarang kurang, tidak dimasukkan ke cart
		if(productlist.get(0).getProst() < curr.getk1buy()) {
			curr.setk1buy(0);
		}
		if(productlist.get(1).getProst() < curr.getk2buy()) {
			curr.setk2buy(0);
		}
		if(productlist.get(2).getProst() < curr.getk3buy()) {
			curr.setk3buy(0);
		}
		if(productlist.get(3).getProst() < curr.getk4buy()) {
			curr.setk4buy(0);
		}
		
		//masukkan ulang cart kalau sudah ada sebelumnya
		if(curr.getk1buy() > 0) {
			addtoCart(carttp, productlist.get(0));
		}
		if(curr.getk2buy() > 0) {
			addtoCart(carttp, productlist.get(1));
		}
		if(curr.getk3buy() > 0) {
			addtoCart(carttp, productlist.get(2));
		}
		if(curr.getk4buy() > 0) {
			addtoCart(carttp, productlist.get(3));
		}
		
		cartsp.setOnDragOver((e)->{
			if(e.getGestureSource()!=cartsp && e.getDragboard().hasString()) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});
		
		cartsp.setOnDragDropped((e)->{
			Dragboard db = e.getDragboard();
			
			if(db.hasString()) {
				Label nodeId = new Label(db.getString());
				
				if(nodeId != null) {
					try {
						Integer idx = 0;
						for(int i = 0; i < productlist.size(); i++) {
							if(productlist.get(i).getPronm().equals(db.getString())) {
								idx = i;
								break;
							}
						}
						
						//Untuk atur stok dan total buy
						if(productlist.get(idx).getPronm().equals("Igoltech Keebs XO200") && productlist.get(idx).getProst() > curr.getk1buy()) {
							
							curr.setk1buy(curr.getk1buy() + 1);
							
						} else if(productlist.get(idx).getPronm().equals("Dark Black RGB")  && productlist.get(idx).getProst() > curr.getk2buy()){

							curr.setk2buy(curr.getk2buy() + 1);
							
						} else if(productlist.get(idx).getPronm().equals("Watermelon Keebs Z200")  && productlist.get(idx).getProst() > curr.getk3buy()){
							
							curr.setk3buy(curr.getk3buy() + 1);
							
						} else if(productlist.get(idx).getPronm().equals("Igoltech Classic Keebs")  && productlist.get(idx).getProst() > curr.getk4buy()){
							
							curr.setk4buy(curr.getk4buy() + 1);
						
						} else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Add to cart error");
							alert.setHeaderText(null);
							alert.setContentText("Not enough stock");
							alert.showAndWait();
							return;
						}
						
						userPage(arg0);

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				e.setDropCompleted(true);
			}else {
				e.setDropCompleted(false);
			}
		});
		
		clrcart.setOnAction(e->{
			
			curr.setk1buy(0);
			curr.setk2buy(0);
			curr.setk3buy(0);
			curr.setk4buy(0);
			
			try {
				userPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		buy.setOnAction(e->{
			if(curr.getk1buy() == 0 && curr.getk2buy() == 0 && curr.getk3buy() == 0 && curr.getk4buy() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Buy");
				alert.setHeaderText(null);
				alert.setContentText("Nothing in Cart");
				alert.showAndWait();
				return;
			}
			
			productlist.get(0).setProst(productlist.get(0).getProst() - curr.getk1buy());
			curr.setk1buy(0);
			
			productlist.get(1).setProst(productlist.get(1).getProst() - curr.getk2buy());
			curr.setk2buy(0);
			
			productlist.get(2).setProst(productlist.get(2).getProst() - curr.getk3buy());
			curr.setk3buy(0);
			
			productlist.get(3).setProst(productlist.get(3).getProst() - curr.getk4buy());
			curr.setk4buy(0);
			
			rewriteproduct(gabungproduct(productlist.get(0).getProimg(), productlist.get(0).getPronm(), productlist.get(0).getPropr(), productlist.get(0).getProst(), productlist.get(0).getProdesc()));
			
			for(int i = 1; i < productlist.size(); i++) {
				insertproduct(gabungproduct(productlist.get(i).getProimg(), productlist.get(i).getPronm(), productlist.get(i).getPropr(), productlist.get(i).getProst(), productlist.get(i).getProdesc()));
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Buy");
			alert.setHeaderText(null);
			alert.setContentText("Successfully bought");
			alert.showAndWait();
			
			try {
				userPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		rewriteuser(gabunguser(registeredUser.get(0).getEmail(), registeredUser.get(0).getPassword(), registeredUser.get(0).getk1buy(), registeredUser.get(0).getk2buy(), registeredUser.get(0).getk3buy(), registeredUser.get(0).getk4buy()));
		
		for(int i = 1; i < registeredUser.size(); i++) {
			insertuser(gabunguser(registeredUser.get(i).getEmail(), registeredUser.get(i).getPassword(), registeredUser.get(i).getk1buy(), registeredUser.get(i).getk2buy(), registeredUser.get(i).getk3buy(), registeredUser.get(i).getk4buy()));
		}
		
		logout.setOnAction(e->{
			try {
				musicuser.stop();
				loginPage(arg0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		userPage = new Scene(container,900,500);
		arg0.setScene(userPage);
	}

}
