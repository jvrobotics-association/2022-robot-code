package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase{
    
    private final CANSparkMax elevator = new CANSparkMax(Constants.ElevatorConstants.ELEVATOR_MOTOR, MotorType.kBrushless);

    public final double lowerEndPoint = 0.0;
    public final double upperEndPoint = 530.0;
    public final double acceptableError = 5.0;
    private double offset = 0.0;

    public ElevatorSubsystem() {
        elevator.setInverted(true);
    }

    public void zeroElevator() {
        offset = elevator.getEncoder().getPosition();
    }

    public void runElevator(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        elevator.set(percentOutput);
        SmartDashboard.putNumber("Elevator Position", getElevatorPosition());
    }

    public void stopElevator() {
        elevator.stopMotor();
        SmartDashboard.putNumber("Elevator Position", getElevatorPosition());
    }

    public double getElevatorPosition() {
        return elevator.getEncoder().getPosition() - offset;
    }
}
