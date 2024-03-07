package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math; // abs

import frc.robot.Robot;
import frc.robot.subsystems.*;

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
public class DropArm extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;

    private boolean _isFinished = false;

    /* You may adjust the values from here down. */
    /* The last I checked, 0.02 seemed to borderline raise the arm and 0.01 didn't do much to stop it */
    /* Also note that these constants are positive, but the speed we apply will be negative */
    private final double SPEED_MIN = 0.01;
    private final double SPEED_MAX = 0.02; // Speed to apply to the motors 
    
    /* If the arm is falling, it should have a "fast" encoder velocity */
    /* These are the values I've done the least amount of work with, so they'll likely need tuning */
    /* ENCODER_VELOCITY_MIN should be close to what the encoders read if someone gently lowers the arm */
    /* ENCODER_VELOCITY_MAX should be close to what the encoders read if someone simulates slamming the arms down */
    private final double ENCODER_VELOCITY_MIN = 0;
    private final double ENCODER_VELOCITY_MAX = 12; // Encoder readings

    /* If the average encoder position is between 0 and FLAT_ENOUGH_POSITION, then this command has done its job. */
    /* We can additionally add a maximum amount of time we want this command to take, but as of right now the timer does nothing */
    private final double FLAT_ENOUGH_POSITION = 2;
    private final double _speed;
    private final double _revs;
    
    public DropArm(sparkMaxSubsystem sparkMax, double speed, double time, double revs) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _speed = speed;
        _revs = revs;

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        s_sparkmax.run(_speed);
    }

    @Override
    
    public boolean isFinished() {
        if (_revs >= 18) {
        _isFinished = true;
    }
     return _isFinished;
    }


    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
    }

}
 