package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//import subsystems
import frc.robot.subsystems.*;

public class IntakeCommand extends CommandBase {

    //subsystem that the command runs on
    private final intakeSubsystem s_sparkmax3; // 2/11/2024 - The spark max subsystem
    private double __speed;
    
    public IntakeCommand (intakeSubsystem sparkMax3, double speed) {
        s_sparkmax3 = sparkMax3;
        __speed = speed;
        addRequirements(s_sparkmax3);
    }

    @Override
    public void initialize() {
        s_sparkmax3.run(__speed);
    }

    // 2/11/2024 - isFinished always returns true for while held commands
    @Override
    public boolean isFinished(){
        return true;
    }

}
 