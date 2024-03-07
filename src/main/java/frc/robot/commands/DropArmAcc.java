package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class DropArmAcc extends SequentialCommandGroup {
    public DropArmAcc() {
        addCommands(  
            new DropArm(Robot.m_robotContainer.s_sparkMax, .02, 2, 5),
            new LowerArm(Robot.m_robotContainer.s_sparkMax)
        );
    }
}
    