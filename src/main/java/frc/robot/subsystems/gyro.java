
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class gyro extends SubsystemBase{

    

    public gyro() {
        //kungstructor
    }

    public void init() {

    }

    public double get() {
        return RobotMap._gyro.getAngle();
    }

    public double rate() {
        return RobotMap._gyro.getRate();
    }
    
}
