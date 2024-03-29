package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.*;
/* The IntakeCommand runs the intake Spark Max at a specified speed */
public class IntakeCommandStop extends Command {

    /* A reference to the subsystem that controls the intake Spark Max */
    private final intakeSubsystem s_sparkmax3;
    
    public IntakeCommandStop(intakeSubsystem sparkMax3) {
        s_sparkmax3 = sparkMax3;
        addRequirements(s_sparkmax3);
    }

    @Override
    public void initialize() {
        /* Initialize does nothing here, but that's ok. */
        /* In cases like this, it is not technically required to @Override it, but to keep program structure, I recommend leaving it in. */
    }

    @Override 
    public void execute() {}

    /* Stop commands immediately finish, unlike onTrue() commands which return false to run forever */
    @Override
    public boolean isFinished() {
        return true;
    }

    /* For safety reasons, it's a good idea to override end() in all your commands and call the stop() method on its required subsystem */
    /* If commands seem to be stopping prematurely, checking to see if end() was invoked is a good starting point to debbug. */
    @Override 
    public void end(boolean interrupted) {
        s_sparkmax3.stop();
    }
}
 