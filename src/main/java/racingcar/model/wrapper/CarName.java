package racingcar.model.wrapper;

import racingcar.exception.CarNameException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarName {
    public static final int MAX_NAME_LENGTH = 5;
    public static final String NOT_ALLOWED_EMPTY_NAME = "자동차 이름은 빈값일 수 없습니다.";
    public static final String NOT_ALLOWED_EXCEED_MAX_NAME_LENGTH = "자동차 이름은 5자를 초과할 수 없습니다.";
    public static final String CAR_NAME_SPLITTER = ",";
    public static final String NOT_ALLOWED_EMPTY_CAR_STRINGS = "자동차 이름 문자열은 빈값일 수 없습니다.";
    public static final String NOT_ALLOWED_DUPLICATED_CAR_NAMES = "자동차들의 이름들은 중복될 수 없습니다.";
    private final String name;

    public CarName(final String name) {
        if (isEmpty(name)) {
            throw new CarNameException(NOT_ALLOWED_EMPTY_NAME);
        }
        if (isExceedMaxNameLength(name)) {
            throw new CarNameException(NOT_ALLOWED_EXCEED_MAX_NAME_LENGTH);
        }
        this.name = name;
    }

    private static boolean isExceedMaxNameLength(String name) {
        return name.length() > MAX_NAME_LENGTH;
    }

    private static boolean isEmpty(String name) {
        return name == null ||
                name.trim().isEmpty();
    }

    public static List<CarName> convertStringToCarNames(String stringCarNames) {
        if (stringCarNames == null || stringCarNames.isEmpty()) {
            throw new CarNameException(NOT_ALLOWED_EMPTY_CAR_STRINGS);
        }

        String[] carNames = stringCarNames.split(CAR_NAME_SPLITTER);

        long distinctCarNamesCount = Arrays.stream(carNames).distinct().count();
        if (carNames.length > distinctCarNamesCount) {
            throw new CarNameException(NOT_ALLOWED_DUPLICATED_CAR_NAMES);
        }

        return Arrays.stream(carNames)
                .map(CarName::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarName carName = (CarName) o;
        return Objects.equals(name, carName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String name() {
        return this.name;
    }
}
