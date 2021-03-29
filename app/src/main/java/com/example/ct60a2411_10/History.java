package com.example.ct60a2411_10;

import java.util.ArrayList;
import java.util.ListIterator;

public class History {

    History() {}

    private ArrayList<WebPage> myPages = new ArrayList<>(); // Array list size to be set 10 + 1
    private ListIterator pageLitr = myPages.listIterator(); // List iterator to handle array list actions (get, set, add, remove,..)

    public static History myHistory = new History(); // Singleton

    public static History getInstance() {
        return myHistory;
    } // Singleton

    public void addPage(String url) {

        // Add new page into the array list
        // Array list max size 1 + 10 items
        // If page amount exceeds array list max size, remove oldest from the list
        // If page is not stored in the last position (newest) in the array list, remove all
        // "newer" pages from the list

    }

    public String getPage(int direction) { // Direction prev -1, current 0, next 1
        String url = "";

        //

        return url;
    }

    private void removePage(int pos) {

        // Removes page in position pos from array list
        // Can be used for dropping the oldest page in the history and
        // cleaning the "next" pages if a new page is added

    }

}
