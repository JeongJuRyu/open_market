package com.tmax.cm.superstore.returns.entity;

import com.tmax.cm.superstore.code.CancelType;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ReturnType;
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
public class Returns extends BaseTimeEntity {

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
    private String reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReturnType returnType;

    public void acceptState() throws UnchangeableOrderStateException {
        if (getReturnType() != ReturnType.RETURN_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setReturnType(ReturnType.RETURN_ACCEPT);
    }

    public void refuseState() throws UnchangeableOrderStateException {
        if (getReturnType() != ReturnType.RETURN_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setReturnType(ReturnType.RETURN_REFUSED);
    }

    public void doneState() throws UnchangeableOrderStateException {
        if (getReturnType() != ReturnType.RETURN_PROCEED) {
            throw new UnchangeableOrderStateException();
        }
        setReturnType(ReturnType.RETURN_DONE);
    }

    public void proceedState() throws UnchangeableOrderStateException {
        if (getReturnType() != ReturnType.RETURN_ACCEPT) {
            throw new UnchangeableOrderStateException();
        }
        setReturnType(ReturnType.RETURN_PROCEED);
    }
}
