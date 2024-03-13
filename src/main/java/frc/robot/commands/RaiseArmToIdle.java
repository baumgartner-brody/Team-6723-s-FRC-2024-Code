package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math; // abs

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.encoder_velocity;
import frc.robot.OI.ArmStates;

@SuppressWarnings("unused") 
public class RaiseArmToIdle extends Command {

    /* A reference to the Spark Max subsystem that controls the arm spark maxes */
    private final sparkMaxSubsystem s_sparkmax;
    public static RobotContainer m_robotContainer;
    public double idleposition = -.56;
    public double idlepositionmin = -.55;
    public double idlepositionmax = -.5;
    private  double _speed;
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
        Robot.oi.armstate = ArmStates.IDLE;
    }

    @Override
    public void execute() {
        
  
        if(average_encoder_revs >= idleposition){
            s_sparkmax.run(-.025);
        } else if (avg_encoder_revs > idlepositionmax){
            s_sparkmax.run(.001);
        } else {
            s_sparkmax.run(-.2);
        }
    
        SmartDashboard.putBoolean("is finished", _isFinished);  
    

    if (average_encoder_revs<0 && average_encoder_revs>idleposition){
        if(average_encoder_revs >= idleposition){
            s_sparkmax.run(-.025);
        } else if (avg_encoder_revs > idlepositionmax){
            s_sparkmax.run(.001);
        } else {
            s_sparkmax.run(-.001); //TODO
        }
    }
    
    
    if (average_encoder_revs>0){
        if(average_encoder_revs >= idleposition){
            s_sparkmax.run(-.025);
        } else if (avg_encoder_revs > idlepositionmax){
            s_sparkmax.run(.001);
        } else {
            s_sparkmax.run(-.001); //TODO
        }
    }


    }
    @Override
    public boolean isFinished(){
      //change
        return Robot.oi.armstate == ArmStates.IDLE;

    }

    @Override
    public void end(boolean interrupted) {
        s_sparkmax.stop();
        System.out.println("Ground to Idle is complete");
    }
}
 