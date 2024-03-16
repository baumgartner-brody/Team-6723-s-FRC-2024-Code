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
public class RaiseArmToScore extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */

    // Code should be refactored so references to subsystems are similar to the following
    private final sparkMaxSubsystem s_sparkmax = RobotContainer.s_sparkMax; 

    private final double Ninety_Degrees = .15;
    

    public RaiseArmToScore() {}

    @Override
    public void initialize() {
        System.out.println("Initialize Raise Arm To Score");
    }

    @Override
    public void execute() {

        // Reference to avg_encoder_revs in the encoder thread
        double avg_encoder_revs = Robot.encoder_data.avg_encoder_revs; 
        
        if (avg_encoder_revs >= Ninety_Degrees) {
            s_sparkmax.run(-0.005); // The arm is past 90, just a small amount of force
            System.out.println("Past 90");
        } else {
            System.out.println("Rasing to 90");
            s_sparkmax.run(-0.2); // The arm should run at a decent speed before we hit 0 revs
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
        System.out.println("Raise Arm to Scoring complete");
    }
}
 