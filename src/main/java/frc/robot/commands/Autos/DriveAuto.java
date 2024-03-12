package frc.robot.commands.Autos;

import frc.robot.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

/* DriveAuto is an autonomous command that drives the robot with a specified x, y, z speed for a specified amount of time. */
public class DriveAuto extends Command {
    private Timer timer;
    private double xSpeed;
    private double ySpeed;
    private double zSpeed;
    private double timeout;

    /* DriveAuto constructor, the speeds and time are set here */
    /* xSpeed - Back and forth  */
    /* ySpeed - Scuttling side to side */
    /* zSpeed -  Rotation */
    /* time_in - The time this command should run in seconds */
    public DriveAuto(double x, double y, double z, double time_in) {	
        xSpeed = x;
        ySpeed = y;
        zSpeed = z;

        timer = new Timer();

        timeout = time_in;
    }

    /** @Override is a special java token. Whenever you see a class that has "implements" or "extends" in its declaration, 
     *  you must override inherited methods for them to be invoked properly. The full explanation for why this is has something to do with a "v-table"
     *  more information about inheritance can be found here: https://en.wikipedia.org/wiki/Method_overriding
     *  TL;DR - When you make commands from scratch, copy an existing command and ensure you have a initialize(), execute(), isFinished() and end() marked with @Override 
     **/
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    /* Execute can be thought of as the part of the command that is continuously executed while the command is running. */
    @Override
    public void execute() {
        /* Use the mecanumDrive subsystem to drive the robot */
        Robot.m_robotContainer.c_mecanumDrive.run(xSpeed, ySpeed, zSpeed);
    }
    
    /* isFinished() will return a boolean that determines if end() should be called. For commands that run while a button is held, they return true. */
    /* For time-based, encoder-based, or commands that stop based on some sort of condition, they return whether that condition has been satisfied. */
    @Override
    public boolean isFinished() {
        return timer.get() >= timeout;
    }
 
    // Called once the command ends or is interrupted.
    /* Note that the boolean interrupted parameter is required for the @Override to work properly on end() */
    /* This has to do with something called a function signature: https://en.wikipedia.org/wiki/Type_signature */
    @Override
    public void end(boolean interrupted) {
        Robot.m_robotContainer.c_mecanumDrive.run(0, 0, 0);
        System.out.println("finished drivetrain");
    }
    
}
