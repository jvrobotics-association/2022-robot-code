package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.strykeforce.swerve.SwerveDrive;
import frc.robot.Constants.DriveConstants;

import static frc.robot.Constants.TALON_CONFIG_TIMEOUT;

public class DriveSubsystem extends SubsystemBase {
    private final SwerveDrive swerveDrive;

    // values are in percent of full speed
    private final double speedLimiter = 0.40;
    private final double rotationSpeedLimiter = 0.50;

    private boolean turboLocked = true;

    public DriveSubsystem() {
        var moduleBuilder =
                new CustomSwerveModule.Builder()
                        .driveGearRatio(DriveConstants.DRIVE_GEAR_RATIO)
                        .wheelDiameterInches(DriveConstants.WHEEL_DIAMETER_INCHES)
                        .driveMaximumMetersPerSecond(DriveConstants.MAX_SPEED_METERS_PER_SECOND);

        CustomSwerveModule[] swerveModules = new CustomSwerveModule[4];
        Translation2d[] wheelLocations = DriveConstants.getWheelLocationMeters();


        var leftFrontDrive = new TalonFX(DriveConstants.LEFT_FRONT_DRIVE);
        leftFrontDrive.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        leftFrontDrive.configAllSettings(DriveConstants.getDriveTalonConfig(), TALON_CONFIG_TIMEOUT);
        leftFrontDrive.enableVoltageCompensation(true);
        leftFrontDrive.setNeutralMode(NeutralMode.Brake);

        var leftFrontRotation = new TalonSRX(DriveConstants.LEFT_FRONT_ROTATION);
        leftFrontRotation.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        leftFrontRotation.configAllSettings(DriveConstants.getAzimuthTalonConfig(), TALON_CONFIG_TIMEOUT);
        leftFrontRotation.enableCurrentLimit(true);
        leftFrontRotation.enableVoltageCompensation(true);
        leftFrontRotation.setNeutralMode(NeutralMode.Coast);

        var rightFrontDrive = new TalonFX(DriveConstants.RIGHT_FRONT_DRIVE);
        rightFrontDrive.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        rightFrontDrive.configAllSettings(DriveConstants.getDriveTalonConfig(), TALON_CONFIG_TIMEOUT);
        rightFrontDrive.enableVoltageCompensation(true);
        rightFrontDrive.setNeutralMode(NeutralMode.Brake);

        var rightFrontRotation = new TalonSRX(DriveConstants.RIGHT_FRONT_ROTATION);
        rightFrontRotation.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        rightFrontRotation.configAllSettings(DriveConstants.getAzimuthTalonConfig(), TALON_CONFIG_TIMEOUT);
        rightFrontRotation.enableCurrentLimit(true);
        rightFrontRotation.enableVoltageCompensation(true);
        rightFrontRotation.setNeutralMode(NeutralMode.Coast);

        var leftRearDrive = new TalonFX(DriveConstants.LEFT_REAR_DRIVE);
        leftRearDrive.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        leftRearDrive.configAllSettings(DriveConstants.getDriveTalonConfig(), TALON_CONFIG_TIMEOUT);
        leftRearDrive.enableVoltageCompensation(true);
        leftRearDrive.setNeutralMode(NeutralMode.Brake);

        var leftRearRotation = new TalonSRX(DriveConstants.LEFT_REAR_ROTATION);
        leftRearRotation.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        leftRearRotation.configAllSettings(DriveConstants.getAzimuthTalonConfig(), TALON_CONFIG_TIMEOUT);
        leftRearRotation.enableCurrentLimit(true);
        leftRearRotation.enableVoltageCompensation(true);
        leftRearRotation.setNeutralMode(NeutralMode.Coast);

        var rightRearDrive = new TalonFX(DriveConstants.RIGHT_REAR_DRIVE);
        rightRearDrive.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        rightRearDrive.configAllSettings(DriveConstants.getDriveTalonConfig(), TALON_CONFIG_TIMEOUT);
        rightRearDrive.enableVoltageCompensation(true);
        rightRearDrive.setNeutralMode(NeutralMode.Brake);

        var rightRearRotation = new TalonSRX(DriveConstants.RIGHT_REAR_ROTATION);
        rightRearRotation.configFactoryDefault(TALON_CONFIG_TIMEOUT);
        rightRearRotation.configAllSettings(DriveConstants.getAzimuthTalonConfig(), TALON_CONFIG_TIMEOUT);
        rightRearRotation.enableCurrentLimit(true);
        rightRearRotation.enableVoltageCompensation(true);
        rightRearRotation.setNeutralMode(NeutralMode.Coast);

        swerveModules[0] =
                moduleBuilder
                        .azimuthTalon(leftFrontRotation)
                        .driveTalon(leftFrontDrive)
                        .wheelLocationMeters(wheelLocations[0])
                        .build();

        swerveModules[1] =
                moduleBuilder
                        .azimuthTalon(rightFrontRotation)
                        .driveTalon(rightFrontDrive)
                        .wheelLocationMeters(wheelLocations[1])
                        .build();
        swerveModules[2] =
                moduleBuilder
                        .azimuthTalon(leftRearRotation)
                        .driveTalon(leftRearDrive)
                        .wheelLocationMeters(wheelLocations[2])
                        .build();

        swerveModules[3] =
                moduleBuilder
                        .azimuthTalon(rightRearRotation)
                        .driveTalon(rightRearDrive)
                        .wheelLocationMeters(wheelLocations[3])
                        .build();
                
        
        swerveModules[0].loadAndSetAzimuthZeroReference();
        swerveModules[1].loadAndSetAzimuthZeroReference();
        swerveModules[2].loadAndSetAzimuthZeroReference();
        swerveModules[3].loadAndSetAzimuthZeroReference();

        swerveDrive = new SwerveDrive(swerveModules);
    }

    /** Perform periodic swerve drive odometry update. */
    @Override
    public void periodic() {
        swerveDrive.periodic();
    }

    /** Drive the robot with given x, y, and rotational velocities with open-loop velocity control. */
    public void drive(
            double vxMetersPerSecond, double vyMetersPerSecond, double omegaRadiansPerSecond, boolean isFieldOriented) {
        swerveDrive.drive(vxMetersPerSecond * (turboLocked ? speedLimiter : 1), vyMetersPerSecond * (turboLocked ? speedLimiter : 1), omegaRadiansPerSecond * (turboLocked ? rotationSpeedLimiter : 1), isFieldOriented);
    }

    public void resetGyro() {
        swerveDrive.resetGyro();
    }

    public void unlockTurbo(boolean lock) {
        turboLocked = !lock;
    }
}
