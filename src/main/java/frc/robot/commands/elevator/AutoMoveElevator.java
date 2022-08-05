package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class AutoMoveElevator extends CommandBase {
    private final ElevatorSubsystem ELEVATOR = RobotContainer.ELEVATOR;

    private final double lowerEndPoint = 0.0;
    private final double upperEndPoint = 530.0;
    private final double acceptableError = 5.0;

    // used to determine direction
    private boolean isGoingDown;

    public AutoMoveElevator() {
        addRequirements(ELEVATOR);
    }

    // TODO: implement locking switch

    @Override
    public void initialize() {
        double elevatorPos = ELEVATOR.getElevatorPosition();

        // if extended go down
        isGoingDown = (elevatorPos > lowerEndPoint + acceptableError);
        
        ELEVATOR.runElevator(isGoingDown ? -1.0 : 1.0);
    }

    @Override
    public boolean isFinished() {
        double elevatorPos = ELEVATOR.getElevatorPosition();

        if (isGoingDown) {
            return (elevatorPos - lowerEndPoint < acceptableError);
        } else {
            return (upperEndPoint - elevatorPos < acceptableError);
        }
    }

    @Override
    public void end(boolean interrupted) {
        ELEVATOR.stopElevator();
    }
}
