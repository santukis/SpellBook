package com.santukis.spellbook.domain.boundary;


public interface UseCaseInput<Params> {

   void execute(Params params);

}
