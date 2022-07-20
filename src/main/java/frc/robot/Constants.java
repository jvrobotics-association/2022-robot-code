// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;
import edu.wpi.first.math.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int TALON_CONFIG_TIMEOUT = 10;

    public static final class DriveConstants {
        public static final double DEADBAND_X_LOCK = 0.2;
        public static final double WHEEL_DIAMETER_INCHES = 4.0;
        public static final double MAX_SPEED_METERS_PER_SECOND = 3.5052;

        public static final double MAX_OMEGA =
                (MAX_SPEED_METERS_PER_SECOND / Math.hypot(0.8128 / 2.0, 0.6858 / 2.0))
                        / 2.0;

        // Configure the gear ratio for the drive modules
        static final double DRIVE_MOTOR_OUTPUT_GEAR = 40;
        static final double DRIVE_INPUT_GEAR = 12;
        static final double BEVEL_INPUT_GEAR = 40;
        static final double BEVEL_OUTPUT_GEAR = 20;
        public static final double DRIVE_GEAR_RATIO =
                (DRIVE_MOTOR_OUTPUT_GEAR / DRIVE_INPUT_GEAR) * (BEVEL_INPUT_GEAR / BEVEL_OUTPUT_GEAR);


        public static Translation2d[] getWheelLocationMeters() {
            final double x = 0.8128 / 2.0; // front-back
            final double y = 0.6858 / 2.0; // left-right
            Translation2d[] locs = new Translation2d[4];
            locs[0] = new Translation2d(x, y); // left front
            locs[1] = new Translation2d(x, -y); // right front
            locs[2] = new Translation2d(-x, y); // left rear
            locs[3] = new Translation2d(-x, -y); // right rear
            return locs;
        }

        public static TalonSRXConfiguration getAzimuthTalonConfig() {
            TalonSRXConfiguration azimuthConfig = new TalonSRXConfiguration();

            // azimuthConfig.primaryPID.selectedFeedbackCoefficient = 1.0;
            azimuthConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.Analog;
            azimuthConfig.auxiliaryPID.selectedFeedbackSensor = FeedbackDevice.None;

            azimuthConfig.forwardLimitSwitchSource = LimitSwitchSource.Deactivated;
            azimuthConfig.reverseLimitSwitchSource = LimitSwitchSource.Deactivated;

            azimuthConfig.continuousCurrentLimit = 10;
            azimuthConfig.peakCurrentDuration = 0;
            azimuthConfig.peakCurrentLimit = 0;
            azimuthConfig.slot0.kP = 10.0;
            azimuthConfig.slot0.kI = 0.0;
            azimuthConfig.slot0.kD = 0.0;
            azimuthConfig.slot0.kF = 0.0;
            azimuthConfig.slot0.integralZone = 0;
            azimuthConfig.slot0.allowableClosedloopError = 0;
            azimuthConfig.slot0.maxIntegralAccumulator = 0;
            azimuthConfig.motionCruiseVelocity = 800;
            azimuthConfig.motionAcceleration = 10_000;
            azimuthConfig.velocityMeasurementWindow = 64;
            azimuthConfig.voltageCompSaturation = 12;
            return azimuthConfig;
        }

        public static TalonFXConfiguration getDriveTalonConfig() {
            TalonFXConfiguration driveConfig = new TalonFXConfiguration();
            driveConfig.supplyCurrLimit.currentLimit = 0.04;
            driveConfig.supplyCurrLimit.triggerThresholdCurrent = 45;
            driveConfig.supplyCurrLimit.triggerThresholdTime = 40;
            driveConfig.supplyCurrLimit.enable = true;
            driveConfig.slot0.kP = 0.045;
            driveConfig.slot0.kI = 0.0005;
            driveConfig.slot0.kD = 0.000;
            driveConfig.slot0.kF = 0.047;
            driveConfig.slot0.integralZone = 500;
            driveConfig.slot0.maxIntegralAccumulator = 75_000;
            driveConfig.slot0.allowableClosedloopError = 0;
            driveConfig.velocityMeasurementPeriod = SensorVelocityMeasPeriod.Period_100Ms;
            driveConfig.velocityMeasurementWindow = 64;
            driveConfig.voltageCompSaturation = 12;
            return driveConfig;
        }

        public static final int LEFT_FRONT_DRIVE = 1;
        public static final int LEFT_FRONT_ROTATION = 2;

        public static final int RIGHT_FRONT_DRIVE = 3;
        public static final int RIGHT_FRONT_ROTATION = 4;

        public static final int LEFT_REAR_DRIVE = 5;
        public static final int LEFT_REAR_ROTATION = 6;

        public static final int RIGHT_REAR_DRIVE = 7;
        public static final int RIGHT_REAR_ROTATION = 8;
    }

    public static final class ElevatorConstants {
        public static final int ELEVATOR_MOTOR = 9;
    }

    public static final class ShooterConstants {
        public static final int INTAKE_MOTOR = 10;
        public static final int SECONDARY_INTAKE_MOTOR = 11;
        public static final int INDEXER_MOTOR = 12;
        public static final int SHOOTER_MOTOR = 13;
    }
}
