package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.*;
/* The IntakeCommand runs the intake Spark Max at a specified speed */
public class IntakeCommand extends Command {

    /* A reference to the subsystem that controls the intake Spark Max */
    private final intakeSubsystem s_sparkmax3 = Robot.SparkMaxSubsystem;
    private double _speed;

    private final Timer _timer = new Timer();
    private double _time = 0.0; // If time is not specified this command will run indefinitely
    
    public IntakeCommand(double speed) {
        _speed = speed;
    }

    /* Additional constructor that allows a time to be specified */
    public IntakeCommand(double speed, double time) {
        _speed = speed;
        _time = time;
    }

    @Override
    public void initialize() {
        // ! IMPORTANT ! We put addRequirements in initialize since it is always called upon when a command is created
        addRequirements(s_sparkmax3);

        _timer.reset();
        _timer.start();
    }

    @Override 
    public void execute() {
        s_sparkmax3.run(_speed); // Run the intake Spark Max at the specified speed
    }

    @Override
    public boolean isFinished() {
        /* If you specified a time in, this command will run for that time, otherwise it will run indefinitely */
        if (_time != 0.0) {
            return _timer.get() > _time;
        } else {
            return false;
        }
    }

    /* For safety reasons, it's a good idea to override end() in all your commands and call the stop() method on its required subsystem */
    /* If commands seem to be stopping prematurely, checking to see if end() was invoked is a good starting point to debbug. */
    @Override 
    public void end(boolean interrupted) {
        s_sparkmax3.stop();
    }

}
 