package com.example.clikentertainment.live;

public class Event {

    private String name;
    private String password;
    private String code;
    private SongList songList;

    public Event(String name, String code, String password) {
        this.name = name;
        this.password = password;
        this.code = code;
        this.songList = new SongList();
    }

    public String getName() {
        return name;
    }

    public Boolean codeCheck(String attempt) {
        if (attempt.equals(code)) {
            return true;
        } else {
            return false;
         }
    }

    public Boolean passwordCheck(String attempt) {
        if (attempt.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public SongList getSongList() {
        return songList;
    }
}