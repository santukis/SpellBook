package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.AvatarsGateway;
import com.santukis.spellbook.domain.model.Avatar;

public class DeleteAvatar extends UseCase<Avatar, Boolean> {

    private final AvatarsGateway gateway;

    public DeleteAvatar(UseCaseScheduler useCaseScheduler, AvatarsGateway gateway) {
        super(useCaseScheduler);
        this.gateway = gateway;
    }

    @Override
    protected void executeUseCase(Avatar avatar) {
        gateway.deleteAvatar(avatar);
    }

    @Override
    protected void onResponse(Response<Boolean> response) {

    }
}
