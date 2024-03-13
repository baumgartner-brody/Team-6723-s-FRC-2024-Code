package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math; // abs

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.encoder_velocity;


/* The idea behind this command is that the driver should be able to press a button */
/*  and have the code do all of the work involved in gently lowering the arm. */

/**********************************************************************/
/* The math in a nutshell:                                            */
/*  The arm motor speed should be restricted between two values, one  */
/*  keeps the arm up rigidly, and one does little to keep the arm up. */
/*                                                                    */
/*  The arm motors should run at a speed within the specified range,  */
/*  calculated by determining how fast the arm is falling.            */
/*                                                                    */
/*  If the arm is falling "fast" then more speed (resistance) should  */
/*  be applied to prevent it from doing so.                           */
/*  If the arm is falling "slow" then less speed should be applied to */
/*  let the arm lower itself gracefully.                              */
/*                                                                    */
/*  The command should stop running when the arm is in a flat enough  */
/*  state, specified by an encoder position close to 0                */
/**********************************************************************/

/* Since the logic to throw the exception uses "constants", it will report a Dead Code warning */
/* We can use @SuppressWarnings to ignore certain warnings in java. */
@SuppressWarnings("unused") 
public class RaiseArmToIdle extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;
    public static RobotContainer m_robotContainer;
    public double idleposition = -.56;
    private boolean _isFinished = false;
    encoder_velocity encoder = new encoder_velocity();
    double  avg_encoder_revs = encoder.avg_encoder_revs;
    double average_encoder_revs = avg_encoder_revs;
     
    public RaiseArmToIdle(sparkMaxSubsystem sparkMax) {
       
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _isFinished = false;

    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {

        if (average_encoder_revs > idleposition ) {
            _isFinished = true;
        } else {
            _isFinished = false;
        }

        SmartDashboard.putBoolean("is finished", _isFinished);  
    }

    @Override
    public boolean isFinished(){
        return Robot.encoder_data.avg_encoder_revs > idleposition;

    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
        System.out.println("Ground to Idle is complete");
    }
}
 