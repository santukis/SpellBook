package com.santukis.spellbook.domain;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCaseThreadPoolExecutor implements UseCaseScheduler {

   private static UseCaseThreadPoolExecutor INSTANCE = null;

   private static final int POOL_SIZE = 5;
   private static final int MAX_POOL_SIZE = 10;
   private static final int TIMEOUT = 30;

   private ThreadPoolExecutor poolExecutor;
   private final Handler handler;

   private UseCaseThreadPoolExecutor() {
      handler = new Handler(Looper.getMainLooper());
      poolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
              TimeUnit.SECONDS, new ArrayBlockingQueue<>(POOL_SIZE));
   }

   public static UseCaseThreadPoolExecutor getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new UseCaseThreadPoolExecutor();
      }
      return INSTANCE;
   }

   @Override
   public <Params> void execute(UseCase<Params, ?> useCase, Params params) {
      poolExecutor.execute(() -> {
         useCase.executeUseCase(params);
      });
   }

   @Override
   public <Result> void notifyResponse(UseCase<?, Result> useCase, Response<Result> response) {
      handler.post(() -> {
         useCase.onResponse(response);
      });
   }
}
