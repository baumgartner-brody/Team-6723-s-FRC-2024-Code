package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;



//import subsystems
import frc.robot.subsystems.*;

public class Wait extends CommandBase{

    //subsystem that the command runs on
    private final Timer timer = new Timer();
    private double time;
    private boolean ched2 = false;
    public Wait (double Time) {
        
        time = Time;
    }

    @Override
    public void initialize() {
        //m_drivetrain.run(0.6);
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.get() >= time) {
            ched2 = true;
        }
    }

    @Override
    public boolean isFinished(){
        return ched2;
    }

}
 