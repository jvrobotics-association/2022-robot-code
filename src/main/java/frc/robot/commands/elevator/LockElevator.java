package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LockElevator extends CommandBase {

    public LockElevator() {}

    @Override
    public void initialize() {
        ElevatorDown.setLock(true);
        ElevatorUp.setLock(true);
        AutoMoveElevator.setLock(true);
        SmartDashboard.putBoolean("Elevator Lock", true);
        System.out.println("Locked");
    }

    @Override
    public void end(boolean interrupted) {
        ElevatorDown.setLock(false);
        ElevatorUp.setLock(false);
        AutoMoveElevator.setLock(false);
        SmartDashboard.putBoolean("Elevator Lock", false);
        System.out.println("Unlocked");
    }
}
