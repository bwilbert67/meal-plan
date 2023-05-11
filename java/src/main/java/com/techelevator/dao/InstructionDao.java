package com.techelevator.dao;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.techelevator.model.Instruction;

import java.util.List;

public interface InstructionDao {
List<Instruction> getInstructionsByRecipeId(int recipeId);
Instruction updateInstruction(Instruction newInstruction);
Instruction addInstruction(Integer recipeId, Instruction instruction);
}
