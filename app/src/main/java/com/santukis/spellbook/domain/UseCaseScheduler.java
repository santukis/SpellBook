package com.santukis.spellbook.domain;



public interface UseCaseScheduler {

   <Params> void execute(UseCase<Params, ?> useCase, Params params);

   <Result> void notifyResponse(UseCase<?, Result> useCase, Response<Result> response);
}
