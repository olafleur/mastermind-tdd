package com.olivierlafleur.testprojet;

import org.junit.Test;

import static com.olivierlafleur.testprojet.Color.BLACK;
import static com.olivierlafleur.testprojet.Color.BLUE;
import static com.olivierlafleur.testprojet.Color.GREEN;
import static com.olivierlafleur.testprojet.Color.RED;
import static com.olivierlafleur.testprojet.Color.WHITE;
import static com.olivierlafleur.testprojet.Color.YELLOW;
import static com.olivierlafleur.testprojet.Result.GOOD_COLOR;
import static com.olivierlafleur.testprojet.Result.GOOD_COLOR_PLACE;
import static com.olivierlafleur.testprojet.Result.NONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MasterMindTest {
    private static final Color[] GOOD_ANSWER = {RED, GREEN, BLUE, BLACK, YELLOW};
    private static final Color[] ALL_WRONG_COLOR = new Color[]{WHITE, WHITE, WHITE, WHITE, WHITE};
    private static final Color[] ONE_GOOD_COLOR_GOOD_PLACE = new Color[]{RED, WHITE, WHITE, WHITE, WHITE};
    private static final Color[] TWO_GOOD_COLOR_GOOD_PLACE = new Color[]{RED, GREEN, WHITE, WHITE, WHITE};
    private static final Color[] ONE_GOOD_COLOR_WRONG_PLACE = {WHITE, RED, WHITE, WHITE, WHITE};
    private static final Color[] ONE_GOOD_COLOR_GOOD_PLACE_ONE_GOOD_COLOR_WRONG_PLACE = {RED, WHITE, GREEN, WHITE, WHITE};
    private static final Color[] TWO_GOOD_COLOR_ONE_GOOD_PLACE = {RED, RED, WHITE, WHITE, WHITE};

    private MasterMind board = new MasterMind(GOOD_ANSWER);

    @Test
    public void whenGameStartedGameIsNotFinished() {
        assertFalse(board.isGameFinished());
    }

    @Test
    public void whenTryGoodAnswerGameIsFinished() {
        //when
        board.tryMove(GOOD_ANSWER);

        //then
        assertTrue(board.isGameFinished());
    }

    @Test
    public void whenTryWrongFirstAnswerGameIsNotFinished() {
        //when
        board.tryMove(allRedMove());

        //then
        assertFalse(board.isGameFinished());
    }

    @Test
    public void whenTryGoodAnswerResultIsFiveBlack() {
        assertEquals(allBlackResult(), board.tryMove(GOOD_ANSWER));
    }

    @Test
    public void whenNoGoodColorResultIsFiveNone() {
        assertEquals(allNoneResult(), board.tryMove(ALL_WRONG_COLOR));
    }

    @Test
    public void whenOneGoodColorGoodPlaceResultIsOneBlack() {
        assertEquals(oneBlackResult(), board.tryMove(ONE_GOOD_COLOR_GOOD_PLACE));
    }

    @Test
    public void whenTwoGoodColorsGoodPlaceResultIsTwoBlack() {
        assertEquals(twoBlackResult(), board.tryMove(TWO_GOOD_COLOR_GOOD_PLACE));
    }

    @Test
    public void whenOneGoodColorWrongPlaceResultIsOneWhite() {
        assertEquals(oneWhiteResult(), board.tryMove(ONE_GOOD_COLOR_WRONG_PLACE));
    }

    @Test
    public void WhenOneGoodColorGoodPlaceOneGoodColorWrongPlaceResultIsOneBlackOneWhite() {
        assertEquals(oneBlackOneWhiteResult(), board.tryMove(ONE_GOOD_COLOR_GOOD_PLACE_ONE_GOOD_COLOR_WRONG_PLACE));
    }

    @Test
    public void WhenTwoTimesGoodColorWhenThereIsOnlyOneResultIsOneBlack() {
        assertEquals(oneBlackResult(), board.tryMove(TWO_GOOD_COLOR_ONE_GOOD_PLACE));
    }

    @Test
    public void GameSimulation() {
        assertEquals(oneBlackOneWhiteResult(), board.tryMove(new Color[]{BLUE, BLUE, BLUE, GREEN, GREEN}));
        assertEquals(new Result[]{GOOD_COLOR_PLACE, GOOD_COLOR, GOOD_COLOR, NONE, NONE}, board.tryMove(new Color[]{BLUE, GREEN, RED, RED, RED}));
        assertEquals(new Result[]{GOOD_COLOR, GOOD_COLOR, GOOD_COLOR, NONE, NONE}, board.tryMove(new Color[]{WHITE, WHITE, RED, BLUE, GREEN}));
        assertFalse(board.isGameFinished());
        assertEquals(allBlackResult(), board.tryMove(GOOD_ANSWER));
        assertTrue(board.isGameFinished());
    }

    private Result[] oneWhiteResult() {
        return new Result[]{GOOD_COLOR, NONE, NONE, NONE, NONE};
    }

    private Result[] oneBlackOneWhiteResult() {
        return new Result[]{GOOD_COLOR_PLACE, GOOD_COLOR, NONE, NONE, NONE};
    }

    private Result[] twoBlackResult() {
        return new Result[]{GOOD_COLOR_PLACE, GOOD_COLOR_PLACE, NONE, NONE, NONE};
    }

    private Result[] oneBlackResult() {
        return new Result[]{GOOD_COLOR_PLACE, NONE, NONE, NONE, NONE};
    }

    private Result[] allNoneResult() {
        return new Result[]{NONE, NONE, NONE, NONE, NONE};
    }

    private Color[] allRedMove() {
        return new Color[]{RED, RED, RED, RED, RED};
    }

    private Result[] allBlackResult() {
        return new Result[]{GOOD_COLOR_PLACE, GOOD_COLOR_PLACE, GOOD_COLOR_PLACE, GOOD_COLOR_PLACE, GOOD_COLOR_PLACE};
    }
}
