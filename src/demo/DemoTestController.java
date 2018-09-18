
package demo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import util.Question;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoTestController implements Initializable {

    @FXML private Label questionLabel;
    @FXML private RadioButton answer1;
    @FXML private RadioButton answer2;
    @FXML private RadioButton answer3;
    @FXML private RadioButton answer4;
    @FXML private Button nextButton;
    
    public static ToggleGroup answerGroup;
    
    public static int counter;
    public static int rightAnswers;
    public static ArrayList<Question> newTest;
    public static Question currentQuestion;
    
    public static boolean showRight;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        counter = 0;
        rightAnswers = 0;
        showRight = false;
        
        answerGroup = new ToggleGroup();
        answer1.setToggleGroup(answerGroup);
        answer2.setToggleGroup(answerGroup);
        answer3.setToggleGroup(answerGroup);
        answer4.setToggleGroup(answerGroup);
         
        ArrayList<Question> temp = DemoTestData.questionList;
        Collections.shuffle(temp);
        newTest = new ArrayList<Question>();
        
        for(int i=0; i<10; i++)
        {
            newTest.add(temp.get(i));
        }
        
        if(DemoTestData.showHints)
        {
            String hints = "(" + newTest.get(0).getHint() + ")" + "\n";
            questionLabel.setText(hints + "1. " + newTest.get(0).getQuestion());
        }
        else
        {
            questionLabel.setText("1. " + newTest.get(0).getQuestion());
        }
        
        
        ArrayList<String> answers = newTest.get(0).getAnswers();
        Collections.shuffle(answers);
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));
        
        currentQuestion = newTest.get(0);
        
        counter++;
    } 
    
    public void next(ActionEvent event) throws IOException
    {
        
        if((showRight == false) && DemoTestData.showHints)
        {
            if(answerGroup.getSelectedToggle() != null)
            {
                if(((RadioButton)answerGroup.getSelectedToggle()).getText().equals(currentQuestion.getRightAnswer()))
                {
                    rightAnswers++;
                    System.out.println("Tacan odgovor na pitanje " + currentQuestion.getQuestion());
                    
                    String style = "-fx-border-color: greenyellow; -fx-border-width: 5 5 5 5;";
                    ((RadioButton)answerGroup.getSelectedToggle()).setStyle(style);
                }
                else
                {
                    System.out.println("Pogresan odgovor na pitanje " + currentQuestion.getQuestion());
                    
                    String style = "-fx-border-color: red; -fx-border-width: 5 5 5 5;";
                    ((RadioButton)answerGroup.getSelectedToggle()).setStyle(style);
                    
                    if(answer1.getText().equals(currentQuestion.getRightAnswer()))
                    {
                        style = "-fx-border-color: greenyellow; -fx-border-width: 5 5 5 5;";
                        answer1.setStyle(style);
                    }
                    
                    if(answer2.getText().equals(currentQuestion.getRightAnswer()))
                    {
                        style = "-fx-border-color: greenyellow; -fx-border-width: 5 5 5 5;";
                        answer2.setStyle(style);
                    }
                    
                    if(answer3.getText().equals(currentQuestion.getRightAnswer()))
                    {
                        style = "-fx-border-color: greenyellow; -fx-border-width: 5 5 5 5;";
                        answer3.setStyle(style);
                    }
                    
                    if(answer4.getText().equals(currentQuestion.getRightAnswer()))
                    {
                        style = "-fx-border-color: greenyellow; -fx-border-width: 5 5 5 5;";
                        answer4.setStyle(style);
                    }
                }
           
                showRight = true;
                return;
            }
            else
            {
                Alert alertBox = new Alert(Alert.AlertType.WARNING);
                alertBox.setTitle("Test Warning");
                alertBox.setHeaderText(null);
                alertBox.setContentText("Please sellect an answer!");
                alertBox.showAndWait();
        
                System.out.println("Please sellect an answer!");
                
                return;
            }
        }
        else
        {
            if(counter < 10)
            { 
                if(!DemoTestData.showHints)
                {
                    if(answerGroup.getSelectedToggle() != null)
                    {
                        if(((RadioButton)answerGroup.getSelectedToggle()).getText().equals(currentQuestion.getRightAnswer()))
                        {
                            rightAnswers++;
                            System.out.println("Tacan odgovor na pitanje " + currentQuestion.getQuestion());
                        }
                        else
                        {
                            System.out.println("Pogresan odgovor na pitanje " + currentQuestion.getQuestion());
                        }
                    }
                    else
                    {
                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                        alertBox.setTitle("Test Warning");
                        alertBox.setHeaderText(null);
                        alertBox.setContentText("Please sellect an answer!");
                        alertBox.showAndWait();
                        
                        System.out.println("Please sellect an answer!");
                        
                        return;
                    }
                }
                
                currentQuestion = newTest.get(counter); 
                
                if(DemoTestData.showHints)
                {
                    String hints = "(" + newTest.get(counter).getHint() + ")" + "\n";
                    questionLabel.setText(hints + (counter + 1) + ". " + newTest.get(counter).getQuestion());
                }
                else
                {
                    questionLabel.setText((counter+1) + ". " + newTest.get(counter).getQuestion());
                }
                
                ArrayList<String> answers = newTest.get(counter).getAnswers();
                Collections.shuffle(answers);
                answer1.setText(answers.get(0));
                answer2.setText(answers.get(1));
                answer3.setText(answers.get(2));
                answer4.setText(answers.get(3));
            
                answerGroup.selectToggle(null);
            
                String style = "-fx-border-width: 0;";
                answer1.setStyle(style);
                answer2.setStyle(style);
                answer3.setStyle(style);
                answer4.setStyle(style);
                
                counter++;
                showRight = false;
            }
            else
            {
                
                if((!DemoTestData.showHints) && counter == 10)
                {
                    if(answerGroup.getSelectedToggle() != null)
                    {
                        if(((RadioButton)answerGroup.getSelectedToggle()).getText().equals(currentQuestion.getRightAnswer()))
                        {
                            rightAnswers++;
                            System.out.println("Tacan odgovor na pitanje " + currentQuestion.getQuestion());
                        }
                        else
                        {
                            System.out.println("Pogresan odgovor na pitanje " + currentQuestion.getQuestion());
                        }
                    }
                    else
                    {
                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                        alertBox.setTitle("Test Warning");
                        alertBox.setHeaderText(null);
                        alertBox.setContentText("Please sellect an answer!");
                        alertBox.showAndWait();
                        
                        System.out.println("Please sellect an answer!");
                        
                        return;
                    }
                }
                
                if(DemoTestData.showHints)
                {
                    Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
                    alertBox.setTitle("Test Completed");
                    alertBox.setHeaderText(null);
                    alertBox.setContentText("Number of right answers was: " + rightAnswers + "/10");
                    alertBox.showAndWait();
                    
                    Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                    mainWindow.close();
                    
                    System.out.println("Number of right answers was: " + rightAnswers);
                    
                    DemoTestData.showHints = false;
                    
                    return;
                }
                
                Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
                alertBox.setTitle("Test Completed");
                alertBox.setHeaderText(null);
                alertBox.setContentText("Number of right answers was: " + rightAnswers + "/10");
                alertBox.showAndWait();
                
                System.out.println("Number of right answers was: " + rightAnswers);
                
                DemoTestData.showHints = false;
                
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                
                return;
            }
        } 
    }
    
    
    
}
