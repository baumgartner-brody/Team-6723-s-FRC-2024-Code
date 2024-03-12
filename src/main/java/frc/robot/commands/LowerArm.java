package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

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

public class LowerArm extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;

    private  double _speed; // The speed we should run the arm

    // The encoder position we should turn the arm off at
    // When this position is reached we'll run LowerArm2
    private  double _pos; 
    
    public LowerArm(sparkMaxSubsystem sparkMax, double speed, double pos) {
        s_sparkmax = sparkMax;
        addRequirements(s_sparkmax);
        _speed = speed;
        _pos = pos;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        s_sparkmax.run(_speed);
    }

    @Override
    public boolean isFinished(){
        System.out.println("avg: " + Robot.encoder_data.avg_encoder_revs);
        System.out.println("_pos: " + _pos);
        return Robot.encoder_data.avg_encoder_revs >= _pos;
    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
        System.out.println("End of Lowerarm");
    }

}
 