package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class intakeSubsystem extends SubsystemBase {

    public intakeSubsystem() {
        // ctor - nothing is needed here
    }

    // run the sparkMax at a set speed
    public void run(double speed) {
        RobotMap.sparkMax3.set(speed);
    }

    public void stop() {
        RobotMap.sparkMax3.set(0);
    }
}