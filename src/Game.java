import GameFlow.DirectHit;
import GameFlow.GameFlow;
import GameFlow.FinalHour;
import GameFlow.Green3;
import GameFlow.WideEasy;
import GameInterfaces.LevelInformation;
import Helpers.StringToInts;
import java.util.ArrayList;

/**
 * @author Hadar Padael.
 * 212916753
 * a class for testing the game.
 */
public class Game {
    /**
     * a main method for running the game.
     * @param args String.
     */
    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow();
        ArrayList<LevelInformation> levels1 = new ArrayList<>();
        ArrayList<LevelInformation> levels2 = new ArrayList<>();
        levels1.add(new DirectHit());
        levels1.add(new WideEasy());
        levels1.add(new Green3());
        levels1.add(new FinalHour());
        int[] input = StringToInts.toInts(args);

        if (args.length == 0 || input[0] == 0) {
            gameFlow.runLevels(levels1);
        } else {
            for (int i = 0; i < args.length; ++i) {
                if (input[i] >= 1 && input[i] <= 4) {
                    levels2.add(levels1.get(input[i] - 1));
                }
            }
            gameFlow.runLevels(levels2);
        }
    }
}



