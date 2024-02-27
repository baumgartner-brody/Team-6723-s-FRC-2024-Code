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
  // The robot's subsystems and commands are defined here...
  public static final drivetrain s_drivetrain = new drivetrain();
  public static final sparkMaxSubsystem s_sparkMax = new sparkMaxSubsystem();
  public static final intakeSubsystem s_sparkMax3 = new intakeSubsystem();

  //commands
  
  public final MecanumDrive c_mecanumDrive = new MecanumDrive(s_drivetrain);
  //private final DriveFowardAuto c_DriveForwardAuto = new DriveFowardAuto(0.3, 0, 0, 2); // 2/17/2024 Auto that drives straight for 2 seconds (incorrect way to instantiate - see below)
 
  //commands
  
  public SendableChooser<Command> autoChooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
     
    //configureBindings();

    //autoChooser.setDefaultOption("Nothing", c_BlankAuto);
    //autoChooser.addOption("Score", c_ScoreAuto);
    //autoChooser.addOption("Score + Climb", c_ScoreClimbAuto);
   // autoChooser.addOption("Taxi", c_TaxiAuto);
   // autoChooser.addOption("Taxi + cube", c_CubeTaxiAuto);
   // autoChooser.addOption("Climb", c_ClimbAuto);
   // autoChooser.addOption("Dance", c_DanceAuto);
    autoChooser.setDefaultOption("DriveForward", new DriveFowardAuto(0.3, 0, 0, 2)); // 2/17/2024 - correct way to instantiate an auto command
    autoChooser.addOption("DriveForward then back", new DriveFowardacc());

    SmartDashboard.putData(autoChooser);
    
    //SmartDashboard.putData("Auto:",  autoChooser);
    System.out.println("in robotcontainer ctor"); // guof
  
    //autoChooser.addOption("Test", c_TestAuto);
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

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition)
        //.onTrue(new ExampleCommand(m_exampleSubsystem));

    // 2/19/2024 Uncomment this to use the joystick
    //Robot.oi.button1.onTrue(new SparkMaxCommand(-0.1));

  }

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
   // s_sparkMax.AButton().whileTrue(s_sparkMax.SparkMaxCommand());


    /*  
    Robot.oi.button1.onTrue(c_LifterForward);
    Robot.oi.button1.onFalse(c_LifterStop);
    Robot.oi.button2.onTrue(c_ToggleGrabber);
    //Robot.oi.button2.whenPressed(c_ToggleGrabber2);
    Robot.oi.button3.onTrue(ArmDrop);
    //Robot.oi.button4.whenPressed(c_ToggleGrabber);
    //Robot.oi.button4.onTrue(c_LifterStop);
    //Robot.oi.button3.onFalse(c_LifterStop);
    // Robot.oi.button11.onTrue(c_LifterStop);
    Robot.oi.button5.onTrue(c_LifterStand);
    Robot.oi.button6.onTrue(c_LifterHeavyStand);
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChooser.getSelected();
  }

  public Command getMecanumDrive() {
    return c_mecanumDrive;
  }
}
 
