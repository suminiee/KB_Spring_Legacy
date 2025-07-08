package org.example.springtest.controller.index;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.dto.index.PerformanceTestResult;
import org.example.springtest.service.index.IndexTestService;
import org.example.springtest.service.index.NightmareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/index-test")
@RequiredArgsConstructor
@Slf4j
public class IndexTestController {
    private static final String DATA_COUNTS = "10000";
    private static final String TEST_DATA_NUM = "9999";
    private static final String ITERATIONS = "100";

    private final IndexTestService indexTestService;
    private final NightmareService nightmareService;

    // 테스트 데이터 생성
    // GET, http://localhost:8080/index-test/generate-data
    @GetMapping("/generate-data")
    public ResponseEntity<String> generateTestData(@RequestParam(defaultValue = DATA_COUNTS) int count) {
        try {
            indexTestService.generateTestData(count);
            return ResponseEntity.ok(count + "개의 테스트 데이터 생성 완료");
        } catch (Exception e) {
            log.error("테스트 데이터 생성 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("테스트 데이터 생성 실패: " + e.getMessage());
        }
    }

    // 인덱스 적용, EMAIL 검색
    // GET, http://localhost:8080/index-test/performance/email
    @GetMapping("/performance/email")
    public ResponseEntity<PerformanceTestResult> testEmailPerformance(
            @RequestParam(defaultValue = "email" + TEST_DATA_NUM) String email,
            @RequestParam(defaultValue = ITERATIONS) int iterations
    ) {
        PerformanceTestResult result = indexTestService.findByEmailPerformanceTest(email, iterations);
        return ResponseEntity.ok(result);
    }

    // 인덱스 미적용, username 검색
    // GET, http://localhost:8080/index-test/performance/username
    @GetMapping("/performance/username")
    public ResponseEntity<PerformanceTestResult> testUsernamePerformance(
            @RequestParam(defaultValue = "user" + TEST_DATA_NUM) String username,
            @RequestParam(defaultValue = ITERATIONS) int iterations
    ) {
        PerformanceTestResult result = indexTestService.findByUsernamePerformanceTest(username, iterations);
        return ResponseEntity.ok(result);
    }

    // 인덱스 적용 된, EMAIL 을 LIKE %email% 조건 검색 - 결국 못찾으니까 풀스캔으로 찾음 이런 상황에서는 (포함관계)
    // GET, http://localhost:8080/index-test/performance/like-search?keyword=9999

    // 인덱스 적용 된, EMAIL 을 LIKE email% 조건 검색 - 인덱스 적용 ok 접두사
    // GET, http://localhost:8080/index-test/performance/like-search?keyword=9999&option=on
    @GetMapping("/performance/like-search")
    public ResponseEntity<List<PerformanceTestResult>> testLikeSearchPerformance(
            @RequestParam(defaultValue = TEST_DATA_NUM) String keyword,
            @RequestParam(defaultValue = ITERATIONS) int iterations,
            @RequestParam(required = false) String option
    ) {
        List<PerformanceTestResult> results = indexTestService.likeSearchPerformanceTest(keyword, iterations, option);
        return ResponseEntity.ok(results);
    }

    // 최적 조건, EMAIL 만 검색
    // http://localhost:8080/index-test/performance/complex?email=9999email

    // 일반 조건, EMAIL 과 USERNAME 동시 검색 - 인덱스가 걸린 쪽을 먼저 확인하고 그 다음에 남은것중에 풀스캔떄림 / 상황마다 다르기는 함
    // http://localhost:8080/index-test/performance/complex?email=9999email&username=9999user

    // 최악 조건, USERNAME 만 검색
    // http://localhost:8080/index-test/performance/complex?username=9999user
    @GetMapping("/performance/complex")
    public ResponseEntity<PerformanceTestResult> testComplexSearchPerformance(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(defaultValue = ITERATIONS) int iterations
    ) {
        PerformanceTestResult result = indexTestService.complexSearchPerformanceTest(email, username, iterations);
        return ResponseEntity.ok(result);
    }

//     나이트메어 테스트 데이터 생성
//     GET, http://localhost:8080/index-test/nightmare/generate-data
    @GetMapping("/nightmare/generate-data")
    public ResponseEntity<String> generateNightmareData(@RequestParam(defaultValue = DATA_COUNTS) int count) {
        long excutionTime = nightmareService.generateTestData(100000);

        String message = "나이트메어 샘플 데이터 저장 완료!!!!!, 실행 시간 - " + excutionTime + " ms";
        return ResponseEntity.ok(message);
    }
}







