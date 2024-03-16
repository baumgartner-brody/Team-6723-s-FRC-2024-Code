package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

/** Command that runs through the thought process for getting the arm to 
 *  return to the idle position from anywhere and getting it to stay there. 
 */
@SuppressWarnings("unused") 
public class RaiseArmToIdle extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */

    // Code should be refactored so references to subsystems are similar to the following
    private final sparkMaxSubsystem s_sparkmax = Robot.sparkMaxSubsystem; 

    private final double IDLE_POSITION_LOWER_BOUND = -0.6;
    private final double IDLE_POSITION_UPPER_BOUND = -0.5;

    public RaiseArmToIdle() {}

    @Override
    public void initialize() {}

    @Override
    public void execute() {

        // Reference to avg_encoder_revs in the encoder thread
        double avg_encoder_revs = Robot.encoder_data.avg_encoder_revs; 
        
        if (avg_encoder_revs >= IDLE_POSITION_LOWER_BOUND && avg_encoder_revs <= IDLE_POSITION_UPPER_BOUND) {
            s_sparkmax.run(-0.025); // The arm is within the idle range, so all we need to do is suspend it
        } else if (avg_encoder_revs >= -0.65 && avg_encoder_revs <= IDLE_POSITION_LOWER_BOUND) {
            s_sparkmax.run(-0.1); // The arm is near the idle position but not in it, so we should raise the arm a bit slower
        } else if (avg_encoder_revs < -0.65) {
            s_sparkmax.run(-0.25); // The arm is "far" below the idle range, so we should raise it as fast as we're comfortable
        } else if (avg_encoder_revs > IDLE_POSITION_UPPER_BOUND && avg_encoder_revs <= -0.45) {
            s_sparkmax.run(-0.001); // The arm is slightly above the idle range, so we can let gravity lower it
        } else {
            s_sparkmax.run(0.1); // The arm is above the idle range, so we need to lower it
        }

    }

    /* The arm returns to idle from wherever, but once it's there, it  
     * stays there until the driver needs it to do something else
    */
    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
        System.out.println("Idle command interrupted by another command");
    }
}
 