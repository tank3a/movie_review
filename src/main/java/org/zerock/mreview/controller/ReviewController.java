package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mreview.dto.ReviewDTO;
import org.zerock.mreview.service.ReviewService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("-------------list-------------");
        log.info("MNO: " + mno);
        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> getList(@RequestBody ReviewDTO movieReviewDTO) {
        log.info("---------------list---------------");
        log.info("reviewDTO: " + movieReviewDTO);

        Long reviewNum = reviewService.register(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO reviewDTO) {
        log.info("----------------modify review-------------------");
        log.info("reviewDTO: " + reviewDTO);

        reviewService.modify(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum) {
        log.info("-------------remove review-------------------");
        log.info("reviewnum: " + reviewnum);

        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }
}
