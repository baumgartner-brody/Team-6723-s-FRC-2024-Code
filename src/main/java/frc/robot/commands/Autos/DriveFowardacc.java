package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DriveFowardacc extends SequentialCommandGroup {
    
    public DriveFowardacc() {
        addCommands(
            new DriveFowardAuto(0.3, 0, 0, 2),
            new DriveFowardAuto(-0.3, 0, 0, 2)
        );
    }
}
   