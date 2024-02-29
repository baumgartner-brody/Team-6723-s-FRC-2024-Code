package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class encoder_velocity implements Runnable {
  Robot robot;

  public encoder_velocity(Robot robot) {
    this.robot = robot;

    // Resets the encoders to zero rotations
    RobotMap.encoder5.setPosition(0);
    RobotMap.encoder6.setPosition(0);
    RobotMap.encoder7.setPosition(0);
  }

  public void run() {
    while (true) {
      
      /* Yield for a millisecond  */
      try {
        Thread.sleep(1);
      } catch (Exception e) {
        /* Do Nothing */
      }
      
      /* With brushless motors, we use 42 encoder units per revolution, hence the position and revs calucations */
      double sparkMax5_vel = RobotMap.encoder5.getVelocity();
      double sparkMax5_pos = RobotMap.encoder5.getPosition() % 42;
      double sparkMax5_rev = RobotMap.encoder5.getPosition() / 42;

      double sparkMax6_vel = RobotMap.encoder6.getVelocity();
      double sparkMax6_pos = RobotMap.encoder6.getPosition() % 42;
      double sparkMax6_rev = RobotMap.encoder6.getPosition() / 42;

      double sparkMax7_vel = RobotMap.encoder7.getVelocity();
      double sparkMax7_pos = RobotMap.encoder7.getPosition() % 42;
      double sparkMax7_rev = RobotMap.encoder7.getPosition() / 42;

      SmartDashboard.putNumber("SparkMax 5 vel: ", sparkMax5_vel);
      SmartDashboard.putNumber("SparkMax 5 pos: ", sparkMax5_pos);
      SmartDashboard.putNumber("SparkMax 5 rev: ", sparkMax5_rev);

      SmartDashboard.putNumber("SparkMax 6 vel: ", sparkMax6_vel);
      SmartDashboard.putNumber("SparkMax 6 pos: ", sparkMax6_pos);
      SmartDashboard.putNumber("SparkMax 6 rev: ", sparkMax6_rev);

      SmartDashboard.putNumber("SparkMax 7 vel: ", sparkMax7_vel);
      SmartDashboard.putNumber("SparkMax 7 pos: ", sparkMax7_pos);
      SmartDashboard.putNumber("SparkMax 7 rev: ", sparkMax7_rev);
    }
  }
}