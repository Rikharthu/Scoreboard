package com.uv.scoreboard;

public class Hole {

    private int mStrokes;
    private String mLabel;

    public Hole(String label, int strokes) {
        mStrokes = strokes;
        mLabel = label;
    }

    public Hole() {
        mStrokes=0;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getStrokes() {
        return mStrokes;
    }

    public void setStrokes(int strokes) {
        mStrokes = strokes;
    }
}
