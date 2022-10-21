package com.tmax.cm.superstore.cancel.service;

import com.tmax.cm.superstore.cancel.entity.Cancel;
import com.tmax.cm.superstore.cancel.repository.CancelRepository;
import com.tmax.cm.superstore.code.CancelType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancelService {

    private final CancelRepository cancelRepository;

    @Transactional
    public Cancel create(String recipient, String address, String mobile, String request) {
        Cancel cancel = Cancel.builder()
                .recipient(recipient)
                .address(address)
                .mobile(mobile)
                .requests(request)
                .cancelType(CancelType.CANCEL_WAITING)
                .build();

        cancelRepository.save(cancel);
        return cancel;
    }

    @Transactional
    public void doneState(UUID cancelId) {
        Cancel cancel = cancelRepository.findWithIdForUpdate(cancelId);
        cancel.changeState();
    }
}
