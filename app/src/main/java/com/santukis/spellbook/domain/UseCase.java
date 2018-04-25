package com.santukis.spellbook.domain;


import com.santukis.spellbook.domain.boundary.UseCaseInput;

public abstract class UseCase<Params, Result> implements UseCaseInput<Params> {

   private UseCaseScheduler useCaseScheduler;

   public UseCase(UseCaseScheduler useCaseScheduler) {
      this.useCaseScheduler = useCaseScheduler;
   }

   protected abstract void executeUseCase(Params params);

   protected abstract void onResponse(Response<Result> response);

   protected final void submitResponse(Response<Result> response) {
      useCaseScheduler.notifyResponse(this, response);
   }

   @Override
   public void execute(Params params) {
      useCaseScheduler.execute(this, params);
   }
}
