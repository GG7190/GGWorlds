package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.GGHardware;
import org.firstinspires.ftc.teamcode.GGParameters;
/**
 * Created by User on 10/30/2017.
 */

@TeleOp(name="AutonomousRecord",group="TeleOp")
public class AutonomousRecord extends LinearOpMode
{


    GGHardware robot = new GGHardware();
    private ElapsedTime  runtime= new ElapsedTime();


    @Override
    public void runOpMode()
    {

        GGParameters parameters = new GGParameters(this);
        robot.init(parameters);
        robot.resetEncoders();
        waitForStart();

        double encoderAv =  0;

        while (opModeIsActive())
        {

            robot.backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            int position = robot.backleft.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            telemetry.update();


            ///////////////////////////////////////
            ///                                 ///
            /// Equation that assigns power and ///
            /// direction of wheels based on    ///
            /// joystick position               ///
            ///                                 ///
            ///////////////////////////////////////
            robot.getJoyVals();

            robot.pwr = robot.y; //this can be tweaked for exponential power increase

            robot.frontright.setPower(Range.clip(robot.pwr - robot.x + robot.z, -1, 1));
            robot.backleft.setPower(Range.clip(robot.pwr - robot.x - robot.z, -1, 1));
            robot.frontleft.setPower(Range.clip(robot.pwr + robot.x - robot.z, -1, 1));
            robot.backright.setPower(Range.clip(robot.pwr + robot.x + robot.z, -1, 1));

            /////////////////////////
            ///                   ///
            ///  record encoder   ///
            ///  and reset w/ b   ///
            ///                   ///
            /////////////////////////
            /*encoderAv = (robot.frontleft.getCurrentPosition() + robot.frontright.getCurrentPosition()
                    + robot.backleft.getCurrentPosition() + robot.backright.getCurrentPosition()) /4;
            int newInches = robot.getEncoderPulses((int) encoderAv);

            telemetry.addData("encoder value: ", newInches);
            telemetry.update();*/

            if (gamepad1.b)
            {
                //newInches = 0;
                robot.backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            /////////////////////////
            ///                   ///
            ///   intake motors   ///
            ///                   ///
            /////////////////////////
            if (gamepad2.left_bumper)
            {
                robot.intakel.setPower(-0.75);
                robot.intaker.setPower(0.75);
            }

            if (gamepad2.right_bumper)
            {
                robot.intakel.setPower(0.75);
                robot.intaker.setPower(-0.75);
            }

            else if(gamepad2.right_trigger > 0.2 && gamepad2.right_bumper)
            {
                robot.intakel.setPower(0.5);
                robot.intaker.setPower(-1);
            }

            else
            {
                robot.intakel.setPower(0);
                robot.intaker.setPower(0);
                robot.intakel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                robot.intaker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }

            //////////////////
            ////         /////
            //// flipper /////
            ////         /////
            //////////////////
            if (gamepad2.y)
            {
                robot.flipper.setPower(0.4);
            }

            else if (gamepad2.a)
            {
                robot.flipper.setPower(-0.25);
            }

            else
            {
                robot.flipper.setPower(0);
                robot.relicLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }


            ///////////////////
            ///             ///
            /// relic lift  ///
            ///             ///
            ///////////////////
            if(gamepad2.x)
            {
                robot.relicLift.setPower(-1);

            }

            else if(gamepad2.b)
            {
                robot.relicLift.setPower(1);

            }
            else
            {
                robot.relicLift.setPower(0);
                robot.relicLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            ///////////////////////
            ///                 ///
            ///relic wrist joint///
            ///                 ///
            ///////////////////////

            //relic wrist down
            if(gamepad2.dpad_down)
            {
                robot.relicUpDown.setPosition(robot.RELICUPDOWN_MAX_RANGE);
            }

            //color arm middle
            if(gamepad2.dpad_up)
            {
                robot.relicUpDown.setPosition(robot.RELICUPDOWN_MIN_RANGE);
            }


            ///////////////
            ///         ///
            ///  pivot  ///
            ///         ///
            //////////////
            if(gamepad1.b)
            {
                //robot.pivot.setPosition(robot.PIVOT_MAX_RANGE);
                //robot.spin.setPosition(robot.SPIN_MID_RANGE);
            }

            //////////////////
            ///            ///
            /// relic claw ///
            ///            ///
            //////////////////
            if(gamepad1.dpad_left)
            {
                robot.relicClaw.setPosition(robot.RELICCLAW_MIN_RANGE);
            }
            if(gamepad1.dpad_right)
            {
                robot.relicClaw.setPosition(robot.RELICCLAW_MAX_RANGE);
            }

        }
    }


}
