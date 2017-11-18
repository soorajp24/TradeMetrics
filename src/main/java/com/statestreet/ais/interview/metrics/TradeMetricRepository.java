package com.statestreet.ais.interview.metrics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMetricRepository extends CrudRepository<TradeMetric, Long> {
	
	Iterable<TradeMetric> findByClient(String client);
	
	Iterable<TradeMetric> findByProduct(Product product);
	
	Iterable<TradeMetric> findByInstrument(String instrument);
	
	Iterable<TradeMetric> findByRecordAction(RecordAction recordAction);
	
	Iterable<TradeMetric> findByClientAndProduct(String client, Product product);
	
	Iterable<TradeMetric> findByClientAndInstrument(String client, String instrument);
	
	Iterable<TradeMetric> findByClientAndRecordAction(String client, RecordAction recordAction);
	
	Iterable<TradeMetric> findByProductAndInstrument(Product product, String instrument);
	
	Iterable<TradeMetric> findByProductAndRecordAction(Product product, RecordAction recordAction);
	
	Iterable<TradeMetric> findByInstrumentAndRecordAction(String instrument, RecordAction recordAction);
	
	Iterable<TradeMetric> findByClientAndProductAndInstrument(String client, Product product, String instrument);
	
	Iterable<TradeMetric> findByClientAndProductAndRecordAction(String client, Product product, RecordAction recordAction);
	
	Iterable<TradeMetric> findByProductAndInstrumentAndRecordAction(Product product, String instrument, RecordAction recordAction);
	
	Iterable<TradeMetric> findByClientAndProductAndInstrumentAndRecordAction(String client, Product product, String instrument, RecordAction recordAction);
}
