package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class CalibrateElevator extends CommandBase {

    private final ElevatorSubsystem ELEVATOR = RobotContainer.ELEVATOR;

    public CalibrateElevator() {}

    @Override
    public void initialize() {
        ELEVATOR.zeroElevator();
    }
    
}
