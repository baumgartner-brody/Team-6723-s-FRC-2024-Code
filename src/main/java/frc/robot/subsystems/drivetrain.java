package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Timer;

import java.lang.Math; // abs

public class drivetrain extends SubsystemBase {

    private final Timer timer;

    private final const double X_DRIFT_THRESHOLD = 0.15;
    private final const double Y_DRIFT_THRESHOLD = 0.15;
    private final const double Z_DRIFT_THRESHOLD = 0.15;

    private final const double X_OFFSET_TIMEOUT = 0.5;
    private final const double Y_OFFSET_TIMEOUT = 0.5;
    private final const double Z_OFFSET_TIMEOUT = 0.5;

    private final double STORED_X_TIME = 0.0;
    private final double STORED_Y_TIME = 0.0;
    private final double STORED_Z_TIME = 0.0;

    private final double STORED_X_READING = 0.0;
    private final double STORED_Y_READING = 0.0;
    private final double STORED_Z_READING = 0.0;

    private final double X_OFFSET = 0.0;
    private final double Y_OFFSET = 0.0;
    private final double Z_OFFSET = 0.0;

    public drivetrain() {
        //kungstructor
        timer = new Timer();
        timer.reset();
        timer.start();
    }

    public void init() {

    }

    public void resetOffsets() {
        X_OFFSET = Y_OFFSET = Z_OFFSET = 0.0;
    }

    public void doMecanumDrive(XboxController xbox) {

        // 2/17/2024 - based xbox axes off Mr. Louis' vex robots, however this order may not be correct for FRC and may need further adjusting
        //RobotMap.RobotDrive.driveCartesian(xbox.getRightX(), xbox.getLeftY(), xbox.getLeftX());

        // X offset
        if (xbox.getRightX() == STORED_X_READING) {
            if (Math.abs(xbox.getRightX()) > X_DRIFT_THRESHOLD) {
                if (timer.get() > STORED_X_TIME + X_OFFSET_TIMEOUT) {
                    X_OFFSET = xbox.getRightX();
                    STORED_X_READING = xbox.getRightX();
                    STORED_X_TIME = timer.get();
                }
            } else {
                X_OFFSET = 0.0; // Reset offset when driver is driving the robot faster than the threshold
            }
        } else {
            STORED_X_READING = xbox.getRightX();
            STORED_X_TIME = timer.get();
        }

        // Y offset
        if (xbox.getLeftY() == STORED_Y_READING) {
            if (Math.abs(xbox.getLeftY()) > Y_DRIFT_THRESHOLD) {
                if (timer.get() > STORED_Y_TIME + Y_OFFSET_TIMEOUT) {
                    Y_OFFSET = xbox.getLeftY();
                    STORED_Y_READING = xbox.getLeftY();
                    STORED_Y_TIME = timer.get();
                }
            } else {
                Y_OFFSET = 0.0; // Reset offset when driver is driving the robot faster than the threshold
            }
        } else {
            STORED_Y_READING = xbox.getRightX();
            STORED_Y_TIME = timer.get();
        }

        // Z offset
        if (xbox.getLeftX() == STORED_Z_READING) {
            if (Math.abs(xbox.getLeftX()) > Z_DRIFT_THRESHOLD) {
                if (timer.get() > STORED_Z_TIME + Z_OFFSET_TIMEOUT) {
                    Z_OFFSET = xbox.getLeftX();
                    STORED_Z_READING = xbox.getLeftX();
                    STORED_Z_TIME = timer.get();
                }
            } else {
                Z_OFFSET = 0.0; // Reset offset when driver is driving the robot faster than the threshold
            }
        } else {
            STORED_Z_READING = xbox.getRightX();
            STORED_Z_TIME = timer.get();
        }

        RobotMap.RobotDrive.driveCartesian(xbox.getRightX() - X_OFFSET, xbox.getLeftY() - Y_OFFSET, xbox.getLeftX() - Z_OFFSET);
        //RobotMap.RobotDrive.driveCartesian(0, xbox.getLeftY(), 0);
        // the middle value is the turn

        // first value makes all 4 motors go the same way
    }

    // 2/19/2024 - mecanumDrive with flight stick
    public void mecanumDriveJoystick(Joystick stick) {
        RobotMap.RobotDrive.driveCartesian(stick.getX(), stick.getY(), stick.getZ());
    }

    public void run(double leftspeed, double rightspeed) {
        RobotMap.left.set(leftspeed);
        RobotMap.right.set(-rightspeed);
    }

    public void stop() {
        RobotMap.left.set(0);
        RobotMap.right.set(0);
    }

    public void doMecanumDrive (double x, double y, double z) {
        RobotMap.RobotDrive.driveCartesian(x, y, z);
    }
    
}
