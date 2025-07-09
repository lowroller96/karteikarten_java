package org.example.model;
import java.util.ArrayList;
import java.util.List;
public class Card {
    private String id;
    private String question;
    private List <AnswerOption> answerOptions;
    private String topic;
    // Spaced Repetition hinzufügen
    private int repetition;
    private int intervall;
    private double efactor;
    private String lastReviewed;

    // Es folgen Getter und Setter
    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(){
        this.question=question;
    }
    public List<AnswerOption> getAnswerOptions(){
        return answerOptions;
    }
    public void setAnswerOptions(List<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }
    public String getTopic(){
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public int getRepetition(){
        return repetition;
    }
    public void setRepetition(int repetition){
        this.repetition=repetition;
    }
    public int getIntervall(){
        return intervall;
    }
    public void setIntervall(int intervall){
        this.intervall=intervall;
    }
    public double getEfactor(){
        return efactor;
    }
    public void setEfactor(){
        this.efactor=efactor;
    }
    public  String getLastReviewed(){
        return lastReviewed;
    }
    public void setLastReviewed(){
        this.lastReviewed=lastReviewed;
    }

}
