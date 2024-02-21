package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class encoder_velocity implements Runnable {
  Robot robot;

  public encoder_velocity(Robot robot) {
    this.robot = robot;

    // Resets the gyro and the encoders to zero rotations/ degrees
    RobotMap.encoder.setPosition(0);
    RobotMap.encoder2.setPosition(0);
  }

  public void run() {
    /**
     * Speed up network tables, this is a test project so eat up all of
     * the network possible for the purpose of this test.
     */

    while (true) {
      /* Yield for a Ms or so - this is not meant to be accurate */
      try {
        Thread.sleep(1);
      } catch (Exception e) {
        /* Do Nothing */
      }

      /* Grab the latest signal update from our 1ms frame update 
       * Calculates and logs all the encoder data. Encoders are measured in encoder units and work 
       * because only a small arc of the actual output shaft is magnetic. Using 4096 encoder units per
       * rotation, one rotation equates to about 23 inches, you may adjust this to get a cleaner number,
       * but be wary all auto routines are measured by desired encoder revolutions! 
      */
      double velocity = RobotMap.encoder.getVelocity();
      double position = RobotMap.encoder.getPosition() % 42;
      double revs = RobotMap.encoder.getPosition() / 42;

      double velocity2 = RobotMap.encoder2.getVelocity();
      double position2 = RobotMap.encoder2.getPosition() % 42;
      double revs2 = RobotMap.encoder2.getPosition() / 42;

      /*
      double right_velocity = RobotMap.RrearMotor.getSelectedSensorVelocity(0);
      double right_position = RobotMap.RrearMotor.getSelectedSensorPosition(0) % 4096;
      double right_rotations = RobotMap.RrearMotor.getSelectedSensorPosition(0) / 4096;
      //Robot.offset = Math.abs(left_rotations) - Math.abs(right_rotations); 
      //Robot.offset_speed = Robot.offset / 10;
      
      double shooter_vel = RobotMap.Shooter.getSelectedSensorVelocity(0);
      double shooter_pos = RobotMap.Shooter.getSelectedSensorPosition(0) % 4096;
      double shooter_rots = RobotMap.Shooter.getSelectedSensorPosition(0) / 4096; 
      
      SmartDashboard.putNumber("L_vel", left_velocity);
      SmartDashboard.putNumber("L_pos", left_position);
      SmartDashboard.putNumber("L_rotations", left_rotations);
      SmartDashboard.putNumber("R_vel", right_velocity);
      SmartDashboard.putNumber("R_pos", right_position);
      SmartDashboard.putNumber("R_rotations", right_rotations);
      SmartDashboard.putNumber("shooter_velocity", shooter_vel);
      SmartDashboard.putNumber("shooter_position", shooter_pos);
      SmartDashboard.putNumber("shooter_rotations", shooter_rots); 
      SmartDashboard.putNumber("offset", Robot.offset);
      SmartDashboard.putNumber("offset_speed", Robot.offset_speed);
      SmartDashboard.putNumber("Gyro Angle", RobotMap.Mutton.getAngle());
      */
      SmartDashboard.putNumber("Encoder velocity: ", velocity);
      SmartDashboard.putNumber("Encoder position: ", position);
      SmartDashboard.putNumber("Encoder revolutions: ", revs);

      SmartDashboard.putNumber("Encoder2 velocity: ", velocity2);
      SmartDashboard.putNumber("Encoder2 position: ", position2);
      SmartDashboard.putNumber("Encoder2 revolutions: ", revs2);
    }
  }
}