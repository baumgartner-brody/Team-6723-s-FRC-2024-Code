package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
/* A command that does nothing for a specified number of seconds */
public class Wait extends Command {

    private final Timer timer = new Timer();
    private double _time;

    /* Wait constructor */
    /* Time - the amount of time in seconds to wait for */
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

    @Override
    public void end(boolean interrupted) {}
}
 