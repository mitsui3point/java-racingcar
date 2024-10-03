package racingcar;

import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Winner;
import racingcar.model.wrapper.CarName;
import racingcar.model.wrapper.MovementNumber;
import racingcar.util.PrintUtil;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.ResultView;

import java.util.List;
import java.util.Scanner;

public class RacingGame {

    public static void play() {
        InputView inputView = new InputView(new Scanner(System.in));
        List<CarName> carNames = CarName.convertStringToCarNames(inputView.carNames());
        MovementNumber movementNumber = new MovementNumber(inputView.movement());

        Cars cars = createCars(carNames);
        StringBuilder result = new StringBuilder();
        for (int index = 0; movementNumber.isGreaterThan(index); index++) {
            cars.moveAllByNumberCreator(new RandomNumberGenerator());
            result.append(PrintUtil.result(cars));
        }

        ResultView resultView = new ResultView();
        resultView.printResult(result.toString());
        resultView.printWinner(PrintUtil.winner(new Winner(cars)));
    }

    private static Cars createCars(List<CarName> carNames) {
        Car[] cars = carNames.stream()
                .map(Car::new)
                .toArray(Car[]::new);

        return Cars.newInstance(cars);
    }
}
