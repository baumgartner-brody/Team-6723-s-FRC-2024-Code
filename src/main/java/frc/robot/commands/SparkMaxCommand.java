package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//import subsystems
import frc.robot.subsystems.*;

public class SparkMaxCommand extends CommandBase {

    //subsystem that the command runs on
    private final sparkMaxSubsystem s_sparkmax; // 2/11/2024 - The spark max subsystem
    
    public SparkMaxCommand (sparkMaxSubsystem sparkMax) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
    }

    @Override
    public void initialize() {
        //s_sparkmax.run(speed);
    }

    public void run(double speed) {
        s_sparkmax.run(speed);
    }

    // 2/11/2024 - isFinished always returns true for while held commands
    @Override
    public boolean isFinished(){
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
    }

}
 