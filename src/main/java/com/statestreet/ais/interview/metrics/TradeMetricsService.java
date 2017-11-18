package com.statestreet.ais.interview.metrics;

import java.util.List;

public interface TradeMetricsService {

	TradeMetric record(TradeMetric tradeMetric);

	List<TradeMetric> findTradeMetrics();
	
    MetricsSummary getTradeMetricsSummary(MetricsSearchCriteria metricsSearchCriteria);

}
