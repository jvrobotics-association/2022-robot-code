package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LockElevator extends CommandBase {

    public LockElevator() {}

    @Override
    public void initialize() {
        ElevatorDown.setLock(true);
        ElevatorUp.setLock(true);
        AutoMoveElevator.setLock(true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        ElevatorDown.setLock(false);
        ElevatorUp.setLock(false);
        ElevatorDown.setLock(false);
    }
}
