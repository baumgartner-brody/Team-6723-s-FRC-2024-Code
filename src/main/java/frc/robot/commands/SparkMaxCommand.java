package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

public class SparkMaxCommand extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final SparkMaxSubsystem s_sparkMax = Robot.SparkMaxSubsystem;
    private double _speed;
    
    private Timer _timer = new Timer();
    private double _time = 0.0; // If time is not specified this command will run indefinitely
    
    public SparkMaxCommand (double speed) {
        _speed = speed;
    }

    /* Additional constructor that allows a time to be specified */
    public SparkMaxCommand(double speed, double time) {
        _speed = speed;
        _time = time;
    }

    @Override
    public void initialize() {
        addRequirements(Robot.SparkMaxSubsystem); // Ensure other commands needing this subsystem have to stop
        _timer.reset();
        _timer.start();
    }

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
    public boolean isFinished() {

        /* If _time is specified, use the timer to dictate how long this command should run */
        if (_time != 0.0) {
            return _timer.get() > _time;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
    }

}
 