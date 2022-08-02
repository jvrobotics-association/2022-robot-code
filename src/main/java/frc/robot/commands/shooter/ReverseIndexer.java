package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ReverseIndexer extends CommandBase {

    private final ShooterSubsystem SHOOTER = RobotContainer.SHOOTER;

    public ReverseIndexer() {
        addRequirements(SHOOTER);
    }

    @Override
    public void initialize() {
        SHOOTER.runIndexer(-0.7);
    }

    @Override
    public void end(boolean interupted) {
        SHOOTER.stopIndexer();
    }
}