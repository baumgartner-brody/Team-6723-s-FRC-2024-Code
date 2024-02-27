package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class Wait extends CommandBase{

    //subsystem that the command runs on
    private final Timer timer = new Timer();
    private double _time;

    public Wait (double Time) {
        _time = Time;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        /* Do nothing */
    }

    @Override
    public boolean isFinished() {
        return timer.get() > _time;
    }

}
 