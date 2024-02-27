package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.*;
import frc.robot.subsystems.*;

public class MecanumDrive extends CommandBase{

    private final drivetrain s_drivetrain;

    public MecanumDrive(drivetrain subsystem) {
        s_drivetrain = subsystem;
        addRequirements(s_drivetrain);
    }

    @Override
    public void initialize() {
        //ooga booga
    }

    @Override
    public void execute() {
        //calls repeatedly when command is scheduled to run
        s_drivetrain.doMecanumDrive(Robot.oi.getXboxController());
    }

    public void run(double x, double y, double z){
        s_drivetrain.doMecanumDrive(x, y, z);
    }
     
    @Override 
    public boolean isFinished() {
        return false;
    }
}
