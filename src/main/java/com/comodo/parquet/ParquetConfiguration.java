package com.comodo.parquet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ParquetConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory commandDataSourceFactory = new DataSourceFactory();

    @Valid
    @NotNull
    private DataSourceFactory queryDataSourceFactory = new DataSourceFactory();

    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    @JsonProperty("swagger")
    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }

    @JsonProperty("commandDatabase")
    public DataSourceFactory getCommandDataSourceFactory() {
        return commandDataSourceFactory;
    }

    @JsonProperty("commandDatabase")
    public void setCommandDataSourceFactory(DataSourceFactory commandDataSourceFactory) {
        this.commandDataSourceFactory = commandDataSourceFactory;
    }

    @JsonProperty("queryDatabase")
    public DataSourceFactory getQueryDataSourceFactory() {
        return queryDataSourceFactory;
    }

    @JsonProperty("queryDatabase")
    public void setQueryDataSourceFactory(DataSourceFactory queryDataSourceFactory) {
        this.queryDataSourceFactory = queryDataSourceFactory;
    }

}
