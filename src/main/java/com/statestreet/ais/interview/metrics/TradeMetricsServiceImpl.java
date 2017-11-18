package com.statestreet.ais.interview.metrics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TradeMetricsServiceImpl implements TradeMetricsService {
	
	@Autowired
    private TradeMetricRepository tradeMetricRepository;

	@Override
	public TradeMetric record(TradeMetric tradeMetric) {
		tradeMetricRepository.save(tradeMetric);
		return null;
	}
	
	@Override
	public List<TradeMetric> findTradeMetrics(){
		List<TradeMetric> tradeMetrics = new ArrayList<TradeMetric>();
		tradeMetricRepository.findAll().forEach(tradeMetrics::add);
		return tradeMetrics;
	}

	@Override
	public MetricsSummary getTradeMetricsSummary(MetricsSearchCriteria metricsSearchCriteria) {
		List<TradeMetric> tradeMetrics = new ArrayList<TradeMetric>();
		
		boolean isClientPresent = metricsSearchCriteria.getClient().isPresent();
		boolean isProductPresent = metricsSearchCriteria.getProduct().isPresent();
		boolean isInstrumentPresent = metricsSearchCriteria.getInstrument().isPresent();
		boolean isRecordActionPresent = metricsSearchCriteria.getRecordAction().isPresent();
		
		/*
		 * Using the if else blocks to take advantage of Spring JPA provided 'findBy' repository implementations
		 * Can think of foregoing that and provide our own JDBCTemplate query to avoid the if else block.
		 */
		if (isClientPresent && (!isProductPresent) && (!isInstrumentPresent) && (!isRecordActionPresent)) {
			tradeMetricRepository.findByClient(metricsSearchCriteria.getClient().get()).forEach(tradeMetrics::add);
		} else if (isProductPresent && (!isClientPresent) && (!isInstrumentPresent) && (!isRecordActionPresent)) {
			tradeMetricRepository.findByProduct(metricsSearchCriteria.getProduct().get()).forEach(tradeMetrics::add);
		} else if (isInstrumentPresent && (!isClientPresent) && (!isProductPresent) && (!isRecordActionPresent)) {
			tradeMetricRepository.findByInstrument(metricsSearchCriteria.getInstrument().get()).forEach(tradeMetrics::add);
		} else if (isRecordActionPresent && (!isClientPresent) && (!isProductPresent) && (!isInstrumentPresent)) {
			tradeMetricRepository.findByRecordAction(metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} else if (isClientPresent && isProductPresent && (!isInstrumentPresent) && (!isRecordActionPresent)) {
			tradeMetricRepository.findByClientAndProduct(metricsSearchCriteria.getClient().get(), 
					metricsSearchCriteria.getProduct().get()).forEach(tradeMetrics::add);
		} else if (isClientPresent && isInstrumentPresent && (!isProductPresent) && (!isRecordActionPresent)) {
			tradeMetricRepository.findByClientAndInstrument(metricsSearchCriteria.getClient().get(), 
					metricsSearchCriteria.getInstrument().get()).forEach(tradeMetrics::add);
		} else if (isClientPresent && isRecordActionPresent && (!isProductPresent) && (!isInstrumentPresent)) {
			tradeMetricRepository.findByClientAndRecordAction(metricsSearchCriteria.getClient().get(), 
					metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} else if (isProductPresent && isInstrumentPresent && (!isClientPresent) && (!isRecordActionPresent)) {
			tradeMetricRepository.findByProductAndInstrument(metricsSearchCriteria.getProduct().get(), 
					metricsSearchCriteria.getInstrument().get()).forEach(tradeMetrics::add);
		} else if (isProductPresent && isRecordActionPresent && (!isClientPresent) && (!isInstrumentPresent)) {
			tradeMetricRepository.findByProductAndRecordAction(metricsSearchCriteria.getProduct().get(), 
					metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} else if (isInstrumentPresent && isRecordActionPresent && (!isClientPresent) && (!isProductPresent)) {
			tradeMetricRepository.findByInstrumentAndRecordAction(metricsSearchCriteria.getInstrument().get(), 
					metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} else if (isClientPresent && isProductPresent && isInstrumentPresent && (!isRecordActionPresent)) {
			tradeMetricRepository.findByClientAndProductAndInstrument(metricsSearchCriteria.getClient().get(), 
					metricsSearchCriteria.getProduct().get(), metricsSearchCriteria.getInstrument().get()).forEach(tradeMetrics::add);
		} else if (isClientPresent && isProductPresent && isRecordActionPresent && (!isInstrumentPresent)) {
			tradeMetricRepository.findByClientAndProductAndRecordAction(metricsSearchCriteria.getClient().get(), 
					metricsSearchCriteria.getProduct().get(), metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} else if (isProductPresent && isInstrumentPresent && isRecordActionPresent && (!isClientPresent)) {
			tradeMetricRepository.findByProductAndInstrumentAndRecordAction(metricsSearchCriteria.getProduct().get(), 
					metricsSearchCriteria.getInstrument().get(), metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} else if (isProductPresent && isInstrumentPresent && isRecordActionPresent && isClientPresent) {
			tradeMetricRepository.findByClientAndProductAndInstrumentAndRecordAction(metricsSearchCriteria.getClient().get(),
					metricsSearchCriteria.getProduct().get(), metricsSearchCriteria.getInstrument().get(), 
					metricsSearchCriteria.getRecordAction().get()).forEach(tradeMetrics::add);
		} 
		
		MetricsSummary.Builder metricsSummaryBuilder = new MetricsSummary.Builder();
		
		/*
		 * Stream provided a concurrent approach towards aggregations, hence will be faster.
		 * To improve performance even further; average, min and max calculations can be done in different threads
		 */
		double averageExecutionTimeInMilliseconds = tradeMetrics
			    .stream()
			    .mapToDouble(TradeMetric::getExecutionTimeInMilliseconds)
			    .average()
			    .getAsDouble();
		
		long maxExecutionTimeInMilliseconds = tradeMetrics
			    .stream()
			    .mapToLong(TradeMetric::getExecutionTimeInMilliseconds)
			    .max()
			    .getAsLong();
		
		long minExecutionTimeInMilliseconds = tradeMetrics
			    .stream()
			    .mapToLong(TradeMetric::getExecutionTimeInMilliseconds)
			    .min()
			    .getAsLong();
		
		metricsSummaryBuilder.count(tradeMetrics.size()).averageExecutionTimeInMilliseconds(BigDecimal.valueOf(averageExecutionTimeInMilliseconds)).
			maxExecutionTimeInMilliseconds(maxExecutionTimeInMilliseconds).minExecutionTimeInMilliseconds(minExecutionTimeInMilliseconds);
		metricsSummaryBuilder.build();
		
		return metricsSummaryBuilder.build();
	}
}
