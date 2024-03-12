// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /* These declarations are for the autonomous chooser button on the SmartDashboard
  *  If it ever doesn't show up try either running the code on another computer or 
  *  renaming the variable "autoChooser" to something else (supposedly this was a more common old bug)
  */
  Command autonomousCommand;
  public SendableChooser<Command> autoChooser;

  /** The static keyword needs to be used with caution. 
   *  It is used to declare variables that may only have 1 unique instance in an entire codebase.
   *  For this reason, we use it for subsystems and OI, since the robot should only ever have one 
   *  of each subsystem, and one object to interact with the joystick. 
   * 
   *  Unless you know what you are doing, avoid using "static" in other places.
  */
  public static OI oi;
  public static drivetrain Drivetrain;
  public static intakeSubsystem IntakeSubsystem;
  public static sparkMaxSubsystem SparkMaxSubsystem;

  /* Simple thread to plot sensor velocity and such */
	public encoder_velocity encoder_data;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    RobotMap.init(); // RobotMap is the first thing that should be initialized.

    /* Make sure to initialize each of your subsystems here  */
    oi = new OI();
    Drivetrain = new drivetrain();
    IntakeSubsytem = new intakeSubsystem();
    SparkMaxSubsystem = new sparkMaxSubsystem();

    // Begin camera capture
    CameraServer.startAutomaticCapture();

    /* AutoChooser config goes here */
    // ! IMPORTANT ! - You need to fill this out again 3/12/2024
    autoChooser = new SendableChooser<Command>();

    autoChooser.setDefaultOption("DriveForward", new DriveAuto(0.3, 0, 0, 2)); // 2/17/2024 - correct way to instantiate an auto command
    autoChooser.addOption("DriveForward then back", new AutoCommandGroup());

    /* Put the autoChooser on the SmartDashboard so it can be interacted with and seen */
    SmartDashboard.putData(autoChooser);
    
    // EXPERIMENTAL SECTION (remove unless tested with 2 cameras)
    /* Configure camera's FPS and resolution, the string can be any string, and the number is the USB port */
    // We can play with this more
    UsbCamera camera = new UsbCamera("camera", "dev/usb0"); 
    camera.setFPS(24);
    camera.setResolution(640, 800);

    /* Start a camera on RIO USB port 0 */
    //CameraServer.startAutomaticCapture(camera);

    // END OF EXPERIMENTAL SECTION

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
    CommandScheduler.getInstance().removeAll(); // Ensures nothing runs when robot is disabled
    CommandScheduler.getInstance().close();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    // Starts encoder data thread
    encoder_data = new encoder_velocity();
    new Thread(encoder_data).start();

    /* Get the selected command from SmartDashboard's autoChooser */
    autonomousCommand = (Command)autoChooser.getSelected();

    /* Start the command. This is why they need to be instantiated the way they are in RobotContainer. They need to be treated as new objects. */
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    } // ! IMPORTANT ! - With an else clause here, you can have a failsafe auto if for whatever reason SmartDashboard is acting up
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    oi.otherOI();
  }

  @Override
  public void teleopInit() {

    // Starts encoder data thread
    encoder_data = new encoder_velocity();
    new Thread(encoder_data).start();

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /* This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    drivetrain.doMecanumDrive(oi.getXboxController());

    oi.otherOI(); // Auxilary OI helper
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
