package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.*;

public class SuspendArmAndDriveB extends ParallelCommandGroup {

    public SuspendArmAndDriveB() {
        addCommands(
            new DriveAuto(.2, .3, 0, 2),
            new SparkMaxCommand(Robot.m_robotContainer.s_sparkMax, -0.02, 2.5) // Keep the arm up for 2  second
         
        );
    }
}
