package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class ArmDrop extends SubsystemBase{
   private edu.wpi.first.wpilibj.DoubleSolenoid.Value ched2;
    public ArmDrop(){

    }
    
    
    public void initSendable() {
    
    }

    
    
    public void toggleArmDown() {
        System.out.println("arm drop");
        ched2 = RobotMap.DoubleSolenoid2.get();
        if (ched2 == kReverse) {
            System.out.println("arm in reverde");
            //fdisabled = RobotMap.han_solonoid.isFwdSolenoidDisabled();
            //System.out.println("fwd solenoid disabled:");
            //System.out.println(fdisabled);
            RobotMap.DoubleSolenoid2.set(DoubleSolenoid.Value.kForward);
            System.out.println("arm idk");
        } else if (ched2 == kForward) {
            System.out.println("dik2");
            //rdisabled = RobotMap.han_solonoid.isRevSolenoidDisabled();
            //System.out.println("rev solenoid disabled:");
            //System.out.println(rdisabled);
            RobotMap.DoubleSolenoid2.set(DoubleSolenoid.Value.kReverse);
            System.out.println("di2");
        } else {
            System.out.println("d1");
            RobotMap.DoubleSolenoid2.set(DoubleSolenoid.Value.kReverse);
            System.out.println("d3");
        }
    
  
}
}
