package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final TalonSRX flywheel = new TalonSRX(Constants.ShooterConstants.SHOOTER_MOTOR);
    private final VictorSPX indexer = new VictorSPX(Constants.ShooterConstants.INDEXER_MOTOR);

    public ShooterSubsystem() {}

    public void runShooter(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        flywheel.set(ControlMode.PercentOutput, percentOutput);
    }

    public void runIndexer(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        indexer.set(ControlMode.PercentOutput, percentOutput);
    }

    public void stopShooter() {
        flywheel.set(ControlMode.PercentOutput, 0.0);
    }

    public void stopIndexer() {
        indexer.set(ControlMode.PercentOutput, 0.0);
    }
}
