package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class OI {
    private XboxController xbox;

    public JoystickButton A;
    public JoystickButton B;
    public JoystickButton X;
    public JoystickButton Y; /* Adding Y button as a test button for the new arm lowering command */
    public JoystickButton RightBumper;
    public JoystickButton LeftBumper; // You can remap this. But this is the reverse raise command in case the arm goes too far

    // ! IMPORTANT ! - these commands are NOT up to date with what we had at competition
    public OI() {
        xbox = new XboxController(0);

        A = new JoystickButton(xbox, XboxController.Button.kA.value);
        A.whileHeld(new SparkMaxCommand(-0.1));

        B = new JoystickButton(xbox, XboxController.Button.kB.value);
        B.toggleWhenPressed(new SparkMaxCommand(-0.015)); 

        X = new JoystickButton(xbox, XboxController.Button.kX.value);
        X.whileHeld(new IntakeCommand(-0.5));

        Y = new JoystickButton(xbox, XboxController.Button.kY.value);
        Y.toggleWhenPressed(new LowerArmCommandGroup());

        RightBumper = new JoystickButton(xbox, XboxController.Button.kRightBumper.value);
        RightBumper.whileHeld(new IntakeCommand(s_sparkMax3, -0.2));

        LeftBumper = new JoystickButton(xbox, XboxController.Button.kLeftBumper.value);
        LeftBumper.whileHeld(new SparkMaxCommandStop(0.1));
    }

    public XboxController getXboxController() {
        return xbox;
    }

    public void otherOI () {
        //all my homies love otheroi

        /* Put xbox axes readings on the SmartDashboard */
        SmartDashboard.putNumber("Xbox Turn", xbox.getRightX());
        SmartDashboard.putNumber("Xbox Strafe", xbox.getLeftX());
        SmartDashboard.putNumber("Xbox Foward", xbox.getLeftY());
        
        // 2/11/2024 - put A button status on smartDashboard (debugging)
        SmartDashboard.putBoolean("A is pressed: ", xbox.getAButton());
    }
}
