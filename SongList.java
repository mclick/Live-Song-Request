package com.example.clikentertainment.live;

import java.util.ArrayList;

public class SongList {

    private ArrayList<Song> songs;

    //Constructor
    public SongList() {
        songs = new ArrayList<>();
    }

    public void addSong(String name, String artist, String notes) {
        songs.add(new Song(name, artist, notes, 0));
    }

    public int getSongListCount() {
        return songs.size();
    }

    public Song getSong(int position) {
        return songs.get(position);
    }

    public void removeSong(Song theRemovingSong) {
        songs.remove(theRemovingSong);
    }

}
