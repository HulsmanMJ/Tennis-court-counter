package com.example.android.tenniscourtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.R.attr.id;

import com.example.android.tenniscourtcounter.R;

public class MainActivity extends AppCompatActivity {
    int scorePlayerA = 0;    //Tracks the score for Player A
    int scorePlayerB = 0;    //Tracks the score for Player B
    int set1A = 0;
    int set2A = 0;
    int set3A = 0;
    int set1B = 0;
    int set2B = 0;
    int set3B = 0;
    int setOfTheGame = 1;    // Tracks the current set of the game
    boolean newGame = false;
    boolean endOfGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Increase the score for Player A.
     */
    public void addScoreForPlayerA(View v) {
        if (!endOfGame) {
            scorePlayerA = scorePlayerA + 1;
            displayForPlayerA(scorePlayerA);
        }
    }

    /**
     * Increase the score of the set for Player A by 1 point.
     */
    public void addSetScoreForPlayerA(View v) {
        if (setOfTheGame == 1) {
            if ((set1A == 6 && set1A - set1B >= 2) || (set1A == 7)) {
                setOfTheGame = 2;
                set2A++;
                TextView scoreView = (TextView) findViewById(R.id.player_a_score_set_2);
                scoreView.setText(String.valueOf(set2A));
            } else {
                set1A++;
            }
            newGame = false;
            TextView scoreView = (TextView) findViewById(R.id.player_a_score_set_1);
            scoreView.setText(String.valueOf(set1A));
        } else if (setOfTheGame == 2) {
            if ((set2A == 6 && set2A - set2B >= 2) || (set2A == 7)) {
                setOfTheGame = 3;
                set3A++;
            } else {
                set2A++;
            }
            newGame = false;
            TextView scoreView = (TextView) findViewById(R.id.player_a_score_set_2);
            scoreView.setText(String.valueOf(set2A));
        } else if (setOfTheGame == 3) {
            if ((set3A == 6 && set3A - set3B >= 2) || (set3A == 7) || (set3B == 6 && set3B - set3A >= 2) || (set3B == 7)) {
                setOfTheGame = 4;
            } else {
                set3A++;
            }
            newGame = false;
            TextView scoreView = (TextView) findViewById(R.id.player_a_score_set_3);
            scoreView.setText(String.valueOf(set3A));
        }
    }

