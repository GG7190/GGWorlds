package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by User on 10/29/2017.
 */

public class GGHardware {
    GGParameters _parameters = null;
    /* Public OP Mode Members*/
    public DcMotor motor1 = null;

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    /* Local OP Mode Members*/
    HardwareMap hwMap = null;
    public LinearOpMode BaseOpMode = null;
    private ElapsedTime runtime = new ElapsedTime();

    public final double BOTTOMRCLAW_CLOSE = 0.0;
    public final double BOTTOMRCLAW_OPEN = 0.5;
    public final double TOPRCLAW_CLOSE = 0.04;
    public final double TOPRCLAW_OPEN = 0.7;
    public final double BOTTOMLCLAW_OPEN = 0.25;
    public static double BOTTOMLCLAW_CLOSE = 0.95;
    public static double TOPLCLAW_CLOSE = 1;
    public static double TOPLCLAW_OPEN = 0.3;
    public static double LEFT_CLAW_MID = 0.90;
    public static double RIGHT_CLAW_MID = 0.20;
    public static double BOTTOM_LEFT_MID = 0.70;
    public static double JEWELUPDOWN_MAX_RANGE = .75;
    public static double JEWELUPDOWN_MID_RANGE = .5;
    public static double JEWELUPDOWN_MIN_RANGE = .21;
    public static double JEWELSIDE_MAX_RANGE = .5;
    public static double JEWELSIDE_MID_RANGE = .35;
    public static double JEWELSIDE_MIN_RANGE = .1;
    public final double RELICUPDOWN_MIN_RANGE = 0.01;
    public final double RELICUPDOWN_MID_RANGE = 0.30;
    public final double RELICUPDOWN_MAX_RANGE = 0.90;
    public final double RELICCLAW_MIN_RANGE = 0.01;
    public final double RELICCLAW_MID_RANGE = 0.30;
    public final double RELICCLAW_MAX_RANGE = 0.9;
    public final double SPIN_MIN_RANGE = 0.15;
    public final double SPIN_MID_RANGE = 0.30;
    public final double SPIN_MAX_RANGE = 0.90;
    public final double FLIPPERL_MIN_RANGE = 1.00;
    public final double FLIPPERL_MID_RANGE = 0.50;
    public final double FLIPPERL_MAX_RANGE = 0.00;
    public final double FLIPPERR_MIN_RANGE = 0.00;
    public final double FLIPPERR_MID_RANGE = 0.50;
    public final double FLIPPERR_MAX_RANGE = 1.00;

    public DcMotor frontleft, frontright, backleft, backright, intakel, intaker, relicLift, flipper;
    public Servo jewelUpDown, jewelSide, relicClaw, relicUpDown;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    public static double deadzone2 = 0.1;

