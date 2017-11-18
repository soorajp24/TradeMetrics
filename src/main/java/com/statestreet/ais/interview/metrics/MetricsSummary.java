package com.statestreet.ais.interview.metrics;

import java.math.BigDecimal;
import java.util.Objects;

public class MetricsSummary {

    private final long count;
    private final BigDecimal averageExecutionTimeInMilliseconds;
    private final long maxExecutionTimeInMilliseconds;
    private final long minExecutionTimeInMilliseconds;

    private MetricsSummary(Builder builder) {
        this.count = builder.count;
        this.averageExecutionTimeInMilliseconds = builder.averageExecutionTimeInMilliseconds;
        this.maxExecutionTimeInMilliseconds = builder.maxExecutionTimeInMilliseconds;
        this.minExecutionTimeInMilliseconds = builder.minExecutionTimeInMilliseconds;
    }

    public long getCount() {
        return count;
    }

    public BigDecimal getAverageExecutionTimeInMilliseconds() {
        return averageExecutionTimeInMilliseconds;
    }

    public long getMaxExecutionTimeInMilliseconds() {
        return maxExecutionTimeInMilliseconds;
    }

    public long getMinExecutionTimeInMilliseconds() {
        return minExecutionTimeInMilliseconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetricsSummary that = (MetricsSummary) o;
        return count == that.count &&
                BigDecimalHelper.equals(averageExecutionTimeInMilliseconds, that.averageExecutionTimeInMilliseconds) &&
                Objects.equals(maxExecutionTimeInMilliseconds, that.maxExecutionTimeInMilliseconds) &&
                Objects.equals(minExecutionTimeInMilliseconds, that.minExecutionTimeInMilliseconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, averageExecutionTimeInMilliseconds, maxExecutionTimeInMilliseconds, minExecutionTimeInMilliseconds);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetricsSummary{");
        sb.append("count=").append(count);
        sb.append(", averageExecutionTimeInMilliseconds=").append(averageExecutionTimeInMilliseconds);
        sb.append(", maxExecutionTimeInMilliseconds=").append(maxExecutionTimeInMilliseconds);
        sb.append(", minExecutionTimeInMilliseconds=").append(minExecutionTimeInMilliseconds);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {

        private long count = 0L;
        private BigDecimal averageExecutionTimeInMilliseconds = BigDecimal.ZERO;
        private long maxExecutionTimeInMilliseconds = 0L;
        private long minExecutionTimeInMilliseconds = 0L;

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public Builder averageExecutionTimeInMilliseconds(BigDecimal averageExecutionTimeInMilliseconds) {
            this.averageExecutionTimeInMilliseconds = averageExecutionTimeInMilliseconds;
            return this;
        }

        public Builder maxExecutionTimeInMilliseconds(long maxExecutionTimeInMilliseconds) {
            this.maxExecutionTimeInMilliseconds = maxExecutionTimeInMilliseconds;
            return this;
        }

        public Builder minExecutionTimeInMilliseconds(long minExecutionTimeInMilliseconds) {
            this.minExecutionTimeInMilliseconds = minExecutionTimeInMilliseconds;
            return this;
        }

        public MetricsSummary build() {
            return new MetricsSummary(this);
        }
    }
}
