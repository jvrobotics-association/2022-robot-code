package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorDown extends CommandBase {
    
    private final ElevatorSubsystem ELEVATOR = RobotContainer.ELEVATOR;

    private static boolean locked = true;
    private static boolean bounds = false;

    public ElevatorDown() {
        addRequirements(ELEVATOR);
    }

    public static void setLock(boolean isLocked) {
        locked = isLocked;
    }

    public static void unlockBounds(boolean unlock) {
        bounds = !unlock;
    }

    @Override
    public void initialize() {
        if (locked || (bounds && ELEVATOR.getElevatorPosition() < ELEVATOR.lowerEndPoint)) return;
        ELEVATOR.runElevator(-0.5);
    }

    @Override
    public boolean isFinished() {
        return bounds && ELEVATOR.getElevatorPosition() < ELEVATOR.lowerEndPoint;
    }

    @Override
    public void end(boolean interrupted) {
        ELEVATOR.stopElevator();
    }
}