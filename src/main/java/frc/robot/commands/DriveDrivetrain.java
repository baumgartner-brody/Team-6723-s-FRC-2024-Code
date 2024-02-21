package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

//import subsystems
import frc.robot.subsystems.*;

public class DriveDrivetrain extends CommandBase{

    //subsystem that the command runs on
    private final drivetrain s_drivetrain;
    private double l_speed;
    private double r_speed;
    //private final Timer timer = new Timer();
    public DriveDrivetrain (drivetrain subsystem, double leftspeed, double rightspeed) {
        s_drivetrain = subsystem;
        l_speed = leftspeed;
        r_speed = rightspeed;
        addRequirements(s_drivetrain);
    }

    @Override
    public void initialize() {
        //m_drivetrain.run(0.6);
        s_drivetrain.run(l_speed, r_speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
 