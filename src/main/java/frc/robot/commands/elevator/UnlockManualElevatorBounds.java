package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class UnlockManualElevatorBounds extends CommandBase {

    public UnlockManualElevatorBounds() {}

    @Override
    public void initialize() {
        ElevatorDown.unlockBounds(true);
        ElevatorUp.unlockBounds(true);
        SmartDashboard.putBoolean("Manual Elevator Lock", false);
    }

    @Override
    public void end(boolean interrupted) {
        ElevatorDown.unlockBounds(false);
        ElevatorUp.unlockBounds(false);
        SmartDashboard.putBoolean("Manual Elevator Lock", true);
    }
    
}
