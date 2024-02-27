package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class OI {
    private XboxController xbox;

    public Trigger A;
    public Trigger B;
    public Trigger X;

    public OI() {
        xbox = new XboxController(0);

        /* Construct triggers using the JoystickButton constructor. */
        /* This is known as upcasting, which is similar, but not the same thing as downcasting: https://en.wikipedia.org/wiki/Downcasting */
        A = new JoystickButton(xbox, XboxController.Button.kA.value);
        B = new JoystickButton(xbox, XboxController.Button.kB.value);
        X = new JoystickButton(xbox, XboxController.Button.kX.value);
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
