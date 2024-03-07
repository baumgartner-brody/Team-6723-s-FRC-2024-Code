package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

/* A SequentialCommandGroup is a chain of commands that executes in the order they were added to the group. */
/* In its current state, this autonomous routine will drive forward for 2 seconds, then backwards for 2 seconds. */

/** A ParallelCommandGroup can be used to schedule commands to run concurrently. Back in 2020, a general CommandGroup object 
 *  served both purposes, and had an addParallel() and addSequential() method. Now, if you wish to chain them, you should use 
 *  a SequentialCommandGroup that adds a ParallelCommandGroup as a command in addCommands().
 *  https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/ParallelCommandGroup.html
 **/
public class LowerArmCommandGroup extends SequentialCommandGroup {
    
    /* The idea behind this command group is that LowerArm will bring the arm back to a position below 90, *
    /* and then LowerArm2 will manage it from there. This should allow you to lower the arm from any position in auto or teleop. */
    /* Adjust params accordingly. */
    public LowerArmCommandGroup() {
        addCommands(
            new LowerArm(Robot.m_robotContainer.s_sparkMax, 0.1, 16.0),
            new LowerArm2(Robot.m_robotContainer.s_sparkMax)
        );
    }
}
   