package com.statestreet.ais.interview.metrics;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricsSearchCriteria {

	@JsonProperty("client")
    private Optional<String> client;
	
	@JsonProperty("product")
    private Optional<Product> product;
	
	@JsonProperty("instrument")
    private Optional<String> instrument;
	
	@JsonProperty("recordAction")
    private Optional<RecordAction> recordAction;
    
    public MetricsSearchCriteria() {
    	
    }

    private MetricsSearchCriteria(Builder builder) {
        this.client = Optional.ofNullable(builder.client);
        this.product = Optional.ofNullable(builder.product);
        this.instrument = Optional.ofNullable(builder.instrument);
        this.recordAction = Optional.ofNullable(builder.recordAction);
    }

    public Optional<String> getClient() {
        return client;
    }

    public Optional<Product> getProduct() {
        return product;
    }

    public Optional<String> getInstrument() {
        return instrument;
    }

    public Optional<RecordAction> getRecordAction() {
        return recordAction;
    }

    public static class Builder {

        private String client;
        private Product product;
        private String instrument;
        private RecordAction recordAction;

        public Builder client(String client) {
            this.client = client;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder instrument(String instrument) {
            this.instrument = instrument;
            return this;
        }

        public Builder recordAction(RecordAction recordAction) {
            this.recordAction = recordAction;
            return this;
        }

        public MetricsSearchCriteria build() {
            return new MetricsSearchCriteria(this);
        }
    }

}
