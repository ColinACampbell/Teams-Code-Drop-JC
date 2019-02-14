
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


// @Authors Chavaughn Wilkins, Colin  Campbell & Javon Peart
@TeleOp(name = "JavonTeamMyles")

public class JavonTest extends LinearOpMode
{

    private DcMotor FrontLeft, BackLeft, FrontRight, BackRight, ChainLift, HookLift;
    private Servo trayDispL, trayDispR,IntakeR,IntakeL;

    @Override
    public void runOpMode() throws InterruptedException {

        //Wheel Motors
        FrontLeft =  hardwareMap.dcMotor.get("FrontLeft");
        FrontRight =  hardwareMap.dcMotor.get("FrontRight");
        BackLeft = hardwareMap.dcMotor.get("BackLeft");
        BackRight = hardwareMap.dcMotor.get("BackRight");

        //Collection Devices
        HookLift = hardwareMap.dcMotor.get("HookLift");
        ChainLift = hardwareMap.dcMotor.get("ChainLift");
        trayDispL = hardwareMap.servo.get("TrayDispL");
        trayDispR = hardwareMap.servo.get("TrayDispR");
        trayDispL.setDirection(Servo.Direction.REVERSE);
        // TODO inverse either of the servos if anything goes wrong in testing

        //Lowest Intake Servos
        IntakeL = hardwareMap.servo.get("IntakeL");
        IntakeR = hardwareMap.servo.get("IntakeR");
        //TODO : inverse either of the servos in case if anything goes wrong in testing

        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //ChainLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive()){
            MovementSys();
            CollectionSys();
            trayControlSys();
            HookSys();
            telemetry.update();
        }

    }

    private void MovementSys() {
        telemetry.addLine("************Status: Running***********");

        double Power1, Power2;
        Power1 = Range.clip(gamepad1.left_stick_y, -0.8, 0.8);
        Power2 = Range.clip(gamepad1.right_stick_y, -0.8, 0.8);

        //Powers Set for Wheel Motors
        FrontLeft.setPower(Power2);
        BackLeft.setPower (Power2);
        BackRight.setPower (-Power1);
        FrontRight.setPower(-Power1);



        telemetry.addLine("Robots as seen...");
        telemetry.addData("In their Natural Habitat ", Power1);
    }

    private void CollectionSys(){
        if (gamepad1.right_bumper){
            IntakeL.setPosition(-1);
            IntakeR.setPosition(1);
        }

        else if (gamepad1.left_bumper){
            IntakeL.setPosition(1);
            IntakeR.setPosition(-1);
        }

        else{
            IntakeL.setPosition(0.5);
            IntakeR.setPosition(0.5);
        }
    }

    private void trayControlSys(){
        // Control and Power for chain lift
        if (gamepad1.dpad_up){
            ChainLift.setPower(1);
        }

        else if(gamepad1.dpad_down){
            ChainLift.setPower(-1);
        }

        else{
            ChainLift.setPower(0);
        }

        //Control and Power for Tray Dispenser servos
        if (gamepad1.dpad_left) {
            trayDispL.setPosition(1.0);
            trayDispR.setPosition(1.0);
        }

        else if (gamepad1.dpad_right) {
            trayDispL.setPosition(-1.0);
            trayDispR.setPosition(-1.0);
        }

        else{
            trayDispL.setPosition(0.5);
            trayDispR.setPosition(0.5);
        }
    }

    private void HookSys(){
        //Control for Hook Lift
        if (gamepad1.y) {
            HookLift.setPower(1);
        }

        else if ( gamepad1.x) {
            HookLift.setPower(-1);
        }
        else {
            HookLift.setPower(0);
        }
    }
}

