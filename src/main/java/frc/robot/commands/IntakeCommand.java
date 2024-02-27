package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;

public class IntakeCommand extends CommandBase {

    //subsystem that the command runs on
    private final intakeSubsystem s_sparkmax3; // 2/11/2024 - The spark max subsystem
    private double _speed;
    
    public IntakeCommand(intakeSubsystem sparkMax3, double speed) {
        s_sparkmax3 = sparkMax3;
        _speed = speed;
        addRequirements(s_sparkmax3);
    }

    @Override
    public void initialize() {
        s_sparkmax3.run(_speed);
    }

    // 2/11/2024 - isFinished always returns true for while held commands
    @Override
    public boolean isFinished(){
        return true;
    }

}
 