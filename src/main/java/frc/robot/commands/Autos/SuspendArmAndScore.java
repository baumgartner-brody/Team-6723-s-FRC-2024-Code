package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.*;

public class SuspendArmAndScore extends ParallelCommandGroup {

    public SuspendArmAndScore() {
        addCommands(
            new SparkMaxCommand(Robot.m_robotContainer.s_sparkMax, -0.025, 1.0), // Keep the arm up for one second
            new IntakeCommand(Robot.m_robotContainer.s_sparkMax3, -0.5, 1.0)     // Eject a note at -0.5 speed for 1 second
        );
    }
}
