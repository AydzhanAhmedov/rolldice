package com.roller.coff.controller;

import com.roller.coff.model.DiceRoll;
import com.roller.coff.model.DiceRollStatistic;
import com.roller.coff.repository.DiceRollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("api/dice")
@CrossOrigin
public class DiceController {

    static final int DICE_ROLL_COUNT = 300;

    @Autowired
    private DiceRollRepository diceRollRepository;

    @GetMapping("/rolldice")
    public List<DiceRollStatistic> rollDice(){

        for (int i = 0; i < DICE_ROLL_COUNT; i++){
            DiceRoll diceRoll = new DiceRoll();
            Random random = new Random();
            diceRoll.setDiceRoll1(random.nextInt(6) + 1);
            diceRoll.setDiceRoll2(random.nextInt(6) + 1);
            diceRollRepository.save(diceRoll);
        }

        List<DiceRollStatistic> diceRolls = diceRollRepository.GetDiceRoll();

        return diceRolls;
    }

    @GetMapping("/results")
    public List<DiceRollStatistic> getResults()
    {
        List<DiceRollStatistic> diceRolls = diceRollRepository.GetDiceRoll();
        return diceRolls;
    }

}
