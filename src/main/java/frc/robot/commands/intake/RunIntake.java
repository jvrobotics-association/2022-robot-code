package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;

public class RunIntake extends CommandBase {
    
    private final IntakeSubsystem INTAKE = RobotContainer.INTAKE;

    public RunIntake() {
        addRequirements(INTAKE);
    }

    @Override
    public void initialize() {
        INTAKE.runIntakeArm(0.5);
        INTAKE.runUpperIntake(0.5);
    }

    @Override
    public void end(boolean interupted) {
        INTAKE.stopIntakeArm();
        INTAKE.stopUpperIntake();
    }

}
