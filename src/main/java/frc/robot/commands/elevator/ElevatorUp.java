package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorUp extends CommandBase {
    
    private final ElevatorSubsystem ELEVATOR = RobotContainer.ELEVATOR;

    public ElevatorUp() {
        addRequirements(ELEVATOR);
    }

    @Override
    public void initialize() {
        ELEVATOR.runElevator(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        ELEVATOR.stopElevator();
    }
}
