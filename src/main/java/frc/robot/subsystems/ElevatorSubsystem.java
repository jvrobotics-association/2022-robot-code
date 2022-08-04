package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase{
    
    private final Spark elevator = new Spark(Constants.ElevatorConstants.ELEVATOR_MOTOR);

    public ElevatorSubsystem() {}

    public void runElevator(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        elevator.set(percentOutput);
    }

    public void stopElevator() {
        elevator.stopMotor();
    }

    public double getElevatorPosition() {
        return elevator.get();
    }
}
