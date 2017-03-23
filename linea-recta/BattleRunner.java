package fnl;
import robocode.control.*;
import robocode.control.events.*;

//
// Application that demonstrates how to run two sample robots in Robocode using the
// RobocodeEngine from the robocode.control package.
//
// @author Flemming N. Larsen
//
public class BattleRunner {

    public static void main(String[] args) {
        // Disable log messages from Robocode
        RobocodeEngine.setLogMessagesEnabled(false);
        // Create the RobocodeEngine
        //   RobocodeEngine engine = new RobocodeEngine(); // Run from current working directory
        RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/Robocode")); // Run from C:/Robocode

        // Add our own battle listener to the RobocodeEngine 
        engine.addBattleListener(new BattleObserver());

        // Show the Robocode battle view
        engine.setVisible(true);

        // Setup the battle specification
        long inactivityTime=10000000; 
        double gunCoolingRate=1.0;
        int sentryBorderSize=50;
        boolean hideEnemyNames=false;
        int numRounds = 5;
        BattlefieldSpecification battlefield = new BattlefieldSpecification(400, 400); // 800x600
        RobotSpecification[] selectedRobots = new RobotSpecification[2];
        RobotSetup[] initialSetups= new RobotSetup[2];
        selectedRobots= engine.getLocalRepository("fnl.RouteBot*, sample.SittingDuck");
        initialSetups[0]=new RobotSetup(50.0,310.0,0.0);
        initialSetups[1]=new RobotSetup(500.0,500.0,45.0);
        
        BattleSpecification battleSpec = new BattleSpecification(battlefield, numRounds, inactivityTime,gunCoolingRate,sentryBorderSize, hideEnemyNames, selectedRobots, initialSetups) ;

        // Run our specified battle and let it run till it is over
        engine.runBattle(battleSpec, true); // waits till the battle finishes

        // Cleanup our RobocodeEngine
        engine.close();

        // Make sure that the Java VM is shut down properly
        System.exit(0);
    }
}

//
// Our private battle listener for handling the battle event we are interested in.
//
class BattleObserver extends BattleAdaptor {

    // Called when the battle is completed successfully with battle results
    public void onBattleCompleted(BattleCompletedEvent e) {
        System.out.println("-- Battle has completed --");
        
        // Print out the sorted results with the robot names
        System.out.println("Battle results:");
        for (robocode.BattleResults result : e.getSortedResults()) {
            System.out.println("  " + result.getTeamLeaderName() + ": " + result.getScore());
        }
    }

    // Called when the game sends out an information message during the battle
    public void onBattleMessage(BattleMessageEvent e) {
        System.out.println("Msg> " + e.getMessage());
    }

    // Called when the game sends out an error message during the battle
    public void onBattleError(BattleErrorEvent e) {
        System.out.println("Err> " + e.getError());
    }
}
