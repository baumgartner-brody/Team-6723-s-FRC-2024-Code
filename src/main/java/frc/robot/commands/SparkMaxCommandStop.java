package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;

public class SparkMaxCommandStop extends CommandBase {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;
    
    public SparkMaxCommandStop (sparkMaxSubsystem sparkMax) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
    }

}
 