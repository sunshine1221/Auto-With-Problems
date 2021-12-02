package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Placing a block")
public class Autonomous_Test extends LinearOpMode {
    DcMotor motor1;
    boolean a, b;
    double x;
    double y;
    double p;
    boolean a1, b1, a2, b2;
    boolean up, down, left, right;
    double speed;
    DcMotor motor;
    boolean wheelon = false;
    double x2;
    // x is value of wheels, back is -x,  y+x y-x
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor wheel;
    @Override
    public void runOpMode() throws InterruptedException {
        //Declaring motors through hardwareMap
        motor1 = hardwareMap.dcMotor.get("claw");
        motor = hardwareMap.dcMotor.get("motor");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        wheel = hardwareMap.dcMotor.get("wheel");
        //Encoders reset and initialized
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        //Move lift up 1444 ticks, or to where the top level is
        movingvoid(1444, motor);
        //Moving chassis forward 12000 ticks
        movingvoid(12000, frontLeft);
        movingvoid(12000, backLeft);
        movingvoid(-12000, frontRight);
        movingvoid(-12000, backRight);
        //Opening claw
        motor1.setPower(-0.25);
        sleep(500);
        motor1.setPower(0);
    }
    public void movingvoid(double ticks, DcMotor motorbeingused){
        int newTarget;
        double speed2 = 1;
        if(opModeIsActive() && motorbeingused.getCurrentPosition() >= 0 && motorbeingused.getCurrentPosition() <= 3190){
            newTarget = motorbeingused.getCurrentPosition() + (int)ticks;
            motorbeingused.setTargetPosition(newTarget);
            motorbeingused.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorbeingused.setPower(Math.abs(speed2));


            motorbeingused.setPower(0);
            motorbeingused.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addData("Height", motorbeingused.getCurrentPosition());
            telemetry.update();
        }
        /*
        make sure opmode is active
        find current position and add distance to move
        set target position of motor
        run to that position

        make sure that power is not negative (abs)

        stop all motors

        set to run using encoders

        wait a quarter second (for accuracy, so that the program doesn't make the robot move in an unwanted direction)
        */
    }
}
