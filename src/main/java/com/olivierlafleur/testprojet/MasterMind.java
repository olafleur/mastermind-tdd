package com.olivierlafleur.testprojet;

import static com.olivierlafleur.testprojet.Result.GOOD_COLOR;
import static com.olivierlafleur.testprojet.Result.GOOD_COLOR_PLACE;
import static com.olivierlafleur.testprojet.Result.NONE;

public class MasterMind {
    private boolean gameFinished = false;
    private Color[] answer;
    private boolean[] canTake = {true, true, true, true, true};

    public MasterMind(Color[] answer) {
        this.answer = answer;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public Result[] tryMove(Color[] move) {
        int nbOfBlack = 0;
        int nbOfWhite = 0;

        canTake = new boolean[]{true, true, true, true, true};

        for (int i = 0; i < 5; i++) {
            if (presentInAnswer(move[i]) && goodPlace(i, move[i])) {
                nbOfBlack++;
                canTake[i] = false;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (presentInAnswer(move[i])) {
                nbOfWhite++;
                canTake[whereInAnswer(move[i])] = false;
            }
        }

        if (nbOfBlack == 5) {
            gameFinished = true;
        }

        return createResult(nbOfBlack, nbOfWhite);
    }

    private int whereInAnswer(Color color) {
        for (int i = 0; i < 5; i++) {
            if (answer[i] == color && canTake[i]) {
                return i;
            }
        }

        return -1;
    }

    private boolean goodPlace(int i, Color color) {
        return answer[i] == color;
    }

    private Result[] createResult(int nbOfBlack, int nbOfWhite) {
        Result[] results = allNoneResult();
        int i;

        for (i = 0; i < nbOfBlack; i++) {
            results[i] = GOOD_COLOR_PLACE;
        }

        for (int j = i; i < j + nbOfWhite; i++) {
            results[i] = GOOD_COLOR;
        }

        return results;
    }

    private boolean presentInAnswer(Color color) {
        for (int i = 0; i < 5; i++) {
            if (answer[i] == color && canTake[i]) {
                return true;
            }
        }

        return false;
    }

    private Result[] allNoneResult() {
        return new Result[]{NONE, NONE, NONE, NONE, NONE};
    }
}
