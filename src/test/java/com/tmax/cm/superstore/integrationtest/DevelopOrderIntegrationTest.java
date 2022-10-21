package com.tmax.cm.superstore.integrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.tmax.cm.superstore.EasyRestDocumentation;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ShippingType;

public class DevelopOrderIntegrationTest extends AbstractIntegrationTest {

    private String tag = "Develop Order";

    <T extends Enum<T>> String createTypeDescription(Class<T> enumClass) {
        StringBuilder typeDescriptionBuilder = new StringBuilder("상태에 따른 필터링 [ ");

        for (T type : enumClass.getEnumConstants()) {
            typeDescriptionBuilder.append(type.toString()).append(", ");
        }
        typeDescriptionBuilder.delete(typeDescriptionBuilder.length() - 2, typeDescriptionBuilder.length());
        typeDescriptionBuilder.append(" ]");

        return typeDescriptionBuilder.toString();
    }

    @Test
    void testPutSetShippingType() throws Exception {
        // given
        ShippingType shippingType = ShippingType.SHIPPING_WAITING;
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/develop/order/shippingAndDelivery/{selectedOptionId}", selectedOptionId)
                .param("shippingType", shippingType.toString()));

        // then
        ParameterDescriptorWithType requestParameterShippingType = EasyRestDocumentation
                .createRequestParameter("shippingType", this.createTypeDescription(ShippingType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithRequestParameter("putSetShippingType", "상태 변경", this.tag,
                        requestParameterShippingType));
    }

    @Test
    void testFailedPutSetPickupTypeIfSelectedOptionNotFound() throws Exception {
        // given
        PickupType pickupType = PickupType.PICKUP_WAITING;
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/develop/order/visitAndPickup/{selectedOptionId}", selectedOptionId)
                .param("pickupType", pickupType.toString()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("pickupType", this.createTypeDescription(PickupType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.documentWithRequestParameter("putSetPickupType", "상태 변경", this.tag,
                        requestParameterPickupType));
    }

}
