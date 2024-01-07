package org.cis1200.TwentyFortyEight;

public class Tile {
    private int value;
    public Tile() {
        this.value = 0; // Set the default value to 0
    }
    public Tile(int t) {
        this.value = t;
    }
    public void setTile(int t) {
        this.value = t;
    }
    public int getValue() {
        return this.value;
    }

}
