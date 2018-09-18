
package util;

import java.util.ArrayList;

/**
 *
 * @author Mirko
 */
public class Question {
    
    private String question;
    private ArrayList<String> answers;
    private String rightAnswer;
    private int id;
    private String hint;
    
    public static int currentId = 0;
    
    public Question(String q, String a1, String a2, String a3, String a4, int h)
    {
        question = q;
        answers = new ArrayList<String>();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        rightAnswer = a1;
        id = currentId++;
        
        if(h == 1) { hint = "Authentication"; }
        if(h == 2) { hint = "Confidentiality"; }
        if(h == 3) { hint = "Radix64 Conversion"; }
        if(h == 4) { hint = "Radix64 and Compression"; }
        if(h == 5) { hint = "Authentication and Confidentiality"; }
        if(h == 6) { hint = "Segmentation and Reassembly"; }
        if(h == 7) { hint = "Session Key"; }
        if(h == 8) { hint = "Private Key Ring"; }
        if(h == 9) { hint = "Public Key Ring"; }
        if(h == 10) { hint = "Web of Trust"; }
        
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Question.currentId = currentId;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    
    
    
}
