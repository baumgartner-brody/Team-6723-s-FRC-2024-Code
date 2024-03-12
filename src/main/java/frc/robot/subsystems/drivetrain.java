package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

import java.lang.Math; // abs

public class drivetrain extends SubsystemBase {

    private final Timer timer;

    /* The speed that offsets will be calculated below and reset when the driver exceeds */
    private double X_DRIFT_THRESHOLD = 0.15;
    private double Y_DRIFT_THRESHOLD = 0.15;
    private double Z_DRIFT_THRESHOLD = 0.15;

    /* The amount of time (in seconds) for a controller to read the same number for the reading to be considered an offset */
    private double X_OFFSET_TIMEOUT = 0.5;
    private double Y_OFFSET_TIMEOUT = 0.5;
    private double Z_OFFSET_TIMEOUT = 0.5;

    /* The time that STORED_READING was updated (the lower end of a time window) */
    private double STORED_X_TIME = 0.0;
    private double STORED_Y_TIME = 0.0;
    private double STORED_Z_TIME = 0.0;

    /* Controller readings per axis to determine if the controller has read the same value for too long */
    private double STORED_X_READING = 0.0;
    private double STORED_Y_READING = 0.0;
    private double STORED_Z_READING = 0.0;

    /* Offsets for correcting drift */
    private double X_OFFSET = 0.0;
    private double Y_OFFSET = 0.0;
    private double Z_OFFSET = 0.0;

    /* drivetrain constructor initializes the timer */
    public drivetrain() {
        timer = new Timer();
        timer.reset();
        timer.start();
        RobotMap.gyro.reset();
    }

    public void doMecanumDrive(XboxController xbox) {
        /** The offsets are calculated based on the following logic: (X used in explanation, but this applies to all 3 axes)
         *  If the controller reports the same reading for more than X_OFFSET_TIMEOUT seconds, the reading is considered to be an offset
         *  If the driver exceeds X_DRIFT_THRESHOLD, X_OFFSET is reset. Offsets only apply at speeds below this threshold. This is to prevent the controller
         *  from misbehaving when the driver is moving around at significant speeds.
         *  STORED_X_TIME and STORED_X_READING are bookkeeping variables to ensure the offset calculation window is dynamic and updated correctly.
         */ 

        /* FOD math from https://www.kauailabs.com/support/sf2/kb/faq.php?id=28 */
        double gyro_angle = RobotMap.gyro.getAngle();
        double gyro_radians = gyro_angle * (Math.PI / 180);

        // X offset
        if (xbox.getLeftX() == STORED_X_READING) {
            if (Math.abs(xbox.getLeftX()) < X_DRIFT_THRESHOLD) {
                if (timer.get() > STORED_X_TIME + X_OFFSET_TIMEOUT) {
                    X_OFFSET = xbox.getLeftX();
                    STORED_X_READING = xbox.getLeftX();
                    STORED_X_TIME = timer.get();
                }
            } else {
                X_OFFSET = 0.0; // Reset offset when driver is driving the robot faster than the threshold
            }
        } else {
            STORED_X_READING = xbox.getLeftX();
            STORED_X_TIME = timer.get();
        }

        // Y offset
        if (xbox.getLeftY() != 0 && xbox.getLeftY() == STORED_Y_READING) {
            if (Math.abs(xbox.getLeftY()) < Y_DRIFT_THRESHOLD) {
                if (timer.get() > STORED_Y_TIME + Y_OFFSET_TIMEOUT) {
                    Y_OFFSET = xbox.getLeftY();
                    STORED_Y_READING = xbox.getLeftY();
                    STORED_Y_TIME = timer.get();
                }
            } else {
                Y_OFFSET = 0.0; // Reset offset when driver is driving the robot faster than the threshold
            }
        } else {
            STORED_Y_READING = xbox.getLeftY();
            STORED_Y_TIME = timer.get();
        }

        // Z offset
        if (xbox.getRightX() != 0 && xbox.getRightX() == STORED_Z_READING) {
            if (Math.abs(xbox.getRightX()) < Z_DRIFT_THRESHOLD) {
                if (timer.get() > STORED_Z_TIME + Z_OFFSET_TIMEOUT) {
                    Z_OFFSET = xbox.getRightX();
                    STORED_Z_READING = xbox.getRightX();
                    STORED_Z_TIME = timer.get();
                }
            } else {
                Z_OFFSET = 0.0; // Reset offset when driver is driving the robot faster than the threshold
            }
        } else {
            STORED_Z_READING = xbox.getRightX();
            STORED_Z_TIME = timer.get();
        }

        /* Put offsets on the SmartDashboard for debugging */
        SmartDashboard.putNumber("X_OFFSET: ", X_OFFSET);
        SmartDashboard.putNumber("Y_OFFSET: ", Y_OFFSET);
        SmartDashboard.putNumber("Z_OFFSET: ", Z_OFFSET);

        // These may not refer to the same thing
        double forward = (xbox.getLeftY() - Y_OFFSET);
        double strafe = (-xbox.getLeftX() + X_OFFSET);

        double temp = (forward * Math.cos(gyro_radians)) + (strafe * Math.sin(gyro_radians));

        strafe = (-forward * Math.sin(gyro_radians)) + (strafe * Math.sin(gyro_radians));
        forward = temp;

        // Original code in case FOD doesn't work
        RobotMap.RobotDrive.driveCartesian(xbox.getLeftY() - Y_OFFSET, -xbox.getLeftX() + X_OFFSET, -xbox.getRightX() + Z_OFFSET);

       // RobotMap.RobotDrive.driveCartesian(forward, strafe, -xbox.getRightX() + Z_OFFSET);
    }

    /* Drive the robot with a parameter x, y, and z speed */
    /* x - Back and forth speed (maps to xbox.getLeftY()) */
    /* y - Scuttle speed (maps to xbox.getLeftX()) */
    /* z - Rotation (maps to xbox.getRightX()) */
    public void doMecanumDrive (double x, double y, double z) {
        RobotMap.RobotDrive.driveCartesian(x, y, z);
    }
    
}
