package lotto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FakeNumberGenerator extends NumberGenerator {

    private final List<List<Integer>> lottos;
    private final AtomicInteger currentIndex;

    FakeNumberGenerator(List<List<Integer>> lottos) {
        this.lottos = lottos;
        this.currentIndex = new AtomicInteger(0);
    }

    @Override
    public List<Integer> generateNumbers(List<Integer> candidateNumbers, int size) {
        return lottos.get(currentIndex.getAndIncrement() % lottos.size());
    }
}
