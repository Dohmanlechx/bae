package com.dohman.boilaneggbae;

import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView b1;
    private TextView b2;
    private TextView b3;
    private TextView c1;
    private TextView c2;
    private TextView c3;
    private TextView youScore;
    private TextView botScore;
    private Button resetScore;
    GamePlayer player = new GamePlayer("PLAYER", "x");
    GamePlayerAI ai = new GamePlayerAI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        youScore = findViewById(R.id.youScore);
        botScore = findViewById(R.id.botScore);
        resetScore = findViewById(R.id.buttonReset);

        a1.setOnClickListener(setSuitA1);
        a2.setOnClickListener(setSuitA2);
        a3.setOnClickListener(setSuitA3);
        b1.setOnClickListener(setSuitB1);
        b2.setOnClickListener(setSuitB2);
        b3.setOnClickListener(setSuitB3);
        c1.setOnClickListener(setSuitC1);
        c2.setOnClickListener(setSuitC2);
        c3.setOnClickListener(setSuitC3);
        resetScore.setOnClickListener(resetCL);

        resetScore.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);

    }

    public void resetBoard() {
        a1.setText(" ");
        a2.setText(" ");
        a3.setText(" ");
        b1.setText(" ");
        b2.setText(" ");
        b3.setText(" ");
        c1.setText(" ");
        c2.setText(" ");
        c3.setText(" ");
    }

    public boolean checkWinner(String suit) {
        if (a1.getText().toString().equals(suit) && a2.getText().toString().equals(suit) && a3.getText().toString().equals(suit) ||
                b1.getText().toString().equals(suit) && b2.getText().toString().equals(suit) && b3.getText().toString().equals(suit) ||
                c1.getText().toString().equals(suit) && c2.getText().toString().equals(suit) && c3.getText().toString().equals(suit) ||
                a1.getText().toString().equals(suit) && b1.getText().toString().equals(suit) && c1.getText().toString().equals(suit) ||
                a2.getText().toString().equals(suit) && b2.getText().toString().equals(suit) && c2.getText().toString().equals(suit) ||
                a3.getText().toString().equals(suit) && b3.getText().toString().equals(suit) && c3.getText().toString().equals(suit) ||
                a1.getText().toString().equals(suit) && b2.getText().toString().equals(suit) && c3.getText().toString().equals(suit) ||
                a3.getText().toString().equals(suit) && b2.getText().toString().equals(suit) && c1.getText().toString().equals(suit)) {
            return true;
        }
        return false;
    }

    public void winningGame(GamePlayer winner) {
        winner.gameAddWin();
        if (winner.getPlayerName().equals("PLAYER")) {
            youScore.setText(String.valueOf(winner.getWins()));
        } else {
            botScore.setText(String.valueOf(winner.getWins()));
        }
    }

    public boolean evenGameCheck() {
        if (!a1.getText().toString().equals(" ") && !a2.getText().toString().equals(" ") && !a3.getText().toString().equals(" ") &&
                !b1.getText().toString().equals(" ") && !b2.getText().toString().equals(" ") && !b3.getText().toString().equals(" ") &&
                !c1.getText().toString().equals(" ") && !c2.getText().toString().equals(" ") && !c3.getText().toString().equals(" ")) {
            return true;
        }
        return false;
    }

    public void gameInitialize() {
        // Checking if X won
        if (checkWinner(player.getSuit())) {
            winningGame(player);
            resetBoard();
            if (evenGameCheck()) {
                resetBoard();
            }

        }
        // Checking if tied
        // DO I NEED TO PUT CODE HERE?

        // AI inputting
        String inputO;
        do {
            inputO = ai.makeInput();
            if (evenGameCheck()) {
                resetBoard();
            }
        } while (!setonBoard(inputO, ai.getSuit()));
        // Checking if O won
        if (checkWinner(ai.getSuit())) {
            winningGame(ai);
            resetBoard();
        }

        if (evenGameCheck()) {
            resetBoard();
        }
    }

    private View.OnClickListener resetCL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            player.setWins(0);
            ai.setWins(0);
            youScore.setText(String.valueOf(player.getWins()));
            botScore.setText(String.valueOf(ai.getWins()));
        }
    };

    private View.OnClickListener setSuitA1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("a1", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitA2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("a2", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitA3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("a3", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitB1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("b1", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitB2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("b2", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitB3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("b3", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitC1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("c1", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitC2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("c2", "x")) {
                gameInitialize();
            }
        }
    };

    private View.OnClickListener setSuitC3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (setonBoard("c3", "x")) {
                gameInitialize();
            }
        }
    };

    public boolean setonBoard(String input, String suit) {

        switch (input) {
            case "a1":
                if (a1.getText().toString().equals(" ")) {
                    a1.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "a2":
                if (a2.getText().toString().equals(" ")) {
                    a2.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "a3":
                if (a3.getText().toString().equals(" ")) {
                    a3.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "b1":
                if (b1.getText().toString().equals(" ")) {
                    b1.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "b2":
                if (b2.getText().toString().equals(" ")) {
                    b2.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "b3":
                if (b3.getText().toString().equals(" ")) {
                    b3.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "c1":
                if (c1.getText().toString().equals(" ")) {
                    c1.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "c2":
                if (c2.getText().toString().equals(" ")) {
                    c2.setText(suit);
                    break;
                } else {
                    return false;
                }
            case "c3":
                if (c3.getText().toString().equals(" ")) {
                    c3.setText(suit);
                    break;
                } else {
                    return false;
                }
        }
        return true;
    }

}
