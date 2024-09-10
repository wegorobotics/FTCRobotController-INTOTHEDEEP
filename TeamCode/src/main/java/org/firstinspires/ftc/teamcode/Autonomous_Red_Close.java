//autoblueclose


package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name= "Autonomous_Red_Close")
//@Disabled
public class Autonomous_Red_Close extends LinearOpMode {
    // names
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;
    DcMotor arm_motorLeft;
    DcMotor arm_motorRight;
    Servo wrist_servo;
    CRServo claw_servo;
    //DcMotor drone_motor;




    @Override
    public void runOpMode() {
        // definitions
        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_motor");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_motor");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_motor");
        arm_motorLeft = hardwareMap.get(DcMotor.class, "arm_motorLeft");
        arm_motorRight = hardwareMap.get(DcMotor.class, "arm_motorRight");
        wrist_servo = hardwareMap.get(Servo.class, "wrist_servo");
        claw_servo = hardwareMap.get(CRServo.class, "claw_servo");
        //drone_motor = hardwareMap.get(DcMotor.class, "drone_motor");




        fr_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fl_Wheel.setDirection(DcMotor.Direction.REVERSE);
        br_Wheel.setDirection(DcMotor.Direction.REVERSE);
        bl_Wheel.setDirection(DcMotor.Direction.REVERSE);
        arm_motorLeft.setDirection(DcMotor.Direction.FORWARD);
        arm_motorRight.setDirection(DcMotor.Direction.REVERSE);
        //drone_motor.setDirection(DcMotor.Direction.FORWARD);




        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm_motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm_motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //drone_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


       /*
       fr_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       fl_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       br_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       bl_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       */


        waitForStart();


        double drift = 0.93;


        fl_Wheel.setPower(-0.7);
        fr_Wheel.setPower(0.7);
        bl_Wheel.setPower(0.7);
        br_Wheel.setPower(-0.7);


        sleep(250);


        fr_Wheel.setPower(0);
        bl_Wheel.setPower(0);
        fl_Wheel.setPower(0);
        br_Wheel.setPower(0);


        fr_Wheel.setPower(0.7);
        bl_Wheel.setPower(0.7);
        fl_Wheel.setPower(0.7);
        br_Wheel.setPower(0.7);

        sleep(1000);

        fr_Wheel.setPower(0);
        bl_Wheel.setPower(0);
        fl_Wheel.setPower(0);
        br_Wheel.setPower(0);

        wrist_servo.setPosition(0.5);

        sleep(2500);


        claw_servo.setPower(-1);

        sleep(1450);

        claw_servo.setPower(0);
        sleep(2500);

        arm_motorLeft.setPower(0.5);
        arm_motorRight.setPower(0.5);

        sleep(1500);

        arm_motorLeft.setPower(0);
        arm_motorRight.setPower(0);

        sleep(1500);



    }
}

