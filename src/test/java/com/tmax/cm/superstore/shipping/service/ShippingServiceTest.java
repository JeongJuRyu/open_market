//package com.tmax.cm.superstore.shipping.service;
//
//import com.tmax.cm.superstore.SuperstoreApplication;
//import com.tmax.cm.superstore.code.ShippingType;
//import com.tmax.cm.superstore.shipping.entity.Shipping;
//import com.tmax.cm.superstore.shipping.repository.ShippingRepository;
//import lombok.RequiredArgsConstructor;
//import org.assertj.core.api.AtomicIntegerArrayAssert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.PessimisticLockingFailureException;
//import org.springframework.orm.ObjectOptimisticLockingFailureException;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.persistence.*;
//import javax.transaction.Transactional;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.assertj.core.api.Assertions.as;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("develop-chj")
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////@DataJpaTest
//class ShippingServiceTest {
//
//    @Autowired
//    private ShippingRepository shippingRepository;
//
//    @Autowired
//    private ShippingService shippingService;
//
//    private UUID id;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @BeforeEach
//    public void setUp() {
////        shippingService = new ShippingService(shippingRepository);
//        Shipping b = Shipping.builder()
//                .address("경기도 안양시")
//                .recipient("test")
//                .mobile("01062833841")
//                .requests("jal")
//                .finalstatus(ShippingType.SHIPPING_WAITING)
//                .build();
//        Shipping temp = this.shippingRepository.save(b);
//        id = temp.getId();
//    }
//
//    @Test
//    public void create() {
//        LocalDateTime now = LocalDateTime.of(2022, 10, 6, 0, 0);
//        LocalDateTime next = LocalDateTime.of(2022, 11, 6, 0, 0);
//        Shipping shipping1 = shippingService.create("test", "경기도 안양시", "01062833841", "잘");
//
//        assertThat(shipping1.getFinalstatus()).isEqualTo(ShippingType.SHIPPING_WAITING);
//        assertThat(1).isEqualTo(shippingRepository.findAll().size());
//
//        List<Shipping> shippingList = shippingRepository.findAll();
//
//        Shipping shipping = shippingList.get(0);
////        shippingService.acceptShipping(shipping.getId());
//
//        assertThat(shipping.getModifiedAt()).isAfter(now);
//        assertThat(shipping.getModifiedAt()).isBefore(next);
//    }
//
//    @Test
//    @DisplayName("상태 변경 동시성 테스트")
//    public void updateStatusWithMultiThread() throws InterruptedException {
////        Shipping shipping1 = shippingService.create("test", "경기도 안양시", "01062833841", "잘");
//
//        AtomicInteger successCount = new AtomicInteger();
//        int numberOfExecute = 10;
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(numberOfExecute);
//        CountDownLatch totalLatch = new CountDownLatch(10);
//        AtomicBoolean result1 = new AtomicBoolean(true);
//        AtomicBoolean result2 = new AtomicBoolean(true);
//
//        for(int i = 0; i < 5; i++) {
//            totalLatch.countDown();
//            service.execute(() -> {
//                try {
//                    latch.countDown();
//                    latch.await();
//                    shippingService.acceptShipping(id);
//                    successCount.getAndIncrement();
//                    System.out.println("fuck success");
//                } catch(PessimisticLockingFailureException oe) {
//                    System.out.println("collision");
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            });
//        }
//
//        for(int i = 0; i < 5; i++) {
//            totalLatch.countDown();
//            service.execute(() -> {
//                try {
//                    latch.countDown();
//                    latch.await();
//                    shippingService.rejectShipping(id);
//                    successCount.getAndIncrement();
//                    System.out.println("success");
//                } catch(PessimisticLockingFailureException oe) {
//                    System.out.println("collision");
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            });
//        }
//
//        totalLatch.await();
//        Shipping test = shippingRepository.findById(id).orElseThrow();
//        assertThat(successCount).hasValueLessThan(10);
//    }
//
//    @Test
//    @Transactional
//    public void exceptionTest() throws InterruptedException {
//        AtomicInteger successCount = new AtomicInteger();
//        int numberOfExecute = 100;
//        ExecutorService service = Executors.newFixedThreadPool(100);
//        CountDownLatch latch = new CountDownLatch(numberOfExecute);
//        CountDownLatch totalLatch = new CountDownLatch(100);
//
//        for(int i = 0; i < 100; i++) {
//            service.execute(() -> {
//                assertThrows(PessimisticLockingFailureException.class, () -> {
//                    latch.countDown();
//                    latch.await();
//                    em.find(Shipping.class, id, LockModeType.PESSIMISTIC_READ);
//                });
//            });
//        }
//    }
//
//}