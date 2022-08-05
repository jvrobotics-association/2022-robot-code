package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class UnlockManualElevatorBounds extends CommandBase {

    public UnlockManualElevatorBounds() {}

    @Override
    public void initialize() {
        ElevatorDown.unlockBounds(true);
        ElevatorUp.unlockBounds(true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        ElevatorDown.unlockBounds(false);
        ElevatorUp.unlockBounds(false);
    }
    
}
