package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//import subsystems
import frc.robot.subsystems.*;

public class SparkMaxCommand extends CommandBase {

    //subsystem that the command runs on
    private final sparkMaxSubsystem s_sparkmax; // 2/11/2024 - The spark max subsystem
    private double _speed;
    
    public SparkMaxCommand (sparkMaxSubsystem sparkMax, double speed) {
        s_sparkmax = sparkMax;
        _speed = speed;
        addRequirements(s_sparkmax);
    }

    @Override
    public void initialize() {
        s_sparkmax.run(_speed);
    }

    // 2/11/2024 - isFinished always returns true for while held commands
    @Override
    public boolean isFinished(){
        return true;
    }

}
 