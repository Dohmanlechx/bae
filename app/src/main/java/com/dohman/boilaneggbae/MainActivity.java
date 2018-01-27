package com.dohman.boilaneggbae;

import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long CURRENT_TIME = 600000;
    private long mTimeLeftInMillis = CURRENT_TIME;
    private long mEndTime;
    private TextView mTextViewCountDown;
    private boolean mTimerRunning;
    private Button buttonLargeSize;
    private Button buttonMediumSize;
    private Button buttonSoft;
    private Button buttonMedium;
    private Button buttonHard;
    private Button buttonHellaHard;
    private CountDownTimer mCountDownTimer;
    private EggSize mediumOrLarge = EggSize.UNDEFINED;
    private boolean alreadyRunning = false;

    enum EggSize {
        UNDEFINED, MEDIUM, LARGE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hittar knapparna
        buttonMediumSize = findViewById(R.id.buttonMediumSize);
        buttonLargeSize = findViewById(R.id.buttonLargeSize);

        buttonSoft = findViewById(R.id.buttonSoft);
        buttonMedium = findViewById(R.id.buttonMedium);
        buttonHard = findViewById(R.id.buttonHard);
        buttonHellaHard = findViewById(R.id.buttonHellaHard);

        // Sätter på listener på knapparna
        buttonMediumSize.setOnClickListener(btnMediumSizeClickListener);
        buttonLargeSize.setOnClickListener(btnLargeSizeClickListener);

        buttonSoft.setOnClickListener(btnSoftClickListener);
        buttonMedium.setOnClickListener(btnMediumClickListener);
        buttonHard.setOnClickListener(btnHardClickListener);
        buttonHellaHard.setOnClickListener(btnHellaHardClickListener);

        //Färglägger knapparna
        buttonSoft.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        buttonMedium.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        buttonHard.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        buttonHellaHard.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);

        mTextViewCountDown = findViewById(R.id.time);

    }

    private View.OnClickListener btnMediumSizeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mediumOrLarge = EggSize.MEDIUM;
        }
    };

    private View.OnClickListener btnLargeSizeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mediumOrLarge = EggSize.LARGE;
        }
    };

    private View.OnClickListener btnSoftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ((mediumOrLarge != EggSize.UNDEFINED) && (alreadyRunning == false)) {
                alreadyRunning = true;
                //durationTime = 240;
                mTimeLeftInMillis = 240000;
                start();
            } else if (mediumOrLarge == EggSize.UNDEFINED) {
                mTextViewCountDown.setText("Choose size first");
            } else {
                alreadyRunning = false;
                cancel();
            }
        }
    };

    private View.OnClickListener btnMediumClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ((mediumOrLarge != EggSize.UNDEFINED) && (alreadyRunning == false)) {
                alreadyRunning = true;
                //durationTime = 420;
                mTimeLeftInMillis = 420000;
                start();
            } else if (mediumOrLarge == EggSize.UNDEFINED) {
                mTextViewCountDown.setText("Choose size first");
            } else {
                alreadyRunning = false;
                cancel();
            }
        }
    };

    private View.OnClickListener btnHardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ((mediumOrLarge != EggSize.UNDEFINED) && (alreadyRunning == false)) {
                alreadyRunning = true;
                //durationTime = 660;
                mTimeLeftInMillis = 660000;
                start();
            } else if (mediumOrLarge == EggSize.UNDEFINED) {
                mTextViewCountDown.setText("Choose size first");
            } else {
                alreadyRunning = false;
                cancel();
            }
        }
    };

    private View.OnClickListener btnHellaHardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ((mediumOrLarge != EggSize.UNDEFINED) && (alreadyRunning == false)) {
                alreadyRunning = true;
                //durationTime = 1800;
                mTimeLeftInMillis = 1800000;
                start();
            } else if (mediumOrLarge == EggSize.UNDEFINED) {
                mTextViewCountDown.setText("Choose size first");
            } else {
                alreadyRunning = false;
                cancel();
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("millisLeft", mTimeLeftInMillis);
        savedInstanceState.putBoolean("timerRunning", mTimerRunning);
        savedInstanceState.putLong("endTime", mEndTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();

        if (mTimerRunning) {
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            start();
        }
        //durationTime = savedInstanceState.getInt(DURATION_TIME);
    }

    private void start() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        //mTextViewCountDown.setText("");

        if (mediumOrLarge == EggSize.MEDIUM) {
            mTimeLeftInMillis -= 60000;
        }
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
//                String text = String.format(Locale.getDefault(), "%02d:%02d",
//                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
//                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
//                mTextViewCountDown.setText(text);
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

        mTimerRunning = true;
        //mCountDownTimer.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void cancel() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
            mTextViewCountDown.setText("Stopped");
        }
    }
}
