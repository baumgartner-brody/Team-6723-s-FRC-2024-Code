// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick; 
//import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
//import frc.robot.commands.autonomous;
//import frc.robot.robot_container.RobotContainer;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;
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
  private Command p_lifterStopCommand;

  private Command c_sparkMaxCommand;

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
    oi = new OI();
    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();
   

    
    RobotMap._gyro.calibrate();
    

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
  public void disabledInit() {
    RobotMap.c.disable();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    // Starts encoder data thread
    encoder_data = new encoder_velocity(this);
    new Thread(encoder_data).start();

    RobotMap.c.enableDigital();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
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

    RobotMap.c.enableDigital();
    //RobotMap._gyro.calibrate(); //probably move this for comp

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
    c_mecanumDriveCommand.schedule();

    SmartDashboard.putNumber("Compressor PSI: ", RobotMap.c.getPressure());
    //SmartDashboard.putBoolean("Pressure Switch: ", RobotMap.c.getPressureSwitchValue());
    SmartDashboard.putNumber("Compressor: ", 0);
    oi.otherOI();

    if (!oi.getXboxController().getAButton() && !oi.getXboxController().getBButton()) {
      RobotContainer.s_sparkMax.stop();
    } else if (oi.getXboxController().getAButton()) {
      RobotContainer.s_sparkMax.run(-0.1);
    } else if (oi.getXboxController().getBButton()) {
      RobotContainer.s_sparkMax.run(0.1);
    }

    if (!oi.getXboxController().getXButton() && !oi.getXboxController().getYButton()) {
      RobotContainer.s_sparkMax3.stop();
    } else if (oi.getXboxController().getXButton()) {
      RobotContainer.s_sparkMax3.run(0.1); // run the motor at half speed
    } else if (oi.getXboxController().getYButton()) {
      RobotContainer.s_sparkMax3.run(-0.1);
    }

    //SmartDashboard.putString("han_solenoid", RobotMap.han_solonoid.get());
  }
   
// i can code pretty good
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
