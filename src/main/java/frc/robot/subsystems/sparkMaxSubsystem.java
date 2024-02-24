package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class sparkMaxSubsystem extends SubsystemBase {

    public sparkMaxSubsystem() {
        // ctor - nothing is needed here
    }

    // run the sparkMax at a set speed
    public void run(double speed) {
        RobotMap.sparkMax.set(speed);
        RobotMap.sparkMax2.set(-speed);
    }

    public void stop() {
        RobotMap.sparkMax.set(0);
        RobotMap.sparkMax2.set(0);
    }
}
