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
public class LowerArm extends Command {

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
    
    public LowerArm(sparkMaxSubsystem sparkMax) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);

        /* Throw an exception if the programmer provided invalid values for the above constants */
        if (SPEED_MAX < SPEED_MIN || ENCODER_VELOCITY_MAX <= ENCODER_VELOCITY_MIN || FLAT_ENOUGH_POSITION < 0) {
            System.out.println("Invalid constants specified in LowerArm");
        }
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        /* Sanity check - verify these encoder readings match the ones from encoder_velocity.java on the SmartDashboard */
        /* You may remove or comment this out when it is working consistently */
        SmartDashboard.putNumber("Encoder 5 vel from LowerArm: ", Robot.encoder_data.sparkMax5_vel);
        SmartDashboard.putNumber("Encoder 6 vel from LowerArm: ", Robot.encoder_data.sparkMax6_vel);

        /* Calculate averages between the two encoders */
        double average_encoder_velocity = (Math.abs(Robot.encoder_data.sparkMax5_vel) + Math.abs(Robot.encoder_data.sparkMax6_vel)) / 2.0;
        double average_encoder_position = (Math.abs(Robot.encoder_data.sparkMax5_pos) + Math.abs(Robot.encoder_data.sparkMax6_pos)) / 2.0;
        SmartDashboard.putNumber("Average encoder velocity: ", average_encoder_velocity);
        SmartDashboard.putNumber("Average encoder position: ", average_encoder_position);

        /* The following formula will map any number in a specified range to [0, 1] */
        /* normalize = (VALUE - MIN) / (MAX - MIN) */ 

        // The denominator for the above equation
        double denom = (Math.abs(ENCODER_VELOCITY_MAX) - Math.abs(ENCODER_VELOCITY_MIN));
        double normalized_encoder_velocity = Math.abs(average_encoder_velocity - Math.abs(ENCODER_VELOCITY_MIN)) / denom;

        /* Failsafes for cases where the actual encoder velocity is outside [ENCODER_VELOCITY_MIN, ENCODER_VELOCITY_MAX] */
        if (average_encoder_velocity < ENCODER_VELOCITY_MIN) {
            normalized_encoder_velocity = 0.0;
        } else if (average_encoder_velocity > ENCODER_VELOCITY_MAX) {
            normalized_encoder_velocity = 1.0;
        }

        /* Ensure this number stays between [0, 1] */
        SmartDashboard.putNumber("Normalized encoder velocity: ", normalized_encoder_velocity);

        /* Multiply this by a [0, 1] value to get a speed range */
        double SPEED_RANGE = Math.abs(SPEED_MAX) - Math.abs(SPEED_MIN);

        /* Calculate a speed between SPEED_MIN and SPEED_MAX using the normalized velocity */
        double speed_to_apply = Math.abs(SPEED_MIN) + (normalized_encoder_velocity * SPEED_RANGE);
        SmartDashboard.putNumber("Abs(speed to run the arm motors): ", speed_to_apply);

        /* Finally flip the calculated speed and run the motor */
        s_sparkmax.run(-speed_to_apply);

        /* Add conditions here for determining if the arm is lowered */
        /* Right now the command will stop when it deems the arm "flat enough" */
        if (average_encoder_position <= FLAT_ENOUGH_POSITION) {
            _isFinished = true;
        }
    }

    @Override
    public boolean isFinished(){
        return _isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
    }

}
 