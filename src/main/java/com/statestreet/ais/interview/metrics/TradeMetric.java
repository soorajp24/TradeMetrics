package com.statestreet.ais.interview.metrics;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TradeMetric {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    private String client;
    
    @Enumerated(EnumType.STRING)
    private Product product;
    
    private String instrument;
    
    @Enumerated(EnumType.STRING)
    private RecordAction recordAction;
    
    private long executionTimeInMilliseconds;
    
    public TradeMetric() {
    	
    }

    public TradeMetric(String client, Product product, String instrument, RecordAction recordAction, long executionTimeInMilliseconds) {
        this.client = client;
        this.product = product;
        this.instrument = instrument;
        this.recordAction = recordAction;
        this.executionTimeInMilliseconds = executionTimeInMilliseconds;
    }

    public String getClient() {
        return client;
    }

    public Product getProduct() {
        return product;
    }

    public String getInstrument() {
        return instrument;
    }

    public RecordAction getRecordAction() {
        return recordAction;
    }

    public long getExecutionTimeInMilliseconds() {
        return executionTimeInMilliseconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TradeMetric that = (TradeMetric) o;
        return executionTimeInMilliseconds == that.executionTimeInMilliseconds &&
                Objects.equals(client, that.client) &&
                product == that.product &&
                Objects.equals(instrument, that.instrument) &&
                recordAction == that.recordAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, product, instrument, recordAction, executionTimeInMilliseconds);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TradeMetric{");
        sb.append("client='").append(client).append('\'');
        sb.append(", product=").append(product);
        sb.append(", instrument='").append(instrument).append('\'');
        sb.append(", recordAction=").append(recordAction);
        sb.append(", executionTimeInMilliseconds=").append(executionTimeInMilliseconds);
        sb.append('}');
        return sb.toString();
    }
}
