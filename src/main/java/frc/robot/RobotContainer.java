// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

import frc.robot.commands.*;
import frc.robot.commands.Autos.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; 

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here...
  public final drivetrain s_drivetrain = new drivetrain();
  public final sparkMaxSubsystem s_sparkMax = new sparkMaxSubsystem();
  public final intakeSubsystem s_sparkMax3 = new intakeSubsystem();

  //commands
  
  public final MecanumDrive c_mecanumDrive = new MecanumDrive(s_drivetrain);
 
  //commands
  
  public SendableChooser<Command> autoChooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
     
    configureBindings();

    /* Add options to the autoChooser */
    /* The option added via setDefaultOption() will be selected by default, and returned unless another option has been selected by the driver. */
    /* Options added via addOption() must be manually selected to be run. Commands must be instantiated this way to ensure they return new objects and */
    /* not references to objects that are old and no longer valid. */
    autoChooser.setDefaultOption("DriveForward", new DriveAuto(0.3, 0, 0, 2)); // 2/17/2024 - correct way to instantiate an auto command
    autoChooser.addOption("DriveForward then back", new AutoCommandGroup());

    /* Put the autoChooser on the SmartDashboard so it can be interacted with and seen */
    SmartDashboard.putData(autoChooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  public void configureBindings() {
    /* A Button */
    Robot.oi.A.onTrue(new SparkMaxCommand(s_sparkMax, -0.1)); // Raise the arm at 10% speed while A is held
    Robot.oi.A.onFalse(new SparkMaxCommandStop(s_sparkMax));

    /* B Button */
    Robot.oi.B.onTrue(new SparkMaxCommand(s_sparkMax, -0.015)); 

    // TODO
    /* We'll have to make a button capable of reverse raising the arm in case it goes too far back */

    /* X Button */
    Robot.oi.X.whileTrue(new IntakeCommand(s_sparkMax3, -0.5)); // Run the intake at full speed while X is held
    Robot.oi.X.onFalse(new IntakeCommandStop(s_sparkMax3));

    /* Test a smarter arm lowering command that the driver presses a button and then the code does the work */
    Robot.oi.Y.onTrue(new LowerArm(s_sparkMax));
    Robot.oi.RightBumper.whileTrue(new IntakeCommand(s_sparkMax3, -0.2));

    /* This is an attempt to use configureBindings with xbox buttons. */
    /* This code was written on 2/26/2024, and has not been tested. The old code is available to un-comment in Robot.teleopPeriodic */
    /* Right now, our B button mechanism to lower the arm is wonky, unreliable, and requires the driver to repeatly press the button as the arm jerks downwards. */
    /* We would like to make a command that uses encoders or timing to lower the arm in a smooth and controlled manner. */
    /* This requires gradually lowering a speed of about -0.02 to -0.01, as -0.02 suspends the arm, but -0.01 allows it to gradually lower. */

    /* This command should be set up so the driver taps it and the code does the rest, so via toggleOnTrue() */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public Command getMecanumDrive() {
    return c_mecanumDrive;
  }
}
 
