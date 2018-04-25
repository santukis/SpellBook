package com.santukis.spellbook.domain;

public class Response<T> {

   private String error;
   private T body;

   private Response(T body, String error){
      this.body = body;
      this.error = error;
   }

   public static <T> Response<T> success(T body) {
      return new Response<>(body, "");
   }

   public static <T> Response<T> error(String error) {
      return new Response<>(null, error);
   }

   public boolean isSuccessful() {
      return body != null;
   }

   public T getBody() {
      return body;
   }

   public String getError() {
      return error;
   }
}