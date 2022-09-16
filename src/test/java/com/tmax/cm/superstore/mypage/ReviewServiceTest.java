/*package com.tmax.cm.superstore.mypage;

import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewImage;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;
import com.tmax.cm.superstore.mypage.repository.ReviewRepository;
import com.tmax.cm.superstore.mypage.service.ReviewService;
import com.tmax.cm.superstore.user.entities.User;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
	@InjectMocks
	private ReviewService reviewService;

	@Mock
	private ReviewRepository reviewRepository;

	@Test
	public void 리뷰_생성() throws Exception {
		//given
		UUID id = UUID.randomUUID();
		Review review = Review.ReviewBuilder()
			.id(id)
			.content("맛있어요")
			.user(User.builder()
				.address("사랑시 고백구 행복동")
				.phoneNum("010-4523-6994")
				.password("1234")
				.email("totw2018@naver.com")
				.build())
			.reviewReply(ReviewReply.ReviewReplyBuilder()
				.content("감사합니다.")
				.build())
			.reviewImages(List.of(ReviewImage.ReviewImageBuilder()
				.url("https://naver.com")
				.build()))
			.build();
		//mocking
		given(reviewRepository.save(any())
			).willReturn(review);
		given(reviewRepository.findReviewById(id))
			.willReturn(Optional.ofNullable(review));
		// when
=

		// then
		Review findReview = reviewRepository.findReviewById(id).get();

		Assertions.assertThat(findReview.getId()).isEqualTo(id);


	}
}
*/