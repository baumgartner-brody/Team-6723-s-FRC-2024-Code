package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;

public class SparkMaxCommand extends CommandBase {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;
    private double _speed;
    
    public SparkMaxCommand (sparkMaxSubsystem sparkMax, double speed) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _speed = speed;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        System.out.println("called sparkmaxcommand");
        s_sparkmax.run(_speed);
    }

    /* Instead of using execute(), we use our own method run() here so we can pass in a speed as a parameter */
    /* Some commands pass a speed in the constructor so they can use execute() with no parameters and properly @Override it. */
    /* IntakeCommand is set up like this. No way is better or more correct than the other. */
    public void run(double speed) {
        s_sparkmax.run(speed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
    }

}
 