    /**
     * Displays the given score for Player A.
     */
    public void displayForPlayerA(int score) {
        String scoreName = "";
        switch (score) {
            case 0: {
                scoreName = "Love";
                break;
            }
            case 1: {
                scoreName = "15";
                break;
            }
            case 2: {
                scoreName = "30";
                break;
            }
            case 3: {
                if (scorePlayerB == 3) {
                    scoreName = "deuce";
                    TextView scoreView = (TextView) findViewById(R.id.player_b_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "40";
                }
                break;
            }
            case 4: {
                if (score - scorePlayerB > 2) {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                } else if (score == scorePlayerB) {
                    scorePlayerA = 3;
                    scorePlayerB = 3;
                    scoreName = "deuce";
                    TextView scoreView = (TextView) findViewById(R.id.player_b_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "adv";
                    TextView scoreView = (TextView) findViewById(R.id.player_b_score);
                    scoreView.setText(String.valueOf(40));
                }
                break;
            }
            case 5: {
                if (endOfGame) {
                    newGame = false;
                } else {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                }
                break;
            }
            default:
                break;
        }
        if (newGame) {
            addSetScoreForPlayerA(null);
        }
        TextView scoreView = (TextView) findViewById(R.id.player_a_score);
        scoreView.setText(String.valueOf(scoreName));
    }

    /**
     * Increase the score for Player B.
     */
    public void addScoreForPlayerB(View v) {
        if (!endOfGame) {
            scorePlayerB = scorePlayerB + 1;
            displayForPlayerB(scorePlayerB);
        }
    }

    /**
     * Increase the score of the set for Player B by 1 point.
     */
    public void addSetScoreForPlayerB(View v) {
        if (setOfTheGame == 1) {
            if ((set1B == 6 && set1B - set1A >= 2) || (set1B == 7)) {
                setOfTheGame = 2;
                set2B++;
                TextView scoreView = (TextView) findViewById(R.id.player_b_score_set_2);
                scoreView.setText(String.valueOf(set2B));
            } else {
                set1B++;
            }
            newGame = false;
            TextView scoreView = (TextView) findViewById(R.id.player_b_score_set_1);
            scoreView.setText(String.valueOf(set1B));
        } else if (setOfTheGame == 2) {
            if ((set2B == 6 && set2B - set2A >= 2) || (set2B == 7)) {
                setOfTheGame = 3;
                set3B++;
                TextView scoreView = (TextView) findViewById(R.id.player_b_score_set_3);
                scoreView.setText(String.valueOf(set3B));
            } else {
                set2B++;
            }
            newGame = false;
            TextView scoreView = (TextView) findViewById(R.id.player_b_score_set_2);
            scoreView.setText(String.valueOf(set2B));
        } else if (setOfTheGame == 3)  {
            if ((set3B == 6 && set3B - set3A >= 2) || (set3B == 7) || (set3A == 6 && set3A - set3B >= 2) || (set3A == 7)) {
                setOfTheGame = 4;
            } else {
                set3B++;
            }
            newGame = false;
            TextView scoreView = (TextView) findViewById(R.id.player_b_score_set_3);
            scoreView.setText(String.valueOf(set3B));
        }
    }

    /**
     * Displays the given score for Player B.
     */
    public void displayForPlayerB(int score) {
        String scoreName = "";
        switch (score) {
            case 0: {
                scoreName = "Love";
                break;
            }
            case 1: {
                scoreName = "15";
                break;
            }
            case 2: {
                scoreName = "30";
                break;
            }
            case 3: {
                if (scorePlayerA == 3) {
                    scoreName = "deuce";
                    TextView scoreView = (TextView) findViewById(R.id.player_a_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "40";
                }
                break;
            }
            case 4: {
                if (score - scorePlayerA > 2) {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                } else if (score == scorePlayerA) {
                    scorePlayerA = 3;
                    scorePlayerB = 3;
                    scoreName = "deuce";
                    TextView scoreView = (TextView) findViewById(R.id.player_a_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "adv";
                    TextView scoreView = (TextView) findViewById(R.id.player_a_score);
                    scoreView.setText(String.valueOf(40));
                }
                break;
            }
            case 5: {
                if (endOfGame) {
                    newGame = false;
                } else {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                }
                break;
            }
            default:
                break;
        }
        if (newGame) {
            addSetScoreForPlayerB(null);
        }
        TextView scoreView = (TextView) findViewById(R.id.player_b_score);
        scoreView.setText(String.valueOf(scoreName));
    }

    /**
     * Resets both scores for Player A and for Player B to 0.
     */
    public void resetGame(View v) {
        scorePlayerA = 0;
        scorePlayerB = 0;
        endOfGame = false;
        displayForPlayerA(scorePlayerA);
        displayForPlayerB(scorePlayerB);
    }

    /**
     * Resets all scores for Player A and for Player B to 0.
     */
    public void resetScore(View v) {
        scorePlayerA = 0;
        scorePlayerB = 0;
        endOfGame = false;
        newGame = false;
        set1A = 0;
        set2A = 0;
        set3A = 0;
        set1B = 0;
        set2B = 0;
        set3B = 0;
        setOfTheGame = 1;
        displayForPlayerA(scorePlayerA);
        displayForPlayerB(scorePlayerB);
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_set_1);
        scoreView.setText(String.valueOf(set1A));
        scoreView = (TextView) findViewById(R.id.player_a_score_set_2);
        scoreView.setText(String.valueOf(set2A));
        scoreView = (TextView) findViewById(R.id.player_a_score_set_3);
        scoreView.setText(String.valueOf(set3A));
        scoreView = (TextView) findViewById(R.id.player_b_score_set_1);
        scoreView.setText(String.valueOf(set1B));
        scoreView = (TextView) findViewById(R.id.player_b_score_set_2);
        scoreView.setText(String.valueOf(set2B));
        scoreView = (TextView) findViewById(R.id.player_b_score_set_3);
        scoreView.setText(String.valueOf(set3B));
    }
}