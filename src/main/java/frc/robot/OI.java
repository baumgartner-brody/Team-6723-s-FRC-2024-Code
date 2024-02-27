package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.*;


public class OI {
    private XboxController xbox;

    public OI() {
        xbox = new XboxController(0);
    }

    public XboxController getXboxController() {
        return xbox;
    }

    public void otherOI () {
        //all my homies love otheroi
        SmartDashboard.putNumber("Gyro Angle", Robot.robotmap._gyro.getAngle());
        SmartDashboard.putNumber("Gyro Rate", Robot.robotmap._gyro.getRate());
        SmartDashboard.putNumber("Xbox Turn", xbox.getRightX());
        SmartDashboard.putNumber("Xbox Strafe", xbox.getLeftX());
        SmartDashboard.putNumber("Xbox Foward", xbox.getLeftY());
        
        // 2/11/2024 - put A button status on smartDashboard
        SmartDashboard.putBoolean("A is pressed: ", xbox.getAButton());

        /*
        if (RobotMap.c.getPressure() >= 60) {
            RobotMap.c.disable();
        } else {
            if (RobotMap.C.isEnabled())
        } */

        //limit switch code
        /*if ((RobotMap.lsTop.get() || RobotMap.lsBottom.get()) && lsTimer.get() > 0.8) {
            System.out.println("limitswitch triggered");
            lsTimer.reset();
            lsTimer.start();
            
            c_LifterStop.schedule();
            
        } */

        //liftmotor is reversed
        /*if (RobotMap.lsTop.get() && lsTimer.get() > 0.8 && RobotMap.liftmotor.get() < 0) {
            System.out.println("lstop triggered");
            lsTimer.reset();
            lsTimer.start();
            
            c_LifterStop.schedule();
        }

        if (RobotMap.lsBottom.get() && lsTimer.get() > 0.8 && RobotMap.liftmotor.get() > 0) {
            System.out.println("lsbottom triggered");
            lsTimer.reset();
            lsTimer.start();
            
            c_LifterStop.schedule();
        }*/
        /*if (RobotMap.lsBottom.get() && lsTimer.get() > 0.8) {
            System.out.println("lsbottom triggered");
            lsTimer.reset();
            lsTimer.start();
            
            c_LifterStop.schedule();
        }*/
        
    }
}
