package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;

public class ReverseUpperIntake extends CommandBase {
    
    private final IntakeSubsystem INTAKE = RobotContainer.INTAKE;

    public ReverseUpperIntake() {
        addRequirements(INTAKE);
    }

    @Override
    public void initialize() {
        INTAKE.runUpperIntake(-0.5);
    }

    @Override
    public void end(boolean interupted) {
        INTAKE.stopUpperIntake();
    }

}
