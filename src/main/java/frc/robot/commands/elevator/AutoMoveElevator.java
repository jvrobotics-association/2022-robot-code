package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class AutoMoveElevator extends CommandBase {
    private final ElevatorSubsystem ELEVATOR = RobotContainer.ELEVATOR;

    private static boolean locked = true;

    // used to determine direction
    private boolean isGoingDown;

    public AutoMoveElevator() {
        addRequirements(ELEVATOR);
    }

    public static void setLock(boolean isLocked) {
        locked = isLocked;
    }

    @Override
    public void initialize() {
        if (locked) return;
        double elevatorPos = ELEVATOR.getElevatorPosition();

        // if extended go down
        isGoingDown = (elevatorPos > ELEVATOR.lowerEndPoint + ELEVATOR.acceptableError);
        
        ELEVATOR.runElevator(isGoingDown ? -1.0 : 1.0);
    }

    @Override
    public boolean isFinished() {
        if (locked) return true;
        double elevatorPos = ELEVATOR.getElevatorPosition();

        if (isGoingDown) {
            return (elevatorPos - ELEVATOR.lowerEndPoint < ELEVATOR.acceptableError);
        } else {
            return (ELEVATOR.upperEndPoint - elevatorPos < ELEVATOR.acceptableError);
        }
    }

    @Override
    public void end(boolean interrupted) {
        ELEVATOR.stopElevator();
    }
}
