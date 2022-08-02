package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase{
    
    private final TalonSRX elevator = new TalonSRX(Constants.ElevatorConstants.ELEVATOR_MOTOR);

    public ElevatorSubsystem() {}

    public void runElevator(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        elevator.set(ControlMode.PercentOutput, percentOutput);
    }

    public void stopElevator() {
        elevator.set(ControlMode.PercentOutput, 0.0);
    }

    public double getElevatorPosition() {
        return elevator.getSelectedSensorPosition();
    }
}
