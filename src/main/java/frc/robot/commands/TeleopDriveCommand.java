package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class TeleopDriveCommand extends CommandBase {
    private static final double DEADBAND = 0.05;
    private final DoubleSupplier forward;
    private final DoubleSupplier strafe;
    private final DoubleSupplier yaw;
    private final BooleanSupplier isFieldOriented;

    private final DriveSubsystem driveSubsystem = DriveSubsystem.getInstance();

    public TeleopDriveCommand(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier yaw, BooleanSupplier isFieldOriented) {
        this.forward = forward;
        this.strafe = strafe;
        this.yaw = yaw;
        this.isFieldOriented = isFieldOriented;
        addRequirements(this.driveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double forward = deadband(this.forward.getAsDouble());
        double strafe = deadband(this.strafe.getAsDouble());
        double yaw = deadband(this.yaw.getAsDouble());

        driveSubsystem.drive(forward, strafe, yaw, isFieldOriented.getAsBoolean());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(0.0, 0.0, 0.0, false);
    }

    private double deadband(double value) {
        if (Math.abs(value) < DEADBAND) return 0.0;
        return value;
      }
}