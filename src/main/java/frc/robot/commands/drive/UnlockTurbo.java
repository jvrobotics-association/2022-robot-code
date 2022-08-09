package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class UnlockTurbo extends CommandBase {
    
    private final DriveSubsystem DRIVE = RobotContainer.DRIVE;

    public UnlockTurbo() {}

    @Override
    public void initialize() {
        DRIVE.unlockTurbo(true);
    }

    @Override
    public void end(boolean interupted) {
        DRIVE.unlockTurbo(false);
    }
    
}
