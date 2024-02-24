package frc.robot.commands.Autos;
import frc.robot.commands.Wait;
import frc.robot.subsystems.drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DriveFowardacc extends SequentialCommandGroup {
    
    public DriveFowardacc() {
        //DriveFowardAuto driveFowardAuto = new DriveFowardAuto(.8, 0, 0, 5);
        /*
        addCommands(
            new DriveFowardAuto(.8,0,0,3),
            new DriveFowardAuto(0, 0, .5, 2),
            new DriveFowardAuto(.8, 0, 0, 2)
            // will add code for using the neo to drop a note into the amp
        );
        */
        addCommands(
            new DriveFowardAuto(0.3, 0, 0, 2),
            new DriveFowardAuto(-0.3, 0, 0, 2)
        );
            

    }
}


/* 
    package frc.robot.commands;

import frc.robot.*;
import frc.robot.commands.auto.StraightDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

// A variation of the three ball auto with a seven second delay
public class three_pt_auto_delay extends CommandGroup {

    public three_pt_auto_delay() {
        requires(Robot.drivetrain);
        requires(Robot.shooter);
        requires(Robot.indexer);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        addSequential(new delay(7)); // wait 7 seconds

        addSequential(new StraightDrive(-0.6, 5)); // drive forward @ 60% speed for 5 revolutions ~10 ft
        addSequential(new full_shooter());

        addSequential(new StraightDrive(0.6, 0)); // drive out from the wall

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
*/
    
   