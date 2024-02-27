package frc.robot.commands.Autos;

import frc.robot.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class DriveFowardAuto extends CommandBase {
    private final Timer timer = new Timer();
    private double xSpeed;
    private double ySpeed;
    private double zSpeed;
    private double timeout;

    public DriveFowardAuto(double x, double y, double z, double time_in) {
    	
        xSpeed = x;
        ySpeed = y;
        zSpeed = z;

        timeout = time_in;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        
        Robot.m_robotContainer.c_mecanumDrive.run(xSpeed, ySpeed, zSpeed);
    
    }
    
    @Override
    public boolean isFinished() {
        return timer.get() >= timeout;
    }
 
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("called end()");
        Robot.m_robotContainer.c_mecanumDrive.run(0, 0, 0);
    }
    
}
