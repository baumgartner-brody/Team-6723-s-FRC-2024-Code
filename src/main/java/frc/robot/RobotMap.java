package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.math.controller.PIDController;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

import java.beans.Encoder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.GroupMotorControllers;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.PneumaticsControlModule;
//import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

// 2/11/2024 REVLib.json is required in vendordeps to use Spark Maxes
// https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Tank%20Drive%20With%20CAN/src/main/java/frc/robot/Robot.java
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;

public class RobotMap {

    public static MecanumDrive RobotDrive;

    public static WPI_TalonSRX rightmotor1;
    public static WPI_TalonSRX rightmotor2;
    public static WPI_TalonSRX leftmotor1;
    public static WPI_TalonSRX leftmotor2;
    public static WPI_VictorSPX liftmotor;
    public static MotorControllerGroup left;
    public static MotorControllerGroup right;
    public static DoubleSolenoid DoubleSolenoid2;

    // 2/11/2024 CanSparkMax type & declaration
    public static CANSparkMax sparkMax;  // ID 5
    public static CANSparkMax sparkMax2; // ID 6
    public static CANSparkMax sparkMax3; // ID 7

    public static SparkPIDController pidController;
    public static SparkPIDController pidController2;
    public static SparkPIDController pidController3;


    public static RelativeEncoder encoder;
    public static RelativeEncoder encoder2;
    public static RelativeEncoder encoder3;

    /*
     * public static WPI_TalonSRX outdexerMotor;
     * public static WPI_TalonSRX indexerMotor;
     * 
     * public static PWMTalonSRX stage1climberMotor;
     * 
     * public static DigitalInput limitswitch0;
     * 
     * public static WPI_TalonSRX stage2climberMotorA;
     * public static WPI_VictorSPX stage2climberMotorB;
     * 
     * 
     * 
     */

    public static ADXRS450_Gyro _gyro;
    public static SPI.Port h;

    public static Compressor c;

    public static DoubleSolenoid han_solonoid;
    public static DoubleSolenoid ben_solonoid;

    public static DigitalInput lsTop;
    public static DigitalInput lsBottom;

    public static DigitalInput lsTop2;
    public static DigitalInput lsBottom2;

    public RobotMap() {
        init();
    }

    public static void init() {

        System.out.println("Called init()");

        c = new Compressor(0, PneumaticsModuleType.CTREPCM);
        c.enableHybrid(100, 120);
        // c.enableHybrid(60, 120);
        // c.enableAnalog(60, 120);
        // c.disable();
        
        // 2/16/2024 Drivetrain motors were rearranged
        rightmotor1 = new WPI_TalonSRX(2);
        rightmotor2 = new WPI_TalonSRX(3);
        leftmotor1 = new WPI_TalonSRX(0);
        leftmotor2 = new WPI_TalonSRX(1);

        liftmotor = new WPI_VictorSPX(4);

        // 2/11/2024 Sample SparkMax declaration
        //sparkMax = new CANSparkMax//(TOD(o) - calibrate motor with talon tuner, MotorType.kBrushless);
        sparkMax = new CANSparkMax(5, MotorType.kBrushless);
        sparkMax2 = new CANSparkMax(6, MotorType.kBrushless);
        sparkMax3 = new CANSparkMax (7, MotorType.kBrushless);

        // 2/16/2024 encoder
        encoder = sparkMax.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        encoder2 = sparkMax2.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        encoder3 = sparkMax3.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        
        //https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Encoder%20Feedback%20Device/src/main/java/frc/robot/Robot.java
        /**
         * In order to use PID functionality for a controller, a SparkPIDController object
         * is constructed by calling the getPIDController() method on an existing
         * CANSparkMax object
         */
        pidController = sparkMax.getPIDController();
        pidController2 = sparkMax2.getPIDController();
        pidController3 = sparkMax3.getPIDController();

        /**
         * The PID Controller can be configured to use the analog sensor as its feedback
         * device with the method SetFeedbackDevice() and passing the PID Controller
         * the CANAnalog object. 
         */
        pidController.setFeedbackDevice(encoder);
        pidController2.setFeedbackDevice(encoder2);
        pidController3.setFeedbackDevice(encoder3);

      //  leftmotor1.configFactoryDefault();
      //  leftmotor2.configFactoryDefault();
      //  rightmotor1.configFactoryDefault();
      //  rightmotor2.configFactoryDefault();

      //  leftmotor2.follow(leftmotor1);
      //  rightmotor2.follow(rightmotor1);

        leftmotor1.setInverted(true);
        rightmotor1.setInverted(true);
        leftmotor2.setInverted(true);

     //   leftmotor2.setInverted(InvertType.FollowMaster);
       // rightmotor2.setInverted(InvertType.FollowMaster); 

        liftmotor.setInverted(true);
        // left = new MotorControllerGroup(leftmotor1, leftmotor2);
        // right = new MotorControllerGroup(rightmotor1, rightmotor2);
        // rightmotor2.follow(rightmotor1);
        // leftmotor2.follow(leftmotor1);

        RobotDrive = new MecanumDrive(leftmotor1, leftmotor2, rightmotor1, rightmotor2 );
        RobotDrive.setSafetyEnabled(false);

        lsTop2 = new DigitalInput(2);
        lsBottom2 = new DigitalInput(3);
        DoubleSolenoid2 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 2, 3);
        DoubleSolenoid2.set(DoubleSolenoid.Value.kOff);

        /*
         * indexerMotor = new WPI_TalonSRX(5);
         * outdexerMotor = new WPI_TalonSRX(6);
         * 
         * stage1climberMotor = new PWMTalonSRX(0);
         * 
         * stage2climberMotorA = new WPI_TalonSRX(4);
         * stage2climberMotorB = new WPI_VictorSPX(7);
         * 
         * limitswitch0 = new DigitalInput(1);
         * 
         * 
         * 
         */
        // _gyro.reset();

        lsTop = new DigitalInput(0);
        lsBottom = new DigitalInput(1);
        // WELCOME TO HELL
        han_solonoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
        han_solonoid.set(DoubleSolenoid.Value.kOff);
        // ben_solonoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
        // ben_solonoid.set(DoubleSolenoid.Value.kOff);
        h = SPI.Port.kOnboardCS0;
        _gyro = new ADXRS450_Gyro(h);
        _gyro.calibrate();

    }
}
