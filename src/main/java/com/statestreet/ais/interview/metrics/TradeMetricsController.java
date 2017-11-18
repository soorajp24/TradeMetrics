package com.statestreet.ais.interview.metrics;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trademetrics")
public class TradeMetricsController {
	
	@Autowired
	private TradeMetricsService tradeMetricsService;
	
	@Autowired
	private MetricsSearchCriteriaValidator metricsSearchCriteriaValidator;
	
	@RequestMapping("/hello")
    public String index() {
        return "Hello from Trade metrics application";
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public List<TradeMetric> fetchTradeMetrics() {
	    return tradeMetricsService.findTradeMetrics();
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/summary")
	public MetricsSummary fetchTradeMetricsSummary(@Valid @RequestBody MetricsSearchCriteria metricsSearchCriteria) {
		MetricsSearchCriteria.Builder metricsSearchCriteriaBuilder = new MetricsSearchCriteria.Builder();
		metricsSearchCriteriaBuilder.client(metricsSearchCriteria.getClient()!=null?metricsSearchCriteria.getClient().get(): null);
		metricsSearchCriteriaBuilder.product(metricsSearchCriteria.getProduct()!=null?metricsSearchCriteria.getProduct().get(): null);
		metricsSearchCriteriaBuilder.instrument(metricsSearchCriteria.getInstrument()!=null?metricsSearchCriteria.getInstrument().get(): null);
		metricsSearchCriteriaBuilder.recordAction(metricsSearchCriteria.getRecordAction()!=null?metricsSearchCriteria.getRecordAction().get(): null);
	    return tradeMetricsService.getTradeMetricsSummary(metricsSearchCriteriaBuilder.build());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public TradeMetric createTradeMetric(@RequestBody TradeMetric tradeMetric) {
	    return tradeMetricsService.record(tradeMetric);
	}
	
	@InitBinder("metricsSearchCriteria")
	public void setupBinder(WebDataBinder binder) {
		binder.addValidators(metricsSearchCriteriaValidator);
	}

}
