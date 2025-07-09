package org.example.model;
import java.util.List;
public class Card {
    private String id;
    private String question;
    private List <AnswerOption> answerOptions;
    private String topic;
    // Spaced Repetition hinzufügen
    private int repetition=0;
    private int intervall=1;
    private double efactor=2.5;
    private String lastReviewed="";
public Card(String id,String question,List<AnswerOption> answerOptions,String topic){
    this.id=id;
    this.question=question;
    this.answerOptions=answerOptions;
    this.topic=topic;
}
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
    public void setLastReviewed(String string){
        this.lastReviewed=lastReviewed;
    }
    public void setInterval(int interval) {this.intervall = interval;}


    public void setEfactor(double max) {
    }
}
