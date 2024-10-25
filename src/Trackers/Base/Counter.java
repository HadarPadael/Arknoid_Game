package Trackers.Base;
/**
 * @author Hadar Padael.
 * 212916753
 * a class for testing the game.
 */
public class Counter {
    //class property/
    private int value;
//constructor/
    /**
     * constructor method.
     * @param number int.
     */
    public Counter(int number) {
        this.value = number;
    }
    //accessor/
    // get current count.
    /**
     * accessor.
     * @return int counter.
     */
    public int getValue() {
        return value;
    }
//other methods/
    // add number to current count.
    /**
     * a method which increases the counter by the given number.
     * @param number int.
     */
    public void increase(int number){
        this.value = this.value + number;
    }
    // subtract number from current count.
    /**
     * a method which decreases from the counter a given number.
     * @param number int.
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

}