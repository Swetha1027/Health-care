package ucmHealthCareSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login  extends Application{
	TextField firsttext,lasttext,usertext,passwordtext,phonetext,setpasswordtext,addresstext,emailtext;
	DatePicker datePicker;
	Connection connection;
	PreparedStatement pstmt;
	String sql;
	ListView<String> BookingList = new ListView();
	ArrayList<String> wordList = new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	
//final screen display method
	
	public void appointments_page(Stage primaryStage) throws ClassNotFoundException, SQLException
	{
		  ConnectToDB();
	      int count=1;
	      Button listbackButton = new Button("BACK");
	      listbackButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
	  
// back button click
	      
	      listbackButton.setOnAction(listbackAction -> 
	      {
	                  homescene(primaryStage); 
	  	 }
	      		);
	
	       String Bookingsql="select * from appointmentdetails";
	       pstmt = connection.prepareStatement(Bookingsql);
	      
	      
	     
	      ResultSet BookingRs=pstmt.executeQuery();
	      while(BookingRs.next()) {
	    	  
	    	  wordList.add("Patient Name  :    "+BookingRs.getString(1)  + "  " + BookingRs.getString(2)  +"\nAddress      :     "+ BookingRs.getString(4)+"\nAppointment Date :         "+BookingRs.getString(6)+"\nDoctor        :     "+BookingRs.getString(7)+"\nCheck-in Date :    "+BookingRs.getString(8));
	    	  count++;
	    	  
	    	  
	      }
	      
	        BookingList.setItems(FXCollections.observableArrayList(wordList));
	        BookingList.setMaxSize(1000, 1000);
	        VBox vBox = new VBox(BookingList, listbackButton);
			Scene sc = new Scene(vBox,2000,1000);
			sc.getStylesheets().add(
	                Login.class.getResource("context.css")
	                        .toExternalForm());
			primaryStage.setTitle("My Appointments");
			primaryStage.setScene(sc);
			primaryStage.show();
		    	

	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		GridPane loginpage = new GridPane();
		
		loginpage.setAlignment(Pos.CENTER);
		//loginpage.setContentDisplay(Content);
		Label namelabel = new Label(" Username  ");
		loginpage.getChildren().add(namelabel);
		loginpage.setConstraints(namelabel,0,0);
		namelabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
	       
		TextField nametext = new TextField();
		loginpage.getChildren().add(nametext);
		loginpage.setConstraints(nametext,1,0);
		
		Label namelabelvalid = new Label();
		loginpage.getChildren().add(namelabelvalid);
		loginpage.setConstraints(namelabelvalid,0,3);
		namelabelvalid.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
		   
		Label passwordlabel = new Label(" Password  ");
		loginpage.getChildren().add(passwordlabel);
		loginpage.setConstraints(passwordlabel,0,1);
		passwordlabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
		

		PasswordField passwordtext = new PasswordField();
		loginpage.getChildren().add(passwordtext);
		loginpage.setConstraints(passwordtext,1,1);
		

		Label passwordlabelvalid = new Label("");
		loginpage.getChildren().add(passwordlabelvalid);
		loginpage.setConstraints(passwordlabelvalid,2,1);
		passwordlabelvalid.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
		
		Button loginbutton = new Button("Login");
		loginpage.getChildren().add(loginbutton);
		loginbutton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
		
		loginpage.setConstraints(loginbutton,0,2);

		
		Button signupbutton = new Button("Signup");
		loginpage.getChildren().add(signupbutton);
		loginbutton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
		
		loginpage.setConstraints(signupbutton,1,2);

        final ContextMenu usernameValidator = new ContextMenu();
        usernameValidator.setAutoHide(false);
        final ContextMenu passValidator = new ContextMenu();
        passValidator.setAutoHide(false);

        final ContextMenu loginValidator = new ContextMenu();
        loginValidator.setAutoHide(false);
        
        
// signup button click
        signupbutton.setOnAction(event->{
        	
        	GridPane  signuppage = new GridPane();

    		signuppage.setVgap(10);
			signuppage.setHgap(20);
			 
		
			Label signnamelabel = new Label("   First Name");
			signuppage.getChildren().add(signnamelabel);
			signuppage.setConstraints(signnamelabel,0,0);
			
			TextField signname = new TextField();
			signuppage.getChildren().add(signname);
			signuppage.setConstraints(signname,1,0);
			
			Label signlastlabel = new Label("   Last name");
			signuppage.getChildren().add(signlastlabel);
			signuppage.setConstraints(signlastlabel,0,1);
			
			TextField signlasttext = new TextField();
			signuppage.getChildren().add(signlasttext);
			signuppage.setConstraints(signlasttext,1,1);
			
			
			Label signphonelabel = new Label("   Phone");
			signuppage.getChildren().add(signphonelabel);
			signuppage.setConstraints(signphonelabel,0,2);
			
			TextField signphonetext = new TextField();
			signuppage.getChildren().add(signphonetext);
			signuppage.setConstraints(signphonetext,1,2);
			
			
			Label signaddresslabel = new Label("   Address");
			signuppage.getChildren().add(signaddresslabel);
			signuppage.setConstraints(signaddresslabel,0,3);
			
			TextField signaddresstext = new TextField();
			signuppage.getChildren().add(signaddresstext);
			signuppage.setConstraints(signaddresstext,1,3);
			
			
			
			Label signemaillabel = new Label("   Email");
			signuppage.getChildren().add(signemaillabel);
			signuppage.setConstraints(signemaillabel,0,4);
			
			TextField signemailtext = new TextField();
			signuppage.getChildren().add(signemailtext);
			signuppage.setConstraints(signemailtext,1,4);
			
			Label signusername = new Label("   UserName");
			signuppage.getChildren().add(signusername);
			signuppage.setConstraints(signusername,0,5);
			
			TextField signusernametext = new TextField();
			signuppage.getChildren().add(signusernametext);
			signuppage.setConstraints(signusernametext,1,5);
			
			
			Label signpassword = new Label("   Password");
			signuppage.getChildren().add(signpassword);
			signuppage.setConstraints(signpassword,0,6);
			
			TextField signpasswordtext = new TextField();
			signuppage.getChildren().add(signpasswordtext);
			signuppage.setConstraints(signpasswordtext,1,6);
			
			Button sgnbutton = new Button("SignUp");
			signuppage.getChildren().add(sgnbutton);
			signuppage.setConstraints(sgnbutton, 0,7);
			
			Scene sgnscene = new Scene(signuppage,1000,1000);
			sgnscene.getStylesheets().add(
	                Login.class.getResource("context.css")
	                        .toExternalForm());
			primaryStage.setScene(sgnscene);
			primaryStage.setTitle("SignUp Page");
			primaryStage.show();
			
			sgnbutton.setOnAction(e1->{
			
					try {
						ConnectToDB();
						
						 String sql="insert into  patientdetails values(?,?,?,?,?,?,?)";
					     PreparedStatement pstmt;
						pstmt = connection.prepareStatement(sql);
						pstmt.setString(1, signname.getText().toString());
				        pstmt.setString(2, signlasttext.getText().toString());
				        pstmt.setString(3, signusernametext.getText().toString());
				        pstmt.setString(4, signphonetext.getText().toString());
				        pstmt.setString(5, signaddresstext.getText().toString());
				        pstmt.setString(6, signpasswordtext.getText().toString());
				        pstmt.setString(7, signemailtext.getText().toString());
				        
				        pstmt.executeUpdate(); 
				        
				        homescene(primaryStage);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
			        
				
			});
			

        });
// login button click
        
        loginbutton.setOnAction(event->{

          System.out.println("login button clicked");
          // Checking if the userTextField is empty
          if (nametext.getText().equals("")) {
          	System.out.println("checking the name validation");
             usernameValidator.getItems().clear();
              usernameValidator.getItems().add(
                      new MenuItem("Please enter username"));
              usernameValidator.show(nametext, Side.RIGHT, 10, 0);
          	//namelabelvalid.setText("please entet the username");
          }
          // Checking if the pwBox is empty
          if (passwordtext.getText().equals("")) {
          	
              passValidator.getItems().clear();
              passValidator.getItems().add(
                      new MenuItem("Please enter Password"));
              passValidator.show(passwordtext, Side.RIGHT, 10, 0);
              
          	//passwordlabelvalid.setText("enter the password");
          }
          if(!(nametext.getText().equals("")) && (!passwordtext.getText().equals("")) )
          {
        	  try {
         			ConnectToDB();
  					
  					Statement st = connection.createStatement();
      				
      	             sql="select * from patientdetails where username=? and password =?";
      	             pstmt = connection.prepareStatement(sql);
      	             pstmt.setString(1, nametext.getText().toString());
      	             pstmt.setString(2, passwordtext.getText().toString());
      	             ResultSet rs=pstmt.executeQuery();
      	             
      	             if(rs.next())
      	             {
      	            	 homescene(primaryStage);
      	           }
    	             else
    	             {
    	            	 GridPane failedpane = new GridPane();
    	            	 
    	            	 Label failedlabel =new Label("Login failed   .  Close the application and run again");
    	            	 failedpane.getChildren().add(failedlabel);
    	            	 failedpane.setConstraints(failedlabel, 0, 0);
    	            	 Scene failedscene = new Scene(failedpane,1000,1000);
    	            	 failedscene.getStylesheets().add(
    	     	                Login.class.getResource("context.css")
    	     	                        .toExternalForm());
    	    				primaryStage.setScene(failedscene);
    	    				primaryStage.show();
    	             }
    	             
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        
        }
    	

        });
		
		Scene scene = new Scene(loginpage,1000,1000);
		scene.getStylesheets().add(
                Login.class.getResource("Login.css")
                        .toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login Page");
		primaryStage.show();
	  /*  
		   // Context Menu for error messages
        final ContextMenu usernameValidator = new ContextMenu();
        usernameValidator.setAutoHide(false);
        final ContextMenu passValidator = new ContextMenu();
        passValidator.setAutoHide(false);

        final ContextMenu loginValidator = new ContextMenu();
        loginValidator.setAutoHide(false);

		*/
	}

// home screen to display appointment page
	
    public void homescene(Stage primaryStage)
    {
    	
    	            		GridPane signupgrid = new GridPane();
    	    				signupgrid.setVgap(10);
    	    				signupgrid.setHgap(20);
    	    				 
    	    			
    	    				Label firstlabel = new Label("   First name");
    	    				signupgrid.getChildren().add(firstlabel);
    	    				signupgrid.setConstraints(firstlabel,0,0);
    	    				
    	    			    firsttext = new TextField();
    	    				signupgrid.getChildren().add(firsttext);
    	    				signupgrid.setConstraints(firsttext,1,0);
    	    				
    	    				Label lastlabel = new Label("   Last name");
    	    				signupgrid.getChildren().add(lastlabel);
    	    				signupgrid.setConstraints(lastlabel,0,1);
    	    				
    	    				lasttext = new TextField();
    	    				signupgrid.getChildren().add(lasttext);
    	    				signupgrid.setConstraints(lasttext,1,1);
    	    				
    	    				
    	    				Label phonelabel = new Label("   Phone");
    	    				signupgrid.getChildren().add(phonelabel);
    	    				signupgrid.setConstraints(phonelabel,0,2);
    	    				
    	    				phonetext = new TextField();
    	    				signupgrid.getChildren().add(phonetext);
    	    				signupgrid.setConstraints(phonetext,1,2);
    	    				
    	    				
    	    				Label addresslabel = new Label("   Address");
    	    				signupgrid.getChildren().add(addresslabel);
    	    				signupgrid.setConstraints(addresslabel,0,3);
    	    				
    	    				addresstext = new TextField();
    	    				signupgrid.getChildren().add(addresstext);
    	    				signupgrid.setConstraints(addresstext,1,3);
    	    				
    	    				
    	    				
    	    				Label emaillabel = new Label("   Email");
    	    				signupgrid.getChildren().add(emaillabel);
    	    				signupgrid.setConstraints(emaillabel,0,4);
    	    				
    	    				emailtext = new TextField();
    	    				signupgrid.getChildren().add(emailtext);
    	    				signupgrid.setConstraints(emailtext,1,4);

    	      				Label fromlabel = new Label("   Date");
    	      				signupgrid.getChildren().add(fromlabel);
    	      				signupgrid.setConstraints(fromlabel,0,5);
    	    				
    	    				 datePicker = new DatePicker();
    	    				 signupgrid.getChildren().add(datePicker);
    	    				 signupgrid.setConstraints(datePicker,1,5);
    	    				 datePicker.setOnAction(new EventHandler() {
    	    				     public void handle(Event t) {
    	    				         LocalDate date = datePicker.getValue();
    	    				         
    	    				     }

    	    				 });
    	    		
    	    				 Label doctorlabel = new Label("   Doctor Type");
    	     				signupgrid.getChildren().add(doctorlabel);
    	     				signupgrid.setConstraints(doctorlabel,0,6);
    	     				
    	    				 ComboBox cb = new ComboBox();
    		      				cb.getItems().addAll(
    		      						"Allergists",
    		      						"Dermotologist",
    		      						"Opthalmologist",
    		      						"Obstetrician",
    		      						"Cardiologist",
    		      						"Endocrinologists",
    		      						"Gastroenterologists",
    		      						"Nephrologists",
    		      						"Urologists",
    		      						"Neurologists",
    		      						"General surgeons"
    		      						
    		      						);

    		      				signupgrid.getChildren().add(cb);
    		    				 signupgrid.setConstraints(cb,1,6);


    		    				 Label timelabel = new Label("   Time");
    		     				signupgrid.getChildren().add(timelabel);
    		     				signupgrid.setConstraints(timelabel,0,7);
    		     				
    		    				 ComboBox timeslot = new ComboBox();
    			      				timeslot.getItems().addAll(
    			      						"7AM - 9AM",
    			      						"10AM -11AM",
    			      					    "12PM - 1PM",
    			      						"3PM - 5PM"
    			      						);

    			      				signupgrid.getChildren().add(timeslot);
    			    				 signupgrid.setConstraints(timeslot,1,7);

    		    				 
    		    				 Button submitbutton = new Button("Submit");
    		    				 signupgrid.getChildren().add(submitbutton);
    		    				 signupgrid.setConstraints(submitbutton,1,8);
    		    		
    		    					
    	    	      				submitbutton.setOnAction(myBookingsAction->{
    									try {
    										ConnectToDB();
    										
    										sql="insert into  appointmentdetails values(?,?,?,?,?,?,?,?)";
    									    PreparedStatement pstmt;
    										pstmt = connection.prepareStatement(sql);
    										pstmt.setString(1, firsttext.getText().toString());
    								        pstmt.setString(2, lasttext.getText().toString());
    								        pstmt.setString(3, phonetext.getText().toString());
    								        pstmt.setString(4, addresstext.getText().toString());
    								        pstmt.setString(5, emailtext.getText().toString());
    								        pstmt.setString(6, datePicker.getValue().toString());
    								        pstmt.setString(7, cb.getValue().toString());
    								        pstmt.setString(8, timeslot.getValue().toString());
    								        
    								        pstmt.executeUpdate(); 
											appointments_page(primaryStage);
										} catch (ClassNotFoundException | SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
    								});
    	    	      			
    		    				 
    	    				Scene bookingscene = new Scene(signupgrid,1000,1000);
    	    				bookingscene.getStylesheets().add(
    	    		                Login.class.getResource("context.css")
    	    		                        .toExternalForm());
    	    				primaryStage.setScene(bookingscene);
    	    				primaryStage.setTitle("Appointment Page");
    	    				primaryStage.show();

    	             }
    
	public void ConnectToDB() throws SQLException, ClassNotFoundException {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection
		        ("jdbc:mysql://localhost/appointment", "root", "swetha"
		        		+ "");
		
		}

}
