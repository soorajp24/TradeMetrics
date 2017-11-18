package com.statestreet.ais.interview.metrics;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MetricsSearchCriteriaValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MetricsSearchCriteria.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MetricsSearchCriteria metricsSearchCriteria = (MetricsSearchCriteria) target;
		if (metricsSearchCriteria == null) {
			throw new IllegalArgumentException("Metrics search criteria should not be null");
		} else {
			if ((metricsSearchCriteria.getClient()==null) && (metricsSearchCriteria.getInstrument()==null) &&
					(metricsSearchCriteria.getProduct()==null) && (metricsSearchCriteria.getRecordAction()==null)) {
				throw new IllegalArgumentException("Metrics search criteria should contain atleast one out of Client, Instrument,Product and RecordAction");
			}
		}
	}

	
		
}

