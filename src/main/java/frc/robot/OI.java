package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;



public class OI {
    public XboxController xbox;
    //XboxController xboxController = new XboxController (0); // 2/19/2024 Do NOT bind two devices to port 0. This causes problems
    private final Joystick stick;

    public final JoystickButton button1; // 2/19/2024 Could be any button

    public OI() {
        xbox = new XboxController (0);
        stick = new Joystick(1); // 2/19/2024 

        button1 = new JoystickButton(stick, 1);
    }

    public XboxController getXboxController() { // 2/19/2024 This method is not necessary since xbox is public
        return xbox;
    }

    public Joystick getJoystick() {
        return stick;
    }

    public void otherOI () {
        //all my homies love otheroi
        SmartDashboard.putNumber("Gyro Angle", Robot.robotmap._gyro.getAngle());
        SmartDashboard.putNumber("Gyro Rate", Robot.robotmap._gyro.getRate());
        SmartDashboard.putNumber("Xbox Turn",xbox.getRightX());
        SmartDashboard.putNumber("Xbox Strafe",xbox.getLeftX());
        SmartDashboard.putNumber("Xbox Foward",xbox.getLeftY());
        
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