    /* Constructor*s/
    public GGHardware()
    {


    }

    /*Initialize Standard Hardware Interfaces*/
    public void init(GGParameters parameters /*HardwareMap ahwMap*/) {
        _parameters = parameters;
        hwMap = parameters.BaseOpMode.hardwareMap;/*ahwMap*/
        BaseOpMode = parameters.BaseOpMode;

        frontleft = hwMap.get(DcMotor.class, "fleft");
        BaseOpMode.telemetry.addData("fleft", 0);
        BaseOpMode.telemetry.update();

        frontright = hwMap.get(DcMotor.class, "fright");
        BaseOpMode.telemetry.addData("fright", 0);
        BaseOpMode.telemetry.update();

        backleft = hwMap.get(DcMotor.class, "bleft");
        BaseOpMode.telemetry.addData("bleft", 0);
        BaseOpMode.telemetry.update();

        backright = hwMap.get(DcMotor.class, "bright");
        BaseOpMode.telemetry.addData("bright", 0);
        BaseOpMode.telemetry.update();

        intakel = hwMap.get(DcMotor.class, "intakel");
        BaseOpMode.telemetry.addData("intakel", 0);
        BaseOpMode.telemetry.update();

        intaker = hwMap.get(DcMotor.class, "intaker");
        BaseOpMode.telemetry.addData("intaker", 0);
        BaseOpMode.telemetry.update();

        flipper = hwMap.get(DcMotor.class, "flipper");
        BaseOpMode.telemetry.addData("flipper", 0);
        BaseOpMode.telemetry.update();

        relicClaw = hwMap.get(Servo.class, "relicClaw");
        BaseOpMode.telemetry.addData("relicClaw", 0);
        BaseOpMode.telemetry.update();

        relicUpDown = hwMap.get(Servo.class, "relicUpDown");
        BaseOpMode.telemetry.addData("relicUpDown", 0);
        BaseOpMode.telemetry.update();

        relicLift = hwMap.get(DcMotor.class, "relicLift");
        BaseOpMode.telemetry.addData("relicLift", 0);
        BaseOpMode.telemetry.update();

        jewelUpDown = hwMap.get(Servo.class, "jewelUpDown");
        BaseOpMode.telemetry.addData("JewelUpDown", 0);
        BaseOpMode.telemetry.update();

        jewelSide = hwMap.get(Servo.class, "jewelSide");


        // get a reference to the color sensor.
        //sensorColor = hwMap.get(ColorSensor.class, "sensor_color_distance");

        // get a reference to the distance sensor that shares the same name.
        //sensorDistance = hwMap.get(DistanceSensor.class, "sensor_color_distance");

        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);



    }


    public void forwBakw(double motorPwr) {
        frontright.setPower(motorPwr);
        frontleft.setPower(motorPwr);
        backright.setPower(motorPwr);
        backleft.setPower(motorPwr);
    }


    public void driftRight() {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(-1);
        backleft.setPower(1);
    }

    public void driftLeft() {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(1);
        backleft.setPower(-1);
    }


    public void turnRight() {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(1);
        backleft.setPower(-1);
    }

    public void turnLeft() {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(-1);
        backleft.setPower(1);
    }


    public void getJoyVals() {
        float pwrFactor = (float) 1;
        float pwrFactorY = (float) 0.5;
        y = pwrFactorY * _parameters.BaseOpMode.gamepad1.right_stick_y; // joyval = -1 to 1; forward right joy 0 = stop -1 = reverse 1 = forward
        x = pwrFactor * _parameters.BaseOpMode.gamepad1.right_stick_x; //-1 to 1
        z = pwrFactor * _parameters.BaseOpMode.gamepad1.left_stick_x;  //-1 to 1
        w = pwrFactor * _parameters.BaseOpMode.gamepad1.left_stick_y;  //-1 to 1
        //updates joystick values

        if (Math.abs(x) < deadzone * 2.5) x = 0;
        if (Math.abs(y) < deadzone2) y = 0;
        if (Math.abs(z) < deadzone) z = 0;
        if (Math.abs(w) < 0.9) w = 0;
        //checks deadzones
    }

    //NeverRest 40's = 1062 PPR
    //NeverRest 20's = 562
    final int PPR = 562;
    final int wheelDiameter = 4;

    public int getEncoderPulses(int inches) {
        int ppi = (int) (PPR / (wheelDiameter * Math.PI));
        ppi = (ppi * inches);
        return ppi;
    }


    public void DriveMotorUsingEncoder(double speed, int targetDistance, double timeoutSeconds, int direction) {
        // Ensure that the opmode is still active
        if (_parameters.BaseOpMode.opModeIsActive()) {
            _parameters.BaseOpMode.telemetry.addData("Im Running", backleft.getCurrentPosition());
            _parameters.BaseOpMode.telemetry.update();

            //Set Target Position
            targetDistance = getEncoderPulses(targetDistance);
            //Reset Encoders
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            // Turn On Run Without Encoders
            backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            // reset the timeout time and start motion.WITHOUT
            runtime.reset();
            if (direction == 0)
            {
                forwBakw(-speed);
            }
            else if (direction == 1)
            {
                forwBakw(speed);
            }
            else if (direction == 2)
            {
                turnRight();
            }
            else if (direction == 3)
            {
                turnLeft();
            }
            else if (direction == 4)
            {
                driftRight();
            }
            else if (direction == 5)
            {
                driftLeft();
            }
            else
            {
                forwBakw(0);
            }


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (//_parameters.BaseOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutSeconds) &&
                            (Math.abs(backleft.getCurrentPosition()) < targetDistance)
                    ) {
                // Display it for the driver.
                _parameters.BaseOpMode.telemetry.addData("Path1", "Running to %7d :%7d", targetDistance, backleft.getCurrentPosition());
                _parameters.BaseOpMode.telemetry.update();
            }

            // Stop all motion;
            forwBakw(0);
            //Reset Encoders
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Set to Run Without Encoders
            backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void resetEncoders() {
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


}


