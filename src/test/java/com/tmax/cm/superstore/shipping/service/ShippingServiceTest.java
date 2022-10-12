package com.tmax.cm.superstore.shipping.service;

import com.tmax.cm.superstore.SuperstoreApplication;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ShippingServiceTest {

    @Autowired
    private ShippingRepository shippingRepository;

    private ShippingService shippingService;

    @BeforeEach
    public void setUp() {
        shippingService = new ShippingService(shippingRepository);
    }

    @Test
    public void create() {
        LocalDateTime now = LocalDateTime.of(2022, 10, 6, 0, 0);
        LocalDateTime next = LocalDateTime.of(2022, 11, 6, 0, 0);
        Shipping shipping1 = shippingService.create("test", "경기도 안양시", "01062833841", "잘");

        assertThat(shipping1.getFinalstatus()).isEqualTo(ShippingType.SHIPPING_WAITING);
        assertThat(1).isEqualTo(shippingRepository.findAll().size());

        List<Shipping> shippingList = shippingRepository.findAll();

        Shipping shipping = shippingList.get(0);
        shippingService.acceptShipping(shipping);

        assertThat(shipping.getModifiedAt()).isAfter(now);
        assertThat(shipping.getModifiedAt()).isBefore(next);
    }

}