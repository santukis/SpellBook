package com.santukis.spellbook.domain.usecase;


import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SettingsGateway;

public class SavePreference extends UseCase<SavePreference.RequestValues, Boolean> {

    private final SettingsGateway gateway;

    public SavePreference(UseCaseScheduler useCaseScheduler,
                          SettingsGateway gateway) {
        super(useCaseScheduler);
        this.gateway = gateway;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        gateway.save(requestValues.getKey(), requestValues.getValue());
    }

    @Override
    protected void onResponse(Response<Boolean> response) {

    }

    public static class RequestValues {
        private String key;
        private Object value;

        public RequestValues(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
