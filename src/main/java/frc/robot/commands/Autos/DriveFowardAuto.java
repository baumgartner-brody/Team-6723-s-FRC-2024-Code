package frc.robot.commands.Autos;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveFowardAuto extends CommandBase {
    private final Timer timer = new Timer();
    public double xSpeed;
    public double ySpeed;
    public double zSpeed;
    public double timeout;

    public DriveFowardAuto(double x, double y, double z, double time_in) {
    	
        xSpeed = x;
        ySpeed = y;
        zSpeed = z;

        timeout = time_in;
    }

    @Override
    public void initialize() {
        System.out.println("called init()");
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        
        SmartDashboard.putNumber("Timer.get(): ", timer.get());
        SmartDashboard.putNumber("timeout: ", timeout);
        
        Robot.m_robotContainer.c_mecanumDrive.run(xSpeed, ySpeed, zSpeed);
        
        /*
        if (timer.get() < timeout) {
            //Robot.m_robotContainer.c_mecanumDrive.run(xSpeed, ySpeed, zSpeed);
            Robot.robotmap.leftmotor1.set(-ySpeed);
            Robot.robotmap.leftmotor2.set(-ySpeed);
            Robot.robotmap.rightmotor1.set(ySpeed);
            Robot.robotmap.rightmotor2.set(ySpeed);
        } */
        //Robot.m_robotContainer.c_mecanumDrive.run(0, 0, 0);
    }
    
    @Override
    public boolean isFinished() {
        //return true;
        return timer.get() >= timeout;
    }
 
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("called end()");
        Robot.m_robotContainer.c_mecanumDrive.run(0, 0, 0);
    }
    
}
