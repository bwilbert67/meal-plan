package com.techelevator.dao;

import com.techelevator.model.Instruction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcInstructionDao implements InstructionDao{

   private final JdbcTemplate jdbcTemplate;

   public JdbcInstructionDao (JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = jdbcTemplate;
   }

    @Override
    public List<Instruction> getInstructionsByRecipeId(int recipeId) {
        List<Instruction> result = new ArrayList<>();
        String sql = "SELECT * FROM instruction WHERE recipe_id = ? ORDER BY step_number ASC";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipeId);

        while(rowSet.next()) {
            Instruction curInstruction = instructionMapper(rowSet);
            result.add(curInstruction);
        }

        return result;
    }

    @Override
    public Instruction updateInstruction(Instruction newInstruction) {
       //query for object with new instruction as paramter, return the new instruction
        return null;
    }

    @Override
    public Instruction addInstruction(Integer recipeId, Instruction instruction) {
        String sql = "INSERT INTO instruction VALUES (DEFAULT, ?, ?, ?) RETURNING instruction_id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, recipeId, instruction.getStepNumber(), instruction.getDetails());
        instruction.setId(id);
        return instruction;
    }

    private Instruction instructionMapper(SqlRowSet rowSet) {
       Instruction result = new Instruction();
       result.setDetails(rowSet.getString("instruction_text"));
       result.setId(rowSet.getInt("instruction_id"));
       result.setStepNumber(rowSet.getInt("step_number"));
       return result;
    }
}
