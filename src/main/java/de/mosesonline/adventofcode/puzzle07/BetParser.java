package de.mosesonline.adventofcode.puzzle07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static java.lang.Integer.parseInt;

public class BetParser {

    public List<Bet> readBets(File input) throws IOException {
        List<Bet> result = new ArrayList<>();
        getInstance().parseLineByLine(input, (currentLine) -> {
            String[] betLine = currentLine.split(" +");
            String handData = betLine[0];
            Hand hand = new Hand();
            for(int i = 0; i < handData.length(); i++){
                hand.setCard(i, Card.fromValue(handData.charAt(i)));
            }
            result.add(new Bet(hand, parseInt(betLine[1])));
        });
        return result;
    }
}
