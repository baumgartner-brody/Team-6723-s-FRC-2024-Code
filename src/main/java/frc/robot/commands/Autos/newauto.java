package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.SparkMaxCommand;
import frc.robot.commands.*;


public class newauto extends ParallelCommandGroup {

    public newauto() {
        addCommands(
            new SparkMaxCommand(Robot.m_robotContainer.s_sparkMax, 0.02),
            new DriveAuto(-.2, 0, 0, 5)
        );
    }
}
