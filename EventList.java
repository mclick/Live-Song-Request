package com.example.clikentertainment.live;

import java.util.ArrayList;
import java.util.Iterator;

//Singleton Class
public class EventList {

    private static EventList eventList = null;
    private ArrayList<Event> events = null;

    static {
        eventList = new EventList();
    }

    //Constructor
    private EventList() {
        events = new ArrayList<>();
    }

    public final static EventList getEventList() {
        return eventList;
    }

    public void addEvent(String name, String code, String password) {
        events.add(new Event(name, code, password));
    }

    public int getCount() {
        return events.size();
    }

    public Event getEvent(int position) {
        return events.get(position);
    }

    public Event findEvent(String name) {
        int spot = 0;
        for (Event event : events) {
            if (name.equals(event.getName())) {
                return getEvent(spot);
            } else {
                spot = spot + 1;
            }
        }
        return null;
    }
}
