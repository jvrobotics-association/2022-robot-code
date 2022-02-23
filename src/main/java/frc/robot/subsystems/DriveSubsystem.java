package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.CustomGyroModule;
import org.strykeforce.swerve.SwerveDrive;
import org.strykeforce.swerve.TalonSwerveModule;

public class DriveSubsystem extends SubsystemBase {
    private final SwerveDrive swerveDrive;

    public DriveSubsystem() {
        var talonModules = new TalonSwerveModule[4];

        var driveMotorConfig = new TalonFXConfiguration();
        driveMotorConfig.openloopRamp = 0.5;
        driveMotorConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
        driveMotorConfig.supplyCurrLimit = new SupplyCurrentLimitConfiguration(true, 25, 30, 0.5);

        var rotationMotorConfig = new TalonSRXConfiguration();
        rotationMotorConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Absolute;
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
        leftFrontWheel.driveGearRatio(6.67);
        leftFrontWheel.wheelDiameterInches(4);
        leftFrontWheel.driveMaximumMetersPerSecond(4.11);
        leftFrontWheel.wheelLocationMeters(new Translation2d(-0.267, 0.292));
        talonModules[0] = leftFrontWheel.build();

        var rightFrontWheel = new TalonSwerveModule.Builder();
        rightFrontWheel.driveTalon(rightFrontDriveTalon);
        rightFrontWheel.azimuthTalon(rightFrontRotationTalon);
        rightFrontWheel.driveGearRatio(6.67);
        rightFrontWheel.wheelDiameterInches(4);
        rightFrontWheel.driveMaximumMetersPerSecond(4.11);
        rightFrontWheel.wheelLocationMeters(new Translation2d(0.267, 0.292));
        talonModules[1] = rightFrontWheel.build();

        var leftRearWheel = new TalonSwerveModule.Builder();
        leftRearWheel.driveTalon(leftRearDriveTalon);
        leftRearWheel.azimuthTalon(leftRearRotationTalon);
        leftRearWheel.driveGearRatio(6.67);
        leftRearWheel.wheelDiameterInches(4);
        leftRearWheel.driveMaximumMetersPerSecond(4.11);
        leftRearWheel.wheelLocationMeters(new Translation2d(-0.267, -0.292));
        talonModules[2] = leftRearWheel.build();

        var rightRearWheel = new TalonSwerveModule.Builder();
        rightRearWheel.driveTalon(rightRearDriveTalon);
        rightRearWheel.azimuthTalon(rightRearRotationTalon);
        rightRearWheel.driveGearRatio(6.67);
        rightRearWheel.wheelDiameterInches(4);
        rightRearWheel.driveMaximumMetersPerSecond(4.11);
        rightRearWheel.wheelLocationMeters(new Translation2d(0.267, -0.292));
        talonModules[3] = rightRearWheel.build();

        Gyro robotGyro = CustomGyroModule.getInstance();

        swerveDrive = new SwerveDrive(robotGyro, talonModules);
    }

    public void drive(double forward, double strafe, double azimuth, boolean isFieldOriented) {
        swerveDrive.drive(forward, strafe, azimuth, isFieldOriented);
    }
}

