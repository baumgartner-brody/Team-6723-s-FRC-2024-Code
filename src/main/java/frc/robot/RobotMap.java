package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

//import edu.wpi.first.wpilibj.DigitalInput; // This will be needed for limit switches

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// 2/11/2024 REVLib.json is required in vendordeps to use Spark Maxes
// https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Tank%20Drive%20With%20CAN/src/main/java/frc/robot/Robot.java
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

// https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java/Encoder%20Feedback%20Device
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;

/** Since all of the MotorControllers in RobotMap are static,
 *  there is no need to ever create a RobotMap object in Robot.java
 *  You do, however, need to call RobotMap.init() before trying to use any of the motors.
 */
public class RobotMap {
    public static MecanumDrive RobotDrive;

    public static WPI_TalonSRX rightmotor1;
    public static WPI_TalonSRX rightmotor2;
    public static WPI_TalonSRX leftmotor1;
    public static WPI_TalonSRX leftmotor2;

    // 2/11/2024 CanSparkMax type & declaration
    public static CANSparkMax sparkMax5;  // ID 5
    public static CANSparkMax sparkMax6; // ID 6
    public static CANSparkMax sparkMax7; // ID 7

    public static SparkPIDController pidController5;
    public static SparkPIDController pidController6;
    public static SparkPIDController pidController7;

    public static RelativeEncoder encoder5;
    public static RelativeEncoder encoder6;
    public static RelativeEncoder encoder7;

    /* Old limit switch code - leaving in for reference */
    /*
    public static DigitalInput lsTop;
    public static DigitalInput lsBottom;

    public static DigitalInput lsTop2;
    public static DigitalInput lsBottom2;
    */

    public void init() {
      leftmotor1 = new WPI_TalonSRX(0);
      leftmotor2 = new WPI_TalonSRX(1);
      rightmotor1 = new WPI_TalonSRX(2);
      rightmotor2 = new WPI_TalonSRX(3);
      

      // 2/11/2024 Sample SparkMax declaration
      sparkMax5 = new CANSparkMax(5, MotorType.kBrushless);
      sparkMax6 = new CANSparkMax(6, MotorType.kBrushless);
      sparkMax7 = new CANSparkMax(7, MotorType.kBrushless);

      // 2/16/2024 encoder
      encoder5 = sparkMax5.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
      encoder6 = sparkMax6.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
      encoder7 = sparkMax7.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
      
      //https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Encoder%20Feedback%20Device/src/main/java/frc/robot/Robot.java
      /**
       * In order to use PID functionality for a controller, a SparkPIDController object
       * is constructed by calling the getPIDController() method on an existing
       * CANSparkMax object
       */
      pidController5 = sparkMax5.getPIDController();
      pidController6 = sparkMax6.getPIDController();
      pidController7 = sparkMax7.getPIDController();

      /**
       * The PID Controller can be configured to use the analog sensor as its feedback
       * device with the method SetFeedbackDevice() and passing the PID Controller
       * the CANAnalog object. 
       */
      pidController5.setFeedbackDevice(encoder5);
      pidController6.setFeedbackDevice(encoder6);
      pidController7.setFeedbackDevice(encoder7);

      /* Invert some of the drivetrain motors, essentially flipping their direction */
      leftmotor1.setInverted(true);
      rightmotor1.setInverted(true);
      leftmotor2.setInverted(true);

      RobotDrive = new MecanumDrive(leftmotor1, leftmotor2, rightmotor1, rightmotor2);
      RobotDrive.setSafetyEnabled(false);

      /* Old limit switch code - the number in ( ) is the DIO port on the RIO */
      //lsTop2 = new DigitalInput(2);
      //lsBottom2 = new DigitalInput(3);

      //lsTop = new DigitalInput(0);
      //lsBottom = new DigitalInput(1);
    }
}
