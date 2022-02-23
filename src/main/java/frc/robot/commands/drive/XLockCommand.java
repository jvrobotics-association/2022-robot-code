package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.DriverControls;
import frc.robot.subsystems.DriveSubsystem;

public class XLockCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem = RobotContainer.DRIVE;
    private DriverControls controls;

    public XLockCommand() {
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        controls = RobotContainer.CONTROLS.getDriverControls();
        driveSubsystem.xLockSwerveDrive();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(controls.getYaw()) >= Constants.DriveConstants.DEADBAND_X_LOCK
                || Math.abs(controls.getForward()) >= Constants.DriveConstants.DEADBAND_X_LOCK
                || Math.abs(controls.getStrafe()) >= Constants.DriveConstants.DEADBAND_X_LOCK;
    }
}