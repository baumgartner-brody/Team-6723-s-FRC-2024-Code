package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.*;

public class SuspendArmAndDriveB extends ParallelCommandGroup {

    public SuspendArmAndDriveB() {
        addCommands(
            new DriveAuto(.15, .3, 0, 1.5),
            new SparkMaxCommand(Robot.m_robotContainer.s_sparkMax, -0.025, 3) // Keep the arm up for 3 second
         
        );
    }
}
