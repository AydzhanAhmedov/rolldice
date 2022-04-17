package com.roller.coff.model;

import javax.persistence.*;

@Entity
@Table(name = "dice_rolls")
public class DiceRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int diceRoll1;
    private int diceRoll2;

    public long getId() {
        return id;
    }

    public int getDiceRoll1() {
        return diceRoll1;
    }

    public void setDiceRoll1(int diceRoll1) {
        this.diceRoll1 = diceRoll1;
    }

    public int getDiceRoll2() {
        return diceRoll2;
    }

    public void setDiceRoll2(int diceRoll2) {
        this.diceRoll2 = diceRoll2;
    }
}
