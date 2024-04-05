package view;

import lotto.Lotto;
import lotto.WinningLotto;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGameInputView {
    private static final String INPUT_BUDGET_MSG = "구입금액을 입력해 주세요.";
    private static final String INPUT_NUMBER_OF_MANUAL_BUY_MSG = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MSG = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS_MSG = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_BONUS_NUMBERS_MSG = "보너스 볼을 입력해 주세요.";

    public static int getBudget() {
        Integer budget = retryableInput(LottoGameInputView::inputBudget, LottoGameInputView::isValidBudget);
        System.out.println();
        return budget;
    }

    private static <T> T retryableInput(Supplier<T> supplier, Predicate<T> validator) {
        T input = supplier.get();
        while (!validator.test(input)) {
            input = supplier.get();
        }
        return input;
    }

    private static boolean isValidBudget(int budget) {
        return budget >= 1_000;
    }

    private static int inputBudget() {
        return inputInt(INPUT_BUDGET_MSG);
    }
    
    private static int inputInt(String inputMsg) {
        String input = inputString(inputMsg);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return inputInt(inputMsg);
        }
    }

    private static String inputString(String msg) {
        if (!msg.isEmpty()) {
            System.out.println(msg);
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static WinningLotto getWinningNumber() {
        List<Integer> winningLottoNumbers = getWinningLottoNumbers();
        int winningBonusNumber = getWinningBonusNumber(winningLottoNumbers);
        System.out.println();
        return new WinningLotto(winningLottoNumbers, winningBonusNumber);
    }

    private static List<Integer> getWinningLottoNumbers() {
        return retryableInput(LottoGameInputView::inputWinningLottoNumbers, LottoGameInputView::isValidWinningLottoNumbers);
    }

    private static boolean isValidWinningLottoNumbers(List<Integer> winningLottoNumbers) {
        boolean isSixLength = winningLottoNumbers.size() == 6;
        boolean outBoundedNumber = winningLottoNumbers.stream().anyMatch(number -> number < 1 || number > 45);
        boolean duplicatedNumber = winningLottoNumbers.size() != new HashSet<>(winningLottoNumbers).size();
        return isSixLength && !outBoundedNumber && !duplicatedNumber;
    }

    private static List<Integer> inputWinningLottoNumbers() {
        return inputIntList(INPUT_WINNING_LOTTO_NUMBERS_MSG);
    }

    private static List<Integer> inputIntList(String inputMsg) {
        String input = inputString(inputMsg);
        try {
            return Arrays.stream(input.split(", "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return inputIntList(inputMsg);
        }
    }

    private static int getWinningBonusNumber(List<Integer> winningLottoNumbers) {
        Function<List<Integer>, Predicate<Integer>> mixedValidate =
                lottoNumbers ->
                        bonusNumber -> isValidWinningBonusNumber(bonusNumber, lottoNumbers);
        return retryableInput(LottoGameInputView::inputWinningBonusNumber, mixedValidate.apply(winningLottoNumbers));
    }
    private static boolean isValidWinningBonusNumber(int winningBonusNumber, List<Integer> winningLottoNumber) {
        boolean outBoundedBonusNumber = winningBonusNumber < 1 || winningBonusNumber > 45;
        boolean duplicatedBonusNumber = winningLottoNumber.contains(winningBonusNumber);
        return !outBoundedBonusNumber && !duplicatedBonusNumber;
    }

    private static int inputWinningBonusNumber() {
        return inputInt(INPUT_WINNING_BONUS_NUMBERS_MSG);
    }

    public static List<Lotto> getManualLottos() {
        int numberOfManualBuy = getNumberOfManualBuy();
        System.out.println();
        List<Lotto> manualLottoNumbers = getManualLottoNumbers(numberOfManualBuy);
        System.out.println();
        return manualLottoNumbers;
    }

    private static int getNumberOfManualBuy() {
        return retryableInput(LottoGameInputView::inputNumberOfManualBuy, LottoGameInputView::isValidNumberOfManualBuy);
    }

    private static boolean isValidNumberOfManualBuy(int numberOfManualBuy) {
        return numberOfManualBuy > 0;
    }

    private static int inputNumberOfManualBuy() {
        return inputInt(INPUT_NUMBER_OF_MANUAL_BUY_MSG);
    }

    private static List<Lotto> getManualLottoNumbers(int numberOfManualBuy) {
        Supplier<List<Lotto>> mixedSupplier =
                () -> inputManualLottoNumbers(numberOfManualBuy);
        Function<Integer, Predicate<List<Lotto>>> mixedValidate =
                numOfManualBuy ->
                        lottos -> isValidManualLottoNumbers(lottos, numOfManualBuy);
        return retryableInput(mixedSupplier, mixedValidate.apply(numberOfManualBuy));
    }

    private static boolean isValidManualLottoNumbers(List<Lotto> manualLottos, int numberOfManualBuy) {
        return manualLottos.size() == numberOfManualBuy;
    }

    private static List<Lotto> inputManualLottoNumbers(int numberOfManualBuy) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MSG);
        try {
            return IntStream.range(0, numberOfManualBuy)
                    .mapToObj(i -> inputIntList(""))
                    .map(Lotto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("[ERROR] 처음부터 다시 입력해주세요.");
            drainInputStream();
            return inputManualLottoNumbers(numberOfManualBuy);
        }
    }

    private static void drainInputStream() {
        try {
            System.in.transferTo(OutputStream.nullOutputStream());
        } catch (IOException ignore) {
        }
    }

}
