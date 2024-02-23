package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import frc.robot.*;
import frc.robot.OI;
import frc.robot.subsystems.*;
import frc.robot.*;

public class DRIVEMecanumDrive extends CommandBase{

    private final drivetrain s_drivetrain;

    public DRIVEMecanumDrive (drivetrain subsystem) {
        s_drivetrain = subsystem;
        addRequirements(s_drivetrain);
    }

    @Override
    public void initialize() {
        //ooga booga
    }

    @Override
    public void execute() {

        // We're gonna see if the joystick is the problem or not 
        //System.out.println("Value of POV: " + Robot.oi.getJoystick().getPOV(0));

        //System.out.println("Value of x-axis: " + Robot.oi.getJoystick().getX());
        //System.out.println("Value of y-axis: " + Robot.oi.getJoystick().getY());s

        //calls repeatedly when command is scheduled to run
        s_drivetrain.doMecanumDrive(Robot.oi.getXboxController());
    }

    public void run(double x, double y, double z){
        s_drivetrain.doMecanumDrive(x,y,z);
    }
        
    public void run(double leftspeed, double rightspeed) {
        RobotMap.left.set(leftspeed);
        RobotMap.right.set(-rightspeed);
    }
     
    @Override 
    public boolean isFinished() {
        return false;
    }
}
