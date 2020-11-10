package com.example.clikentertainment.live;

import android.graphics.Color;

public class Song {

    private String name;
    private String artist;
    private int votes;
    private String notes;
    private Vote vote;

    public Song(String name, String artist, String notes, int votes) {
        this.name = name;
        this.artist = artist;
        this.votes = votes;
        this.notes = notes;
        vote = new Vote(State.Answer.NONE, State.Answer.NONE, Color.BLACK, Color.BLACK);
    }

    public String getSongName() {
        return name;
    }

    public String getArtistName() {
        return artist;
    }

    public Vote getVote() {
        return vote;
    }

    public int getVoteCount() {
        return votes;
    }

    public void upVote() {
        votes = votes + 1;
    }

    public void downVote() {
        votes = votes - 1;
    }

    public String getNotes() {
        return notes;
    }

}
