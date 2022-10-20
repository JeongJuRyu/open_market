package com.tmax.cm.superstore.cancel.entity;

import com.tmax.cm.superstore.code.CancelType;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.error.exception.UnchangeableOrderStateException;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cancel extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String address;

    private String requests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CancelType cancelType;

    public void changeState() throws UnchangeableOrderStateException {
        if (getCancelType() != CancelType.CANCEL_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setCancelType(CancelType.CANCEL_DONE);
    }
}
