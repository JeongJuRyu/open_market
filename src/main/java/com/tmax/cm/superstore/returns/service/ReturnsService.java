package com.tmax.cm.superstore.returns.service;

import com.tmax.cm.superstore.code.ReturnType;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.returns.entity.Returns;
import com.tmax.cm.superstore.returns.repository.ReturnsRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReturnsService {

    private final ReturnsRepository returnsRepository;

    @Transactional
    public Returns create(String recipient, String address, String mobile, String request) {
        Returns returns = Returns.builder()
                .recipient(recipient)
                .address(address)
                .mobile(mobile)
                .requests(request)
                .returnType(ReturnType.RETURN_WAITING)
                .build();

        returnsRepository.save(returns);
        return returns;
    }

    @Transactional
    public void acceptReturn(UUID returnId) {
        Returns returns = returnsRepository.findWithIdForUpdate(returnId);
        returns.acceptState();
    }

    @Transactional
    public void refuseReturn(UUID returnId) {
        Returns returns = returnsRepository.findWithIdForUpdate(returnId);
        returns.refuseState();
    }

    @Transactional
    public void proceedReturn(UUID returnId) {
        Returns returns = returnsRepository.findWithIdForUpdate(returnId);
        returns.proceedState();
    }

    @Transactional
    public void doneReturn(UUID returnId) {
        Returns returns = returnsRepository.findWithIdForUpdate(returnId);
        returns.doneState();
    }
}
