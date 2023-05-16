package stats_service;

import stats_service.types.StatsValue;

import java.util.EnumMap;
import java.util.Map;

public interface StatsService<T> {
    T getAvg();

    T getMax();

    T getMin();

    default EnumMap<StatsValue, T> getStatsByStatsValue() {
        return new EnumMap<>(Map.of(
                StatsValue.AVG, getAvg(),
                StatsValue.MAX, getMax(),
                StatsValue.MIN, getMin()
        ));
    }

}
