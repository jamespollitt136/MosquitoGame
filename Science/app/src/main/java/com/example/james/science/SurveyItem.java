package com.example.james.science;


public class SurveyItem {

    private String name;
    private String username;
    private float gamesRatingValue;
    private float appRatingValue;
    private float totalRating;
    private FeedbackActivity feedbackActivity;

    public SurveyItem(String name, String username, float gamesRatingValue, float appRatingValue){
        this.name = name;
        this.username = username;
        this.gamesRatingValue = gamesRatingValue;
        this.appRatingValue = appRatingValue;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    private float getGamesRatingValue() {
        return gamesRatingValue;
    }

    private float getAppRatingValue() {
        return appRatingValue;
    }

    // adds users app and game ratings together and divides by 2 to give 1 value up to 5 stars
    private void addTotals() {
        totalRating = getAppRatingValue() + getGamesRatingValue();
        totalRating = totalRating / 2;
    }

    public float getTotalRating() {
        addTotals();
        return totalRating;
    }

    private void setSurveyName(){
        this.name = feedbackActivity.getName();
    }

    private void setSurveyUsername(){
        this.username = feedbackActivity.getUsername();
    }
}
