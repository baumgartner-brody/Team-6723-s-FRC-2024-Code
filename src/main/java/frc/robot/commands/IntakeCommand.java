package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.*;
/* The IntakeCommand runs the intake Spark Max at a specified speed */
public class IntakeCommand extends Command {

    /* A reference to the subsystem that controls the intake Spark Max */
    private final intakeSubsystem s_sparkmax3;
    private double _speed;

    private Timer _timer = null;
    private double _time = 0.0; // If time is not specified this command will run indefinitely
    
    public IntakeCommand(intakeSubsystem sparkMax3, double speed) {
        s_sparkmax3 = sparkMax3;
        _speed = speed;
        addRequirements(s_sparkmax3);
    }

    /* Additional constructor that allows a time to be specified */
    public IntakeCommand(intakeSubsystem sparkMax3, double speed, double time) {
        s_sparkmax3 = sparkMax3;
        addRequirements(s_sparkmax3);
        _speed = speed;
        _time = time;
        _timer = new Timer();
       
    }

    @Override
    public void initialize() {

        if (_timer != null) {
            _timer.reset();
            _timer.start();
        }
        /* Initialize does nothing here, but that's ok. */
        /* In cases like this, it is not technically required to @Override it, but to keep program structure, I recommend leaving it in. */
    }

    @Override 
    public void execute() {
        s_sparkmax3.run(_speed); // Run the intake Spark Max at the specified speed
    }

    @Override
    public boolean isFinished(){
        /* If you specified a time in, this command will run for that time, otherwise it will run indefinitely */
        if (_timer != null) {
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
        System.out.println("End of intake shot auto");
    }

}
 