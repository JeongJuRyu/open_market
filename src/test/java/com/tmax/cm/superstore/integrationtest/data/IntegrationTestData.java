package com.tmax.cm.superstore.integrationtest.data;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegrationTestData {

    private UUID shopId;

    private UUID itemId;

    private UUID optionGroupId;

    private UUID optionId;

    private UUID cartItemId;

    private UUID selectedOptionId;

    private UUID cartOptionGroupId;

    private UUID cartOptionId;
}
