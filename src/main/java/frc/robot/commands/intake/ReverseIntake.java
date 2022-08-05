package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;

public class ReverseIntake extends CommandBase {
    
    private final IntakeSubsystem INTAKE = RobotContainer.INTAKE;

    public ReverseIntake() {
        addRequirements(INTAKE);
    }

    @Override
    public void initialize() {
        System.out.println("Initializing");
        INTAKE.runIntakeArm(-0.5);
    }

    @Override
    public void execute() {
        System.out.println("Executing");
        INTAKE.runIntakeArm(-0.5);
    }

    @Override
    public void end(boolean interupted) {
        INTAKE.stopIntakeArm();
    }
}
