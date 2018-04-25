package com.santukis.spellbook.domain;

public class UseCaseDefaultScheduler implements UseCaseScheduler {

   private static UseCaseDefaultScheduler INSTANCE = null;

   private UseCaseDefaultScheduler() {
   }

   public static UseCaseDefaultScheduler getInstance() {
      if(INSTANCE == null) {
         INSTANCE = new UseCaseDefaultScheduler();
      }
      return INSTANCE;
   }

   @Override
   public <Params> void execute(UseCase<Params, ?> useCase, Params params) {
      useCase.executeUseCase(params);
   }

   @Override
   public <Result> void notifyResponse(UseCase<?, Result> useCase, Response<Result> response) {
      useCase.onResponse(response);
   }
}
