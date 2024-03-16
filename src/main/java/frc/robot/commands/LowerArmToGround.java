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
public class LowerArmToGround extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */

    // Code should be refactored so references to subsystems are similar to the following
    private final sparkMaxSubsystem s_sparkmax = RobotContainer.s_sparkMax; 

    private final double LOW_ENOUGH = -1;
    private final double NINETY_DEGREES = 0;
    private final double IDLE_POSITION_LOWER_BOUND = -0.6;
    private final double IDLE_POSITION_UPPER_BOUND = -0.5;


    public LowerArmToGround() {}

    @Override
    public void initialize() {}

    @Override
    public void execute() {

        // Reference to avg_encoder_revs in the encoder thread
        double avg_encoder_revs = Robot.encoder_data.avg_encoder_revs; 
        
        if (avg_encoder_revs > LOW_ENOUGH && avg_encoder_revs <= IDLE_POSITION_UPPER_BOUND) {
            s_sparkmax.run(0.05); // The arm is at idle run at low speed (will have to test)
        } else if (avg_encoder_revs > IDLE_POSITION_UPPER_BOUND && avg_encoder_revs <NINETY_DEGREES) {
            s_sparkmax.run(0.1); // The arm is between idle and 90, run at a decent speed. This and the one below could be combined
        } else {
            s_sparkmax.run(0.15); // The arm is above the idle range, so can lower it faster (will have to test speed)
        }   

    }

    @Override
    public boolean isFinished(){
        double avg_encoder_revs = Robot.encoder_data.avg_encoder_revs; 
        return avg_encoder_revs <= LOW_ENOUGH ;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
        System.out.println("LowerArmToGround69 complete");
    }
}
 