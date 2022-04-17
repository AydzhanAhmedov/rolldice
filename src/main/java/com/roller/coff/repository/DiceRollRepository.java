package com.roller.coff.repository;

import com.roller.coff.model.DiceRoll;
import com.roller.coff.model.DiceRollStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;

import javax.persistence.Tuple;
import java.util.Collection;
import java.util.List;

public interface DiceRollRepository extends CrudRepository<DiceRoll, Long> {

    @Query(
            value = " SELECT COUNT (D.DICE_ROLL1 + D.DICE_ROLL2) AS count, D.DICE_ROLL1 + D.DICE_ROLL2 AS roll FROM DICE_ROLLS D\n" +
                    " GROUP BY D.DICE_ROLL1 + D.DICE_ROLL2 \n" +
                    " ORDER BY D.DICE_ROLL1 + D.DICE_ROLL2",
            nativeQuery = true
    )
    List<DiceRollStatistic> GetDiceRoll();
}
