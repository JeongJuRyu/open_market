package com.tmax.cm.superstore.common.util;

import java.util.function.Supplier;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Component;

@Component
public class TransactionHandler {

    @Transactional
    public void runInSameTransaction(Runnable runnable) {
        runnable.run();
    }

    @Transactional
    public <T> T runInSameTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    public <T> T runInNewTransaction(Supplier<T> supplier) {
        return supplier.get();
    }
}
