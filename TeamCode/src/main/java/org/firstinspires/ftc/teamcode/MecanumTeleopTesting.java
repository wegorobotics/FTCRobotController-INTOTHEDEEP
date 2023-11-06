package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp (name= "MecanumTeleTesting")
//@Disabled
public class MecanumTeleopTesting extends OpMode {
    // motors
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;


    @Override
    public void init() {


        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_motor");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_motor");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_motor");


        fr_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        br_Wheel.setDirection(DcMotor.Direction.REVERSE);
        bl_Wheel.setDirection(DcMotor.Direction.REVERSE);


        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void loop() {
        //testing a change for github
        // encoders
        double fl_Position = fl_Wheel.getCurrentPosition();
        double bl_Position = bl_Wheel.getCurrentPosition();
        double fr_Position = fr_Wheel.getCurrentPosition();
        double br_Position = br_Wheel.getCurrentPosition();

        telemetry.addData("Front Left Wheel Pos", fl_Position);
        telemetry.addData("Back Left Wheel Pos", bl_Position);
        telemetry.addData("Front Right Wheel Pos", fr_Position);
        telemetry.addData("Back Right Wheel Pos", br_Position);
        telemetry.update();

        // movement
        double y = gamepad1.right_stick_y;
        double x = gamepad1.right_stick_x;
        double rx = gamepad1.left_stick_x; // rotation


        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.4);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;


        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        boolean rightstrafe = gamepad1.right_bumper;
        boolean leftstrafe = gamepad1.left_bumper;


        if (rightstrafe) {
            fl_Wheel.setPower(0.5);
            fr_Wheel.setPower(-0.5);
            bl_Wheel.setPower(-0.5);
            br_Wheel.setPower(0.5);


        } else if (leftstrafe) {
            fl_Wheel.setPower(-0.5);
            fr_Wheel.setPower(0.5);
            bl_Wheel.setPower(0.5);
            br_Wheel.setPower(-0.5);
        } else {
            fl_Wheel.setPower(frontLeftPower);
            bl_Wheel.setPower(backLeftPower);
            fr_Wheel.setPower(frontRightPower);
            br_Wheel.setPower(backRightPower);
            // strafepower levels changed from 1 to 0.5 by Asher in an attempt to adreess problem
            //with wheel spin speed being out of sych. Untested.


            fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }
    }
}

