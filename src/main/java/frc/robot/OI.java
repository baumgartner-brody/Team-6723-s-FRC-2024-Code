package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class OI {
    private XboxController xbox;

    public JoystickButton A;
    public JoystickButton B;
    public JoystickButton X;
    public JoystickButton Menu;
    public JoystickButton Y; /* Adding Y button as a test button for the new arm lowering command */
    public JoystickButton RightBumper;

    public JoystickButton LeftBumper; // You can remap this. But this is the reverse raise command in case the arm goes too far

    public OI() {
        xbox = new XboxController(0);

        /* Construct triggers using the JoystickButton constructor. */
        /* This is known as upcasting, which is similar, but not the same thing as downcasting: https://en.wikipedia.org/wiki/Downcasting */
        A = new JoystickButton(xbox, XboxController.Button.kA.value);
        B = new JoystickButton(xbox, XboxController.Button.kB.value);
        X = new JoystickButton(xbox, XboxController.Button.kX.value);
        Y = new JoystickButton(xbox, XboxController.Button.kY.value);
        Menu = new JoystickButton(xbox, XboxController.Button.kStart.value);
        RightBumper = new JoystickButton(xbox, XboxController.Button.kRightBumper.value);

        LeftBumper = new JoystickButton(xbox, XboxController.Button.kLeftBumper.value);
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
        SmartDashboard.putBoolean("Menu Button Status", xbox.getStartButton());

        // 2/11/2024 - put A button status on smartDashboard (debugging)
        SmartDashboard.putBoolean("A is pressed: ", xbox.getAButton());
    }
}
