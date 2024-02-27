// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static RobotMap robotmap;
  public static OI oi;

  private Command m_autonomousCommand;
  private Command c_mecanumDriveCommand;

  public static RobotContainer m_robotContainer;

  /* Simple thread to plot sensor velocity and such */
	encoder_velocity encoder_data;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotmap = new RobotMap();
    m_robotContainer = new RobotContainer();

    /* As a rule of thumb instantiate OI after RobotMap and RobotContainer due to its tendency to have dependencies in those */
    oi = new OI();

    /* Start a camera on RIO USB port 0 */
    CameraServer.startAutomaticCapture(0);

    /* Allow the arm speed to be controlled when the user presses B */
    SmartDashboard.putNumber("Arm speed", -0.015);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    // Starts encoder data thread
    encoder_data = new encoder_velocity(this);
    new Thread(encoder_data).start();

    /* Get the selected command from SmartDashboard's autoChooser */
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    /* Start the command. This is why they need to be instantiated the way they are in RobotContainer. They need to be treated as new objects. */
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    oi.otherOI();
  }

  @Override
  public void teleopInit() {

    // Starts encoder data thread
    encoder_data = new encoder_velocity(this);
    new Thread(encoder_data).start();

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    c_mecanumDriveCommand = m_robotContainer.getMecanumDrive();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /* This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    c_mecanumDriveCommand.schedule(); // Ensure the mecanumDriveCommand is always running during teleOp

    oi.otherOI();

    /* The old, ah-hoc way to use xbox buttons. We are trying to port them over to configureBindings() in RobotContainer */
    /*
    if (!oi.getXboxController().getAButton() && !oi.getXboxController().getBButton() && 
        !oi.getXboxController().getXButton()) {
      m_robotContainer.s_sparkMax.stop();
    } else if (oi.getXboxController().getAButton()) {
      m_robotContainer.s_sparkMax.run(-0.1);
    } else if (oi.getXboxController().getXButton()) {

      double avg_encoder_velocity = (robotmap.encoder5.getVelocity() + robotmap.encoder6.getVelocity()) / 2;
      double speed = avg_encoder_velocity / 42;

      SmartDashboard.putNumber("speed: ", speed);

      double speed_to_run_arm = Math.max(-speed, -0.015);

      SmartDashboard.putNumber("speed_to_run_arm: ", speed_to_run_arm);

      m_robotContainer.s_sparkMax.run(speed_to_run_arm);
    }

    if (!oi.getXboxController().getYButton()) {
      m_robotContainer.s_sparkMax3.stop();
    } else if (oi.getXboxController().getYButton()) {
      m_robotContainer.s_sparkMax3.run(-1);
    }
    */
  }
   
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

}
