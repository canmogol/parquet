package com.comodo.parquet.logger;

import com.comodo.parquet.ParquetApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * can | 8/23/17.
 */
public class ParquetApplicationLogger {

    private static final Logger logger = LoggerFactory.getLogger(ParquetApplication.class);

    public void usingInternalConfiguration(String[] arguments) {
        logger.debug("No arguments supplied to application, using internal arguments: "
            + Arrays.toString(arguments));
    }

}

