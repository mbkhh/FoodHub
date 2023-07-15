package FoodHub.Control;

import FoodHub.Base.Functions;
import FoodHub.Base.MainParham;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController{

    @FXML
    private Label loginLAbel;

    @FXML
    private Button registerButton;

    @FXML
    private Button regbutton;
    @FXML
    private TextField loginPassword;

    @FXML
    private TextField loginUsername;

    @FXML
    private ImageView loginpress;
    private ImageView loginpress2;

    private Parent root;
    private Stage loginStage;
    private Stage registerStage;
    private Stage startStage;

    @FXML
    private ChoiceBox<String> userType;

    @FXML
    private TextField regFullname;

    @FXML
    private AnchorPane a;

    @FXML
    private TextField regPass;

    @FXML
    private TextField regSA;
    @FXML
    private TextField regAddress;

    @FXML
    private TextField regUsername;
    @FXML
    public Label recoverPass;

    @FXML
    public Label wrongPass;

    @FXML
    public Label wrongUsername;
    @FXML
    private Label secQues;
    @FXML
    private ImageView humanhead;
    @FXML
    private ImageView luck;
    @FXML
    private ImageView questionMark;
    @FXML
    private TextField regSQ;
    @FXML
    private TextField secAnswer;
    @FXML
    private Label wrongSecAns;
    @FXML
    private ImageView okpress;
    @FXML
    private Label insecureName;

    @FXML
    private Label insecurePass;

    @FXML
    private Label inuseUSername;

    @FXML
    private Label less4Name;

    @FXML
    private Label less4Username;

    @FXML
    private Label less8Pass;

    @FXML
    private Label outofrageAddress;
    @FXML
    private Label CalculationLabel;

    @FXML
    private Label typela;

    @FXML
    private TextField calAns;

    @FXML
    private Label salabel;

    @FXML
    private Label sqlabel;
    @FXML
    private Label calEror;
    boolean helper2= true;
    int ans=-1009;



//    @FXML
//    public void initialize() {
//
//        String [] userTypes = {"NORMAL","BUSINESSOWNER","DELIVERY"};
//        if(userType.getScene()registerStage)
//        userType.getItems().addAll(userTypes);
//    }

    /**
     * requirments
     */

    boolean helper=true;

//    @FXML
//    public void initialize()
//    {
//
//    }

    public void regStage(Event e) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
////        registerStage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        Main.primaryStage.setScene(new Scene(root,Main.primaryWidth, Main.primaryHeight));
//        Main.primaryStage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Hello!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    public void logStage(Event e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Hello!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
    String username,password,fullname,address,secA,secQ,type;
    ArrayList <String> info = new ArrayList<>();

    public void loginhandler(Event e)
    {
         username =  loginUsername.getText();
         password =  loginPassword.getText();
        String Authentication=User.loginUser(username,password);
        boolean gotoNextPage = false;
        switch (Authentication) {
            case "Username":
                wrongUsername.setVisible(true);
                wrongPass.setVisible(false);
                recoverPass.setVisible(false);
                break;
            case "Password" :
                wrongUsername.setVisible(false);
                wrongPass.setVisible(true);
                recoverPass.setVisible(true);
                break;
            case "Done" :
                wrongUsername.setVisible(false);
                wrongPass.setVisible(false);
                recoverPass.setVisible(false);
                gotoNextPage=true;
            default:
                break;
    }
    }

    public void recoveryHandler(Event e)
    {
        wrongUsername.setVisible(false);
        wrongPass.setVisible(false);
        recoverPass.setVisible(false);
        humanhead.setVisible(false);
        luck.setVisible(false);
        loginUsername.setVisible(false);
        loginPassword.setVisible(false);
        loginpress.setVisible(false);
        okpress.setVisible(true);
        questionMark.setVisible(true);
        secAnswer.setVisible(true);
        secQues.setVisible(true);
        secQues.setText(FoodHub.Base.Main.sql.getUser(username).securityQuestion);
    }
    public void loginhandler2(Event e) {
        if (!secAnswer.getText().equals(FoodHub.Base.Main.sql.getUser(username).securityAnswer)) {
            wrongSecAns.setVisible(true);
        } else if(secAnswer.getText().equals(FoodHub.Base.Main.sql.getUser(username).securityAnswer)) {
            wrongSecAns.setVisible(false);
        }
    }

    public void registerHandler (Event e)
    {
        boolean x=false;
        boolean y = false;
        boolean z = false;
        if(userType.getValue()!=null) {
            if (userType.getValue().equals("NORMAL"))
                info.add("1");
            else if (userType.getValue().equals("BUSINESSOWNER"))
                info.add("2");
            else if (userType.getValue().equals("DELIVERY"))
                info.add("3");
        }
        else{ x=true;  typela.setVisible(true);}

        if(!x) {
            typela.setVisible(false);
            info.add(regUsername.getText());
            info.add(regPass.getText());
            info.add(regFullname.getText());
            if (regSQ.getText().equals("")) {
                y = true;
                sqlabel.setVisible(true);
            }
            if (regSA.getText().equals("")) {
                z = true;
                salabel.setVisible(true);
            }
            if (!x && !z) {
                info.add(regSQ.getText());
                info.add(regSA.getText());
                salabel.setVisible(false);
                sqlabel.setVisible(false);
                info.add("0");
                info.add(regAddress.getText());
                info.add(calAns.getText());
                System.out.println(ans);

                String regregreg = User.addUser(info, Integer.toString(ans));

                switch (regregreg) {
                    case "Username in use":
                        inuseUSername.setVisible(true);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    case "Username L4":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(true);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    case "Password L8":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(true);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    case "Password style":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(true);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    case "Name L4":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(true);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    case "Name style":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(true);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    case "Address OOR":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(true);
                        calEror.setVisible(false);
                        break;
                    case "Captcha":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(true);
                        break;
                    case "Done":
                        inuseUSername.setVisible(false);
                        less4Username.setVisible(false);
                        less8Pass.setVisible(false);
                        insecurePass.setVisible(false);
                        insecureName.setVisible(false);
                        less4Name.setVisible(false);
                        outofrageAddress.setVisible(false);
                        calEror.setVisible(false);
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(info);
        info = new ArrayList<>();
    }
    public void captcha(Event e)
    {

        if(helper2)
        {
            calEror.setVisible(false);
            char [] operators= {'+','-','*'};
            int operan1 = (int) (Math.random()*10);
            int operan2 = (int) (Math.random()*10);
            int ope = (int) (Math.random()*3);
             ans= Functions.Calculator(operan1,operan2,operators[ope]);
            String captchaShow =Integer.toString(operan1)+operators[ope]+Integer.toString(operan2) +"="; ;
             CalculationLabel.setText(captchaShow);
            helper2=false;

        }
    }



    public void initilizeCombobox (Event e)
    {

//        String [] userTypes = {"NORMAL","BUSINESSOWNER","DELIVERY"};
        if(helper)
            userType.getItems().addAll("NORMAL","BUSINESSOWNER","DELIVERY");
        helper=false;

    }

    public static void show() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Hello!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }






}