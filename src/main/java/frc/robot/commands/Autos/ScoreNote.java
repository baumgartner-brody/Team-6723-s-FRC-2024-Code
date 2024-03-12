package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.LowerArmCommandGroup;
import frc.robot.commands.SparkMaxCommand;

/* A SequentialCommandGroup is a chain of commands that executes in the order they were added to the group. */
/* In its current state, this autonomous routine will drive forward for 2 seconds, then backwards for 2 seconds. */

/** A ParallelCommandGroup can be used to schedule commands to run concurrently. Back in 2020, a general CommandGroup object 
 *  served both purposes, and had an addParallel() and addSequential() method. Now, if you wish to chain them, you should use 
 *  a SequentialCommandGroup that adds a ParallelCommandGroup as a command in addCommands().
 *  https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/ParallelCommandGroup.html
 **/
public class ScoreNote extends SequentialCommandGroup {
    
    public ScoreNote() {
        addCommands(
            new SparkMaxCommand(-0.1, 5.0, 0.75), // We'll have to tweak these TODO
            new SuspendArmAndScore(),
            new LowerArmCommandGroup()
        );
    }
}
   