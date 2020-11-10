package com.example.clikentertainment.live;

public class Vote {

    private State.Answer answerUp;
    private State.Answer answerDown;
    private int colorUp;
    private int colorDown;

    //NONE, UP, DOWN was last move
    public Vote(State.Answer up, State.Answer down, int colorUp, int colorDown) {
        this.answerUp = up;
        this.answerDown = down;
        this.colorUp = colorUp;
        this.colorDown = colorDown;
    }

    public State.Answer getUp() {
        return answerUp;
    }

    public State.Answer getDown() {
        return answerDown;
    }

    public void setUp(State.Answer up) {
        answerUp = up;
    }

    public void setDown(State.Answer down) {
        answerDown = down;
    }

    public int getColorUp() {
        return colorUp;
    }

    public int getColorDown() {
        return colorDown;
    }

    public void setColorUp(int color) {
        colorUp = color;
    }

    public void setColorDown(int color) {
        colorDown = color;
    }

}

