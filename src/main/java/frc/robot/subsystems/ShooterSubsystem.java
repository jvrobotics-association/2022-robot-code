package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax flywheel = new CANSparkMax(Constants.ShooterConstants.SHOOTER_MOTOR, MotorType.kBrushless);
    private final TalonSRX indexer = new TalonSRX(Constants.ShooterConstants.INDEXER_MOTOR);

    public ShooterSubsystem() {}

    public void runShooter(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        flywheel.set(percentOutput);
    }

    public void runIndexer(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        indexer.set(ControlMode.PercentOutput, percentOutput);
    }

    public void stopShooter() {
        flywheel.stopMotor();
    }

    public void stopIndexer() {
        indexer.set(ControlMode.PercentOutput, 0.0);
    }
}
