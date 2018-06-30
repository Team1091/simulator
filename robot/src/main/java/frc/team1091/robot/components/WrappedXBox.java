package frc.team1091.robot.components;

import com.team1091.shared.components.IGameController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class WrappedXBox implements IGameController {

    // wrap the existing xbox controller
    private XboxController xbox;

    public WrappedXBox(int port) {
        xbox = new XboxController(port);
    }

    @Override
    public double getLeftX() {
        return xbox.getX(GenericHID.Hand.kLeft);
    }

    @Override
    public double getLeftY() {
        return xbox.getY(GenericHID.Hand.kLeft);
    }


    @Override
    public double getRightX() {
        return xbox.getX(GenericHID.Hand.kRight);
    }

    @Override
    public double getRightY() {
        return xbox.getY(GenericHID.Hand.kRight);
    }


    @Override
    public boolean pressedY() {
        return xbox.getYButton();
    }

    @Override
    public boolean pressedX() {
        return xbox.getXButton();
    }

    @Override
    public boolean pressedB() {
        return xbox.getBButton();
    }

    @Override
    public boolean pressedA() {
        return xbox.getAButton();
    }


}
