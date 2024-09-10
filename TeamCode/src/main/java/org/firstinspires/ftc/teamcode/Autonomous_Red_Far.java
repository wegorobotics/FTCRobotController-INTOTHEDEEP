package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name= "Autonomous_Red_Far")
//@Disabled
public class Autonomous_Red_Far extends LinearOpMode {
    // names
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;
    DcMotor arm_motor;
    Servo wrist_servo;
    Servo hand_servo;
    DcMotor drone_motor;




    @Override
    public void runOpMode() {
        // definitions
        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_motor");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_motor");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_motor");
        arm_motor = hardwareMap.get(DcMotor.class, "arm_motor");
        wrist_servo = hardwareMap.get(Servo.class, "wrist_servo");
        hand_servo = hardwareMap.get(Servo.class, "hand_servo");
        drone_motor = hardwareMap.get(DcMotor.class, "drone_motor");




        fr_Wheel.setDirection(DcMotor.Direction.FORWARD);
        fl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        br_Wheel.setDirection(DcMotor.Direction.FORWARD);
        bl_Wheel.setDirection(DcMotor.Direction.REVERSE);
        arm_motor.setDirection(DcMotor.Direction.FORWARD);
        drone_motor.setDirection(DcMotor.Direction.FORWARD);




        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drone_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


       /*
       fr_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       fl_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       br_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       bl_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       */


        waitForStart();


        double drift = 0.8;


        fr_Wheel.setPower(-0.5);
        fl_Wheel.setPower(0.5*drift);
        br_Wheel.setPower(0.5);
        bl_Wheel.setPower(-0.5);


        sleep(2400);


        fr_Wheel.setPower(0.5);
        fl_Wheel.setPower(0.5*drift);
        br_Wheel.setPower(0.5);
        bl_Wheel.setPower(0.5);


        sleep (3000);


    }
}
