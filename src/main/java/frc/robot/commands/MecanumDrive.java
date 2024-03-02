package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.*;
import frc.robot.subsystems.*;

public class MecanumDrive extends Command {

    /* A reference to the drivetrain subsystem */
    private final drivetrain s_drivetrain;

    public MecanumDrive(drivetrain subsystem) {
        s_drivetrain = subsystem;
        addRequirements(s_drivetrain);
    }

    @Override
    public void initialize() {
        /* Nothing to initialize */
    }

    /* In execute(), we let the subsystem handle the xbox controller */
    @Override
    public void execute() {
        s_drivetrain.doMecanumDrive(Robot.oi.getXboxController());
    }

    /* Run the drivetrain at a parameter x, y, and z speed */
    public void run(double x, double y, double z){
        s_drivetrain.doMecanumDrive(x, y, z);
    }
     
    @Override 
    public boolean isFinished() {
        return false;
    }
}
