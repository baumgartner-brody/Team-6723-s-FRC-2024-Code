package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LowerArm;
import frc.robot.commands.SparkMaxCommand;

/* A SequentialCommandGroup is a chain of commands that executes in the order they were added to the group. */
/* In its current state, this autonomous routine will drive forward for 2 seconds, then backwards for 2 seconds. */

/** A ParallelCommandGroup can be used to schedule commands to run concurrently. Back in 2020, a general CommandGroup object 
 *  served both purposes, and had an addParallel() and addSequential() method. Now, if you wish to chain them, you should use 
 *  a SequentialCommandGroup that adds a ParallelCommandGroup as a command in addCommands().
 *  https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/ParallelCommandGroup.html
 **/
public class ScoreNote2 extends SequentialCommandGroup {
    
    public ScoreNote2() {
        addCommands(
            new DriveAuto(0, -.4, 0, 1),
            new SparkMaxCommand(Robot.m_robotContainer.s_sparkMax, -0.1, 1.0, 0.75),
            new SuspendArmAndScore(),
            new LowerArm(Robot.m_robotContainer.s_sparkMax),
            new DriveandTurnAuto(),
            new IntakeCommand(Robot.m_robotContainer.s_sparkMax3, 0.5, 1.0),
            new DriveandTurn2(),
            new SparkMaxCommand(Robot.m_robotContainer.s_sparkMax, -0.2, 2.0, 0.75), // We'll have to tweak these T(0)DO no clue
            new SuspendArmAndScore()
        );
    }
}
   