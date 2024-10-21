package Helpers;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which is in charge of converting the given input to integers.
 */
public class StringToInts {

    /**
     * provides us with an array of integers rather than a string.
     * @param toInts string
     * @return array of integers
     */
    public static int[] toInts(String[] toInts) {
        int[] numbers = new int[toInts.length];
        for (int i = 0; i < toInts.length; i++) {
            try {
                numbers[i] = Integer.parseInt(toInts[i]);
            } catch (Exception e) {
                continue;
            }
        }
        return numbers;
    }
}
