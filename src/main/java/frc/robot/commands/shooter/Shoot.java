package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends CommandBase {
    
    private final ShooterSubsystem SHOOTER = RobotContainer.SHOOTER;

    private double revTime = 0.5;
    private double startTime;

    public Shoot() {
        addRequirements(SHOOTER);
    }

    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        double currentTime = Timer.getFPGATimestamp();
        if (currentTime > startTime + revTime) {
            SHOOTER.runIndexer(0.4);
        }
        SHOOTER.runShooter(1.0);
    }

    @Override
    public void end(boolean interupted) {
        SHOOTER.stopIndexer();
        SHOOTER.stopShooter();
    }
    
}
