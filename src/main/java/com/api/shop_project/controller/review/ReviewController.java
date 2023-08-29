package com.api.shop_project.controller.review;

import com.api.shop_project.dto.response.review.ReviewList;
import com.api.shop_project.service.review.ReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public void write(@RequestParam Long memberId,
                      @RequestParam Long itemId,
                      @RequestParam String content) {

        reviewService.save(memberId, itemId, content);

    }

    @GetMapping("/review/{itemId}")
    public void getList(@PathVariable("itemId") Long itemId, Model model) {

        List<ReviewList> reviews = reviewService.getList(itemId);

        model.addAttribute("reviews", reviews);

    }

    @PostMapping("/review/{reviewId}")
    public void Edit(@PathVariable("reviewId") Long reviewId, @RequestParam String content) {
        reviewService.edit(reviewId, content);
    }

    @GetMapping("/review/{reviewId}")
    public void delete(@PathVariable("reviewId") Long reviewId) {
        reviewService.delete(reviewId);
    }



}
