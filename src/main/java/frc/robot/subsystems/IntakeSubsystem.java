package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private final VictorSPX intakeArm =  new VictorSPX(Constants.ShooterConstants.INTAKE_MOTOR);
    private final VictorSPX upperIntake = new VictorSPX(Constants.ShooterConstants.SECONDARY_INTAKE_MOTOR);

    public IntakeSubsystem() {}

    public void runIntakeArm(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        intakeArm.set(ControlMode.PercentOutput, percentOutput);
    }

    public void runUpperIntake(double percentOutput) {
        if (percentOutput > 1.0) percentOutput = 1.0;
        if (percentOutput < -1.0) percentOutput = -1.0;
        upperIntake.set(ControlMode.PercentOutput, percentOutput);
    }

    public void stopIntakeArm() {
        intakeArm.set(ControlMode.PercentOutput, 0.0);
    }

    public void stopUpperIntake() {
        upperIntake.set(ControlMode.PercentOutput, 0.0);
    }
}
