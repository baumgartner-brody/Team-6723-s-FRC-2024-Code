package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

public class SparkMaxCommand extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;
    private double _speed;
    
    private Timer _timer = null;
    private double _time = 0.0; // If time is not specified this command will run indefinitely

    /* https://www.youtube.com/watch?v=BGLGzRXY5Bw */
    private double _revs = 0.0; // Run the arm for a specified number of revolutions
    
    public SparkMaxCommand (sparkMaxSubsystem sparkMax, double speed) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _speed = speed;
    }

    /* Additional constructor that allows a time to be specified */
    public SparkMaxCommand(sparkMaxSubsystem sparkMax, double speed, double time) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _speed = speed;
        _time = time;
        _timer = new Timer();
        _timer.reset();
        _timer.start();
    }

    /* Additional constructor that allows a time and a specified number of revolutions to be specified */
    public SparkMaxCommand(sparkMaxSubsystem sparkMax, double speed, double time, double revs) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _speed = speed;
        _time = time;
        _timer = new Timer();
        _timer.reset();
        _timer.start();
        
        _revs = revs;

        /* When working with encoders, it's a good idea to reset them */
        /* In this case, we're enforcing the arm to read 0 when it's on the ground */
        RobotMap.encoder5.setPosition(0);
        RobotMap.encoder6.setPosition(0);
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
    public boolean isFinished() {

        /* If _revs is specified, */
        if (_timer != null) {
            System.out.println("correct end");
            return _timer.get() > _time;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
        System.out.println("raisearm/lower arm ScoreNote1 done");
    }

}
 