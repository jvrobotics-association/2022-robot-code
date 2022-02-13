package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import org.strykeforce.swerve.SwerveDrive;
import org.strykeforce.swerve.TalonSwerveModule;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    private final SwerveDrive swerveDrive;

    /**
     * The Singleton instance of this DriveSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static DriveSubsystem INSTANCE = new DriveSubsystem();

    /**
     * Returns the Singleton instance of this DriveSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code DriveSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static DriveSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this DriveSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    public DriveSubsystem() {
        var talonModules = new TalonSwerveModule[4];

        var driveMotorConfig = new TalonFXConfiguration();
        driveMotorConfig.openloopRamp = 0.5;
        driveMotorConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Absolute;
        driveMotorConfig.supplyCurrLimit = new SupplyCurrentLimitConfiguration(true, 25, 30, 0.5);

        var rotationMotorConfig = new TalonSRXConfiguration();
        rotationMotorConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        rotationMotorConfig.continuousCurrentLimit = 10;
        rotationMotorConfig.peakCurrentDuration = 0;
        rotationMotorConfig.peakCurrentLimit = 15;
        rotationMotorConfig.slot0.kP = 10.0;
        rotationMotorConfig.slot0.kI = 0.0;
        rotationMotorConfig.slot0.kD = 100.0;
        rotationMotorConfig.slot0.kF = 0.0;
        rotationMotorConfig.slot0.integralZone = 0;
        rotationMotorConfig.slot0.allowableClosedloopError = 0;
        rotationMotorConfig.motionAcceleration = 10_000;
        rotationMotorConfig.motionCruiseVelocity = 800;

        var leftFrontDriveTalon = new TalonFX(Constants.LEFT_FRONT_DRIVE_MOTOR);
        leftFrontDriveTalon.configFactoryDefault();
        leftFrontDriveTalon.configAllSettings(driveMotorConfig);
        leftFrontDriveTalon.setNeutralMode(NeutralMode.Brake);

        var leftFrontRotationTalon = new TalonSRX(Constants.LEFT_FRONT_ROTATION_MOTOR);
        leftFrontRotationTalon.configFactoryDefault();
        leftFrontRotationTalon.configAllSettings(rotationMotorConfig);

        var rightFrontDriveTalon = new TalonFX(Constants.RIGHT_FRONT_DRIVE_MOTOR);
        rightFrontDriveTalon.configFactoryDefault();
        rightFrontDriveTalon.configAllSettings(driveMotorConfig);
        rightFrontDriveTalon.setNeutralMode(NeutralMode.Brake);

        var rightFrontRotationTalon = new TalonSRX(Constants.RIGHT_FRONT_ROTATION_MOTOR);
        rightFrontRotationTalon.configFactoryDefault();
        rightFrontRotationTalon.configAllSettings(rotationMotorConfig);

        var leftRearDriveTalon = new TalonFX(Constants.LEFT_REAR_DRIVE_MOTOR);
        leftRearDriveTalon.configFactoryDefault();
        leftRearDriveTalon.configAllSettings(driveMotorConfig);
        leftRearDriveTalon.setNeutralMode(NeutralMode.Brake);

        var leftRearRotationTalon = new TalonSRX(Constants.LEFT_REAR_ROTATION_MOTOR);
        leftRearRotationTalon.configFactoryDefault();
        leftRearRotationTalon.configAllSettings(rotationMotorConfig);

        var rightRearDriveTalon = new TalonFX(Constants.RIGHT_REAR_DRIVE_MOTOR);
        rightRearDriveTalon.configFactoryDefault();
        rightRearDriveTalon.configAllSettings(driveMotorConfig);
        rightRearDriveTalon.setNeutralMode(NeutralMode.Brake);

        var rightRearRotationTalon = new TalonSRX(Constants.RIGHT_REAR_ROTATION_MOTOR);
        rightRearRotationTalon.configFactoryDefault();
        rightRearRotationTalon.configAllSettings(rotationMotorConfig);

        var leftFrontWheel = new TalonSwerveModule.Builder();
        leftFrontWheel.driveTalon(leftFrontDriveTalon);
        leftFrontWheel.azimuthTalon(leftFrontRotationTalon);
        talonModules[0] = leftFrontWheel.build();

        var rightFrontWheel = new TalonSwerveModule.Builder();
        rightFrontWheel.driveTalon(rightFrontDriveTalon);
        rightFrontWheel.azimuthTalon(rightFrontRotationTalon);
        talonModules[0] = leftFrontWheel.build();

        var leftRearWheel = new TalonSwerveModule.Builder();
        leftRearWheel.driveTalon(leftRearDriveTalon);
        leftRearWheel.azimuthTalon(leftRearRotationTalon);
        talonModules[0] = leftFrontWheel.build();

        var rightRearWheel = new TalonSwerveModule.Builder();
        rightRearWheel.driveTalon(rightRearDriveTalon);
        rightRearWheel.azimuthTalon(rightRearRotationTalon);
        talonModules[0] = leftFrontWheel.build();

        @SuppressWarnings("ConstantConditions")
        Gyro robotGyro = (Gyro) new ADIS16470_IMU();

        swerveDrive = new SwerveDrive(robotGyro, talonModules);
    }

    public void drive(double forward, double strafe, double azimuth, boolean isFieldOriented) {
        swerveDrive.drive(forward, strafe, azimuth, isFieldOriented);
    }
}